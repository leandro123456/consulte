/**
 * Esta clase tiene las animaciones para la carga de los Devices
 */

//Evento para evaluar el contenido del Serial
function evaluarNumeroDeSerie(valorSerial){
	if(valorSerial.includes("WTHUSB") || valorSerial.includes("PSWS1") ||
			valorSerial.includes("PSWS2") ||valorSerial.includes("DSC01") ||
			valorSerial.includes("PS3S1")){
		console.log("se encontro dispositivo de coiaca")
		document.getElementById('marcadevice').value = 'coiaca';
		document.getElementById("marcadevice").disabled = true;
		evaluarSerial(valorSerial);
		
	}else{
		document.getElementById('marcadevice').value = 'otro';
		document.getElementById("marcadevice").disabled = true;
		document.getElementById('selectormodelo').style.display = 'none';
	}
}



//funcion para escribir en el campo serial el id y tambien grisar el tipo de dispositivo
	function evaluarSerial(serial){
		console.log("va a evaluar el serial: "+ serial);
        if(serial.includes("WTHUSB")){ 
        	document.getElementById('modelodevice').value = 'WTHUSB';
        	document.getElementById('tipodevice').value = 'termometro';
        	document.getElementById('tipovistatermometro').value = 'bars';
        	document.getElementById("tipovistatermometro").disabled = true;
        	enableType("termometro");
        	obtenerConfiguracion("termometro",serial);
        }if(serial.includes("PSWS1")){ 
        	document.getElementById('modelodevice').value = 'PSWS1';
        	document.getElementById('tipodevice').value = 'sonoff';
        	document.getElementById('cantidadswiths').value = 'one';
        	document.getElementById("cantidadswiths").disabled = true;    
        	enableType("sonoff");
        	obtenerConfiguracion("sonoff",serial);
        }if(serial.includes("PSWS2")){ 
        	document.getElementById('modelodevice').value = 'PSWS2';
        	document.getElementById("serialnumber").value = "PSWS2";
        	document.getElementById('tipodevice').value = 'sonoff';
        	document.getElementById('cantidadswiths').value = 'two';
        	document.getElementById("cantidadswiths").disabled = true;
        	enableType("sonoff");
        	obtenerConfiguracion("sonoff",serial);
        }if(serial.includes("DSC01")){ 
        	document.getElementById('modelodevice').value = 'BRDSC';
        	document.getElementById('tipodevice').value = 'alarma';
        	enableType("alarma");
        	obtenerConfiguracion("alarma",serial);
        }if(serial.includes("PS3S1")){ 
        	document.getElementById('modelodevice').value = 'PS3S1';
        	document.getElementById('tipodevice').value = 'sonoff';
        	document.getElementById('cantidadswiths').value = 'one';
        	document.getElementById("cantidadswiths").disabled = true;
        	enableType("sonoff");
        	obtenerConfiguracion("sonoff",serial);
        }
        document.getElementById('modelodevice').disabled = true;
        document.getElementById("tipodevice").disabled = true;
	}
	

	
	//Mostrar los tipos de Dispositivos Posibles para Coiaca
	function enableType(valuetype){
        if(valuetype == "termometro"){
        	document.getElementById('cantidadswith').style.display = 'none';
        	document.getElementById('vistastermometro').style.display = 'inline';
        	document.getElementById('parametrostermometro').style.display = 'inline';
        	document.getElementById('timerString').style.display = 'none';
        	document.getElementById('tabletimerstring').style.display = 'none';
        }if(valuetype == "alarma"){
        	document.getElementById('cantidadswith').style.display = 'none';
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        	document.getElementById('timerString').style.display = 'none';
        	document.getElementById('tabletimerstring').style.display = 'none';
        }if(valuetype == "sonoff"){
        	document.getElementById('cantidadswith').style.display = 'inline';
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        	document.getElementById('timerString').style.display = 'inline';
        	document.getElementById('tabletimerstring').style.display = 'inline';
        }if(valuetype != "termometro" && valuetype != "alarma" && valuetype != "sonoff"){
        	document.getElementById('cantidadswith').style.display = 'none';
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        	document.getElementById('timerString').style.display = 'none';
        	document.getElementById('tabletimerstring').style.display = 'none';
        }
        
	}
	
//funcion para mostrar los modelos de coiaca
//function seleccionModelo(){
//	var seleccion=document.getElementById('marcadevice');
//    var valuetype = seleccion.options[seleccion.selectedIndex].value;
//    if(valuetype == "coiaca"){
//    	document.getElementById('selectormodelo').style.display = 'inline';
//    	document.getElementById('modelodevice').style.display = 'inline';
//    }else{
//    	document.getElementById('selectormodelo').style.display = 'none';
//    	document.getElementById('modelodevice').style.display = 'none';
//    }
//}

//Evento asociado a la carga de la ventana Modal
	function siguienteAnimacion(){
		if(document.getElementById("infoGeneral").style.display=="inline"){
			document.getElementById("botonAnterior").style.display="inline";
			document.getElementById("botonSiguiente").style.display="inline";
			document.getElementById("infoDeducidaCoiaca").style.display="inline";
			document.getElementById("infoGeneral").style.display="none";
			document.getElementById("infoPropiaVista").style.display="none";
			document.getElementById("infoAvanzada").style.display="none";
			document.getElementById("infoFinal").style.display="none";
		}
		else if(document.getElementById("infoDeducidaCoiaca").style.display=="inline"){
			document.getElementById("botonAnterior").style.display="inline";
			document.getElementById("botonSiguiente").style.display="inline";
			document.getElementById("infoPropiaVista").style.display="inline";
			document.getElementById("infoDeducidaCoiaca").style.display="none";
			document.getElementById("infoAvanzada").style.display="none";
			document.getElementById("infoGeneral").style.display="none";
			document.getElementById("infoFinal").style.display="none";
		}
		else if(document.getElementById("infoPropiaVista").style.display=="inline"){
			document.getElementById("botonAnterior").style.display="inline";
			document.getElementById("botonSiguiente").style.display="inline";
			document.getElementById("infoAvanzada").style.display="inline";
			document.getElementById("infoDeducidaCoiaca").style.display="none";
			document.getElementById("infoPropiaVista").style.display="none";
			document.getElementById("infoGeneral").style.display="none";
			document.getElementById("infoFinal").style.display="none";
		}
		else if(document.getElementById("infoAvanzada").style.display=="inline"){
			document.getElementById("botonfinalizar").style.display="inline";
			document.getElementById("botoncancelar").style.display="inline";
			document.getElementById("botonAnterior").style.display="inline";
			document.getElementById("botonSiguiente").style.display="none";
			document.getElementById("infoDeducidaCoiaca").style.display="none";
			document.getElementById("infoAvanzada").style.display="none";
			document.getElementById("infoPropiaVista").style.display="none";
			document.getElementById("infoGeneral").style.display="none";
			document.getElementById("infoFinal").style.display="inline";
			if(document.getElementById("tipodevice") != null)
				document.getElementById("tipodevice1").value = document.getElementById("tipodevice").value;
			
			//vista termometro
	  		if(document.getElementById("tipovistatermometro") != null && document.getElementById("tipovistatermometro").value == "watches")
        	  	document.getElementById("tipovistatermometro1").value = "temperatura_reloj";
	  		if(document.getElementById("tipovistatermometro") != null  && document.getElementById("tipovistatermometro").value == "bars")
        	  	document.getElementById("tipovistatermometro1").value = "temperatura_horizontal";
	  		

	  		 //vista sonoff
	  		  if(document.getElementById('dataTable') != null){
	  		  var textos = "";
	  		     for (var i=1;i < document.getElementById('dataTable').rows.length; i++){
	  		             for (var j=0; j<4; j++){
	  		                    textos = textos + document.getElementById('dataTable').rows[i].cells[j].innerHTML;
	  		                    textos = textos +"&"
	  		             }
	  		             textos= textos+"@";
	  		     } 
	  		   console.log(textos);
	  		   if(document.getElementById("timerstringsonoff")!=null)
	  			   document.getElementById("timerstringsonoff").value = textos;
	  			}
	  		 if(document.getElementById("cantidadswiths") != null){
	  			 var cantidades =document.getElementById("cantidadswiths");
	  			 var cantidadelegida =cantidades.options[cantidades.selectedIndex].value;
	  			 console.log("cantidad elegida: "+ cantidadelegida);
	  			 if(cantidadelegida != "none")
	  				document.getElementById("cantidadswiths1").value = cantidadelegida;
	  		 }
	  		 
			if(document.getElementById("iphostescuchar") != null)
				document.getElementById("iphostescuchar1").value = document.getElementById("iphostescuchar").value;
			if(document.getElementById("portescuchar") != null)
				document.getElementById("portescuchar1").value = document.getElementById("portescuchar").value;
			if(document.getElementById("topiclisten") != null)
				document.getElementById("topiclisten1").value = document.getElementById("topiclisten").value;
			if(document.getElementById("userescuchar") != null)
				document.getElementById("userescuchar1").value = document.getElementById("userescuchar").value;
			if(document.getElementById("passescuchar") != null)
				document.getElementById("passescuchar1").value = document.getElementById("passescuchar").value;
			if(document.getElementById("topicwrite") != null)
				document.getElementById("topicwrite1").value = document.getElementById("topicwrite").value;
			if(document.getElementById("iphostescucharremote") != null)
				document.getElementById("iphostescucharremote1").value = document.getElementById("iphostescucharremote").value;
			if(document.getElementById("portescucharremote") != null)
				document.getElementById("portescucharremote1").value = document.getElementById("portescucharremote").value;
			if(document.getElementById("topiclistenremote") != null)
				document.getElementById("topiclistenremote1").value = document.getElementById("topiclistenremote").value;
			if(document.getElementById("userescucharremote") != null)
				document.getElementById("userescucharremote1").value = document.getElementById("userescucharremote").value;
			if(document.getElementById("passescucharremote") != null)
				document.getElementById("passescucharremote1").value = document.getElementById("passescucharremote").value;
			if(document.getElementById("topicwriteremote") != null)
				document.getElementById("topicwriteremote1").value = document.getElementById("topicwriteremote").value;

		}

	}

//Evento asociado a la carga de la ventana Modal para regresar
function anteriorAnimacion(){
	if(document.getElementById("infoGeneral").style.display=="inline"){
		document.getElementById("infoDeducidaCoiaca").style.display="inline";
		document.getElementById("infoGeneral").style.display="none";
		document.getElementById("infoPropiaVista").style.display="none";
		document.getElementById("infoAvanzada").style.display="none";
	}
	else if(document.getElementById("infoDeducidaCoiaca").style.display=="inline"){
		document.getElementById("botonAnterior").style.display="none";
		document.getElementById("infoPropiaVista").style.display="none";
		document.getElementById("infoDeducidaCoiaca").style.display="none";
		document.getElementById("infoAvanzada").style.display="none";
		document.getElementById("infoGeneral").style.display="inline";
	}
	else if(document.getElementById("infoPropiaVista").style.display=="inline"){
		document.getElementById("infoAvanzada").style.display="none";
		document.getElementById("infoDeducidaCoiaca").style.display="inline";
		document.getElementById("infoPropiaVista").style.display="none";
		document.getElementById("infoGeneral").style.display="none";
	}
	else if(document.getElementById("infoAvanzada").style.display=="inline"){
		document.getElementById("infoDeducidaCoiaca").style.display="none";
		document.getElementById("infoAvanzada").style.display="none";
		document.getElementById("infoPropiaVista").style.display="inline";
		document.getElementById("infoGeneral").style.display="none";
	}
	else if(document.getElementById("infoFinal").style.display=="inline"){
		document.getElementById("botonSiguiente").style.display="inline";
		document.getElementById("infoDeducidaCoiaca").style.display="none";
		document.getElementById("infoFinal").style.display="none";
		document.getElementById("infoAvanzada").style.display="inline";
		document.getElementById("infoPropiaVista").style.display="none";
		document.getElementById("infoGeneral").style.display="none";
		document.getElementById("botonfinalizar").style.display="none";
		document.getElementById("botoncancelar").style.display="none";
	}
		
}


//Evento asociado a la confirmacion de los datos de la creacion de Dispositivo
$('#ModalConfirm').on('show.bs.modal', function (event) {
	  		  var button = $(event.relatedTarget)
		  	  var recipient = button.data('whatever') 
	  		  var serial=document.getElementById('serialnumber').value;
		  	  var action= "/home/create/"+serial;
	  		  document.getElementById("form_id").action = action;
	  		  
	  		  
	  		if(document.getElementById("serialnumber") != null)
        	  	document.getElementById("serialnumber1").value = document.getElementById("serialnumber").value;
	  		
	  		if(document.getElementById("descriptiondevice") != null)
        	  	document.getElementById("descriptiondevice1").value = document.getElementById("descriptiondevice").value;
	  		if(document.getElementById("namedevice") != null)
        	  	document.getElementById("namedevice1").value = document.getElementById("namedevice").value;
	  		 if(document.getElementById("tipodevice") != null)
	        	  	document.getElementById("tipodevice1").value = document.getElementById("tipodevice").value;

	  		  
	  		 //vista sonoff
	  		  if(document.getElementById('dataTable') != null){
	  		  var textos = "";
	  		     for (var i=1;i < document.getElementById('dataTable').rows.length; i++){
	  		             for (var j=0; j<4; j++){
	  		                    textos = textos + document.getElementById('dataTable').rows[i].cells[j].innerHTML;
	  		                    textos = textos +"&"
	  		             }
	  		             textos= textos+"@";
	  		     } 
	  		   console.log(textos);
	  		   document.getElementById("timerstringsonoff").value = textos;
	  			}
	  		 if(document.getElementById("cantidadswiths") != null){
	  			 var cantidades =document.getElementById("cantidadswiths");
	  			 var cantidadelegida =cantidades.options[cantidades.selectedIndex].value;
	  			 console.log("cantidad elegida: "+ cantidadelegida);
	  			 if(cantidadelegida != "none")
	  				document.getElementById("cantidadswiths1").value = cantidadelegida;
	  		 }
	  		  
	  		  //vista termometro
	  		if(document.getElementById("tipovistatermometro") != null && document.getElementById("tipovistatermometro").value == "watches")
        	  	document.getElementById("tipovistatermometro1").value = "temperatura_reloj";
	  		if(document.getElementById("tipovistatermometro") != null  && document.getElementById("tipovistatermometro").value == "bars")
        	  	document.getElementById("tipovistatermometro1").value = "temperatura_horizontal";
	  		if(document.getElementById("humedadtermometro") != null && document.getElementById("humedadtermometro").checked == true)
        	  	document.getElementById("humedadtermometro1").value = true;
	  		if(document.getElementById("tempctermometro") != null && document.getElementById("tempctermometro").checked == true)
        	  	document.getElementById("tempctermometro1").value = true;
	  		if(document.getElementById("sensacionctermometro") != null && document.getElementById("sensacionctermometro").checked == true)
        	  	document.getElementById("sensacionctermometro1").value = true;
	  		if(document.getElementById("tempftermometro") != null && document.getElementById("tempftermometro").checked == true)
        	  	document.getElementById("tempftermometro1").value = true;
	  		if(document.getElementById("sensacionftermometro") != null && document.getElementById("sensacionftermometro").checked == true)
        	  	document.getElementById("sensacionftermometro1").value = true;	  
	  		  //fin termometro
	  		  
	  		  var checkBox=document.getElementById("toggle-paramconects");
			  if (checkBox.checked == true){
				  document.getElementById("defaultconfiguration").value = true;
			  }else{
				  document.getElementById("defaultconfiguration").value = false;
				  if(document.getElementById("iphostescuchar") != null)
	        	  	document.getElementById("iphostescuchar1").value = document.getElementById("iphostescuchar").value;
	        	  if(document.getElementById("portescuchar") != null)
	          	  	document.getElementById("portescuchar1").value = document.getElementById("portescuchar").value;
	        	  if(document.getElementById("topiclisten") != null)
	          	  	document.getElementById("topiclisten1").value = document.getElementById("topiclisten").value;
	        	  if(document.getElementById("userescuchar") != null)
	          	  	document.getElementById("userescuchar1").value = document.getElementById("userescuchar").value;
	        	  if(document.getElementById("passescuchar") != null)
	          	  	document.getElementById("passescuchar1").value = document.getElementById("passescuchar").value;
	        	  
	        	  if(document.getElementById("iphostescribir") != null)
	          	  	document.getElementById("iphostescribir1").value = document.getElementById("iphostescribir").value;
	        	  if(document.getElementById("portescribir") != null)
	          	  	document.getElementById("portescribir1").value = document.getElementById("portescribir").value;
	        	  if(document.getElementById("topicwrite") != null)
	          	  	document.getElementById("topicwrite1").value = document.getElementById("topicwrite").value;
	        	  if(document.getElementById("userescribir") != null)
	          	  	document.getElementById("userescribir1").value = document.getElementById("userescribir").value;
	        	  if(document.getElementById("passescribir") != null)
	          	  	document.getElementById("passescribir1").value = document.getElementById("passescribir").value;
	        	  
	        	  if(document.getElementById("iphostescucharremote") != null)
	          	  	document.getElementById("iphostescucharremote1").value = document.getElementById("iphostescucharremote").value;
	          	  if(document.getElementById("portescucharremote") != null)
	            	  	document.getElementById("portescucharremote1").value = document.getElementById("portescucharremote").value;
	          	  if(document.getElementById("topiclistenremote") != null)
	            	  	document.getElementById("topiclistenremote1").value = document.getElementById("topiclistenremote").value;
	          	  if(document.getElementById("userescucharremote") != null)
	            	  	document.getElementById("userescucharremote1").value = document.getElementById("userescucharremote").value;
	          	  if(document.getElementById("passescucharremote") != null)
	            	  	document.getElementById("passescucharremote1").value = document.getElementById("passescucharremote").value;
	          	  
	          	  if(document.getElementById("iphostescribirremote") != null)
	            	  	document.getElementById("iphostescribirremote1").value = document.getElementById("iphostescribirremote").value;
	          	  if(document.getElementById("portescribirremote") != null)
	            	  	document.getElementById("portescribirremote1").value = document.getElementById("portescribirremote").value;
	          	  if(document.getElementById("topicwriteremote") != null)
	            	  	document.getElementById("topicwriteremote1").value = document.getElementById("topicwriteremote").value;
	          	  if(document.getElementById("userescribirremote") != null)
	            	  	document.getElementById("userescribirremote1").value = document.getElementById("userescribirremote").value;
	          	  if(document.getElementById("passescribirremote") != null)
	            	  	document.getElementById("passescribirremote1").value = document.getElementById("passescribirremote").value;
			  }
        	  
          	  
          	  
	  	})
  	
	  	
/**
 * Estas funciones se usan para cargar la configuracion por default de los 
 * parametros de conexion
 */

	function obtenerConfiguracion(tipoDevice,serial) {
		var _url = $(location).attr('pathname') + "/configuraciondefault/"+tipoDevice+"/"+serial;
		$.ajax({ url : _url,
			contentType: "application/json",
			dataType: 'json',
			success: function(data){
				document.getElementById('iphostescuchar').value = data.iphostescuchar;
				document.getElementById('portescuchar').value = data.portescuchar;
				document.getElementById('userescuchar').value = data.userescuchar;
				document.getElementById('topiclisten').value = data.topicescuchar;
				document.getElementById('topicwrite').value = data.topicescribir;
				document.getElementById('iphostescucharremote').value = data.iphostescucharremote;
				document.getElementById('portescucharremote').value = data.portescucharremote;
				document.getElementById('userescucharremote').value = data.userescucharremote;
				document.getElementById('topiclistenremote').value = data.topicescucharremote;
				document.getElementById('topicwriteremote').value = data.topicescribirremote;
			}});
	}
	  	

		
		
		
		//funcion para evaluar el password en las opciones avanzadas
	    function checkPassword(){
		        var passescuchar = document.getElementById('passescuchar').value;
		        var confirpassescuchar = document.getElementById('confirpassescuchar').value;
		        if(confirpassescuchar.length==0){ 
		            console.log("Esta vacio e indefinido: "+confirpassescuchar);
		            document.getElementById("passescuchar").style.background = "transparent";
		            document.getElementById("confirpassescuchar").style.background = "transparent";
		       }
		        if (confirpassescuchar.length!=0 && passescuchar.length!=0 && passescuchar != confirpassescuchar){ 
		        	console.log("sondistintos, se deberia escribir: "+ confirpassescuchar);
		            document.getElementById("confirpassescuchar").style.background = "red";
		        }
				if(confirpassescuchar.length!=0 && passescuchar.length!=0 && (passescuchar == confirpassescuchar)){ 
		   			console.log("ya termino!!");
		   			document.getElementById("confirpassescuchar").style.background = "green";
			} 
		};
		
		
		
		
		//funcion para completar los campos de Opciones Avanzadas por DEfault
		function completarloscamposdeconfiguracion(marca, modelo){
	  		if(marca!=null && marca == "coiaca"){
	  			if(modelo!=null && modelo.includes("BRDSC")){
	  				//usar los valores de confirguracion por default de la alarma  
//	   				document.getElementById("iphostescuchar").value =${configdef.iphostescuchar};
//	   				document.getElementById("portescuchar").value =${configdef.portescuchar};
//	   				document.getElementById("topiclisten").value =${configdef.topicescuchar};
//	   				document.getElementById("userescuchar").value =${configdef.userescuchar};
//	   				document.getElementById("passescuchar").value =${configdef.passescuchar};
//	   				document.getElementById("confirpassescuchar").value =${configdef.passescuchar};
	  				
//	   				document.getElementById("iphostescribir").value =${configdef.iphostescribir};
//	   				document.getElementById("portescribir").value =${configdef.portescribir};
//	   				document.getElementById("topicwrite").value =${configdef.topicescribir};
//	   				document.getElementById("userescribir").value =${configdef.userescribir};
//	   				document.getElementById("passescribir").value =${configdef.passescribir};
//	   				document.getElementById("confirpassescribir").value =${configdef.passescribir};
	  				
//	   				document.getElementById("iphostescuchar").value =${configdef.iphostescuchar};
//	   				document.getElementById("portescucharremote").value =${configdef.portescucharremote};
//	   				document.getElementById("topiclistenremote").value =${configdef.topicescucharremote};
//	   				document.getElementById("userescucharremote").value =${configdef.userescucharremote};
//	   				document.getElementById("passescucharremote").value =${configdef.passescucharremote};
//	   				document.getElementById("confirpassescucharremote").value =${configdef.passescucharremote};
	  				
//	   				document.getElementById("iphostescribirremote").value =${configdef.iphostescribirremote};
//	   				document.getElementById("portescribirremote").value =${configdef.portescribirremote};
//	   				document.getElementById("topicwriteremote").value =${configdef.topicescribirremote};
//	   				document.getElementById("userescribirremote").value =${configdef.userescribirremote};
//	   				document.getElementById("passescribirremote").value =${configdef.passescribirremote};
//	   				document.getElementById("confirmpassescribirremote").value =${configdef.confirmpassescribirremote};
	  				
//	   			}else if(modelo!=null){
//	   				//usar los valores de confirguracion por default de cualquiera
//	   				document.getElementById("iphostescuchar").value =${configdeflocal.iphostescuchar};
//	   				document.getElementById("portescuchar").value =${configdeflocal.portescuchar};
//	   				document.getElementById("topiclisten").value =${configdeflocal.topicescuchar};
//	   				document.getElementById("userescuchar").value =${configdeflocal.userescuchar};
//	   				document.getElementById("passescuchar").value =${configdeflocal.passescuchar};
//	   				document.getElementById("confirpassescuchar").value =${configdeflocal.passescuchar};
	  				
//	   				document.getElementById("iphostescribir").value =${configdeflocal.iphostescribir};
//	   				document.getElementById("portescribir").value =${configdeflocal.portescribir};
//	   				document.getElementById("topicwrite").value =${configdeflocal.topicescribir};
//	   				document.getElementById("userescribir").value =${configdeflocal.userescribir};
//	   				document.getElementById("passescribir").value =${configdeflocal.passescribir};
//	   				document.getElementById("confirpassescribir").value =${configdeflocal.passescribir};
	  				
//	   				document.getElementById("iphostescuchar").value =${configdeflocal.iphostescuchar};
//	   				document.getElementById("portescucharremote").value =${configdeflocal.portescucharremote};
//	   				document.getElementById("topiclistenremote").value =${configdeflocal.topicescucharremote};
//	   				document.getElementById("userescucharremote").value =${configdeflocal.userescucharremote};
//	   				document.getElementById("passescucharremote").value =${configdeflocal.passescucharremote};
//	   				document.getElementById("confirpassescucharremote").value =${configdeflocal.passescucharremote};
	  				
//	   				document.getElementById("iphostescribirremote").value =${configdeflocal.iphostescribirremote};
//	   				document.getElementById("portescribirremote").value =${configdeflocal.portescribirremote};
//	   				document.getElementById("topicwriteremote").value =${configdeflocal.topicescribirremote};
//	   				document.getElementById("userescribirremote").value =${configdeflocal.userescribirremote};
//	   				document.getElementById("passescribirremote").value =${configdeflocal.passescribirremote};
//	   				document.getElementById("confirmpassescribirremote").value =${configdeflocal.confirmpassescribirremote};
	  			}else{
	  				console.log("El MODELO es null");	
	  			}
	  		}else{
	  			console.log("la MARCA es null");
	  		}
	  	}