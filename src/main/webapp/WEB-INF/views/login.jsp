<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<meta name="description" content="">
  	<meta name="author" content="">

  	<title>cDash</title>

  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>'>
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/sb-admin-2.min.css"/>'>

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">
		
		
		
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
          <form class="login100-form validate-form">
					<span class="login100-form-logo">
						<i class="zmdi zmdi-landscape"></i>
					</span>

					<span class="login100-form-title p-b-34 p-t-27">
						
					</span>
                    <c:if test="${not empty msg1}">
                        <div class="alert alert-warning">
                        	${msg1}
                    	</div>
                    </c:if>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid user name and password.</p>
                        </div>
                    </c:if>
                    
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty msg2}">
                        <div class="alert alert-success" id="msg2Div">
                        	${msg2}
                    	</div>
                    </c:if>
                    </form>
            <!-- Nested Row within Card Body -->
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
                        <input type="checkbox" class="custom-control-input" id="customCheck">
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

  <!-- Bootstrap core JavaScript-->
  <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
  <script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
  <script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>

</body>
</html>
