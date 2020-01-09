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
	<link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
  <!-- Custom fonts for this template-->
  <link rel="stylesheet" type="text/css" href="/resources/vendor/fontawesome-free/css/all.min.css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-messaging.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body class="bg-gradient-primary" style="background: #224A85;">

<script type="text/javascript">
	$(document).ready(function() {
		activarboton("armar",${armar});
		activarboton("disparar",${disparar});
	});
</script>


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
              <button class="btn btn-primary" id="my-button" onclick="pedirPermiso()">Activar Notificaciones</button>
              <form class="user" action="<c:url value='/profileuser/notificaciones'/>" method="post" enctype="multipart/form-data" autocomplete="off">
                <table class="table table-sm">
					<tbody>
						<tr class="tablain">
							<td>Enviar notificaion al Armar/Desarmar la Alarma</td>
							<td>
								<div class="custom-control custom-switch">
							      <input data-toggle="toggle" type="checkbox" data-on-color="primary" id="armar" name="armed">
							    </div>
							</td>
						</tr>
						<tr class="tablain">
							<td>Enviar notificacion al Disparar la Alarma</td>
							<td>
								<div class="custom-control custom-switch">
							      <input data-toggle="toggle" type="checkbox" data-on-color="primary" id="disparar" name="trigered">
							    </div>
							</td>
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


  <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
  <script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>

</body>

<script type="text/javascript">
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

	function pedirPermiso(){
 		swal({
 			  title: "cDash",
 			  text: "cDash quiere enviarle notificaciones",
 			  icon: "warning",
 			  buttons: true,
 			  dangerMode: true,
 			})
 			.then((willDelete) => {
 			  if (willDelete) {
 				 const messaging1 = firebase.messaging();
 				 messaging1
 			       .requestPermission()
 			       .then(function () {
 			           console.log("Notification permission granted.");
 			           return messaging1.getToken()
 			       })
 			       .then(function(token) {
 			           console.log("token is : " + token);
 			           enviarToken(token);
 			       })
 			       .catch(function (err) {
 			           console.log("Unable to get permission to notify."+ err);
 			       });
 				 
 				 
 			    swal("Permiso Concedido", {
 			      icon: "success",
 			    });
 			  } else {
 			    swal("Puede habilitarlo en cualquier momento");
 			  }
 			});
 		
 	}
</script>


<script type="text/javascript">
	function activarboton(nombre,valor){
		if(nombre=="armar"){
			if(valor==null || valor==false)
				document.getElementById("armar").checked = false;
			else
				document.getElementById("armar").checked = true;
		}
		else{
			if(valor==null || valor==false)
				document.getElementById("disparar").checked = false;
			else
				document.getElementById("disparar").checked = true;
		}
		
// 	}
</script>

