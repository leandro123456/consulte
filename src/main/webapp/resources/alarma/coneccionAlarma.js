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
      		useSSL: false,
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
	console.log("este es el id del mensaje de status: "+ iddevice);
	console.log("este es el cuerpo del status: "+ mensajerecibido)
	if(mensajerecibido =="online"){
		updateEstado("spanestado"+iddevice, "online","statussonoff");
	}if(mensajerecibido =="offline"){
		updateEstado("spanestado"+iddevice, "offline", "statussonoff");
	}if(mensajerecibido =="disconnected"){
		updateEstado("spanestado"+iddevice, "online", "statussonoff");
	}if(mensajerecibido !="disconnected" && mensajerecibido !="online" && mensajerecibido !="offline"){
		console.log("el mensaje que se recibio es invalido: "+ mensajerecibido);
	}
}



/** comportamiento cuando recibe un mensaje*/
function onMessageArrivedAlarma(message) {
	console.log("LLEGO UN MENSAJE DE LA ALARMA: "+message.destinationName+"; contenido: "+  message.payloadString);
	var inputAll= message.payloadString;	
	var dataObj = null;
	if(message.destinationName.includes("/Status")){
		informarstatus(message.destinationName, message.payloadString)
	}
	if(inputAll.includes("tempC") && inputAll.includes("hum")){
		dataObj = JSON.parse(inputAll);
		var serial =dataObj.deviceId;
		console.log("llego un mensaje de temperatura, cuerpo:  "+ dataObj);
		if(dataObj.hum != null && document.getElementById("humedad"+serial)!= null)
			animatevar("barrahum"+serial,"humedad"+serial,dataObj.hum);
		
		if(dataObj.tempC != null && document.getElementById("temperaturac"+serial)!= null)
			animatevar("barratempc"+serial,"temperaturac"+serial,dataObj.tempC);
		
		if(dataObj.hiC != null && document.getElementById("sensacionc"+serial)!= null)
			animatevar("barrasensc"+serial,"sensacionc"+serial,dataObj.hiC);
		
		if(dataObj.tempF != null && document.getElementById("temperaturaf"+serial)!= null)
			animatevar("barratempf"+serial,"temperaturaf"+serial,dataObj.tempF);
		
		if(dataObj.hiF != null && document.getElementById("sensacionf"+serial)!= null)
			animatevar("barrasensf"+serial,"sensacionf"+serial,dataObj.hiF);
	}
//	else{
//			dataObj = JSON.parse(inputAll);
//		if(dataObj.SW1 != null && dataObj.SW1=="ON"){
//			var deviceserial = "boton1"+dataObj.deviceId;
//			updateEstado(deviceserial,"yes","botonsonoff");
//			var barraho= "sonofftimer1"+dataObj.deviceId;
//			var barranum= "span1"+dataObj.deviceId;
//			animatevar(barraho,barranum,dataObj.PB1TTO);
//		}if(dataObj.SW1 != null && dataObj.SW1=="OFF"){
//			var deviceserial = "boton1"+dataObj.deviceId;
//			updateEstado(deviceserial,"no","botonsonoff");
//		}if(dataObj.SW2 != null && dataObj.SW2=="ON"){
//			var deviceserial = "boton2"+dataObj.deviceId;
//			updateEstado(deviceserial,"yes","botonsonoff");
//			var barraho= "sonofftimer2"+dataObj.deviceId;
//			var barranum= "span2"+dataObj.deviceId;
//			animatevar(barraho,barranum,dataObj.PB2TTO);
//		}if(dataObj.SW2 != null && dataObj.SW2=="OFF"){
//			var deviceserial = "boton2"+dataObj.deviceId;
//			updateEstado(deviceserial,"no","botonsonoff");
//		}
//	}
}
/** comportamiento cuando recibe un mensaje*/




// Called after form input is processed
function startConnect45(host,port,ssl,user,pass,fileouput, topico) {
    // Generate a random client ID
    clientID = "clientID-" + parseInt(Math.random() * 100);
    window.fileouput =fileouput;
    window.topico=topico;

    // Print output for the user in the messages div
    document.getElementById(fileouput).innerHTML += 'Connecting to: ' + host + ' on port: ' + port + '\n';
    document.getElementById(fileouput).innerHTML += 'Using the following client value: ' + clientID + '\n';

    // Initialize new Paho client connection
    clientalarma = new Paho.MQTT.Client(host,port, clientID);
    var text = fileouput;
    // Set callback handlers
    clientalarma.onConnectionLost = onConnectionLost;
    clientalarma.onMessageArrived = onMessageArrived;

	  var options = {
	    useSSL: ssl,
	    userName: user,
	    password: pass,
	    onSuccess:onConnect45
	  }
	  clientalarma.connect(options);

}

function onConnect45() {
    document.getElementById(fileouput).innerHTML += 'Subscribing to: ' + topico + '\n';
    clientalarma.subscribe(topico);
}

function onConnectionLost45(responseObject) {
    var text = fileouput;
    document.getElementById(fileouput).innerHTML += 'Connection lost'+'\n';
    if (responseObject.errorCode !== 0) {
        document.getElementById(fileouput).innerHTML += 'ERROR: ' + responseObject.errorMessage + '\n';
    }
}

function startDisconnect45() {
	clientalarma.disconnect();
    document.getElementById(fileouput).innerHTML += 'Disconnected'+'\n';
}

function updateScroll45() {
    var element = document.getElementById(fileouput);
    element.scrollTop = element.scrollHeight;
}
