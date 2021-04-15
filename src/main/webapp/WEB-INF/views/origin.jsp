<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>cDash</title>

	<link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
<!-- 	<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'> -->


	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>     
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/mqttRecibirMensajes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/alarma/coneccionAlarma.js" />'></script>
	<script src='<c:url value="/resources/alarma/VistaAlarmaV2.js" />'></script>
	<script src='<c:url value="/resources/alarma/VistaAlarma.js" />'></script>
	<script src='<c:url value="/resources/pulsador/descargaImagenes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>
	<script src='<c:url value="/resources/pulsador/cargaPulsadores.js" />'></script>
	<script src='<c:url value="/resources/deviceResources/cargarDevice.js" />'></script>
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<script src='<c:url value="resources/vendor/bootstrap/js/bootstrap.bundle.js" />'></script>

	<!-- WEBSOCKET VIA NODE -->
	<!--<script src="http://localhost:8081/socket.io/socket.io.js"></script> -->
	<!--<script type="text/javascript" src="/mainSocket.js"></script> -->
	
	
	
<!-- 	<script src="/webjars/jquery/jquery.min.js"></script> -->
<!--     <script src="/webjars/sockjs-client/sockjs.min.js"></script> -->
<!--     <script src="/webjars/stomp-websocket/stomp.min.js"></script> -->
	<!-- WEB SOCKET VIA SPRING -->
<%-- 	<script src='<c:url value="/resources/websocketspring/WebSocketSpringImp.js" />'></script> --%>
<%-- 	<script src='<c:url value="/resources/websocketspring/sockjs.min.js" />'></script> --%>
<%-- 	<script src='<c:url value="/resources/websocketspring/stomp.min.js" />'></script> --%>
	

	  <!-- Google Fonts -->
	  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	
	  <!-- Vendor CSS Files -->
	  <link href="resources/mentor/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
	  <link href="resources/mentor/assets/vendor/aos/aos.css" rel="stylesheet">
	  <link href="resources/mentor/miSwitch.css" rel="stylesheet">

  	<!-- Template Main CSS File -->
	<link href="resources/mentor/assets/css/style.css" rel="stylesheet">
	<link href='<c:url value="/resources/mqttResources/estiloalarma.css" />' rel="stylesheet" type="text/css">	
	
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/firebase-messaging-sw.js" />'></script>
	<script src='<c:url value="/resources/firebase-messaging-ws.js" />'></script>
	
	
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-6SSYQD4466');
</script>
<script type="text/javascript">
	$(document).ready(function() {
		console.log("INICIO DE APLICACIONES")
// 		setConnected(true);
		
// 		cargarParticionesAlarmas(${alarmaSerial});
// 		cargarZonas(${alarmaSerial});
		
		cargarParticionesAlarmasV2(${alarmaSerial});
		cargarZonasV2(${alarmaSerial});
		
		if(${serialpulsador}.length >0){
			cargarColorbotones(${serialpulsador});
		}
		setTimeout(iniciaConexion, 2000);
// 		cambio();
	});
</script>


<script type="text/javascript">
function getParameterByName(name) {
	var locacion = location.search;
	var texto=locacion.replace("?"+name+"=","");
	while(texto.includes("+")){
		texto=texto.replace("+"," ");
	}
    return texto;
}	
</script>

<script type="text/javascript">
$("#serialnumber").blur(function() {
	var valorSerial = document.getElementById("serialnumber").value;
      evaluarNumeroDeSerie(valorSerial);
    });
</script>

<script type="text/javascript">
function iniciaConexion(){
	console.log("dispositivos: "+ ${cantidadSensores});
// 	if(${cantidadSensores}!=0){
// 		startConnectSonoff("mqtt.coiaca.com", 8081, false, "mqttusr","mqttpwd",${topicos});
// 	}
	console.log("dispositivos alarma: "+ ${cantidadAlarma})
	if(${cantidadAlarma}!=0){
	startConnectAlarma("${hostalarma}","${puertoalarma}",${sslalarma},"${usuarioalarma}","${passalarma}",${topicosalarmas});
	
	}
}
</script>
<script src='<c:url value="/resources/js/jquery.qrcode.min.js" />'></script>
<script type="text/javascript">
//jQuery("#demo").qrcode("url o algo de texto");
jQuery("#demo").qrcode({
 id: "mycanvas",
 width: 128,
 height: 128,
 text: "http://ourcodeworld.com"
});
</script>

</head>
  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="d-flex align-items-center" style="margin-left: 1em;margin-right: 1em;">

      <h1 class="logo mr-auto">
		<div class="logodash">
			<img alt="" src="resources/mentor/cdash/imagen/logoBig.png">
		</div>
	</h1>

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="active"><a href="/">Inicio</a></li>

          <li class="drop-down"><a href="">Dispositivos</a>
            <ul>
              <li><a href="<c:url value='/home/componentmyown'/>">Mis Dispositivos</a></li>
            </ul>
          </li>
          <li class="drop-down"><a href="">Usuario</a>
            <ul>
              <li>
              	<form role="form" action="<c:url value="/profileuser/userid"/>" method="get" enctype="multipart/form-data">
			        <a class="dropdown-item" href="javascript:;" onclick="parentNode.submit();">
			          Editar Usuario
                  	</a>
				</form>
			 </li>
            </ul>
          </li>
		  <li><a href="<c:url value='../ayuda.html' />">Ayuda</a></li>
		  <li>
		  	<a href="/login" data-toggle="modal" data-target="#logoutModal">
		  		Salir
		  	</a>
		  </li>
        </ul>
      </nav><!-- .nav-menu -->
      <a href="/home/newdeviceb" data-toggle="modal" data-target="#createDeviceModal" class="get-started-btn">Nuevo Dispositivo</a> 
    </div>
  </header>
  


<body>

	<div id="wrapper">	
		<div class="container-fluid">
			<c:if test="${param.msg != null}">
					<script type="text/javascript">
					var mens = getParameterByName('msg');
						swal({
							  title: mens,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/";', 5000);
					</script>
				</c:if>
				<c:if test="${not empty msg1}">
				 	<input type="hidden" id ="mensaje1" value="${msg1}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/";', 5000);
					</script>
				</c:if>	
		<div class="d-flex" style="position: relative;padding: 40px 0;"> </div>
		

		<div class="row" id="cargadora">
				<c:forEach items="${vistas}" var="vista">
                ${vista}
          	</c:forEach>
		</div>
	</div>
	</div>

	<jsp:include page="footer.jsp" />
	
	<div id="token" style="display: none;"></div>	
 
 <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-app.js"></script>
 <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-messaging.js"></script>  
 <script type="text/javascript">
   // Your web app's Firebase configuration
   var firebaseConfig = {
     apiKey: "AIzaSyAUrwGTRCz98u4Tg38iWtKKx-zJEKKH78M",
     authDomain: "cdash-1274d.firebaseapp.com",
     databaseURL: "https://cdash-1274d.firebaseio.com",
     projectId: "cdash-1274d",
     storageBucket: "cdash-1274d.appspot.com",
     messagingSenderId: "368274022300",
     appId: "1:368274022300:web:95be4383f5eef61b0ff259"
   };
   // Initialize Firebase
   firebase.initializeApp(firebaseConfig);
   const messaging = firebase.messaging();
   messaging
       .requestPermission()
       .then(function () {
           console.log("Notification permission granted.");
           return messaging.getToken()
       })
       .then(function(token) {
           console.log("token is : " + token);
           enviarToken(token);
       })
       .catch(function (err) {
           console.log("Unable to get permission to notify."+ err);
       });
   messaging.onMessage(function(payload) {
		swal({
		  title: payload.notification.title,
		  text: payload.notification.body,
		  timer: 3000,
		  buttons: false,
		  });
		});


// Callback fired if Instance ID token is updated.
messaging.onTokenRefresh(() => {
  messaging.getToken().then((refreshedToken) => {
    console.log('Token refreshed.');
    setTokenSentToServer(false);
    sendTokenToServer(refreshedToken);
  }).catch((err) => {
    console.log('Unable to retrieve refreshed token ', err);
    showToken('Unable to retrieve refreshed token ', err);
  });
});
 </script>
 
 <script type="text/javascript">
 function enviarToken(token){
	 console.log("username encontrado: "+ '${pageContext.request.userPrincipal.name}')
	 var enc = window.btoa('${pageContext.request.userPrincipal.name}');
	 var urlsendInformation = $(location).attr('pathname') + "/enviartoken/"+token+"/"+enc;
		$.ajax({ url : urlsendInformation,
			contentType: "application/json",
			dataType: 'json',
			success: function(data){
				console.log("exitoso");
			}			
	});
 }
 </script> 
</body>



<!-- Crear device con Modal -->

<div class="modal fade" id="createDeviceModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
    	<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Nuevo dispositivo</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          	<span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	<div id="infoGeneral1" style="display:inline">
<!-- 				<button data-dismiss="modal" class="btn btn-primary btn-lg btn-block" data-toggle="modal" href="#modalSoftware">Crear Dispositivo Software</button> -->
<!-- 				<p></p> -->
				<button data-dismiss="modal" data-toggle="modal"class="btn btn-primary btn-lg btn-block" href="#modalHardware">Crear Dispositivo Hardware</button>
				<p></p>
			</div>
		</div>
    </div>
  </div>
</div>
	
  <div class="modal fade" id="modalSoftware" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Nuevo dispositivo</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          	<span aria-hidden="true">×</span>
          </button>
        </div>
        <form role="form" action="<c:url value="home/create"/>" method="post">
        	<div class="modal-body">
					<div id="infoGeneralSoft" style="display:inline">
						<b>Alta Protero Virtal</b> 
						<p></p>
						<div class="row">
  							<div class="col-md-6">
								<b>Calle</b> 
								<input name="calle" id="calle" class="form-control" required>
								<p></p>
								<b>Piso</b> 
								<input name="piso" id="piso" class="form-control" >
								<p></p>
								<b>Pais</b> 
								<select id="pais" name="pais" class="form-control">
									<option value="none">Seleccione uno</option>
									  <script>
									  	var sel = document.getElementById('pais');
									  	var state = new Array("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antarctica", "Antigua_and_Barbuda",
									  			"Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
									  			"Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia_and_Herzegovina", "Botswana",
									  			"Brazil", "Brunei", "Bulgaria", "Burkina_Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape_Verde",
									  			"Central_African_Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo_Democratic_Republic",
									  			"Congo_Republic_of_the", "Costa_Rica", "Cote_dIvoire", "Croatia", "Cuba", "Cyprus", "Czech_Republic", "Denmark",
									  			"Djibouti", "Dominica", "Dominican_Republic", "East Timor", "Ecuador", "Egypt", "El_Salvador", "Equatorial_Guinea",
									  			"Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
									  			"Greece", "Greenland", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong_Kong",
									  			"Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
									  			"Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea_North", "Korea_South", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
									  			"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar",
									  			"Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall_Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia",
									  			"Moldova", "Mongolia", "Morocco", "Monaco", "Mozambique", "Namibia", "Nauru", "Nepal", "Netherlands", "New_Zealand",
									  			"Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Panama", "Papua_New_Guinea", "Paraguay", "Peru",
									  			"Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Samoa", "San_Marino", " Sao_Tome",
									  			"Saudi_Arabia", "Senegal", "Serbia_and_Montenegro", "Seychelles", "Sierra_Leone", "Singapore", "Slovakia", "Slovenia",
									  			"Solomon_Islands", "Somalia", "South_Africa", "Spain", "Sri_Lanka", "Sudan", "Suriname", "Swaziland", "Sweden",
									  			"Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad_and_Tobago",
									  			"Tunisia", "Turkey", "Turkmenistan", "Uganda", "Ukraine", "United_Arab_Emirates", "United_Kingdom", "United_States",
									  			"Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
									  	
										 for(var hi=0; hi<state.length; hi++){
											    var sel = document.getElementById("pais");
											    var option = document.createElement("option");
											    option.text = state[hi];
											    option.value = state[hi]; 
											    sel.add(option);
											  }	
									  </script>
								</select>
								<p></p>
								<b>Localidad</b> 
								<input name="localidad" id="localidad" class="form-control" >
							</div>
							<div class="col-md-6">
								<b>Numero</b> 
								<input name="numero" id="numero" class="form-control" required>
								<p></p>
								<b>Depto</b> 
								<input name="depto" id="depto" class="form-control">
								<p></p>
								<b>Provincia</b> 
								<input name="provincia" id="provincia" class="form-control" >
								<p></p>
								<b>Codigo Postal</b> 
								<input name="codpostal" id="codpostal" class="form-control" >
							</div>
							<p></p>
							<div class="col-md-12">
								<b>Tipo de Direccion</b> 
								<select id="tipodireccion" name="tipodireccion" class="form-control">
									<option value="none">Seleccione uno</option>
									<option value="casa">Casa</option>
									<option value="trabajo">Trabajo</option>
									<option value="otro">Otro</option>
								</select>
								<p></p>
							</div>
						</div>
					</div>
					<div id="infoFinalSoft" style="display:none">
						<h5>La configuración está lista. Precione Agregar Dispositivo para terminar.</h5>
						<p></p>
					</div>
					<div class="btn-group">
	          			<button id="botonAnteriorsof" style="display:none" class="btn btn-secondary" onclick="anteriorAnimacionSoft()" type="button">Volver</button>
	          			<button id="botonSiguientesof" class="btn btn-primary" onclick="siguienteAnimacionSoft()" type="button">Continuar</button>
	          		</div>
        	</div>
	        <div class="modal-footer">
	          	<button type="button" style="display:none" id="botoncancelarsof" class="btn btn-secondary"  data-dismiss="modal">Cancelar</button> 	
	          	<button type="submit" style="display:none" id="botonfinalizarsof" class="btn btn-primary">Agregar Dispositivo</button>
	        </div>
        </form>
      </div>
    </div>
  </div>
  
  
  <div class="modal fade" id="modalHardware" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Nuevo dispositivo</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          	<span aria-hidden="true">×</span>
          </button>
        </div>
        <form role="form" action="<c:url value="home/create"/>" method="post">
        	<div class="modal-body">

        		<div id="infoGeneral" style="display:inline">
					<b>Numero de Serie</b> 
					<input name="serialnumber" id="serialnumber" class="form-control" placeholder="DSC010000000999">
					<p></p>
					<b>Nombre del dispositivo</b> 
					<input name="namedevice" id="namedevice" class="form-control" placeholder="Mi dispositivo">
					<p></p>
					<b>Descripción</b> 
					<input name="descriptiondevice" id="descriptiondevice" class="form-control" placeholder="Mi descripción">
					<p></p>
				</div>
				<div id="infoDeducidaCoiaca" style="display:none">
					<h3>Información del dispositivo</h3>
					<div>
						<b>Marca</b> <select id="marcadevice"
							class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="coiaca">Coiaca</option>
							<option value="otro">Otro</option>
						</select>
					</div>
					<p></p>
					<div id="selectormodelo">
						<b>Modelo</b> 
						<select id="modelodevice" class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="WTHUSB">WTHUSB</option>
							<option value="PSWS1">PSWS1</option>
							<option value="PSWS2">PSWS2</option>
							<option value="BRDSC">BRDSC01</option>
							<option value="PS3S1">Sonoff</option>
						</select>
					</div>						
					<p></p>
					<div>
						<b>Tipo</b> 
						<select id="tipodevice" class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="termometro">Awarer</option>
							<option value="alarma">Bridge</option>
							<option value="sonoff">Power Stooge</option>
						</select>
					</div>
					<p></p>	
				</div>
				<div id="infoPropiaVista" style="display:none">
					<h3>Vista</h3>
					<div id="vistastermometro">
						<b>Vista de Termometro</b> 
						<select id="tipovistatermometro"
							name="tipovistatermometro" class="form-control" onchange="changeTipoVistaTermometro()">
							<option value="none">Seleccione uno</option>
<!-- 								<option value="watches">Relojes</option> -->
							<option value="bars">Barras</option>
						</select>
					</div>
					
					<p></p>
					<div id="cantidadswith">
						<b>Cantidad de Swiths</b> <select id="cantidadswiths"
							name="cantidadswiths" class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="one">Uno</option>
							<option value="two">Dos</option>
						</select>
					</div>
					<p></p>

					<div id="timerString" class="text-center">
						<a href="" class="btn btn-primary btn-rounded mb-6"
							data-toggle="modal" data-target="#modalTimerString">Agregar
							Timer String</a>
					</div>
					<div class="panel panel-primary" id="tabletimerstring">
						<div class="panel-heading">
							<p> </p>
							<h6 class="panel-title">Parametros del Timer String</h6>
						</div>style="display:none"
						<div class="panel-body">
							<table name="timerstringsonoff" class="table table-sm" id="dataTable">
									<tr>
										<th>Dias de la Semana</th>
										<th>Hora</th>
										<th>Accion</th>
										<th>Switch</th>
										<th>Accion Sobre Fila</th>
									</tr>
								<tbody id="contenidotablatimerstring">
								</tbody>
							</table>
						</div>
					</div>

					<div id="parametrostermometro">
						<b>Parametros del Termometro</b>
						<table class="table table-sm">
							<tbody>
								<tr class="tablain">
									<td>Humedad</td>
									<td><input type="checkbox" id="humedadtermometro"
										name="humedadtermometro" data-toggle="toggle"></td>
								</tr>
								<tr class="tablain">
									<td>Temperatura (Grados Centigrados)</td>
									<td><input type="checkbox" id="tempctermometro"
										name="tempctermometro" data-toggle="toggle"></td>
								</tr>
								<tr class="tablain">
									<td>Sensacion Termica (Grados Centigrados)</td>
									<td><input type="checkbox" id="sensacionctermometro"
										name="sensacionctermometro" data-toggle="toggle"></td>
								</tr>
								<tr class="tablain">
									<td>Temperatura (Grados Fahrenheit)</td>
									<td><input type="checkbox" id="tempftermometro"
										name="tempftermometro" data-toggle="toggle"></td>
								</tr>
								<tr class="tablain">
									<td>Sensacion Termica (Grados Fahrenheit)</td>
									<td><input type="checkbox" id="sensacionftermometro"
										name="sensacionftermometro" data-toggle="toggle"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="infoAvanzada" style="display:none">
					<h3>Configuración avanzada</h3>
						<div id="parametersConexion" onkeypress=checkPassword()>
							<h6>Los siguientes son los parámetros con los que su equipo ha sido configurado de fábrica. No los modifique si no está seguro de lo que está haciendo.</h6>
							<div class="form-group row ">
								<b>URL del Broker MQTT</b> 
									<input type="text"
									class="form-control form-control-user" id="iphostescuchar"
									placeholder="Hostname"> 
								<b>Puerto del Broker MQTT</b> 
									<input type="text"
									class="form-control form-control-user" id="portescuchar"
									placeholder="Number Port"> 
								<b>Nombre de usuario MQTT</b> 
									<input type="text" class="form-control form-control-user"
									id="userescuchar" placeholder="Nombre de usuario"> 
								<b>Contraseña MQTT</b>
									<input type="password" class="form-control form-control-user"
									id="passescuchar" placeholder="Contraseña"> 
								<b>Repetir contraseña MQTT</b> 
									<input type="password"
									class="form-control form-control-user" id="confirpassescuchar"
									placeholder="Confirmar Contraseña">
								<b>Prefijo de topicos de estados</b>
									<input type="text" class="form-control form-control-user "
									id="topiclisten" placeholder="Topico para recibir Informacion">
								<b>Topico de comandos</b>
									<input type="text" class="form-control form-control-user "
									id="topicwrite" placeholder="Topico para envio de Informacion"> 
							</div>
							
							<h6>Administración Remota</h6>
							<div class="form-group row ">
								<b>URL del Broker MQTT de Administración Remota</b> 
									<input type="text"
									class="form-control form-control-user"
									id="iphostescucharremote" placeholder="Hostname o direccion IP"> 
								<b>Puerto del Broker MQTT de Administración Remota</b>
									<input type="text" class="form-control form-control-user"
									id="portescucharremote" placeholder="Puerto"> 
								<b>Nombre de usuario MQTT de Administración Remota</b> 
									<input
									type="text" class="form-control form-control-user"
									id="userescucharremote" placeholder="Nombre de usuario"> 
								<b>Contraseña MQTT de Administración Remota</b>
									<input type="password" class="form-control form-control-user"
									id="passescucharremote" placeholder="Contraseña"> 
								<b>Confirmar contraseña MQTT de Administración Remota</b> 
									<input type="password"
									class="form-control form-control-user"
									id="confirpassescucharremote" placeholder="Confirmar Contraseña">
								<b>Topico de resultados de Administración Remota</b>
									<input type="text"
									class="form-control form-control-user " id="topiclistenremote"
									placeholder="Topico para recepcion de Respuestas"> 
								<b>Topico de comandos de Administración Remota</b>
									<input type="text"
									class="form-control form-control-user " id="topicwriteremote"
									placeholder="Topico para envio de Comando de Configuracion"> 
							</div>
						</div>
					</div>
					<div id="infoFinal" style="display:none">
						<h5>La configuración está lista. Precione Agregar Dispositivo para terminar.</h5>
					</div>				
				<div class="btn-group">
          			<button id="botonAnterior" style="display:none" class="btn btn-secondary" onclick="anteriorAnimacion()" type="button">Volver</button>
          			<button id="botonSiguiente" class="btn btn-primary" onclick="siguienteAnimacion()" type="button">Continuar</button>
          		</div>
        </div>
        <div class="modal-footer">
          	<button style="display:none" id="botoncancelar" type="button" class="btn btn-secondary"  data-dismiss="modal">Cancelar</button>
          	<input type="hidden" name="tipodevice" id="tipodevice1" />
<!--           	informacion de las vistas SONOFF-->
			<input type="hidden" name="timerstringsonoff" id="timerstringsonoff1" />
			<input type="hidden" name="cantidadswiths" id="cantidadswiths1" />
			<input type="hidden" name="tipovistatermometro" id="tipovistatermometro1" />
			<input type="hidden" name="humedadtermometro" id="humedadtermometro1" />
			<input type="hidden" name="tempctermometro" id="tempctermometro1" />
			<input type="hidden" name="sensacionctermometro" id="sensacionctermometro1" />
			<input type="hidden" name="tempftermometro" id="tempftermometro1" />
			<input type="hidden" name="sensacionftermometro" id="sensacionftermometro1" />
<!-- 				informacion de configuracion -->
			<input type="hidden" name="iphostescuchar" id="iphostescuchar1" />
			<input type="hidden" name="portescuchar" id="portescuchar1" />
			<input type="hidden" name="topiclisten" id="topiclisten1" />
			<input type="hidden" name="userescuchar" id="userescuchar1" />
			<input type="hidden" name="passescuchar" id="passescuchar1" />
			<input type="hidden" name="topicwrite" id="topicwrite1" />
			<input type="hidden" name="iphostescucharremote" id="iphostescucharremote1" />
			<input type="hidden" name="portescucharremote" id="portescucharremote1" />
			<input type="hidden" name="topiclistenremote" id="topiclistenremote1" />
			<input type="hidden" name="userescucharremote" id="userescucharremote1" />
			<input type="hidden" name="passescucharremote" id="passescucharremote1" />
			<input type="hidden" name="topicwriteremote" id="topicwriteremote1" />      	
          	<button type="submit" style="display:none" id="botonfinalizar" class="btn btn-primary">Agregar Dispositivo</button>
        </div>
        </form>
      </div>
    </div>
  </div>


	<div class="modal fade" id="modalTimerString" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Timer String</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">
					<div class="md-form mb-5">
						<form id="timerString">
							<div class="container">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="exampleFormControlSelect2">Dias de la Semana</label> <select id="dias" multiple="multiple" class="form-control"
												id="exampleFormControlSelect2">
												<option value="monday">Lunes</option>
												<option value="tuesday">Martes</option>
												<option value="wednesday">Miercoles</option>
												<option value="thursday">Jueves</option>
												<option value="friday">Viernes</option>
												<option value="saturday">Sabado</option>
												<option value="sunday">Domingo</option>
											</select>
										</div>
									</div>
									<div class="input-group clock col-lg-6">
										<div class="clearfix">
											<label for="exampleFormControlSelect2">Hora</label>
											<div class="input-group clockpicker pull-center"
												data-placement="center" data-align="top"
												data-autoclose="true">
												<input id="hora" type="text" class="form-control" value="00:00">
<!-- 												<span class="input-group-addon"> <span -->
<!-- 													class="glyphicon glyphicon-time"></span> -->
<!-- 												</span> -->
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<label for="exampleFormControlSelect2">Accion</label>
										<div class="form-group">
											<div class="radio">
												<label><input type="radio" name="radiopower" value="on" checked>Encender</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="off" name="radiopower">Apagar</label>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<label for="exampleFormControlSelect2">Switch</label>
										<div class="form-group">
											<div class="radio">
												<label><input type="radio" value="All" 
												name="radioencendido" checked>Todos</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="1" name="radioencendido">1</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="2" name="radioencendido">2</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer d-flex justify-content-center">
					<a class="btn btn-primary btn-lg btn-block" id="botoncreatefile">Crear</a>
				</div>
			</div>
		</div>
	</div>


    <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">¿Esta seguro de Salir?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">¿Está listo para finalizar su sesión actual?</div>
        <div class="modal-footer">
          <form role="form" action="<c:url value="/logoutsession"/>" method="get" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Salir</a>
          </form>
        </div>
      </div>
    </div>
  </div>


	<!--  Confirmar accion -->
	  <div class="modal fade" id="confirmacion" tabindex="-1" role="dialog" aria-labelledby="confirmacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Confirmar Acción</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">¿Esta seguro de confirmar la acción?</div>
        <div class="modal-footer">
          	<input type="hidden" name="accionalarma" id="accionalarma"/>
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          	<button class="btn btn-primary" onclick="enviarComandotobackendV2(${accionalarma});" data-dismiss="modal">Aceptar</button>
        </div>
      </div>
    </div>
  </div>
  
  
  <script type="text/javascript">
    $(function () {
        $(".identificar_confirmacion").click(function () {
            var my_id_value = $(this).data('id');
            console.log("INFO-------------------------------------------- "+ my_id_value);
            $("#accionalarma").val(my_id_value);
        })
    });
   </script>


	
	<script src="resources/mentor/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/mentor/assets/vendor/php-email-form/validate.js"></script>
	<script src="resources/mentor/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
	<script src="resources/mentor/assets/vendor/counterup/counterup.min.js"></script>
	<script src="resources/mentor/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
	<script src="resources/mentor/assets/vendor/aos/aos.js"></script>
	<script src="resources/mentor/assets/vendor/jquery/jquery.min.js"></script>
	<!-- Template Main JS File -->
	<script src="resources/mentor/assets/js/main.js"></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	
	<script src="resources/mentor/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/mqttRecibirMensajes.js" />'></script>
	<script src='<c:url value="/resources/alarma/coneccionAlarma.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>	
	<script src='<c:url value="/resources/pulsador/descargaImagenes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>
	<script src='<c:url value="/resources/pulsador/cargaPulsadores.js" />'></script>
	<script src='<c:url value="/resources/deviceResources/cargarDevice.js" />'></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>    


<%-- 	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.css" />'>  --%>
<!-- 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->

<!-- script alarama -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!-- script alarma -->





