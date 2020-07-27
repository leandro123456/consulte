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

  <!-- Custom styles for checkbox-->
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/checkbox.css" />'>
<link rel="stylesheet" href='<c:url value="/resources/mqttResources/sb-admin-2.css" />'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-app.js"></script>
  <script src="https://www.gstatic.com/firebasejs/7.6.1/firebase-messaging.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body class="bg-gradient-primary" style="background: #224A85;">

<script type="text/javascript">
	$(document).ready(function() {
		activarboton("armarcloud",${armarcloud});
		activarboton("armedmail",${armedmail});
		activarboton("dispararcloud",${dispararcloud});
		activarboton("dispararmail",${dispararmail});
		activarboton("signalwifi",${signalwifi});
		activarboton("signalwifimail",${signalwifimail});
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
              <div class="row">
              	<div class="col-md-12">
<!--               		<img alt="" src="/resources/loginresources/css/images/logoBig.png" style="width:5em;display: block;margin: auto;"> -->
       				<h2 style="text-align: center;">Configuración de Notificaciones</h2>
       			</div>
              </div>
             <div class="row">
             	<div class="col-md-1">
               		<a class="nav-link collapsed" href="/home" aria-expanded="true" aria-controls="collapseUtilities" style="color:#224A85;font-size: 2em; margin-top: -0.6em;">
		         		<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
		       		</a>
		       	</div>
<!-- 		       	<div class="col-md-11" style="text-align: -webkit-left;margin-left: -1.5em;"> -->
<!--                		<h5>Volver</h5> -->
<!--                	</div> -->
             </div>
              <p> </p>
              <input type="hidden" name="serial" id="serial" value=${serial}/>
              <div class="table-responsive">
                <table class="table" width="100%" cellspacing="0">
                	<thead>
                        <tr>
                          <th scope="col"></th>
                          <th scope="col">Notificaciones Push</th>
                          <th scope="col">Notificaciones por email</th>
                        </tr>
                    </thead>
					<tbody>
						<tr class="tablain">
							<td>Enviar notificaion al Armar/Desarmar la Alarma</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;" onclick="pedirPermiso('armarcloud')" id="armarcloud" name="armarcloud">
							      <span class="checkmark"></span>
							    </label>
							</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;"onclick="actualizarEstadoNotificacion('armedmail')" id="armedmail" name="armedmail">
							      <span class="checkmark"></span>
							    </label>
							</td>
						</tr>
						<tr class="tablain">
							<td>Enviar notificacion al Disparar la Alarma</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;"onclick="pedirPermiso('dispararcloud')" id="dispararcloud" name="dispararcloud">
							      <span class="checkmark"></span>
							    </label>
							</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;" onclick="actualizarEstadoNotificacion('dispararmail')" id="dispararmail" name="dispararmail">
							      <span class="checkmark"></span>
							    </label>
							</td>
						</tr>
						<tr class="tablain">
							<td>Enviar notificacion cuando la se&ntildeal WIFI es baja</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;"onclick="pedirPermiso('signalwifi')" id="signalwifi" name="signalwifi">
							      <span class="checkmark"></span>
							    </label>
							</td>
							<td>
								<label class="containercb">
							      <input data-toggle="toggle" type="checkbox" style="display:none;" onclick="actualizarEstadoNotificacion('signalwifimail')" id="signalwifimail" name="signalwifimail">
							      <span class="checkmark"></span>
							    </label>
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

	function pedirPermiso(campoejecutado){
		
		var armado=document.getElementById("armarcloud");
		var disparado=document.getElementById("dispararcloud");
		var signalwifi= document.getElementById("signalwifi");
// 		console.log("dsadsad valor: "+ campoejecutado);
// 		console.log("valor: "+campoejecutado == "armarcloud"+"; "+campoejecutado == "dispararcloud");
		
		if(campoejecutado == "armarcloud"){
// 			console.log("Entro en 1");
			var urlsendInformation = $(location).attr('pathname')+"/condicion_armado/"+armado.checked;
				$.ajax({ url : urlsendInformation,
					contentType: "application/json",
					dataType: 'json',
					success: function(data){
					}			
			});
		} 
		if(campoejecutado == "dispararcloud"){
// 			console.log("Entro en 2");
			var urlsendInformation = $(location).attr('pathname')+"/condicion_disparado/"+disparado.checked;
			$.ajax({ url : urlsendInformation,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
				}			
		});
		}
		if(campoejecutado == "signalwifi"){
// 			console.log("Entro en 3");
			var urlsendInformation = $(location).attr('pathname')+"/condicion_bajasignalwifi/"+signalwifi.checked;
			$.ajax({ url : urlsendInformation,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
				}			
		});
		} 
		if((campoejecutado == "armarcloud" && armado.checked == true && disparado.checked == false && signalwifi.checked == false)||
		   (campoejecutado == "dispararcloud" && armado.checked == false && disparado.checked == true && signalwifi.checked == false) ||
		   (campoejecutado == "signalwifi" && armado.checked == false && disparado.checked == false && signalwifi.checked == true))
		{
 		swal({
 			  title: "cDash",
 			  text: "cDash quiere enviarle notificaciones a su Dispositivo",
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
 	}
</script>

<script type="text/javascript">
	function actualizarEstadoNotificacion(campoejecutado){
		var armado=document.getElementById(campoejecutado);
// 		console.log("llego a actualizar los mails: "+ campoejecutado);
		if(campoejecutado == "armedmail"){
			var urlsendInformation2 = $(location).attr('pathname') + "/condicion_armado_mail/"+armado.checked;
			$.ajax({ url : urlsendInformation2,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
				}			
		});
		} 
		if(campoejecutado == "dispararmail"){
// 			console.log("entro disparar mail: "+armado.checked)
			var urlsendInformation2 = $(location).attr('pathname') + "/condicion_disparado_mail/"+armado.checked;
			$.ajax({ url : urlsendInformation2,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
				}			
		});
		} 
		if(campoejecutado == "signalwifimail"){
// 			console.log("entro disparar mail: "+armado.checked)
			var urlsendInformation2 = $(location).attr('pathname') + "/condicion_bajasignalwifi_mail/"+armado.checked;
			$.ajax({ url : urlsendInformation2,
				contentType: "application/json",
				dataType: 'json',
				success: function(data){
				}			
		});
		} 
	}

</script>

 <script type="text/javascript">
 
 function enviarToken(token){
	 console.log("username encontrado: "+ '${pageContext.request.userPrincipal.name}')
	 var enc = window.btoa('${pageContext.request.userPrincipal.name}');
	 var urlsendInformation = $(location).attr('pathname') + "/enviartoken/"+token+"/"+enc;
		$.ajax({ url : urlsendInformation,
			contentType: "application/json",
			dataType: 'json',
			success: function(data){
				console.log("exitoso");
			}			
	});
 }

 </script> 


<script type="text/javascript">
	function activarboton(nombre,valor){
		if(nombre=="armarcloud"){
			if(valor==null || valor==false)
				document.getElementById("armarcloud").checked = false;
			else
				document.getElementById("armarcloud").checked = true;
		}
		else if(nombre=="armedmail"){
			if(valor==null || valor==false)
				document.getElementById("armedmail").checked = false;
			else
				document.getElementById("armedmail").checked = true;
		}else if(nombre=="dispararcloud"){
			if(valor==null || valor==false)
				document.getElementById("dispararcloud").checked = false;
			else
				document.getElementById("dispararcloud").checked = true;
		}else if(nombre=="dispararmail"){
			if(valor==null || valor==false)
				document.getElementById("dispararmail").checked = false;
			else
				document.getElementById("dispararmail").checked = true;
		}else if(nombre=="signalwifi"){
			console.log("entro a la signal del WIFI: "+ nombre+": "+valor);
			if(valor==null || valor==false)
				document.getElementById("signalwifi").checked = false;
			else
				document.getElementById("signalwifi").checked = true;
		}else if(nombre=="signalwifimail"){
			console.log("entro a la signal del WIFI -MAIL : "+ nombre +": "+valor);
			if(valor==null || valor==false)
				document.getElementById("signalwifimail").checked = false;
			else
				document.getElementById("signalwifimail").checked = true;
		}
		
	}
</script>

