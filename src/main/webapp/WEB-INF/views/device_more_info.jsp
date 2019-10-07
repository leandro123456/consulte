<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>	
  <title>Devices - Dashboard</title>
<link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>


<body id="page-top">
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<div id="wrapper">
<jsp:include page="header.jsp" />
     <!-- Begin Page Content -->
     <div class="container-fluid">
        
			<c:if test="${not empty msg}">
				 	<input type="hidden" id ="mensaje" value="${msg}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  //title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
				</c:if>
				<c:if test="${not empty msg1}">
				 	<input type="hidden" id ="mensaje1" value="${msg1}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  //title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
				</c:if>	
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Dispositivo: ${device.serialnumber}</h6>
            </div>
            <div class="card-body">
              <form role="form" action="<c:url value="/home/savemodification/" />${device.serialnumber}/" method="post" enctype="multipart/form-data">
                   <div class="form-group">
                       <h2>Informaci�n general</h2>
                       <label>Nombre del dispositivo</label>
                       <input name="name" class="form-control" value="${device.name}">
                       <p> </p>
                       <label>Descripci�n</label>
                       <input name="description" class="form-control" value="${device.description}">
                       <p> </p>
                        <hr class="sidebar-divider my-0">
						<h2>Configuracion del dispositivo</h2>
						<label>Nombre de la configuraci�n</label>
						<input name="confname" class="form-control" disabled value="${configuration.name}">
						<label>Broker MQTT</label>
						<input name="confname" class="form-control" disabled value="${configuration.iphostescuchar}">
						<label>Puerto</label>
						<input name="confname" class="form-control" disabled value="${configuration.portescuchar}">
						<label>Nombre de usuario del broker MQTT</label>
						<input name="confname" class="form-control" disabled value="${configuration.name}">
						<label>Contrase�a</label>
						<input type="password" class="form-control form-control-user"  name="password" id = "userPassword"  placeholder="Password">
						<label>Repetir contrase�a</label>
						<input type="password" class="form-control form-control-user"  name="password2" id = "userPassword2"  placeholder="Repeit Password">
						<label>Prefijo para los t�picos de estado</label>
						<input name="confname" class="form-control" disabled value="${configuration.topicescuchar}">
						<label>T�pico de comandos</label>
						<input name="confname" class="form-control" disabled value="${configuration.topicescribir}">
                     </div>
                     <div class="col-md-5 centered">
                     </div>
						<div class="col-md-6 centered">
                     	<button type="submit" name="action" value="save" class="btn btn-default">Guardar</button>

                     	<button type="submit" name="action" value="cancel" class="btn btn-default">Cancelar</button>
                     </div>
                 </form>
            </div>
          </div>

        </div>
</div>
<jsp:include page="footer.jsp" />


  <!-- Logout Modal-->
  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Delete Device</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">�</span>
          </button>
        </div>
        <div class="modal-body">Are you sure you delete the device from the platform?</div>
        <div class="modal-footer">
          <form role="form"  id="form_id" method="post" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Delete</a>
          </form>
        </div>
      </div>
    </div>
  </div>
  
    <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
  	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>
  	
  	<script type="text/javascript">
	  	$('#deleteModal').on('show.bs.modal', function (event) {
	  		  var button = $(event.relatedTarget)
		  	  var recipient = button.data('whatever') 
		  	  // var modal = $(this) modal.find('.modal-body input').val(recipient);
	  		  var action= "/home/remove/"+recipient;
	  		  document.getElementById("form_id").action = action;
	  	})
  	</script>
  	