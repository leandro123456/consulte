/** ENVIO DE COMANDOS AL BACKEND**/
    
   function enviarComandotobackendV2(acalarma){
	//var acalarma = document.getElementById("accionalarma").value;
	var serial = acalarma.split("-",1);
	var particionactivaa= document.getElementById("particion"+serial).textContent;
   	var enc = window.btoa('${pageContext.request.userPrincipal.name}');
   	var urlsendInformation = $(location).attr('pathname') + "/ejecutaraccion/"+acalarma+"-"+particionactivaa;
   		$.ajax({ url : urlsendInformation,
   			contentType: "application/json",
   			dataType: 'json',
   			success: function(data){
   				if(data.fallo){
   				var x= data.status;
				swal({
					  title: x,
					  icon: "error",
					  timer: 2000,
					  closeOnClickOutside: false,
					  buttons: false,
					});
   				}
   			}			
   	});
   }

   
   
   function cambiarParticion(serial,numparticion){
	   	var urlsendInformation = $(location).attr('pathname') + "/changepartition/"+serial+"/"+numparticion;
	   		$.ajax({ url : urlsendInformation,
	   			contentType: "application/json",
	   			dataType: 'json',
	   			success: function(data){
	   				if(data.fallo){
	   				var x= data.status;
					swal({
						  title: x,
						  icon: "error",
						  timer: 2000,
						  closeOnClickOutside: false,
						  buttons: false,
						});
	   				}
	   			}			
	   	});
	   }

   
   function updatepasswordV2(pass,confirmpass){
	   	var urlsendInformation = $(location).attr('pathname') + "/updatepass/"+pass+"/"+confirmpass;
	   		$.ajax({ url : urlsendInformation,
	   			contentType: "application/json",
	   			dataType: 'json',
	   			success: function(data){
	   				if(data.fallo){
	   				var x= data.status;
					swal({
						  title: x,
						  icon: "error",
						  timer: 2000,
						  closeOnClickOutside: false,
						  buttons: false,
						});
	   				}else{
		   				var x= data.status;
						swal({
							  title: x,
							  icon: "success",
							  timer: 2000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
	   				}
	   			}			
	   	});
	   }

   

/** comportamiento cuando recibe un mensaje*/
function ProcesarAlarmaV2(data) {
	var serial= data.serial;
	var accion = data.accion;
	switch(accion){
		case "Status":
			document.getElementById("status"+serial).firstChild.data = data.status;
			break;
		case "activePartition":
			document.getElementById("particion"+serial).innerHTML = data.activo;
			escribirDisplayAlarmaV2(serial,data.statuspartition);
			break;
		case "Zone":
			maximaZonaV2(serial, data.numzone,data.zone);
			pintarBotonDeZonaV2(serial,data.numzone,data.zone);
			HabilitarBotonActivarAlarma(serial);
			break;
		case "keepAlive":
			obtenerIconoNivelSAlarmaV2(serial,data.dBm);
			break;
		case "Partition":
			var numdisplay = document.getElementById("particion"+serial).innerHTML;
			console.log("este es el numero de particion en el display: "+ numdisplay +"; num de particion: "+ data.numpartition);
			if(numdisplay == data.numpartition){
				escribirDisplayAlarmaV2(serial,data.statuspartition);
			}else{
				escribirDisplayAlarmaV2(serial,"Unknown");
			}
			break;		
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
	}else {
		spanStatus.firstChild.data = "Unknown";
		labelStatus.style.backgroundColor = "grey";
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
function pintarBotonDeZonaV2(serial,zona,contenido){
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
function obtenerIconoNivelSAlarmaV2(serial,calidad){
	var valor=calidad *(-1);
	if(valor>90)
		document.getElementById('img_signal_'+serial).src='resources/mqttResources/imgsignal/WS.png';
	if(valor>75 && valor<91)
		document.getElementById('img_signal_'+serial).src='resources/mqttResources/imgsignal/1b.png';
	else if(valor>60 && valor<76)
		document.getElementById('img_signal_'+serial).src='resources/mqttResources/imgsignal/2b.png';
	else if(valor>45 && valor<61)
		document.getElementById('img_signal_'+serial).src='resources/mqttResources/imgsignal/3b.png';
	else if(valor<46)
		document.getElementById('img_signal_'+serial).src='resources/mqttResources/imgsignal/4b.png';

}









//-----------------------------------FUNCIONES SE EJECUTAN AL INICIO---------------------------------


/**
 * 
 * @param serialZonas
 * @function carga de zonas al inicio
 * @returns
 */

function cargarZonasV2(serialZonas){
	if(serialZonas.includes("-")){
		  var res = serialZonas.split("-");
		  for (i = 0; i < res.length; i++) {
			  cargazonaEfectivaV2(res[i])
			}
	  }else{
		  cargazonaEfectivaV2(serialZonas)
	  }
}

function  cargazonaEfectivaV2(item){
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
							pintarBotonDeZonaV2(item,data.listazonasencendidas[k],"1");
						}
					}
				}});
		}



/**
 * @function cargar particiones en el display
 * @param serialAlarma
 * @returns
 */
//cargar particiones en el display
function cargarParticionesAlarmasV2(serialAlarma){	
	 if(serialAlarma.includes("-")){
		 var res = serialAlarma.split("-");
		  for (i = 0; i < res.length; i++) {
			  cargaparticionEfectivaV2(res[i]);
			}
	 }else
		 cargaparticionEfectivaV2(serialAlarma);
}

function cargaparticionEfectivaV2(item){
			var urlsendInformation = $(location).attr('pathname') + "/obtainpartition/"+item+"/inicio";
			$.ajax({ url : urlsendInformation,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
					var numparticion = document.getElementById("particion"+item);
					numparticion.innerHTML = data.particionactiva;
					escribirDisplayAlarmaV2(item,data.contenidoparticion);
					console.log("cambio de particion" + item+"; cambiodeparticion: " +data.contenidoparticion);
				}});
}

