/** ENVIO DE COMANDOS AL BACKEND**/
    
   function enviarComandotobackendV2(acalarma){
	//var acalarma = document.getElementById("accionalarma").value;
	var serial = acalarma.split("-",1);
	console.log("Nombre de la particion: "+ "particion"+serial);
	var particionactivaa= document.getElementById("particion"+serial).textContent;
   	var enc = window.btoa('${pageContext.request.userPrincipal.name}');
   	console.log("busco particion-info: "+ acalarma+"-"+particionactivaa)
   	var urlsendInformation = $(location).attr('pathname') + "/ejecutaraccion/"+acalarma+"-"+particionactivaa;
   		$.ajax({ url : urlsendInformation,
   			contentType: "application/json",
   			dataType: 'json',
   			success: function(data){
   				console.log("resultado despues de la ejecucion: "+ data.status);
   			}			
   	});
   }




/** comportamiento cuando recibe un mensaje*/
function ProcesarAlarmaV2(message) {
	console.log("ALARMA V2: "+message.destinationName+"; contenido: "+  message.payloadString);
	var contenido= message.payloadString;
	var topico = message.destinationName;
	var dataObj = null;
	if(message.destinationName.includes("/Status")){
		informarstatusAlarma(message.destinationName, message.payloadString)
	}
	else if (topico.includes("activePartition")){
		iddevice = topico.substring(0,topico.search("/activePartition"));
		 var numparticion = document.getElementById("particion"+iddevice);
		 numparticion.innerHTML = message.payloadString;
		 console.log("Particion activa-> iddevice: "+iddevice+"; particion: "+ numparticion);
	}
	else if(topico.includes("Partition")){
		var iddevice = topico.substring(0,topico.search("/Partition"));
		var numparticion = topico.substring(topico.search("Partition")).replace("Partition","");
		var numdisplay = document.getElementById("particion"+iddevice).innerHTML;
		console.log("este es el numero de particion en el display: "+ numdisplay +"; num de particion: "+ numparticion);
		if(numdisplay == numparticion){
			escribirDisplayAlarmaV2(iddevice,contenido);
		}
	}
	else if(topico.includes("Zone")){
		var zona= topico.substring(topico.search("/Zone")).replace("/Zone","");
		var serial= topico.substring(0,topico.search("/Zone"));
		maximaZonaV2(serial, zona,contenido);
		pintarBotonDeZonaV2(contenido,zona,serial);
		HabilitarBotonActivarAlarma(serial);
	}
	else if(topico.includes("keepAlive")){
		console.log("emnsaje de keepAlive: "+ contenido);
		obtenerIconoNivelSAlarmaV2(contenido);
	}
}


/**
 * 
 * @param iddevice
 * @param particione
 * @function evaluar status del dispositivo que esta reportando
 * @returns
 */
//
function informarstatusAlarma(topicorecibido, mensajerecibido){
	var iddevice = topicorecibido.replace("/Status","");
	if(mensajerecibido !="disconnected" && mensajerecibido !="online" && mensajerecibido !="offline"){
		console.log("el mensaje que se recibio es invalido: "+ mensajerecibido);
	}else{
		var id= "status"+iddevice;
		var spanStatus = document.getElementById(id);
		spanStatus.firstChild.data = mensajerecibido;
	}
}



/**
 * 
 * @param iddevice
 * @param contenido
 * @function escribir informacion de particion activa en el display
 * @returns
 */
function escribirDisplayAlarmaV2(iddevice,contenido){
	var spanStatus = document.getElementById("condicionalarma"+iddevice);
	var labelStatus= document.getElementById("labelcondicionalarma"+iddevice);
	if(contenido=="disarmed"){
		spanStatus.firstChild.data = "Disarmed";
		labelStatus.style.backgroundColor = "grey";
	}else if(contenido == "armed_home"){
		spanStatus.firstChild.data = "Armed Home";
		labelStatus.style.backgroundColor = "#224A85";
	}else if(contenido =="armed_away"){
		spanStatus.firstChild.data = "Armed Away";
		labelStatus.style.backgroundColor = "#224A85";
	}else if(contenido =="pending"){
		spanStatus.firstChild.data = "Pending";
		labelStatus.style.backgroundColor = "yellow";
	}else if (contenido == "triggered"){			
		spanStatus.firstChild.data = "Triggered";
		labelStatus.style.backgroundColor = "red";
	}else if (contenido == "trouble"){			
		spanStatus.firstChild.data = "Trouble";
		labelStatus.style.backgroundColor = "yellow";
	}else{
		labelStatus.firstChild.data = contenido;
	}
}


/**
 * 
 * @param serial
 * @param zona
 * @param contenido
 * @function verifica si la zona recibida es mayor al maximo que ya existe
 * @returns
 */
function maximaZonaV2(serial, zona,contenido){
	var urlsendInformation = $(location).attr('pathname') + "/obtainzone/"+serial+"/"+zona+"/"+contenido;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			if(data.fueactualizado){
				for(var j=data.inicio+1; j<=data.fin; j++){
					console.log("boton: "+ "zone_"+j+"_"+serial);
					document.getElementById("zone_"+j+"_"+serial).style.display = 'inline';
				}
			}
		}});
}




/**
 * 
 * @param contenido
 * @param zona
 * @param serial
 * @function Pinta las zonas si tienen alarma
 * @returns
 */
function pintarBotonDeZonaV2(contenido,zona,serial){
	if(contenido == "1")
		document.getElementById("izone_"+zona+"_"+serial).style.color = "blue"; 
	else if (contenido =="0")
		document.getElementById("izone_"+zona+"_"+serial).style.color = "black";
}

/**
 * 
 * @param serial
 * @returns
 */
function HabilitarBotonActivarAlarma(serial){
	var urlsendInformation = $(location).attr('pathname') + "/updatetildezone/"+serial;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			var todaszonasapagadas = data.zonasapagadas;
			if(todaszonasapagadas){
			//	setTimeout(actualizarTilde(serial,"green"),1000);
				document.getElementById("ready_icon"+serial).style.color = "green";
			}
			else{
			//	setTimeout(actualizarTilde(serial,"grey"),1000);
				document.getElementById("ready_icon"+serial).style.color = "grey";
			}
		}});
}




/**
 * 
 * @param informacion
 * @funtion analizar la señal y elegir icono
 * @returns
 */
function obtenerIconoNivelSAlarmaV2(informacion){
	var obj = JSON.parse(informacion);
	var serialId=obj.deviceID;
	var valor=obj.dBm *(-1);
	if(valor>90)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/WS.png';
	if(valor>75 && valor<91)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/1b.png';
	else if(valor>60 && valor<76)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/2b.png';
	else if(valor>45 && valor<61)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/3b.png';
	else if(valor<46)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/4b.png';

}









//-----------------------------------FUNCIONES SE EJECUTAN AL INICIO---------------------------------


/**
 * 
 * @param serialZonas
 * @function carga de zonas al inicio
 * @returns
 */
function cargarZonasV2(serialZonas){
	var seriales = serialZonas;
	console.log("estos son los serials: "+ seriales +" ;cantidad: "+ seriales.length);
	seriales.forEach(cargazonaEfectivaV2);
}

function cargazonaEfectivaV2(item, index){
	var urlsendInformation = $(location).attr('pathname') + "/obtainmaxzone/"+item;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			var maximo= data.result;
			console.log("obtuvo las zonas: "+ maximo);
				for(var j=1; j<maximo+1; j++){
					document.getElementById("zone_"+j+"_"+item).style.display = 'inline';
			}
			var todaszonasapagadas = data.zonasapagadas;
			if(todaszonasapagadas){
				document.getElementById("ready_icon"+item).style.color = "green";
			}
			else{
				document.getElementById("ready_icon"+item).style.color = "grey";
				for(var k=0; k<data.listazonasencendidas.length; k++){
					pintarBotonDeZonaV2("1",data.listazonasencendidas[k],item);
				}
			}
		}});
}



/**
 * @function cargar particiones en el display
 * @param serialAlarma
 * @returns
 */
function cargarParticionesAlarmasV2(serialAlarma){	
	serialAlarma.forEach(cargaparticionEfectivaV2);
}

function cargaparticionEfectivaV2(item, index){
	var urlsendInformation = $(location).attr('pathname') + "/obtainpartition/"+item+"/inicio";
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			var numparticion = document.getElementById("particion"+item);
			numparticion.innerHTML = data.particionactiva;
			console.log("cambio de particion" + item+"; cambiodeparticion: " +data.contenidoparticion);
		}});
}

