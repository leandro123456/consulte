/** inicio sonoff**/
function startConnectSonoff(host,port,ssl,user,pass, topico) {
	console.log("llego sonoff");
    // Generate a random client ID
    clientID = "clientID-" + parseInt(Math.random() * 100);
    window.topico=topico;

    // Print output for the user in the messages div
  //  document.getElementById(fileouput).innerHTML += 'Connecting to: ' + host + ' on port: ' + port + '\n';
  //  document.getElementById(fileouput).innerHTML += 'Using the following client value: ' + clientID + '\n';

    // Initialize new Paho client connection
    client = new Paho.MQTT.Client(host,port, clientID);
    if ( !client.isConnected()) {
       	console.log("fallo la conexion");
    }else {
    	console.log("conecto a :" + client);
    }
    // Set callback handlers
    client.onConnectionLost = onConnectionLostSonoff;
    client.onMessageArrived = onMessageArrivedSonoff;

	  var options = {
	    useSSL: ssl,
	    userName: user,
	    password: pass,
	    onSuccess:onConnectsonoff//,
	//    onFailure:doFail
	  }
  client.connect(options);
}

function onConnectsonoff() {
	client.subscribe("RConfig/debug");
	console.log('Subscribing to: ' + "RConfig/debug" + 'primero el de debug');
	
	client.subscribe(topico);
	console.log('Subscribing to: ' + topico + 'segundo');
    
	client.subscribe("DSC010000000001/dsc/Get/Partition1");
    console.log('Subscribing to: ' + "DSC010000000001/dsc/Get/Partition1  TAMBIEN");
    
}

function onConnectionLostSonoff(responseObject) {
    //var text = fileouput;
    //document.getElementById(fileouput).innerHTML += 'Connection lost'+'\n';
    console.log('Connection lost');
    if (responseObject.errorCode !== 0) {
      //  document.getElementById(fileouput).innerHTML += 'ERROR: ' + responseObject.errorMessage + '\n';
    	console.log('ERROR: ' + responseObject.errorMessage);
    }
}


//Called when a message arrives
function onMessageArrivedSonoff(message) {
    console.log("onMessageArrived: " + message.payloadString);
	var inputAll= message.payloadString;

	try {	
	
	var dataObj = JSON.parse(inputAll)
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
