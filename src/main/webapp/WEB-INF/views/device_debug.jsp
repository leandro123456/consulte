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
<%-- <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet"> --%>
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
              <h6 class="m-0 font-weight-bold text-primary">Widget of Status</h6>
            </div>
            <div class="card-body">
							<div class="card-body ">
								<form class="user " id="connection-information-form ">
									<h5>Listen Information</h5>
									<div class="form-group row ">
										<b>Hostname or IP Address</b> <input type="text"
											class="form-control form-control-user" id="iphostescuchar"
											value="${configuration.iphostescuchar}" placeholder="Hostname">
										<b>Port</b> <input type="text"
											class="form-control form-control-user" id="portescuchar"
											value="${configuration.portescuchar}" placeholder="Port">
										<b>Topic to Listen:</b><input id="topicescuchar" type="text"
											class="form-control form-control-user " name="topiclisten"
											value="${configuration.topicescuchar}" placeholder="Topic to Listen">
										<b>User name</b> <input type="text"
											class="form-control form-control-user" id="userescuchar"
											value="${configuration.userescuchar}" placeholder="User name">
										<b>Password</b> <input type="password"
											class="form-control form-control-user" id="pass"
											value="${configuration.passescuchar}" placeholder="Password">
									</div>
									<hr>
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect('${configuration.iphostescuchar}', ${configuration.portescuchar}, ${configuration.usesslescuchar}, '${configuration.userescuchar}', '${configuration.passescuchar}', 'messages','${configuration.topicescuchar}')" value="Connect">
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startDisconnect('messagesescuchar')" value="Disconnect">
									<textarea disabled id="messages" class="form-control" rows="4"></textarea>
																		
									<hr class="sidebar-divider my-0">
									<h5>Write Information</h5>
									<div class="form-group row ">
										<b>Hostname or IP Address</b> <input type="text"
											class="form-control form-control-user" id="iphostescribir"
											value="${configuration.iphostescribir}" placeholder="Hostname">
										<b>Port</b> <input type="text"
											class="form-control form-control-user" id="portescribir"
											value="${configuration.portescribir}" placeholder="Port">
										<b>Topic to Write:</b><input id="topicescribir" type="text"
											class="form-control form-control-user " name="topicwrite"
											value="${configuration.topicescribir}" placeholder="Topic to Write">
										<b>User name</b> <input type="text"
											class="form-control form-control-user" id="userescribir"
											value="${configuration.userescribir}" placeholder="User name">
										<b>Password</b> <input type="password"
											class="form-control form-control-user" id="passescribir"
											value="${configuration.passescribir}" placeholder="Password">
									</div>
									<hr>
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="sendInformation()" value="Send">
									<div id="messagesecribir"></div>
									
									<hr class="sidebar-divider my-0">
									<h5>Listen Information Remote</h5>
									<div class="form-group row ">
										<b>Hostname or IP Address</b> <input type="text"
											class="form-control form-control-user" id="iphostescucharremote"
											value="${configuration.iphostescucharremote}" placeholder="Hostname">
										<b>Port</b> <input type="text"
											class="form-control form-control-user" id="portescucharremote"
											value="${configuration.portescucharremote}" placeholder="Port">
										<b>Topic to Listen:</b><input id="topicescucharremote" type="text"
											class="form-control form-control-user " name="topiclisten"
											value="${configuration.topicescucharremote}" placeholder="Topic to Listen">
										<b>User name</b> <input type="text"
											class="form-control form-control-user" id="userescucharremote"
											value="${configuration.userescucharremote}" placeholder="User name">
										<b>Password</b> <input type="password"
											class="form-control form-control-user" id="passescucharremote"
											value="${configuration.passescucharremote}" placeholder="Password">
									</div>
									<hr>
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect('${configuration.iphostescucharremote}', ${configuration.portescucharremote}, ${configuration.usesslescucharremote}, '${configuration.userescucharremote}', '${configuration.passescucharremote}', 'messageescucharremote','${configuration.topicescucharremote}')" value="Connect">
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startDisconnect('messageescucharremote')" value="Disconnect">
									<textarea disabled id="messageescucharremote" class="form-control" rows="4"></textarea>
									
									<hr class="sidebar-divider my-0">
									<h5>Write Information Remote</h5>
									<div class="form-group row ">
										<b>Hostname or IP Address</b> <input type="text"
											class="form-control form-control-user" id="iphostescribirremote"
											value="${configuration.iphostescribirremote}" placeholder="Hostname">
										<b>Port</b> <input type="text"
											class="form-control form-control-user" id="portescribirremote"
											value="${configuration.portescribirremote}" placeholder="Port">
										<b>Topic to Write:</b><input id="topicescribirremote" type="text"
											class="form-control form-control-user " name="topicwrite"
											value="${configuration.topicescribirremote}" placeholder="Topic to Write">
										<b>User name</b> <input type="text"
											class="form-control form-control-user" id="userescribirremote"
											value="${configuration.userescribirremote}" placeholder="User name">
										<b>Password</b> <input type="password"
											class="form-control form-control-user" id="passescribirremote"
											value="${configuration.passescribirremote}" placeholder="Password">
									</div>
									<hr>
									<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect()" value="Send">
									<textarea disabled id="messagesescribirremote" class="form-control" rows="4"></textarea>
									
									<hr class="sidebar-divider my-0">
									<p> </p>
										
								<div class="btn-group-vertical">
                     	<button type="submit" name="action" value="save" class="btn btn-primary">Save Changes</button>

                     	<button type="submit" name="action" value="setdefault" class="btn btn-primary btn-user btn-block">Restart Default Configuration</button>
                     </div>
								</form>
								
							</div>
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
            <span aria-hidden="true">×</span>
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
	  		  var action= "/mqttmanagment/home/remove/"+recipient;
	  		  document.getElementById("form_id").action = action;
	  	})
  	</script>


