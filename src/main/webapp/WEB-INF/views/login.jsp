<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
    <title>cDash - Control for your IoT devices</title>
    <link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
    <meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link type="text/css" rel="stylesheet" href="resources/loginresources/css/main.css">
	<script data-ad-client="ca-pub-9090543026082195" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script type="text/javascript"> <!-- FEDERICO le meti este script para que cambie la imagen de fondo y que no sea sismpre la misma. Si, me cop� -->
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

			<header id="header">
<!-- 				<img src="resources/loginresources/css/images/logo.png" alt="" width=50 height=auto /> -->
				<div class="logo"><a href="home">cDash<span> - Control for your IoT devices</span></a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="home">Inicio</a></li>
					<li><a href="signup">Crear Cuenta</a></li>
					<li><a href="/ayuda.html">Ayuda</a></li>
					<li><a href="/Hfaq.html">Preguntas Frecuentes</a></li>
				</ul>
			</nav>

			
			
				<c:if test="${param.error != null}">
				 	<input type="hidden" id ="mensaje1" value="Usuario o Contrasea Incorrectos, intentalo nuevamente">
					<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/";', 2000);
					</script>
				</c:if>
			
				<c:if test="${param.logout != null}">
				 	<input type="hidden" id ="mensaje" value="La sesi&oacuten ha sido cerrada correctamente.">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  //title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
						setTimeout('window.location.href = "/";', 2000);
					</script>
				</c:if>

                    <c:if test="${param.logout != null}">
                        <p class="logout">La sesi&oacuten ha sido cerrada correctamente.</p>
                    </c:if>
                    
		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<header>
						<p><img src="resources/loginresources/css/images/logo.png" alt=""/></p>
						<h2 style="color:#FFFFFF; text-align:left !important;">Controla <br/>tus dispositivos</h2>   <!-- FEDERICO cambie de h1 a h2 y agregu� style -->		
						<!-- <p>Aliquam libero augue varius non odio nec faucibus congue<br />felis quisque a diam rutrum tempus massa accumsan faucibus purus.</p> -->
					</header>


			<div class="row justify-content-center">
			      <div class="col-xl-10 col-lg-12 col-md-9">			
			        <div class="card o-hidden border-0 shadow-lg my-5">
			          <div class="card-body p-0">
			            <div class="row">
			              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
			              <div class="col-lg-6">
			                <div class="p-5">
			                  <form class="user" action="<c:url value='/login'/>" method="post" >
			                    <div class="form-group">
			                      <input id = "userName" name="user"  type="text" class="form-control form-control-user" aria-describedby="emailHelp" placeholder="Email">
			                    </div>
			                    <div class="form-group">
			                      <input type="password" class="form-control form-control-user"  name="password" id = "userPassword"  placeholder="Password">
			                    </div>
			                    <div class="form-group">
			                      <div class="custom-control custom-checkbox small">
			                        <input checked type="checkbox" name="remember-me" class="custom-control-input" id="customCheck"> <!-- FEDERICO agregu� el CHECKED -->
			                        <label class="custom-control-label" for="customCheck">Recordar</label>
			                      </div>
			                    </div>
			                 	<div class="container-login100-form-btn">
									<button class="btn btn-primary btn-user btn-block" type="submit" id="sign">Ingresar</button>
								</div>
			                  </form>
			                  <hr>
			                  <div class="text-center">
			                    <a class="small" href="<c:url value='/forgot'/>" id="forgot">&iquestOlvid&oacute su contrase&ntildea?</a>
			                  </div>
			                  <div class="text-center">
			                    <a class="small" href="<c:url value='/signup'/>" id="signup">Crear una cuenta</a>
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

		<!-- Main -->
			<div id="main" class="main-home"> <!-- FEDERICO agregu� el clase main-home -->

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<!-- 2 Columns -->
								<div class="flex flex-2">
									<div class="col col1">
										<div class="image round fit">
											<img src="resources/loginresources/css/images/home1.jpg" alt="" />
										</div>
									</div>
									<div class="col col2">
										<h3>Todos tus dispositivos IoT en un s&oacutelo lugar</h3>
										<p>Desde una simple luminaria hasta un sistema complejo de seguridad. cDash permite controlar uno o m&uacuteltiples dispositivos IoT en una &uacutenica aplicaci&oacuten, a la que podr&aacutes acceder con cualquier dispositivo: PC, Tablet o m&oacutevil.</p>
										<p>Adem&aacutes, podr&aacutes compartir el uso con quien quieras de forma segura y tambi&eacuten programar acciones que se desencadenan en funci&oacuten del estado que reporten tus dispositivos. Por ejemplo, si la luz de la sala est&aacute encendida y la temperatura supera los 25 grados, enciende el ventilador</p>
										<p>Todo esto de manera muy f&aacutecil. Si a&uacuten no tienen tu cuenta cDash, crea una, agrega un dispositivo al panel y toma el control.</p>
										<a href="#" class="button">Ingresar</a>
										<a href="<c:url value='/signup'/>" class="button">Crear Cuenta</a>
										<a style="background-color:#000000"href="/ayuda" class="button">Ayuda</a>
									</div>
								</div>
						</div>
					</section>

				<!-- Section -->
					<section class="wrapper style2">
						<div class="inner">
							<div class="flex flex-2">
								<div class="col col2">
									<h3>Makers</h3>
									<p>Tu proyecto est&aacute listo y ahora necesitas una aplicaci&oacuten para controlarlo. Ya sea para un cliente puntual o para tenga muchos usuarios, si utilizas el protocolo MQTT, podr&aacutes integrarlo a cDash y despreocuparte por el desarrollo de una aplicaci&oacuten para controlarlo.</p>
									<p>Creamos el widget necesario para tu proyecto de acuerdo con tu especificaci&oacuten, y podr&aacutes despreoc&uacutepate de la aplicaci&oacuten y aprovechar el entorno cDash para que tus proyectos puedan interactuar con otros productos ya integrados.</p>
									<p>El costo para puede ser m&aacutes bajo de lo que imaginas y hasta gratis. No dejes de contactarnos.</p>
									<a href="https://coiaca.com/index.php/contact/" class="button">Contacto</a>
								</div>
								<div class="col col1 first">
									<div class="image round fit">
										<img src="resources/loginresources/css/images/home2.jpg" alt="" />
									</div>
								</div>
							</div>
						</div>
					</section>
			</div>

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
