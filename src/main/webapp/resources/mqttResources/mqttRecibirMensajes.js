
function EnviarSonoffSimulatePushbutton(swith,serial){
	var urlsendInformation = $(location).attr('pathname') + "/sendCommand/sonoff/"+serial+"/"+"simulatepushbutton"+"/"+swith;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			console.log(data.result);
		}});
}

function EnviarSonoff(swith,serial,accion){  
	var urlsendInformation = "";
	var acs = document.getElementById(accion).firstChild.nodeValue;
	if (acs == "Apagado" || acs == "Switched_off"){
		urlsendInformation =$(location).attr('pathname') + "/sendCommand/sonoff/"+serial+"/"+"enviaron"+"/"+swith;
	} else {
		urlsendInformation =$(location).attr('pathname') + "/sendCommand/sonoff/"+serial+"/"+"enviaroff"+"/"+swith;
	}
	console.log("voy a enviar esta URL: "+ urlsendInformation);
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			console.log(data.result);
		}});
}

function Connecttotal(swith,serial,message1){ 
	var textpart= "particiones"+serial;
	console.log("particiones elem: "+document.getElementById(textpart).innerHTML );
	var partition=document.getElementById(textpart).innerHTML;
	var enviarcomando=true;
	if(enviarcomando){
		var urlsendInformation = $(location).attr('pathname') + "/sendCommand/alarma/"+serial+"/"+message1+"/"+partition;
		$.ajax({ url : urlsendInformation,
			contentType: "application/json",
			dataType: 'json',
			 beforeSend:function(){ 
	             //animacion del boton
				 var texto= "btn-"+message1.replace("alarm-","")+serial;
				 var activo = document.getElementById(texto);
				 console.log("texto "+texto);
				 if(activo!=null){
				 activo.innerHTML = "";
				 $("#"+texto).append('<i class="fa fa-circle-o-notch fa-spin"></i>');
				 }			
	         },
			success: function(data){
				console.log(data.result);
				if(data.result== "")
					activo.style.color = "red";
				var texto= "btn-"+message1.replace("alarm-","")+serial;
				var activo = document.getElementById(texto);
				if(message1.includes("armarzona")){
					activo.innerHTML = "";
					$("#"+texto).append('<i class="dsc-icon icon-stay_away"></i>');
				}
				else if(message1.includes("armartotal")){
					activo.innerHTML = "";
					$("#"+texto).append('<i class="dsc-icon icon-stay_empty"></i>');
				}
				else{
					if(activo!=null)
					activo.innerHTML = message1.replace("alarm-","");
				}
			}});
	}	  
}
