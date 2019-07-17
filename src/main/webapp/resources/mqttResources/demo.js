/** inicio sonoff**/
function startConnectSonoff(host,port,ssl,user,pass, topicos) {
	console.log("llego sonoff");
    clientID = "clientID-" +parseInt(Math.random() * 100)+"loco" +parseInt(Math.random() * 100);
    window.topicos=topicos;

    var port1=parseInt(port);
    client = new Paho.MQTT.Client(host,port1, clientID);

    client.onConnectionLost = onConnectionLostSonoff;
    client.onMessageArrived = onMessageArrivedSonoff;
    
    if(client.isConnected()==false){
    	client.connect({
      		onSuccess: onConnectsonoff,
      		onFailure: onConnectionLostSonoff,
      		keepAliveInterval: 10,
      		userName: user,
      		useSSL: false,
      		password: pass	
      	});
	  }
	  else{
		  console.log("ya esta conectada!!");
		  Connected121();
	  }   
}

function myFunction(item, index) { 
    client.subscribe(item);
    console.log('Subscribing to: ' + item+ 'primero el de debug');
}

function onConnectsonoff() {
	 topicos.forEach(myFunction);
}

function onConnectionLostSonoff(responseObject) {
    console.log('Connection lost');
    if (responseObject.errorCode !== 0) {
    	console.log('ERROR: ' + responseObject.errorMessage);
    }
}



//animar la barra del timer del sonoff
function animatevar (id,spanid, val){		
		var valorr = val+"%";
		var titulo = id;
		document.getElementById(id).style.width = valorr;
		var span = document.getElementById(spanid);
		if(span.textContent!= 1){
			span.innerHTML = '';
			span.appendChild(document.createTextNode(val));
		}				
}

//mostrar el status del dispositivo que esta reportando
function informarstatus(topicorecibido, mensajerecibido){
	var iddevice = topicorecibido.replace("/Status","");
	console.log("este es el id que me llego: "+ iddevice);
	if(mensajerecibido =="online"){
		console.log ("es online");
		updateEstado("spanestado"+iddevice, "online");
	}if(mensajerecibido =="offline"){
		console.log("es offline");
		updateEstado("spanestado"+iddevice, "offline");
	}if(mensajerecibido =="disconnected"){
		console.log("esta disconnected");
		updateEstado("spanestado"+iddevice, "online");
	}if(mensajerecibido !="disconnected" && mensajerecibido !="online" && mensajerecibido !="offline"){
		console.log("el mensaje que se recibio es invalido: "+ mensajerecibido);
	}
}

//Called when a message arrives
function onMessageArrivedSonoff(message) {
    console.log("LLEGO UN MENSAJE: "+message.destinationName+"; contenido: "+  message.payloadString);
	var inputAll= message.payloadString;
	
	if(message.destinationName.includes("/Status")){
		informarstatus(message.destinationName, message.payloadString)
	}
	else{
	try {	
	var dataObj = JSON.parse(inputAll)
	console.log("device: "+ dataObj.deviceId);
	if(dataObj.SW1 != null && dataObj.SW1=="ON"){
		var deviceserial = "boton1"+dataObj.deviceId;
		console.log("sw1 on: "+ deviceserial);
		toggleText(deviceserial,"yes");
		console.log("tiempo en uno: "+ dataObj.PB1TTO);
		var barraho= "sonofftimer1"+dataObj.deviceId;
		var barranum= "span1"+dataObj.deviceId;
		animatevar(barraho,barranum,dataObj.PB1TTO);
	}if(dataObj.SW1 != null && dataObj.SW1=="OFF"){
		var deviceserial = "boton1"+dataObj.deviceId;
		console.log("sw1 off: " +deviceserial);
		toggleText(deviceserial,"no");
	}if(dataObj.SW2 != null && dataObj.SW1=="ON"){
		var deviceserial = "boton2"+dataObj.deviceId;
		console.log("sw2: "+ dataObj.SW2);
		toggleText(deviceserial,"yes");
		console.log("tiempo en dos: "+ dataObj.PB2TTO);
		var barraho= "sonofftimer2"+dataObj.deviceId;
		var barranum= "span2"+dataObj.deviceId;
		animatevar(barraho,barranum,dataObj.PB2TTO);
	}if(dataObj.SW2 != null && dataObj.SW1=="OFF"){
		var deviceserial = "boton2"+dataObj.deviceId;
		console.log("sw2: "+ dataObj.SW2);
		toggleText(deviceserial,"no");
	}
	
	var state = dataObj.tempC; 
    //document.getElementById("messages").innerHTML += '<span>Topic: ' + message.destinationName + '  | ' + inputAll + '</span><br/>';
	console.log("destino: "+message.destinationName);

	var gaugeDataHum = {'data': dataObj.hum}
	var gaugeDataTempC = {'data': dataObj.tempC}
	var gaugeDataHiC = {'data': dataObj.hiC}
	var gaugeDataTempF = {'data': dataObj.tempF}
	var gaugeDataHiF = {'data': dataObj.hiF}

	console.log("objeto recibido: "+dataObj);
//	var progreso =  animateprogress("humedad",dataObj.hum);
//	var progreso =  animateprogress("temperaturac",dataObj.tempC);
//	var progreso =  animateprogress("sensacionc",dataObj.hiC);
//	var progreso =  animateprogress("temperaturaf",dataObj.tempF);
//	var progreso =  animateprogress("sensacionf",dataObj.hiF);
	// create a chart and set options
	// note that via the c3.js API we bind the chart to the element with id equal to chart1
	
	}catch(err) {
		 console.log(err.message);
		}
	
	}
}
/** fin sonoff**/

// Called after form input is processed
function startConnect(host,port,ssl,user,pass,fileouput, topico) {
    // Generate a random client ID
    clientID = "clientID-" + parseInt(Math.random() * 100);
    window.fileouput =fileouput;
    window.topico=topico;

    // Print output for the user in the messages div
    document.getElementById(fileouput).innerHTML += 'Connecting to: ' + host + ' on port: ' + port + '\n';
    document.getElementById(fileouput).innerHTML += 'Using the following client value: ' + clientID + '\n';

    // Initialize new Paho client connection
    client = new Paho.MQTT.Client(host,port, clientID);
    var text = fileouput;
    // Set callback handlers
    client.onConnectionLost = onConnectionLost;
    client.onMessageArrived = onMessageArrived;

	  var options = {
	    useSSL: ssl,
	    userName: user,
	    password: pass,
	    onSuccess:onConnect//,
	//    onFailure:doFail
	  }
  client.connect(options);

}
//Called when the client connects
function onConnect() {
    // Fetch the MQTT topic from the form
//    topic = document.getElementById(topico).value;

    // Print output for the user in the messages div
    document.getElementById(fileouput).innerHTML += 'Subscribing to: ' + topico + '\n';

    // Subscribe to the requested topic
    client.subscribe(topico);
}

// Called when the client loses its connection
function onConnectionLost(responseObject) {
    var text = fileouput;
    document.getElementById(fileouput).innerHTML += 'Connection lost'+'\n';
    if (responseObject.errorCode !== 0) {
        document.getElementById(fileouput).innerHTML += 'ERROR: ' + responseObject.errorMessage + '\n';
    }
}

// Called when the disconnection button is pressed
function startDisconnect() {
    client.disconnect();
    document.getElementById(fileouput).innerHTML += 'Disconnected'+'\n';
}


// Updates #messages div to auto-scroll
function updateScroll() {
    var element = document.getElementById(fileouput);
    element.scrollTop = element.scrollHeight;
}
