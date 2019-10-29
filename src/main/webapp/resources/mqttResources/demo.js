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



//funcion para actualizar el frase dentro de todos los elementos en la vista
function updateEstado(id, valor,elemento) 
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
function informarstatus(topicorecibido, mensajerecibido){
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

/** fin sonoff*/

/** inicio sensor temperatura*/


//funcion para animar BARRA de temperatura
//function animateprogress (id, val){			
//	var fpAnimationFrame = getRequestAnimationFrame();   
//	var i = 0.0;
//	var animacion = function () {
//	if (i<=val) 
//		{
//			var valorr = i+"%";
//			var titulo = id;
//			document.getElementById(id).style.width = valorr;
//			var paragraph = document.getElementById(titulo);
////			paragraph.innerHTML = id+"<span class='float-right'>"+val+"</span>";     /* <---- Incremento el porcentaje y lo muestro en la etiqueta span */
//			i++;
//			fpAnimationFrame(animacion);          /* <------------------ Mientras que el contador no llega al porcentaje fijado la función vuelve a llamarse con fpAnimationFrame     */
//		}
//										
//	}
//
//		fpAnimationFrame(animacion);
//				
//}

/** fin temperatura*/

/** comportamiento cuando recibe un mensaje*/
function onMessageArrivedSonoff(message) {
	console.log("en ESTE CASO NO DEBERIA SER TOMADA COMO NOTIFICACION: "+ message.destinationName.includes("cmd"));
	console.log("LLEGO UN MENSAJE: "+message.destinationName+"; contenido: "+  message.payloadString);
	
	
	if(message.destinationName.includes("/state")){
		console.log("********************************************* ESTE ES EL STATUS");
		obtenerIconoNivelS(message.payloadString);
	}else{
		console.log("***********************************  NO TIENE STATE");
	}
	
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
	
	if(!message.destinationName.includes("/swcmd") && !message.destinationName.includes("/cmd") && !message.destinationName.includes("/Status") && (!inputAll.includes("tempC") && !inputAll.includes("hum"))){
			dataObj = JSON.parse(inputAll);
		if(dataObj.SW1 != null && dataObj.SW1=="ON"){
			var deviceserial = "boton1"+dataObj.deviceId;
			updateEstado(deviceserial,"yes","botonsonoff");
			var barraho= "sonofftimer1"+dataObj.deviceId;
			var barranum= "span1"+dataObj.deviceId;
			animatevar(barraho,barranum,dataObj.PB1TTO);
		}if(dataObj.SW1 != null && dataObj.SW1=="OFF"){
			var deviceserial = "boton1"+dataObj.deviceId;
			updateEstado(deviceserial,"no","botonsonoff");
		}if(dataObj.SW2 != null && dataObj.SW2=="ON"){
			var deviceserial = "boton2"+dataObj.deviceId;
			updateEstado(deviceserial,"yes","botonsonoff");
			var barraho= "sonofftimer2"+dataObj.deviceId;
			var barranum= "span2"+dataObj.deviceId;
			animatevar(barraho,barranum,dataObj.PB2TTO);
		}if(dataObj.SW2 != null && dataObj.SW2=="OFF"){
			var deviceserial = "boton2"+dataObj.deviceId;
			updateEstado(deviceserial,"no","botonsonoff");
		}
	}
}

/** comportamiento cuando recibe un mensaje*/

//analizar la señal y elegir icono
function obtenerIconoNivelS(informacion){
	console.log("llego la informacion: "+informacion);
	var obj = JSON.parse(informacion);
	var serialId=obj.deviceId;
	var valor=obj.dBm *(-1);
	console.log("**************************** llego a la signal: "+ valor + "serial: "+ serialId); //img_signal_PSWS10000000001
	if(valor>90)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/WSb.png';
	if(valor>75 && valor<91)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/1bb.png';
	else if(valor>60 && valor<76)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/2bb.png';
	else if(valor>45 && valor<61)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/3bb.png';
	else if(valor<46)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/4bb.png';

}


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
