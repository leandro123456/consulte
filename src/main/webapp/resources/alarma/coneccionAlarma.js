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
	if(mensajerecibido =="online"){
		updateEstado45("spanestado"+iddevice, "online","statussonoff");
		//if(document.getElementById("ready_icon"+iddevice)!= null)
			//document.getElementById("ready_icon"+iddevice).style.color = "green"
	}if(mensajerecibido =="offline"){
		updateEstado45("spanestado"+iddevice, "offline", "statussonoff");
		//if(document.getElementById("ready_icon"+iddevice)!= null)
			//document.getElementById("ready_icon"+iddevice).style.color = "red"
	}if(mensajerecibido =="disconnected"){
		updateEstado45("spanestado"+iddevice, "online", "statussonoff");
		//if(document.getElementById("ready_icon"+iddevice)!= null)
			//document.getElementById("ready_icon"+iddevice).style.color = "yellow"
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
		iddevice = topico.substring(0,topico.search("/activePartition"));
		 var numparticion = document.getElementById("particiones"+iddevice);
		 console.log("Particion activa-> iddevice: "+iddevice+"; particion: "+ numparticion);
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
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				document.getElementById("armed_icon"+iddevice).style.color = "grey";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Disarmed";
			}else if(contenido == "armed_home"){
				document.getElementById("armed_icon"+iddevice).style.color = "green";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Armed Home";
			}else if(contenido =="armed_away"){
				document.getElementById("armed_icon"+iddevice).style.color = "green";
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Armed Away";
			}else if(contenido =="pending"){
				document.getElementById("trouble_icon"+iddevice).style.color = "grey";
				document.getElementById("armed_icon"+iddevice).style.color = "yellow";
				document.getElementById("ac_icon"+iddevice).style.color = "grey";
				document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "blue";
				var spanStatus = document.getElementById("second_line"+iddevice);
				spanStatus.firstChild.data = "Pending";
			}else if (contenido == "triggered"){			
				document.getElementById("trouble_icon"+iddevice).style.color = "red";
				document.getElementById("armed_icon"+iddevice).style.color = "red";
				document.getElementById("ac_icon"+iddevice).style.color = "red";
				document.getElementById("virtual_lcd_"+iddevice).style.backgroundColor = "red";
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
		maximaZona(serial, zona,contenido);
		pintarBotonDeZona(contenido,zona,serial);
		
	}
	else if(topico.includes("keepAlive")){
		console.log("emnsaje de keepAlive: "+ contenido);
		obtenerIconoNivelSAlarma(contenido);
	}
}

//analizar la señal y elegir icono
function obtenerIconoNivelSAlarma(informacion){
	var obj = JSON.parse(informacion);
	var serialId=obj.deviceID;
	var valor=obj.dBm *(-1);
	if(valor>90)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/WS.png';
	if(valor>75 && valor<91)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/1b.png';
	else if(valor>60 && valor<76)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/2b.png';
	else if(valor>45 && valor<61)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/3b.png';
	else if(valor<46)
		document.getElementById('img_signal_'+serialId).src='resources/mqttResources/imgsignal/4b.png';

}

//verifica si la zona recibida es mayor al maximo que ya existe
function maximaZona(serial, zona,contenido){
	var urlsendInformation = $(location).attr('pathname') + "/obtainzone/"+serial+"/"+zona+"/"+contenido;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			if(data.fueactualizado){
				console.log("inicio: "+data.inicio+1);
				console.log("fin: "+data.fin);
				for(var j=data.inicio+1; j<=data.fin; j++){
					console.log("boton: "+ "zone_"+j+"_"+serial);
					document.getElementById("zone_"+j+"_"+serial).style.display = 'inline';
				}
			}
		}});
}

//funcion para pintar las zonas si tienen alarma
function pintarBotonDeZona(contenido,zona,serial){
	if(contenido == "1")
		document.getElementById("zone_"+zona+"_"+serial).style.color = "blue"; 
	else if (contenido =="0")
		document.getElementById("zone_"+zona+"_"+serial).style.color = "#858796";
}




//caraga de zonas al inicio
function cargarZonas(serialZonas){
	var seriales = serialZonas;
	console.log("estos son los serials: "+ seriales +" ;cantidad: "+ seriales.length);
	seriales.forEach(cargazonaEfectiva);
}

function cargazonaEfectiva(item, index){
	var urlsendInformation = $(location).attr('pathname') + "/obtainmaxzone/"+item;
	$.ajax({ url : urlsendInformation,
		contentType: "application/json",
		dataType: 'json',
		success: function(data){
			var maximo= data.result;
			console.log("obtuvo las zonas: "+ maximo);
				for(var j=1; j<maximo+1; j++){
					
					document.getElementById("zone_"+j+"_"+item).style.display = 'inline';
			}
			var todaszonasapagadas = data.zonasapagadas;
			if(todaszonasapagadas)
				document.getElementById("ready_icon"+item).style.color = "green";
			else
				document.getElementById("ready_icon"+item).style.color = "grey";
		}});
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
