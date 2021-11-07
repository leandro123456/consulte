
//funcion para actualizar la frase dentro de todos los elementos en la vista
function updateEstado45(id, valor,elemento) 
{
	if(elemento=="statussonoff"){
	   var spanStatus = document.getElementById(id);
	   spanStatus.firstChild.data = valor;
	}
	else if(elemento=="botonsonoff"){
		var boton = document.getElementById(id);
		   if (valor == "yes"){
		       boton.firstChild.data = "Encendido";
		   }
		   else if (valor == "no"){
		     boton.firstChild.data = "Apagado";
		   }
		   else
			   console.log("no encontro el valor de la variable: "+ valor +" en el id: "+ id);
	}
}

//evaluar status del dispositivo que esta reportando
function informarstatus45(topicorecibido, mensajerecibido){
	var iddevice = topicorecibido.replace("/Status","");
	if(mensajerecibido =="online"){
		updateEstado45("spanestado"+iddevice, "online","statussonoff");
	}if(mensajerecibido =="offline"){
		updateEstado45("spanestado"+iddevice, "offline", "statussonoff");
	}if(mensajerecibido =="disconnected"){
		updateEstado45("spanestado"+iddevice, "online", "statussonoff");
	}if(mensajerecibido !="disconnected" && mensajerecibido !="online" && mensajerecibido !="offline"){
		console.log("el mensaje que se recibio es invalido: "+ mensajerecibido);
	}
}



/** comportamiento cuando recibe un mensaje*/
function ProcesarAlarma(message){
	console.log("ALARMA: "+message.destinationName+"; contenido: "+  message.payloadString);
	var contenido= message.payloadString;
	var topico = message.destinationName;
	var dataObj = null;
	if(message.destinationName.includes("/Status")){
		informarstatus45(message.destinationName, message.payloadString)
	}
	if (topico.includes("activePartition")){
		iddevice = topico.substring(0,topico.search("/activePartition"));
		 var numparticion = document.getElementById("particiones"+iddevice);
		 console.log("Particion activa-> iddevice: "+iddevice+"; particion: "+ numparticion);
		 numparticion.innerHTML = message.payloadString;
		 if(contenido =="1"){
			document.getElementById("partant"+iddevice).disabled = true;
			actualizarDisplay(iddevice, message.payloadString);
		}
		else{
			document.getElementById("partant"+iddevice).disabled = false;
			actualizarDisplay(iddevice, message.payloadString);
		}
	}
	else if(topico.includes("Partition")){
		var iddevice = topico.substring(0,topico.search("/Partition"));
		var numparticion = topico.substring(topico.search("Partition")).replace("Partition","");
		var numdisplay = document.getElementById("particiones"+iddevice).innerHTML;
		console.log("este es el numero de particion en el display: "+ numdisplay +"; num de particion: "+ numparticion);
		if(numdisplay == numparticion){
			escribirDisplayAlarma(iddevice,contenido);
		}
	}
	else if(topico.includes("Zone")){
		console.log("llego informacion de una zona: " +topico+"; "+contenido);
		var zona= topico.substring(topico.search("/Zone")).replace("/Zone","");
		var serial= topico.substring(0,topico.search("/Zone"));
		maximaZona(serial, zona,contenido);
		pintarBotonDeZona(contenido,zona,serial);
		ActualizarTildeZona(serial);
	}
	else if(topico.includes("keepAlive")){
		console.log("Mensaje de keepAlive: "+ contenido);
		obtenerIconoNivelSAlarma(contenido);
	}
}


//actualizar display cuando se cambia de particion
function actualizarDisplay(iddevice, particione){
	var urlsendInformation = $(location).attr('pathname') + "/obtainpartition/"+iddevice+"/"+particione;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			console.log("esta es la particion que recibo: "+ particione)
			document.getElementById("particiones"+iddevice).innerHTML=particione;
			console.log("actualizar display: "+ iddevice +"; particion informada: "+data.contenidoparticion)
			escribirDisplayAlarma(iddevice,data.contenidoparticion);
			console.log("cambio de particion"+ iddevice+"; cambiodeparticion: " +data.contenidoparticion);
		}});
}

//cargar particiones en el display
function cargarParticionesAlarmas(serialAlarma){	
	 if(serialAlarma.includes("-")){
		 var res = serialAlarma.split("-");
		  for (i = 0; i < res.length; i++) {
			  	cargaparticionEfectiva(res[i]);
			}
	 }else
		 cargaparticionEfectiva(serialAlarma);
}

function cargaparticionEfectiva(item){
	var urlsendInformation = $(location).attr('pathname') + "/obtainpartition/"+item+"/inicio";
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			document.getElementById("particiones"+item).innerHTML=data.particionactiva;
			escribirDisplayAlarma(item,data.contenidoparticion);
			console.log("cambio de particion" + item+"; cambiodeparticion: " +data.contenidoparticion);
			if(data.particionactiva =="1"){
				document.getElementById("partant"+item).disabled = true;
			}
			else{
				document.getElementById("partant"+item).disabled = false; 
			}
		}});
}


//escribir informacion de particion activa en el display
function escribirDisplayAlarma(iddevice,contenido){
	if(contenido=="disarmed"){
//		document.getElementById("ac_icon"+iddevice).style.color = "grey";
		document.getElementById("armed_icon"+iddevice).style.color = "grey";
		document.getElementById("trouble_icon"+iddevice).style.color = "grey";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Disarmed";
	}else if(contenido == "armed_home"){
		document.getElementById("armed_icon"+iddevice).style.color = "red";
//		document.getElementById("ac_icon"+iddevice).style.color = "grey";
		document.getElementById("trouble_icon"+iddevice).style.color = "grey";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Armed Home";
	}else if(contenido =="armed_away"){
		document.getElementById("armed_icon"+iddevice).style.color = "red";
		document.getElementById("trouble_icon"+iddevice).style.color = "grey";
//		document.getElementById("ac_icon"+iddevice).style.color = "grey";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Armed Away";
	}else if(contenido =="pending"){
		document.getElementById("trouble_icon"+iddevice).style.color = "grey";
		document.getElementById("armed_icon"+iddevice).style.color = "yellow";
//		document.getElementById("ac_icon"+iddevice).style.color = "grey";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Pending";
	}else if (contenido == "triggered"){			
		document.getElementById("trouble_icon"+iddevice).style.color = "red";
		document.getElementById("armed_icon"+iddevice).style.color = "red";
//		document.getElementById("ac_icon"+iddevice).style.color = "red";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "red";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Triggered";
	}else if (contenido == "trouble"){			
		document.getElementById("trouble_icon"+iddevice).style.color = "yellow";
		document.getElementById("armed_icon"+iddevice).style.color = "grey";
//		document.getElementById("ac_icon"+iddevice).style.color = "grey";
		document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "grey";
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = "Trouble";
	}else{
		//como no lo reconoce lo muestra en el segundo display
		var spanStatus = document.getElementById("second_line"+iddevice);
		spanStatus.firstChild.data = contenido;
	}
}

//analizar la señal y elegir icono
function obtenerIconoNivelSAlarma(informacion){
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

//verifica si la zona recibida es mayor al maximo que ya existe
function maximaZona(serial, zona,contenido){
	var urlsendInformation = $(location).attr('pathname') + "/obtainzone/"+serial+"/"+zona+"/"+contenido;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			if(data.fueactualizado){
				console.log("inicio: "+data.inicio+1);
				console.log("fin: "+data.fin);
				for(var j=data.inicio+1; j<=data.fin; j++){
					console.log("boton: "+ "zone_"+j+"_"+serial);
					document.getElementById("zone_"+j+"_"+serial).style.display = 'inline';
				}
			}
		}});
}

//funcion para pintar las zonas si tienen alarma
function pintarBotonDeZona(contenido,zona,serial){
	if(contenido == "1")
		document.getElementById("izone_"+zona+"_"+serial).style.color = "blue"; 
	else if (contenido =="0")
		document.getElementById("izone_"+zona+"_"+serial).style.color = "#858796";
}

//pone en verde el tilde si todas la zonas estan apagadas
function actualizarTilde(serial, color){
	document.getElementById("ready_icon"+serial).style.color = color;
}

function ActualizarTildeZona(serial){
	var urlsendInformation = $(location).attr('pathname') + "/updatetildezone/"+serial;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			var todaszonasapagadas = data.zonasapagadas;
			if(todaszonasapagadas){
				setTimeout(actualizarTilde(serial,"green"),1000);
			}
			else{
				setTimeout(actualizarTilde(serial,"grey"),1000);
			}
		}});
}


function cargarZonas(serialZonas){
	if(serialZonas.includes("-")){
		  var res = serialZonas.split("-");
		  for (i = 0; i < res.length; i++) {
			  cargazonaEfectiva(res[i])
			}
	  }else{
		  cargazonaEfectiva(serialZonas)
	  }
}

function cargazonaEfectiva(item){
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
				actualizarTilde(item,"green");
			}
			else{
				actualizarTilde(item,"grey");
				//cargar todos los botones de color
				for(var k=0; k<data.listazonasencendidas.length; k++){
					pintarBotonDeZona("1",data.listazonasencendidas[k],item);
				}
			}
		}});
}