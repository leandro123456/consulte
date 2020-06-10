<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
    <title>cDash - Recuperar Contraseña</title>
    <link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link type="text/css" rel="stylesheet" href="resources/loginresources/css/main.css">
	<script data-ad-client="ca-pub-9090543026082195" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script type="text/javascript"> <!-- FEDERICO le meti este script para que cambie la imagen de fondo y que no sea sismpre la misma. Si, me copé -->
		if (document.getElementById) { window.onload = swap };
		function swap() {
		var numimages=4;
		rndimg = new Array("resources/loginresources/css/images/banner4.jpg", "resources/loginresources/css/images/banner3.jpg", "resources/loginresources/css/images/banner2.jpg", "resources/loginresources/css/images/banner.jpg"); 
		x=(Math.floor(Math.random()*numimages));
		randomimage=(rndimg[x]);
		document.getElementById("banner").style.backgroundImage = "url("+ randomimage +")"; 
		}
	</script>
  	
  	<script async src="https://www.googletagmanager.com/gtag/js?id=G-6SSYQD4466"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-6SSYQD4466');
</script>
</head>

<body>
  <script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>

<!-- Header -->
			<header id="header" class="alt">
			<img src="resources/loginresources/css/images/logo.png" alt="" width=50 height=auto />
				<div class="logo"><a href="login">cDash <span>- Control for your IoT devices</span></a></div>
			</header>

<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="index.html">Inicio</a></li>
					<li><a href="#">Ayuda</a></li>
					<!-- <li><a href="generic.html">Generic</a></li>
					<li><a href="elements.html">Elements</a></li> -->
				</ul>
			</nav>


		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<header>
						<p><img src="resources/loginresources/css/images/logo.png" alt=""/></p>
						<h2 style="color:#FFFFFF; text-align:left !important;">&iquestOlvidó <br/> su Contrase&ntildea?</h2>   <!-- FEDERICO cambie de h1 a h2 y agregué style -->		
<!-- 						<p>Lo entendemos, cosas que pueden pasar.<br/> Simplemente ingrese su dirección de correo electrónico a continuación <br/> y le enviaremos una nueva contraseña y su codigo de activacion.</p> -->
					</header>
	
			<div class="row justify-content-center">
		      <div class="col-xl-10 col-lg-12 col-md-9">
		        <div class="card o-hidden border-0 shadow-lg my-5">
		          <div class="card-body p-0">
		            <div class="row">
		              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
		              <div class="col-lg-6">
		                <div class="p-5">
		                  <form  class="user" action="<c:url value='forgotpass'/>" method="post" enctype="multipart/form-data" autocomplete="off">
		                    <div class="form-group">
		                      <input type="email" name="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Ingrese su direccion de Email...">
		                    </div>
		                    <button class="btn btn-primary btn-user btn-block" type="submit" id="sign">Recuperar Contrase&ntildea</button>
		                  </form>
		                  <hr>
		                  <div class="text-center">
		                    <a class="small" href="register">Crear una Cuenta</a>
		                  </div>
		                  <div class="text-center">
		                    <a class="small" href="login">&iquestYa tiene una cuenta? Entrar</a>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
		        </div>
	        </div>
        </div>
			<!-- <a href="#main" class="button big scrolly">Learn More</a> -->
		</div>
	</section>

			<!-- Footer -->
			<footer id="footer">
				<div class="copyright">
					<ul class="icons">
						<!-- <li><a target="_blank" href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li> -->
						<li><a target="_blank" href="https://www.facebook.com/cdashiot/" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<!-- <li><a target="_blank" href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a target="_blank" href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li> -->
					</ul>
					<p>&copy; Untitled. All rights reserved. Design: <a target="_blank" href="https://templated.co">TEMPLATED</a>. Images: <a target="_blank" href="https://unsplash.com">Unsplash</a>.</p>
				</div>
			</footer>

		<!-- Scripts -->
			<script src='<c:url value="resources/loginresources/js/jquery.min.js" />'></script>
			<script src='<c:url value="resources/loginresources/js/jquery.scrolly.min.js" />'></script>
			<script src='<c:url value="resources/loginresources/js/jquery.scrollex.min.js" />'></script>
			<script src='<c:url value="resources/loginresources/js/skel.min.js" />'></script>
			<script src='<c:url value="resources/loginresources/js/util.js" />'></script>
			<script src='<c:url value="resources/loginresources/js/main.js" />'></script>
	</body>