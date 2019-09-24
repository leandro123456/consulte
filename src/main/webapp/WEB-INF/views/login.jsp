<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
    <title>cDash</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<%--     <link type="text/css"  href='<c:url value="../resources/assets/css/plugins.css" />'> --%>
    <link type="text/css" rel="stylesheet" href='<c:url value="../resources/vendor/fontawesome-free/css/all.min.css"/>'>
 	<link type="text/css" rel="stylesheet" href="../resources/css/sb-admin-2.css">
 	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  	
</head>

<body class="bg-gradient-primary">

  <script src='<c:url value="resources/vendor/jquery/jquery.min.js" />'></script>
  <script src='<c:url value="resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
  <script src='<c:url value="resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
  <script src='<c:url value="resources/js/sb-admin-2.min.js" />'></script>
  <script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>

  <div class="container">

    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">
				<c:if test="${param.error != null}">
				 	<input type="hidden" id ="mensaje1" value="Usuario o Contraseña Incorrectos, intentalo nuevamente">
					<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/login";', 5000);
					</script>
				</c:if>
				<c:if test="${param.logout != null}">
				 	<input type="hidden" id ="mensaje" value="La sesión ha sido cerrada correctamente.">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  //title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/login";', 5000);
					</script>
				</c:if>

                    <c:if test="${param.logout != null}">
                        <p class="logout">La sesión ha sido cerrada correctamente.</p>
                    </c:if>
		
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Bienvenido</h1>
                  </div>
                  <form class="user" action="<c:url value="/login" />" method="post" >
                    <div class="form-group">
                      <input id = "userName" name="user"  type="text" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="Email">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user"  name="password" id = "userPassword"  placeholder="Password">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" name="remember-me" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Remember Me</label>
                      </div>
                    </div>
<!--                     <input id="sign" type="button" class="btn btn-primary btn-user btn-block" value="Login"> -->
                 	<div class="container-login100-form-btn">
						<button class="btn btn-primary btn-user btn-block" type="submit" id="sign">Entrar</button>
					</div>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="<c:url value="/forgot" />" id="forgot">Olvido su contraseña?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="<c:url value="/signup" />" id="signup">Crear una Cuenta</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>
</body>
