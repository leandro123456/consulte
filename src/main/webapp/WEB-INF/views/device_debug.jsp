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
								<form role="form"  method="post" enctype="multipart/form-data" class="user " id="connection-information-form">
									<c:if test = "${configuration.topicescuchar != null}">
										<h5>Listen Information</h5>
										<div class="form-group row ">
											<b>Hostname or IP Address</b> <input type="text"
												class="form-control form-control-user" id="iphostescuchar"
											    value="${configuration.iphostescuchar}" placeholder="Hostname">
											<b>Port</b> <input type="text"
												class="form-control form-control-user" id="portescuchar"
												value="${configuration.portescuchar}" placeholder="Port">
											<b>Topic to Listen:</b><input type="text"
												class="form-control form-control-user " id="topiclisten"
												value="${configuration.topicescuchar}" placeholder="Topic to Listen">
											<b>User name</b> <input type="text"
												class="form-control form-control-user" id="userescuchar"
												value="${configuration.userescuchar}" placeholder="User name">
											<b>Password</b> <input type="password"
												class="form-control form-control-user" id="passescuchar"
												value="${configuration.passescuchar}" placeholder="Password">
										</div>
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect('${configuration.iphostescuchar}', ${configuration.portescuchar}, ${configuration.usesslescuchar}, '${configuration.userescuchar}', '${configuration.passescuchar}', 'messages','${configuration.topicescuchar}')" value="Connect">
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startDisconnect('messagesescuchar')" value="Disconnect">
										<textarea disabled id="messages" class="form-control" rows="4"></textarea>
										<p> </p>									
										<hr class="sidebar-divider my-0">
										<p> </p>
									</c:if>
									<c:if test = "${configuration.topicescribir != null}">
										<h5>Write Information</h5>
										<div class="form-group row ">
											<b>Hostname or IP Address</b> <input type="text"
												class="form-control form-control-user" id="iphostescribir"
												value="${configuration.iphostescribir}" placeholder="Hostname">
											<b>Port</b> <input type="text"
												class="form-control form-control-user" id="portescribir"
												value="${configuration.portescribir}" placeholder="Port">
											<b>Topic to Write:</b><input type="text"
												class="form-control form-control-user " id="topicwrite"
												value="${configuration.topicescribir}" placeholder="Topic to Write">
											<b>User name</b> <input type="text"
												class="form-control form-control-user" id="userescribir"
												value="${configuration.userescribir}" placeholder="User name">
											<b>Password</b> <input type="password"
												class="form-control form-control-user" id="passescribir"
												value="${configuration.passescribir}" placeholder="Password">
										</div>
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="sendInformation()" value="Send">
										<textarea disabled id="messagesecribir" class="form-control" rows="4"></textarea>
										<p> </p>
										<hr class="sidebar-divider my-0">
										<p> </p>
									</c:if>
									<c:if test = "${configuration.topicescucharremote != null}">
										<h5>Listen Information Remote Configuration</h5>
										<div class="form-group row ">
											<b>Hostname or IP Address</b> <input type="text"
												class="form-control form-control-user" id="iphostescucharremote"
												value="${configuration.iphostescucharremote}" placeholder="Hostname">
											<b>Port</b> <input type="text"
												class="form-control form-control-user" id="portescucharremote"
												value="${configuration.portescucharremote}" placeholder="Port">
											<b>Topic to Listen:</b><input type="text"
												class="form-control form-control-user " id="topiclistenremote"
												value="${configuration.topicescucharremote}" placeholder="Topic to Listen">
											<b>User name</b> <input type="text"
												class="form-control form-control-user" id="userescucharremote"
												value="${configuration.userescucharremote}" placeholder="User name">
											<b>Password</b> <input type="password" 
												class="form-control form-control-user" id="passescucharremote"
												value="${configuration.passescucharremote}" placeholder="Password">
										</div>
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect('${configuration.iphostescucharremote}', ${configuration.portescucharremote}, ${configuration.usesslescucharremote}, '${configuration.userescucharremote}', '${configuration.passescucharremote}', 'messageescucharremote','${configuration.topicescucharremote}')" value="Connect">
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startDisconnect('messageescucharremote')" value="Disconnect">
										<textarea disabled id="messageescucharremote" class="form-control" rows="4"></textarea>
										<p> </p>
										<hr class="sidebar-divider my-0">
										<p> </p>
									</c:if>
									<c:if test = "${configuration.topicescribirremote != null}">
										<h5>Write Information Remote Configuration</h5>
										<div class="form-group row ">
											<b>Hostname or IP Address</b> <input type="text"
												class="form-control form-control-user" id="iphostescribirremote"
												value="${configuration.iphostescribirremote}" placeholder="Hostname">
											<b>Port</b> <input type="text"
												class="form-control form-control-user" id="portescribirremote"
												value="${configuration.portescribirremote}" placeholder="Port">
											<b>Topic to Write:</b><input type="text"
												class="form-control form-control-user " id="topicwriteremote"
												value="${configuration.topicescribirremote}" placeholder="Topic to Write">
											<b>User name</b> <input type="text"
												class="form-control form-control-user" id="userescribirremote"
												value="${configuration.userescribirremote}" placeholder="User name">
											<b>Password</b> <input type="password"
												class="form-control form-control-user" id="passescribirremote"
												value="${configuration.passescribirremote}" placeholder="Password">
										</div>
										<input type="button" class="btn btn-primary btn-user btn-block"	onclick="startConnect()" value="Send">
										<textarea disabled id="messagesescribirremote" class="form-control" rows="4"></textarea>
										<p> </p>
										<hr class="sidebar-divider my-0">
										<p> </p>
									</c:if>	
								<div class="row">
                     				<a data-whatever="save" data-toggle="modal" data-target="#ModalConfirm" name="action" value="save" class="btn btn-primary btn-user btn-block">Save Changes</a>
                     				<a data-whatever="setdefault" data-toggle="modal" data-target="#ModalConfirm" name="action" value="setdefault" class="btn btn-primary btn-user btn-block openBtn">Restart Default Configuration</a>
                     			</div>
								</form>
								
							</div>
				</div>
          </div>

        </div>
</div>
<jsp:include page="footer.jsp" />

  <!-- Logout Modal-->
  <div class="modal fade" id="ModalConfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="titulomodal"></h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body" id="textomodal"></div>
        <div class="modal-footer">
          <form role="form"  id="form_id" method="post" enctype="multipart/form-data">
          	<input type="hidden" name="iphostescuchar" id="iphostescuchar1"/>
          	<input type="hidden" name="portescuchar" id="portescuchar1"/>
          	<input type="hidden" name="topiclisten" id="topiclisten1"/>
          	<input type="hidden" name="userescuchar" id="userescuchar1"/>
          	<input type="hidden" name="passescuchar" id="passescuchar1"/>
          	
          	<input type="hidden" name="iphostescribir" id="iphostescribir1"/>
          	<input type="hidden" name="portescribir" id="portescribir1"/>
          	<input type="hidden" name="topicwrite" id="topicwrite1"/>
          	<input type="hidden" name="userescribir" id="userescribir1"/>
          	<input type="hidden" name="passescribir" id="passescribir1"/>
          	
          	<input type="hidden" name="iphostescucharremote" id="iphostescucharremote1"/>
          	<input type="hidden" name="portescucharremote" id="portescucharremote1"/>
          	<input type="hidden" name="topiclistenremote" id="topiclistenremote1"/>
          	<input type="hidden" name="userescucharremote" id="userescucharremote1"/>
          	<input type="hidden" name="passescucharremote" id="passescucharremote1"/>
          	
          	<input type="hidden" name="iphostescribirremote" id="iphostescribirremote1"/>
          	<input type="hidden" name="portescribirremote" id="portescribirremote1"/>
          	<input type="hidden" name="topicwriteremote" id="topicwriteremote1"/>
          	<input type="hidden" name="userescribirremote" id="userescribirremote1"/>
          	<input type="hidden" name="passescribirremote" id="passescribirremote1"/>
          	
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          	<a class="btn btn-primary" href="javascript:;" id="modalaceptar" onclick="parentNode.submit();"></a>
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
                $('#ModalConfirm').on('show.bs.modal',function (event) {
                		  var mail = "${deviceserial}";
                          var button = $(event.relatedTarget)
                          var recipient = button.data('whatever') 
                          if( recipient =="save"){
                        	  if(document.getElementById("iphostescuchar") != null)
                        	  	document.getElementById("iphostescuchar1").value = document.getElementById("iphostescuchar").value;
                        	  if(document.getElementById("portescuchar") != null)
                          	  	document.getElementById("portescuchar1").value = document.getElementById("portescuchar").value;
                        	  if(document.getElementById("topiclisten") != null)
                          	  	document.getElementById("topiclisten1").value = document.getElementById("topiclisten").value;
                        	  if(document.getElementById("userescuchar") != null)
                          	  	document.getElementById("userescuchar1").value = document.getElementById("userescuchar").value;
                        	  if(document.getElementById("passescuchar") != null)
                          	  	document.getElementById("passescuchar1").value = document.getElementById("passescuchar").value;
                        	  
                        	  if(document.getElementById("iphostescribir") != null)
                          	  	document.getElementById("iphostescribir1").value = document.getElementById("iphostescribir").value;
                        	  if(document.getElementById("portescribir") != null)
                          	  	document.getElementById("portescribir1").value = document.getElementById("portescribir").value;
                        	  if(document.getElementById("topicwrite") != null)
                          	  	document.getElementById("topicwrite1").value = document.getElementById("topicwrite").value;
                        	  if(document.getElementById("userescribir") != null)
                          	  	document.getElementById("userescribir1").value = document.getElementById("userescribir").value;
                        	  if(document.getElementById("passescribir") != null)
                          	  	document.getElementById("passescribir1").value = document.getElementById("passescribir").value;
                        	  
                        	  if(document.getElementById("iphostescucharremote") != null)
                          	  	document.getElementById("iphostescucharremote1").value = document.getElementById("iphostescucharremote").value;
                          	  if(document.getElementById("portescucharremote") != null)
                            	  	document.getElementById("portescucharremote1").value = document.getElementById("portescucharremote").value;
                          	  if(document.getElementById("topiclistenremote") != null)
                            	  	document.getElementById("topiclistenremote1").value = document.getElementById("topiclistenremote").value;
                          	  if(document.getElementById("userescucharremote") != null)
                            	  	document.getElementById("userescucharremote1").value = document.getElementById("userescucharremote").value;
                          	  if(document.getElementById("passescucharremote") != null)
                            	  	document.getElementById("passescucharremote1").value = document.getElementById("passescucharremote").value;
                          	  
                          	  if(document.getElementById("iphostescribirremote") != null)
                            	  	document.getElementById("iphostescribirremote1").value = document.getElementById("iphostescribirremote").value;
                          	  if(document.getElementById("portescribirremote") != null)
                            	  	document.getElementById("portescribirremote1").value = document.getElementById("portescribirremote").value;
                          	  if(document.getElementById("topicwriteremote") != null)
                            	  	document.getElementById("topicwriteremote1").value = document.getElementById("topicwriteremote").value;
                          	  if(document.getElementById("userescribirremote") != null)
                            	  	document.getElementById("userescribirremote1").value = document.getElementById("userescribirremote").value;
                          	  if(document.getElementById("passescribirremote") != null)
                            	  	document.getElementById("passescribirremote1").value = document.getElementById("passescribirremote").value;
                        	  
                        	  
                        	  var action= "/mqttmanagment/home/debugview/"+mail+"/save";
                          }
                         if(recipient =="setdefault"){
                            var action= "/mqttmanagment/home/debugview/"+mail+ "/defaultconfiguration";
                          }
                          document.getElementById("form_id").action = action;
                })
                
                $('#ModalConfirm').on('show.bs.modal', function(event){
                        var button = $(event.relatedTarget)
                        var recipient = button.data('whatever')
                        if( recipient =="save"){
                                document.getElementById("titulomodal").innerHTML = 'Save all Changes';
                                document.getElementById("textomodal").innerHTML = 'Are you sure you save the changes?';
                                document.getElementById("modalaceptar").innerHTML = 'Save'; 
                        }
                        if(recipient == "setdefault"){
                                document.getElementById("titulomodal").innerHTML = 'Reset Defaults';
                                document.getElementById("textomodal").innerHTML = 'Are you sure to reset the connection values?';
                                document.getElementById("modalaceptar").innerHTML = 'Accept';
                        }
                });
        </script>




