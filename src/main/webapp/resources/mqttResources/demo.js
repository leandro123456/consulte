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
