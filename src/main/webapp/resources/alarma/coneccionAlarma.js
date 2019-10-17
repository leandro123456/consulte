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
	console.log("************** este es el id del mensaje de status: "+ iddevice);
	console.log("************** este es el cuerpo del status: "+ mensajerecibido)
	if(mensajerecibido =="online"){
		updateEstado45("spanestado"+iddevice, "online","statussonoff");
		if(document.getElementById("ready_icon"+iddevice)!= null)
			document.getElementById("ready_icon"+iddevice).style.color = "green"
	}if(mensajerecibido =="offline"){
		updateEstado45("spanestado"+iddevice, "offline", "statussonoff");
		if(document.getElementById("ready_icon"+iddevice)!= null)
			document.getElementById("ready_icon"+iddevice).style.color = "red"
	}if(mensajerecibido =="disconnected"){
		updateEstado45("spanestado"+iddevice, "online", "statussonoff");
		if(document.getElementById("ready_icon"+iddevice)!= null)
			document.getElementById("ready_icon"+iddevice).style.color = "yellow"
	}if(mensajerecibido !="disconnected" && mensajerecibido !="online" && mensajerecibido !="offline"){
		console.log("el mensaje que se recibio es invalido: "+ mensajerecibido);
	}
}



/** comportamiento cuando recibe un mensaje*/
function onMessageArrivedAlarma(message) {
	console.log("LLEGO UN MENSAJE DE LA ALARMA: "+message.destinationName+"; contenido: "+  message.payloadString);
	var contenido= message.payloadString;
	var topico = message.destinationName;
	var dataObj = null;
	if(message.destinationName.includes("/Status")){
		informarstatus45(message.destinationName, message.payloadString)
	}
	if (topico.includes("activePartition")){
		console.log("particion activa!!!!!!!!!!!!!!!!!!!!!"+ message.payloadString);
		iddevice = topico.substring(0,topico.search("/activePartition"));
		console.log("particion activa!!!!!!!!!!!!!!!!!!!!!"+ iddevice);
		 var numparticion = document.getElementById("particiones"+iddevice);
		 console.log("particion activa!!!!!!!!!!!!!!!!!!!!!"+ numparticion);
		 numparticion.innerHTML = message.payloadString;
	}
	else if(topico.includes("Partition")){
		var iddevice = topico.substring(0,topico.search("/Partition"));
		var numparticion = topico.substring(topico.search("Partition")).replace("Partition","");
		var numdisplay = document.getElementById("particiones"+iddevice).innerHTML;
		console.log("este es el numero de particion en el display: "+ numdisplay +"; num de particion: "+ numparticion);
		if(numdisplay=="" || numdisplay == numparticion){
			//como el display apunta a la particion correcta veo el estado
			if(contenido=="disarmed"){
				document.getElementById("ac_icon"+iddevice).style.color = "yellow";
				document.getElementById("armed_icon"+iddevice).style.color = "grey";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = contenido;
			}else if(contenido == "armed_home"){
				document.getElementById("armed_icon"+iddevice).style.color = "green";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Armed in Home";
			}else if(contenido =="armed_away"){
				document.getElementById("armed_icon"+iddevice).style.color = "green";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Armed Away";
			}else if(contenido =="pending"){
				document.getElementById("trouble_icon"+iddevice).style.color = "yellow";
				document.getElementById("armed_icon"+iddevice).style.color = "grey";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Pending";
			}else if (contenido == "triggered"){			
				document.getElementById("trouble_icon"+iddevice).style.color = "red";
				document.getElementById("armed_icon"+iddevice).style.color = "red";
				document.getElementById("ac_icon"+iddevice).style.color = "red";
				
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Triggered";
			}else{
				//como no lo reconoce lo muestra en el segundo display
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = contenido;
			}
		}
	}
	else if(topico.includes("Zone")){
		console.log("llego informacion de una zona: " +topico+"; "+contenido);
		var zona= topico.substring(topico.search("/Zone")).replace("/Zone","");
		var serial= topico.substring(0,topico.search("/Zone")); 
//		if(){
//			
//		}else{
			if(contenido == "1")
				document.getElementById("zone_"+zona+"_"+serial).style.color = "blue"; 
			else if (contenido =="0")
				document.getElementById("zone_"+zona+"_"+serial).style.color = "#858796";
//		}
		
	}
}
/** comportamiento cuando recibe un mensaje*/

function cargarZonas(serialZonas){
	for (i = 0; i < serialZonas.length; i++) { 
		var serial= serialZonas[i];  
		var urlsendInformation = $(location).attr('pathname') + "/obtainzone/"+serial;
			$.ajax({ url : urlsendInformation,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
					var maximo= data.maximo;
					for(j=0; j<maximo; j++){
						
					}
				}});
			
		}
	

	
}


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
