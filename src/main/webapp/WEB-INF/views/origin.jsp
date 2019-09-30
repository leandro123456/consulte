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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>     
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/mqttRecibirMensajes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/alarma/coneccionAlarma.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>	
	<link href='<c:url value="/resources/mqttResources/estiloalarma.css" />' rel="stylesheet" type="text/css">
	<!-- Alta de Device -->
	<script src='<c:url value="/resources/deviceResources/cargarDevice.js" />'></script>
  	<!-- esto para el reloj del timerString -->
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.css" />'>
  
  <title>cDash</title>
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
<!--   boton check -->
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<!-- boton check -->
</head>

<body id="page-top" onresize="modificarTamanoAlarma()">
	<div id="wrapper">	
		<jsp:include page="header.jsp" />
		<div class="container-fluid">
			
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
</body>







<!-- script alarama -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!-- script alarma -->


<!-- Crear device con Modal -->
  <div class="modal fade" id="createDeviceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Alta de Dispositivo</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          	<span aria-hidden="true">×</span>
          </button>
        </div>
        <form role="form" action="<c:url value="home/create"/>" method="post">
        	<div class="modal-body">
        		<div id="infoGeneral" style="display:inline">
					<h3>Informacion General</h3>
					<b>Nombre</b> 
					<input name="namedevice" id="namedevice" class="form-control" required>
					<p></p>
					<b>Descripcion</b> 
					<input name="descriptiondevice" id="descriptiondevice" class="form-control" required>
					<p></p>
					<b>Numero de Serie</b> 
					<input name="serialnumber" id="serialnumber" class="form-control" required>
					<p></p>
				</div>
				<div id="infoDeducidaCoiaca" style="display:none">
					<h3>Informacion Dispositivo Coiaca</h3>
					<div>
						<b>Marca del Dispositivo</b> <select id="marcadevice"
							class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="coiaca">Coiaca</option>
							<option value="otro">Otro</option>
						</select>
					</div>
					<p></p>
					<div id="selectormodelo">
						<b>Modelo del Dispositivo</b> 
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
						<b>Tipo de Dispositivo</b> 
						<select id="tipodevice" class="form-control">
							<option value="none">Seleccione uno</option>
							<option value="termometro">Termometro</option>
							<option value="alarma">Alarma</option>
							<option value="sonoff">Sonoff</option>
						</select>
					</div>
					<p></p>	
				</div>
				<div id="infoPropiaVista" style="display:none">
					<h3>Personalizar Vista</h3>
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
					<h3>Informacion Avanzada</h3>
						<b>Usar configuracion por Default<input
							id="toggle-paramconects" name="defaultconfiguration" checked type="checkbox"
							data-toggle="toggle" onchange="checkconfiguration()"
							data-style="slow"></b>
						<p></p>
						<div id="parametersConexion" onkeypress=checkPassword()>
							<h5>Topico para escuchar</h5>
							<div class="form-group row ">
								<b>Hostname or Direccion IP</b> 
									<input type="text"
									class="form-control form-control-user" id="iphostescuchar"
									placeholder="Hostname"> 
								<b>Puerto</b> 
									<input type="text"
									class="form-control form-control-user" id="portescuchar"
									placeholder="Number Port"> 
								<b>Topico</b>
									<input
									type="text" class="form-control form-control-user "
									id="topiclisten" placeholder="Topic to Listen"> 
								<b>Nombre de Usuario</b> 
									<input type="text" class="form-control form-control-user"
									id="userescuchar" placeholder="User name"> 
								<b>Contraseña</b>
									<input type="password" class="form-control form-control-user"
									id="passescuchar" placeholder="Password"> 
								<b>Confirmar Contraseña</b> 
									<input type="password"
									class="form-control form-control-user" id="confirpassescuchar"
									placeholder="Confirm Password">
							</div>


							<h5>Topico para enviar</h5>
							<div class="form-group row ">
								<b>Hostname o Direccion IP</b> 
									<input type="text" 
									class="form-control form-control-user" id="iphostescribir"
									placeholder="Hostname" value=""> 
								<b>Puerto</b> 
									<input type="text"
									class="form-control form-control-user" id="portescribir"
									placeholder=" Number Port"> 
								<b>Topico</b>
									<input type="text" class="form-control form-control-user "
									id="topicwrite" placeholder="Topic to Write"> 
								<b>Nombre de usuario</b> 
									<input type="text" class="form-control form-control-user"
									id="userescribir" placeholder="User name"> 
								<b>Contraseña</b>
								<input type="password" class="form-control form-control-user"
									id="passescribir" placeholder="Password"> 
								<b>Confirmar Contraseña</b> <input type="password"
									class="form-control form-control-user" id="confirpassescribir"
									placeholder="Confirm Password">
							</div>

							<h5>Topico para escuchar comandos remotos</h5>
							<div class="form-group row ">
								<b>Hostname o Direccion IP</b> 
									<input type="text"
									class="form-control form-control-user"
									id="iphostescucharremote" placeholder="Hostname"> 
								<b>Puerto</b>
									<input type="text" class="form-control form-control-user"
									id="portescucharremote" placeholder="Number Port"> 
								<b>Topico</b>
									<input type="text"
									class="form-control form-control-user " id="topiclistenremote"
									placeholder="Topic to Listen"> 
								<b>Nombre de Usuario</b> 
									<input
									type="text" class="form-control form-control-user"
									id="userescucharremote" placeholder="User name"> 
								<b>Contraseña</b>
									<input type="password" class="form-control form-control-user"
									id="passescucharremote" placeholder="Password"> 
								<b>Confirmar Contraseña</b> 
									<input type="password"
									class="form-control form-control-user"
									id="confirpassescucharremote" placeholder="Confirm Password">
							</div>

							<h5>Topico para enviar comandos remotos</h5>
							<div class="form-group row ">
								<b>Hostname or Direccin IP</b> 
									<input type="text"
									class="form-control form-control-user"
									id="iphostescribirremote" placeholder="Hostname"> 
								<b>Puerto</b>
									<input type="text" class="form-control form-control-user"
									id="portescribirremote" placeholder="Number Port"> 
								<b>Topico</b>
									<input type="text"
									class="form-control form-control-user " id="topicwriteremote"
									placeholder="Topic to Write"> 
								<b>Nombre de Usuario</b> 
									<input
									type="text" class="form-control form-control-user"
									id="userescribirremote" placeholder="User name"> 
								<b>Contraseña</b>
								<input type="password" class="form-control form-control-user"
									id="passescribirremote" placeholder="Password"> 
								<b>Confirmar Contraseña</b> 
								<input type="password"
									class="form-control form-control-user"
									id="confirmpassescribirremote" placeholder="Confirm Password">
							</div>
						</div>
					</div>
					<div id="infoFinal" style="display:none">
						<h5>Termino con la configuracion... desea guardar los cambios?</h5>
					</div>
					
				
				<div class="btn-group">
          			<button id="botonAnterior" style="display:none" class="btn btn-secondary" onclick="anteriorAnimacion()" type="button">Anterior</button>
          			<button id="botonSiguiente" class="btn btn-primary" onclick="siguienteAnimacion()" type="button">Siguiente</button>
          		</div>
        </div>
        <div class="modal-footer">
          	<button type="button" class="btn btn-secondary"  data-dismiss="modal">Cancelar</button>
          	<input type="hidden" name="tipodevice" id="tipodevice1" />
          	<button type="submit" class="btn btn-primary">Finalizar</button>
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
//Evento asociado a terminar de escribir el Serial
$("#serialnumber").blur(function() {
	var valorSerial = document.getElementById("serialnumber").value;
      evaluarNumeroDeSerie(valorSerial);
    });
</script>

<script type="text/javascript">
	$(document).ready(function() {
		if(${cantidadSensores}!=0)
			startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr","mqttpwd",${topicos});
		if(${cantidadAlarma}!=0)
		startConnectAlarma("${hostalarma}","${puertoalarma}",${sslalarma},"${usuarioalarma}","${passalarma}",${topicosalarmas});
	});
</script>

<script type="text/javascript">
	function modificarTamanoAlarma(){
		 var w = window.outerWidth;
		  var h = window.outerHeight;
		  var txt = "Window size: width=" + w + ", height=" + h;
		  if(w<980 ){
			  console.log("la pantalla se redujo" + txt);
		  }
	} 
</script>




