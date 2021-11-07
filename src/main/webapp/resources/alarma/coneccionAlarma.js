/**
 * Para establecer la conexion mqtt con la alarma
 */

/** inicio conexion con alarmas**/
function startConnectAlarma(host,port,ssl,user,pass,topicos) {
    clientID = "clientID-" +parseInt(Math.random() * 100) +parseInt(Math.random() * 100);
    window.topicosalarmas=topicos;
    var port1=parseInt(port);
    clientalarma = new Paho.MQTT.Client(host,port1, clientID);
    clientalarma.onConnectionLost = onConnectionLostAlarma;
    clientalarma.onMessageArrived = onMessageArrivedAlarma;
    if(clientalarma.isConnected()==false){
    	clientalarma.connect({
      		onSuccess: onConnectAlarmas,
      		onFailure: onConnectionLostAlarma,
      		keepAliveInterval: 10,
      		userName: user,
      		useSSL: true,
      		password: pass	
      	});
	  }
	  else{
		  console.log("Client MQTT Connected");
		  Connected121();
	  }   
}

function subcripcionatopicos(item, index) { 
	clientalarma.subscribe(item);
    console.log('Subscripcion exitosa a topicos de alarma: ' + item);
}

function onConnectAlarmas() {
	console.log("coneccion exitosa con alarma");
	topicosalarmas.forEach(subcripcionatopicos);
}

function onConnectionLostAlarma(responseObject) {
    console.log('Connection lost con alarma');
    if (responseObject.errorCode !== 0) {
    	console.log('ERROR: ' + responseObject.errorMessage);
    }
}


/** comportamiento cuando recibe un mensaje*/
function onMessageArrivedAlarma(message) {
	console.log("Message: "+ message.destinationName +" more: "+message.payloadString);
   	var urlsendInformation = $(location).attr('pathname') + "/evaluaralarmareportada/"+window.btoa(message.destinationName)+"/"+window.btoa(message.payloadString);
   		$.ajax({ url : urlsendInformation,
   			contentType: "application/json",
   			dataType: 'json',
   			success: function(data){
   				if(data.conocido){
   					if(data.tipovista=="alarmav2"){
   						ProcesarAlarmaV2(data);
   						}
   					else
   						ProcesarAlarma(message);
   				}
   			}			
   	});
}
