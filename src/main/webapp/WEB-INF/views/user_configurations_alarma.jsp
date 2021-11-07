<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>cDash</title>
  
  	<link href='<c:url value="/resources/css/checkbox.css" />' rel="stylesheet" type="text/css" >
  	<link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/style.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/c3.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/all.min.css" />'>
	<link rel="stylesheet" href='<c:url value="/resources/mqttResources/sb-admin-2.css" />'>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
  
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-app.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-messaging.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script src='<c:url value="/resources/alarma/VistaAlarmaV2.js" />'></script>
  <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
  <script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
</head>

<body id="page-top">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<c:if test="${not empty msg1}">
					<div class="alert alert-warning">${msg1}</div>
				</c:if>
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<div class="row">
								<div class="col-md-12">
									<h2 style="text-align: center;">Settings</h2>
								</div>
							</div>
							<div class="row">
								<div class="col-md-1">
									<a class="nav-link collapsed" href="/" aria-expanded="true"
										aria-controls="collapseUtilities"
										style="color: #224A85; font-size: 2em; margin-top: -0.6em;">
										<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
									</a>
								</div>
							</div>
							<p></p>
							<input type="hidden" name="serial" id="serial" value=${serial }/>
							<div class="table-responsive">
								<table class="table" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col"></th>
											<th scope="col">Set password to disarm alarm</th>
											<th scope="col"></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<tr class="tablain">
											<td>Password</td>
											<td>
												<input type="password" placeholder="enter password" id="psw" name="psw" required style="border-block: revert;"	>
											</td>
											<td>Repeat Password</td>
											<td>
												<input type="password" placeholder="repeat password" id="psw-repeat" name="psw-repeat" required style="border-block: revert;">
											</td>
											<td>
												<a href='#' aria-expanded="true" aria-controls="collapseUtilities" data-toggle='modal' 
													class="nav-link collapsed"  data-id='CAMBIARALARMA'
												 	data-target='#confirmacion' style="color: #224A85; font-size: 1em;">
												 	<i class="fa fa-check-circle" aria-hidden="true"></i>
												 </a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!--  Confirmar accion -->
	<div class="modal fade" id="confirmacion" tabindex="-1" role="dialog" aria-labelledby="confirmacion" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Confirm action</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Are you sure to change the password?</div>
        <div class="modal-footer">
          	<input type="hidden" name="pass" id="pass"/>
          	<input type="hidden" name="passconfirm" id="passconfirm"/>
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          	<button class="btn btn-primary" onclick="updatepasswordV2(pass.value,passconfirm.value);" 
          	data-dismiss="modal">Accept</button>
        </div>
      </div>
    </div>
  </div>
  
  
  <script type="text/javascript">
  $("#confirmacion").on('shown.bs.modal',function () {
	var mypass= document.getElementById('psw').value;
	$("#pass").val(mypass);
	var mypass_repeat= document.getElementById('psw-repeat').value;
	$("#passconfirm").val(mypass_repeat);
  });
  
  </script>	
	
</body>
<jsp:include page="footer.jsp" />

