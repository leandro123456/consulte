<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/style.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/c3.min.css" />'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>     
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/mqttRecibirMensajes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/alarma/coneccionAlarma.js" />'></script>
<%-- 	<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script> --%>
<%-- 	<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script> --%>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>
	<script src='<c:url value="/resources/pulsador/cargaPulsadores.js" />'></script>	
	<link href='<c:url value="/resources/mqttResources/estiloalarma.css" />' rel="stylesheet" type="text/css">
	<!-- Alta de Device -->
	<script src='<c:url value="/resources/deviceResources/cargarDevice.js" />'></script>
  	<!-- esto para el reloj del timerString -->
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.css" />'>
  
  <title>cDash</title>
  <link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
  
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.css" />' rel="stylesheet">
<!--   boton check -->
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<!-- boton check -->

<script async src="https://www.googletagmanager.com/gtag/js?id=G-6SSYQD4466"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-6SSYQD4466');
</script>
</head>

<body id="page-top">
<script type="text/javascript">
	$(document).ready(function() {
		cargarParticionesAlarmas(${alarmaSerial});
		cargarZonas(${alarmaSerial});
		if(${serialpulsador}.length >0){
			cargarColorbotones(${serialpulsador});
		}
		setTimeout(iniciaConexion, 2000);
//		requestPermission();


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

	<div id="wrapper">	
		<jsp:include page="header.jsp" />
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
						setTimeout('window.location.href = "/home";', 2000);
					</script>
				</c:if>
			<c:if test="${not empty msg}">
				 	<input type="hidden" id ="mensaje" value="${msg}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/home";', 5000);
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
						setTimeout('window.location.href = "/home";', 5000);
					</script>
				</c:if>	

			
			
			<div class="row" id="cargadora">
				<c:forEach items="${vistas}" var="vista">
	                ${vista}
	          </c:forEach>
			</div>
			<div class="fixed">
				<a href="/home/newdeviceb" data-toggle="modal" data-target="#createDeviceModal"> 
					<i class="fa fa-plus-circle fa-3x" aria-hidden="true"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="visible">
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>
	</div>

	<jsp:include page="footer.jsp" />
	
<div id="token" style="display: none;"></div>	
	
	
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.3.0/firebase-messaging.js"></script>
<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->

<script>
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
  const tokenDivId = 'token_div';
  const permissionDivId = 'permission_div';
	//[END get_messaging_object]
	//[START set_public_vapid_key]
	//Add the public key generated from the console here.
	messaging.usePublicVapidKey('BDKEV8dGaExs2CjrNlkVYZ3L6AuHCCSNt4ELNRSkPHZZnztf1Lf082Q8QmNut7VzTICNaGrjxSp58En2f6jNmbE');
  
	messaging.requestPermission()
	.then(function(){
		console.log("obtuvo el permiso");
		console.log("MENSAJE: "+ messaging.getToken());
		return messaging.getToken();
		//requestPermission();	
	})
	.then(function(token){
		console.log(token);
	})
	.catch(function(err){
		console.log('Ocurrio un problema: '+err)
	})

	
	messaging.onMessage(function(payload){
		console.log('onMenssage: ',payload);
	});
</script>
</body>







<!-- script alarama -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!-- script alarma -->


<!-- Crear device con Modal -->
  <div class="modal fade" id="createDeviceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
<!-- 					<h3>Informacion General</h3> -->
					<b>Nombre del dispositivo</b> 
					<input name="namedevice" id="namedevice" class="form-control" required>
					<p></p>
					<b>Descripción</b> 
					<input name="descriptiondevice" id="descriptiondevice" class="form-control" required>
					<p></p>
					<b>Numero de Serie</b> 
					<input name="serialnumber" id="serialnumber" class="form-control" required>
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
						</div>
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
	
	
	


<script type="text/javascript">
$("#serialnumber").blur(function() {
	var valorSerial = document.getElementById("serialnumber").value;
      evaluarNumeroDeSerie(valorSerial);
    });
</script>



<script type="text/javascript">
function iniciaConexion(){
	console.log("dispositivos: "+ ${cantidadSensores});
	if(${cantidadSensores}!=0){
		startConnectSonoff("mqtttest.qliq.com.ar", 8081, false, "mqttusr","mqttpwd",${topicos});
	}
	console.log("dispositivos alarma: "+ ${cantidadAlarma})
	if(${cantidadAlarma}!=0)
	startConnectAlarma("${hostalarma}","${puertoalarma}",${sslalarma},"${usuarioalarma}","${passalarma}",${topicosalarmas});
}
</script>




