<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Ser Piero Da Vinci</title>

<!-- Bootstrap Core CSS -->
<link href='<c:url value="/resources/css/bootstrap.min.css" />'
	rel="stylesheet">

<!-- Custom CSS -->
<link href='<c:url value="/resources/css/sb-admin.css" />'
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href='<c:url value="/resources/font-awesome/css/font-awesome.min.css" />'
	rel="stylesheet" type="text/css">

<!-- Custom jQuery -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#viewPassword").hide();

		$("#randomPassword").click(function(){
			$.get("<c:url value='/randompassword' />", function(responseText){
				$("#password").val(responseText);
				$("#confirmNewPassword").val(responseText);
				$("#viewPassword").show();
			});
		});

		$("#viewPassword").click(function(){
			$("#password").prop("type", "text");
		});
	});
</script>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/home/provisioning" />"><img
					alt="Nticxs" width="50%"
					src='<c:url value="/resources/images/nticxs-logo.png" />'></a>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Creacion de Usuarios</h1>
					</div>
				</div>
				<div class="row">
					<div class="panel panel-default">
						<c:if test="${not empty msg1}">
							<div class="alert alert-warning">${msg1}</div>
						</c:if>
						<div class="panel-heading">
							<h3 class="panel-title">Detalles del Usuario</h3>
						</div>
						<div class="panel-body">
							<form role="form" action="<c:url value='signup'/>" method="post" enctype="multipart/form-data" autocomplete="off">
								<div class="form-group">
									<label>Rol del Usuario</label> 
									<select id="roles" name="role" class="form-control"  onchange="enableSons()">
										<option value="none">Select</option>
										<option value="ALUMNO">ALUMNO</option>
										<option value="PADRE">PADRE</option>
									</select>
									<p></p>
									
									<label id = "tituloRelacion" style="display: none">Vincular con alumno/s</label>
									<select multiple class="form-control" id="relacion" name="relacion" 
									style="display: none">
										<c:forEach items="${alumnos}" var="alumno">
											<option value="${alumno}">${alumno}</option>
										</c:forEach>
									</select>
									<p></p>
									
									<label>Nombre de Usuario</label>
									<input id="insertname" name="insertName" class="form-control" placeholder="Ingrese el nombre de Usuario" style="display: none">
									<select id="selectName" name="selectName" class="form-control"
									style="display: none">
										<option value="none">Seleccionar Alumno</option>
										<c:forEach items="${alumnos}" var="alumno">
											<option value="${alumno}">${alumno}</option>
										</c:forEach>
									</select>
									
									<p> </p>
                                    <label>Email</label>
                                   	<input name="email" class="form-control" required>
									<p> </p>
									
									<div class="row">
										<div class="col-lg-2"></div>
										<div class="col-lg-8">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h3 class="panel-title">Contraseña</h3>
												</div>
												<div class="panel-body">
													<label>Su contraseña debe tener un largo superior a 10 caracteres</label>
													<p></p>
													<label>Tambien debe cumplir con los siguientes requisitos:</label>
													<p></p>
													<label>-Un valor Alfabetico en mayuscula</label>
													<p></p>
													<label>-Un valor numerico</label>
													<p></p> 
													<label>-Al menos un caracter especial:</label>
													<p></p>
													<label>!@#$&;.,</label>
													<p></p>
													<label>Contraseña</label>
	                                                <input id="password" type="password" name="newPass" class="form-control" autocomplete="new-password">
	                                                <p> </p>
	                                                <input id="randomPassword" type="button" class="btn btn-default" value="Random Password">
	                                                <p> </p>
	                                                <input id="viewPassword" type="button" class="btn btn-default" value="View Password">
	                                                <p> </p>
	                                                <label>Confirmar Contraseña</label>
	                                                <input id="confirmNewPassword" type="password" name="newPass2" class="form-control" autocomplete="new-password">
												</div>
											</div>
										</div>
									</div>
									<p></p>
								</div>
								<div class="col-md-5 centered"></div>
								<div class="col-md-6 centered">
									<button type="submit" name="action" value="save"
										class="btn btn-default">Save</button>

									<button type="submit" name="action" value="cancel"
										class="btn btn-default">Cancel</button>
								</div>
							</form>
						</div>
					</div>
				</div>

<jsp:include page="footer.jsp" />
<script type="text/javascript">
    function enableSons(){
    	var seleccion=document.getElementById('roles');
        var valueRol = seleccion.options[seleccion.selectedIndex].value;
  		if(valueRol == "PADRE"){
  			document.getElementById('relacion').style.display = 'inline';
  			document.getElementById('insertname').style.display = 'inline';
  			document.getElementById('selectName').style.display = 'none';
  			document.getElementById('tituloRelacion').style.display = 'inline';
  		}if(valueRol == "ALUMNO"){
  			document.getElementById('relacion').style.display = 'none';
  			document.getElementById('insertname').style.display = 'none';
  			document.getElementById('selectName').style.display = 'inline';
  			document.getElementById('tituloRelacion').style.display = 'none';
  			}
  		if(valueRol != "ALUMNO" && valueRol != "PADRE"){
  			document.getElementById('relacion').style.display = 'none';
  			document.getElementById('insertname').style.display = 'none';
  			document.getElementById('selectName').style.display = 'none';
  			document.getElementById('tituloRelacion').style.display = 'none';
  			}
    }
</script>