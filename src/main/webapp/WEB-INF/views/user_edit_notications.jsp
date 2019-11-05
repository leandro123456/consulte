<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es" style="background: #224A85;">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>cDash</title>
	<link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
  <!-- Custom fonts for this template-->
  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>'>
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/sb-admin-2.css"/>'>

</head>

<body class="bg-gradient-primary">

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
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">Editar Notificaciones de Usuario</h1>
              </div>
              <form class="user" action="<c:url value=''/>" method="post" enctype="multipart/form-data" autocomplete="off">
                <table class="table table-sm">
					<tbody>
						<tr class="tablain">
							<td>Enviar notificaion al Armar/Desarmar la Alarma</td>
							<td><input type="checkbox" id="armed"
								name="armed" data-toggle="toggle"></td>
						</tr>
						<tr class="tablain">
							<td>Enviar notificacion al Disparar la Alarma</td>
							<td><input type="checkbox" id="trigered"
								name="trigered" data-toggle="toggle"></td>
						</tr>
					</tbody>
				</table>
                <button class="btn btn-primary btn-user btn-block" name="action" value="save" type="submit" id="sign">Actualizar Notificaciones</button>
              	<button class="btn btn-default btn-user btn-block" name="action" value="cancel" type="submit" id="sign">Cancelar</button>
              </form> 
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
 <script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>

  <!-- Core plugin JavaScript-->
  <script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>

  <!-- Custom scripts for all pages-->
  <script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>

</body>