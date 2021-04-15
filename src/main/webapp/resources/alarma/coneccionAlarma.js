/**
 * Para establecer la conexion mqtt con la alarma
 */

/** inicio conexion con alarmas**/
function startConnectAlarma(host,port,ssl,user,pass,topicos) {
	console.log("llego a la alarma");
    clientID = "clientID-" +parseInt(Math.random() * 100) +parseInt(Math.random() * 100);
    window.topicosalarmas=topicos;

    var port1=parseInt(port);
    clientalarma = new Paho.MQTT.Client(host,port1, clientID);
    clientalarma.onConnectionLost = onConnectionLostAlarma;
    clientalarma.onMessageArrived = onMessageArrivedAlarma;
    if(clientalarma.isConnected()==false){
    	console.log("la alarma no esta conectada");
    	clientalarma.connect({
      		onSuccess: onConnectAlarmas,
      		onFailure: onConnectionLostAlarma,
      		keepAliveInterval: 10,
      		userName: user,
      		useSSL: true,
      		password: pass	
      	});
    	console.log("salio de la evauacion de la coneccion");
	  }
	  else{
		  console.log("ya esta conectada!!");
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
	console.log("LLEGO UN MENSAJE DE LA ALARMA: "+message.destinationName+"; contenido: "+  message.payloadString);
	ProcesarAlarmaV2(message);
	//ProcesarAlarma(message);
}


// Called after form input is processed
//function startConnect45(host,port,ssl,user,pass,fileouput, topico) {
//    // Generate a random client ID
//    clientID = "clientID-" + parseInt(Math.random() * 100);
//    window.fileouput =fileouput;
//    window.topico=topico;
//
//    // Print output for the user in the messages div
//    document.getElementById(fileouput).innerHTML += 'Connecting to: ' + host + ' on port: ' + port + '\n';
//    document.getElementById(fileouput).innerHTML += 'Using the following client value: ' + clientID + '\n';
//
//    // Initialize new Paho client connection
//    clientalarma = new Paho.MQTT.Client(host,port, clientID);
//    var text = fileouput;
//    // Set callback handlers
//    clientalarma.onConnectionLost = onConnectionLost;
//    clientalarma.onMessageArrived = onMessageArrived;
//
//	  var options = {
//	    useSSL: ssl,
//	    userName: user,
//	    password: pass,
//	    onSuccess:onConnect45
//	  }
//	  clientalarma.connect(options);
//
//}
//
//function onConnect45() {
//    document.getElementById(fileouput).innerHTML += 'Subscribing to: ' + topico + '\n';
//    clientalarma.subscribe(topico);
//}
//
//function onConnectionLost45(responseObject) {
//    var text = fileouput;
//    document.getElementById(fileouput).innerHTML += 'Connection lost'+'\n';
//    if (responseObject.errorCode !== 0) {
//        document.getElementById(fileouput).innerHTML += 'ERROR: ' + responseObject.errorMessage + '\n';
//    }
//}
//
//function startDisconnect45() {
//	clientalarma.disconnect();
//    document.getElementById(fileouput).innerHTML += 'Disconnected'+'\n';
//}
//
//function updateScroll45() {
//    var element = document.getElementById(fileouput);
//    element.scrollTop = element.scrollHeight;
//}
