<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Forgot Password</title>

   <!-- Custom fonts for this template-->
  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css"/>'>
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

 <!-- Custom styles for this template-->
  <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/sb-admin-2.min.css"/>'>

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-2">Olvido su Contraseña?</h1>
                    <p class="mb-4">Lo entendemos, cosas que pueden pasar. Simplemente ingrese su dirección de correo electrónico a continuación y le enviaremos una nueva contraseña y su codigo de activacion.</p>
                  </div>
                  <form  class="user" action="<c:url value='forgotpass'/>" method="post" enctype="multipart/form-data" autocomplete="off">
                    <div class="form-group">
                      <input type="email" name="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Ingrese su direccion de Email...">
                    </div>
<!--                     <a href="login.html" class="btn btn-primary btn-user btn-block"> -->
<!--                       Reset Password -->
<!--                     </a> -->
                    <button class="btn btn-primary btn-user btn-block" type="submit" id="sign">Recuperar Contraseña</button>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="register">Crear una Cuenta</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="login">Ya tiene una cuenta? Entrar</a>
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

  <!-- Core plugin JavaScript-->
  <script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>

  <!-- Custom scripts for all pages-->
  <script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>

</body>

