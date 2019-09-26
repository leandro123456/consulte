<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">


<link rel="stylesheet" type="text/css" 	href='<c:url value="/resources/mqttResources/style.css" />'>
<link rel="stylesheet" type="text/css" 	href='<c:url value="/resources/mqttResources/c3.min.css" />'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js"
	type="text/javascript"></script>
<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
<script
	src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>
<title>cDash</title>
<link href='<c:url value="/resources/mqttResources/all.min.css" />'
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />'
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<!-- Alta de Device -->
	<script src='<c:url value="/resources/deviceResources/cargarDevice.js" />'></script>
	
<!-- esto para el reloj del timerString -->
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.css" />'>

<style type="text/css">
.navbar h3 {
	color: #f5f5f5;
	margin-top: 14px;
}
.hljs-pre {
	background: #f8f8f8;
	padding: 3px;
}
.footer {
	border-top: 1px solid #eee;
	margin-top: 40px;
	padding: 40px 0;
}
.input-group {
	width: 110px;
	margin-bottom: 10px;
}
.pull-center {
	margin-left: auto;
	margin-right: auto;
}
@media (min-width: 768px) {
  .container {
    max-width: 730px;
  }
}
@media (max-width: 767px) {
  .pull-center {
    float: center;
  }
}
</style>
<!-- esto para el reloj -->



<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<style type="text/css">
.tablain {
	border-color: #e3e6f border-width: 0px;
	border: transparent;
	text-align: left;
	color: #6c6e7e;
	background-color: transparent;
}
</style>

</head>


<body id="page-top">
	<script
		src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<!-- Begin Page Content -->
		<div class="container-fluid">

			<c:if test="${not empty msg}">
				<input type="hidden" id="mensaje" value="${msg}">
				<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
			</c:if>
			<c:if test="${not empty msg1}">
				<input type="hidden" id="mensaje1" value="${msg1}">
				<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
			</c:if>
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h5 class="m-0 font-weight-bold text-primary">Dispositivo Nuevo</h5>
				</div>
				<div class="card-body">
					<div class="form-group">
							<h3>Informacion General</h3>
							<b>Nombre</b> <input id="namedevice" class="form-control" required>
							<p></p>
							<b>Descripcion</b> <input id="descriptiondevice" class="form-control" required>
							<p></p>
							<div>
								<b>Marca del Dispositivo</b> <select id="marcadevice"
									class="form-control" onchange="seleccionModelo()">
									<option value="none">Seleccione uno</option>
									<option value="coiaca">Coiaca</option>
									<option value="otro">Otro</option>
								</select>
							</div>
							<p></p>
							<div id="selectormodelo">
								<b>Modelo del Dispositivo</b> <select id="modelodevice"
									class="form-control" onchange="autocompletarSerial()">
									<option value="none">Seleccione uno</option>
									<option value="WTHUSB">WTHUSB</option>
									<option value="PSWS1">PSWS1</option>
									<option value="PSWS2">PSWS2</option>
									<option value="BRDSC">BRDSC01</option>
									<option value="PS3S1">Sonoff</option>
								</select>
							</div>
							<p></p>
							<b>Numero de Serie</b> <input id="serialnumber" class="form-control" required>							
							<p></p>
							<div>
								<b>Tipo de Dispositivo</b> <select id="tipodevice"
									class="form-control" onchange="enableType()">
									<option value="none">Seleccione uno</option>
									<option value="termometro">Termometro</option>
									<option value="alarma">Alarma</option>
									<option value="sonoff">Sonoff</option>
								</select>
							</div>
							<p></p>
							<div id="vistastermometro">
								<b>Vista de Termometro</b> <select id="tipovistatermometro"
									class="form-control" onchange="changeTipoVistaTermometro()">
									<option value="none">Seleccione uno</option>
<!-- 									<option value="watches">Relojes</option> -->
									<option value="bars">Barras</option>
								</select>
							</div>
							
							<p></p>
							<div id="cantidadswith">
								<b>Cantidad de Swiths</b> <select id="cantidadswiths"
									class="form-control">
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
						<form class="panel panel-primary" id="tabletimerstring">
							<div class="panel-heading">
								<p> </p>
								<h6 class="panel-title">Parametros del Timer String</h6>
							</div>
							<div class="panel-body">
								<table class="table table-sm" id="dataTable">
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
						</form>

						<form id="parametrostermometro">
							<b>Parametros del Termometro</b>
							<table class="table table-sm">
								<tbody>
									<tr class="tablain">
										<td>Humedad</td>
										<td><input type="checkbox" id="humedadtermometro"
											data-toggle="toggle"></td>
									</tr>
									<tr class="tablain">
										<td>Temperatura (Grados Centigrados)</td>
										<td><input type="checkbox" id="tempctermometro"
											data-toggle="toggle"></td>
									</tr>
									<tr class="tablain">
										<td>Sensacion Termica (Grados Centigrados)</td>
										<td><input type="checkbox" id="sensacionctermometro"
											data-toggle="toggle"></td>
									</tr>
									<tr class="tablain">
										<td>Temperatura (Grados Fahrenheit)</td>
										<td><input type="checkbox" id="tempftermometro"
											data-toggle="toggle"></td>
									</tr>
									<tr class="tablain">
										<td>Sensacion Termica (Grados Fahrenheit)</td>
										<td><input type="checkbox" id="sensacionftermometro"
											data-toggle="toggle"></td>
									</tr>
								</tbody>
							</table>
						</form>
						<p></p>
						<b>Ver Opciones Avanzadas<input
							id="toggle-paramconects" checked type="checkbox"
							data-toggle="toggle" onchange="checkconfiguration()"
							data-style="slow"></b>
						<p></p>
						<form id="parametersConexion" onkeypress=checkPassword()>
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
						</form>
					</div>
					<div class="col-md-5 centered"></div>
					<p></p>
					<hr class="sidebar-divider my-0">
					<p></p>
					<div class="row">
						<a data-whatever="save" data-toggle="modal"
							data-target="#ModalConfirm" name="action" value="save"
							class="btn btn-primary btn-user btn-block">Crear Dispositivo</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />


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


	<div class="modal fade" id="ModalConfirm" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Crear Dispositivo</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Esta seguro de crear un nuevo dispositivo?</div>
				<div class="modal-footer">
					<form role="form" id="form_id" method="post"
						enctype="multipart/form-data">
						
						<input type="hidden" name="defaultconfiguration" id="defaultconfiguration" /> 
						<input type="hidden" name="serialnumber" id="serialnumber1" /> <input
							type="hidden" name="namedevice" id="namedevice1" /> <input
							type="hidden" name="descriptiondevice" id="descriptiondevice1" />
						<input type="hidden" name="tipodevice" id="tipodevice1" /> <input
							type="hidden" name="tipovistatermometro"
							id="tipovistatermometro1" /> <input type="hidden"
							name="humedadtermometro" id="humedadtermometro1" /> <input
							type="hidden" name="tempctermometro" id="tempctermometro1" /> <input
							type="hidden" name="sensacionctermometro"
							id="sensacionctermometro1" /> <input type="hidden"
							name="tempftermometro" id="tempftermometro1" /> <input
							type="hidden" name="sensacionftermometro"
							id="sensacionftermometro1" /> <input type="hidden"
							name="iphostescuchar" id="iphostescuchar1" /> <input
							type="hidden" name="portescuchar" id="portescuchar1" /> <input
							type="hidden" name="topiclisten" id="topiclisten1" /> <input
							type="hidden" name="userescuchar" id="userescuchar1" /> <input
							type="hidden" name="passescuchar" id="passescuchar1" /> <input
							type="hidden" name="iphostescribir" id="iphostescribir1" /> <input
							type="hidden" name="portescribir" id="portescribir1" /> <input
							type="hidden" name="topicwrite" id="topicwrite1" /> <input
							type="hidden" name="userescribir" id="userescribir1" /> <input
							type="hidden" name="passescribir" id="passescribir1" /> <input
							type="hidden" name="iphostescucharremote"
							id="iphostescucharremote1" /> <input type="hidden"
							name="portescucharremote" id="portescucharremote1" /> <input
							type="hidden" name="topiclistenremote" id="topiclistenremote1" />
						<input type="hidden" name="userescucharremote"
							id="userescucharremote1" /> <input type="hidden"
							name="passescucharremote" id="passescucharremote1" /> <input
							type="hidden" name="iphostescribirremote"
							id="iphostescribirremote1" /> <input type="hidden"
							name="portescribirremote" id="portescribirremote1" /> <input
							type="hidden" name="topicwriteremote" id="topicwriteremote1" />
						<input type="hidden" name="userescribirremote"
							id="userescribirremote1" /> <input type="hidden"
							name="passescribirremote" id="passescribirremote1" />
						<input type="hidden"
							name="timerstringsonoff" id="timerstringsonoff" />
						<input type="hidden"
							name="cantidadswiths" id="cantidadswiths1" />	
							
							

						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancelar</button>
						<a class="btn btn-primary" href="javascript:;"
							onclick="parentNode.submit();">Crear</a>
					</form>
				</div>
			</div>
		</div>
	</div>



	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
<%-- 	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script> --%>

<!-- esto es para el reloj -->
	<script type="text/javascript" src='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.js" />'></script>
	<script type="text/javascript">
	$('.clockpicker').clockpicker()
	var input = $('#single-input').clockpicker({
	placement: 'bottom',
	align: 'left',
	autoclose: true,
	'default': 'now'
	});
	</script>
<!-- esto es para el reloj -->

<script type="text/javascript">
		$("#botoncreatefile").click(function () {	 
			var raswith=$('input:radio[name=radioencendido]:checked').val();
			var raaction=$('input:radio[name=radiopower]:checked').val();
			var hora = document.getElementById("hora").value;
			var x=document.getElementById("dias");
			var totaldia = "";
			  for (var i = 0; i < x.options.length; i++) {
			     if(x.options[i].selected ==true){
			    	 if(totaldia=="")
			    		 totaldia = x.options[i].value;
			    	 else
			    	 totaldia= totaldia+"-"+ x.options[i].value;
			      }
			  }
			  
			 document.getElementById("contenidotablatimerstring").innerHTML += '<tr> <td>'+totaldia+'</td> <td>'+hora+'</td>  <td>'+raaction+'</td>   <td>'+raswith+'</td> <td>	<a data-whatever="${devices.serial}" data-toggle="modal" data-target="#deletefila"> 	<i class="fa fa-trash" aria-hidden="true"></i>  </a>    </td>  </tr>';
            $('.modal-backdrop').hide();    
            $("#modalTimerString").modal('hide');
			});
</script>


	<script type="text/javascript">
  	$(document).ready(function () {
  		checkconfiguration();
  		enableType();
  		//ocultar los modelos cuando carga la pagina
  		document.getElementById('modelodevice').style.display = 'none';
  	});
  	</script>
  	

		


