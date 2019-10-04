<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
    <title>cDash - Control for your IoT devices</title>
    <link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link type="text/css" rel="stylesheet" href="resources/loginresources/css/main.css">
	
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
						<h2 style="color:#FFFFFF; text-align:left !important;">controla <br/>tus dispositivos</h2>   <!-- FEDERICO cambie de h1 a h2 y agregué style -->		
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
			                      <input type="password" class="form-control form-control-user"  name="password" id = "userPassword"  placeholder="Contraseña">
			                    </div>
			                    <div class="form-group">
			                      <div class="custom-control custom-checkbox small">
			                        <input checked type="checkbox" name="remember-me" class="custom-control-input" id="customCheck"> <!-- FEDERICO agregué el CHECKED -->
			                        <label class="custom-control-label" for="customCheck">Recordar</label>
			                      </div>
			                    </div>
			                 	<div class="container-login100-form-btn">
									<button class="btn btn-primary btn-user btn-block" type="submit" id="sign">Ingresar</button>
								</div>
			                  </form>
			                  <hr>
			                  <div class="text-center">
			                    <a class="small" href="<c:url value='/forgot'/>" id="forgot">¿Olvidó su contraseña?</a>
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
			<div id="main" class="main-home"> <!-- FEDERICO agregué el clase main-home -->

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<!-- 2 Columns -->
								<div class="flex flex-2">
									<div class="col col1">
										<div class="image round fit">
											<a href="generic.html" class="link"><img src="resources/loginresources/css/images/pic01.jpg" alt="" /></a>
										</div>
									</div>
									<div class="col col2">
										<h3>Maecenas a gravida quam</h3>
										<p>Etiam posuere hendrerit arcu, ac blandit nulla. Sed congue malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet, enim magna cursus auctor lacinia nunc ex blandit augue. Ut vitae neque fermentum, luctus elit fermentum, porta augue. Nullam ultricies, turpis at fermentum iaculis, nunc justo dictum dui, non aliquet erat nibh non ex.</p>
										<p>Sed congue malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet, enim magna cursus auctor lacinia nunc ex blandit augue. Ut vitae neque fermentum, luctus elit fermentum, porta augue. Nullam ultricies, turpis at fermentum iaculis, nunc justo dictum dui, non aliquet erat nibh non ex. </p>
										<a href="#" class="button">Learn More</a>
									</div>
								</div>
						</div>
					</section>

				<!-- Section -->
					<section class="wrapper style2">
						<div class="inner">
							<div class="flex flex-2">
								<div class="col col2">
									<h3>Suspendisse quis massa vel justo</h3>
									<p>Etiam posuere hendrerit arcu, ac blandit nulla. Sed congue malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet, enim magna cursus auctor lacinia nunc ex blandit augue. Ut vitae neque fermentum, luctus elit fermentum, porta augue. Nullam ultricies, turpis at fermentum iaculis, nunc justo dictum dui, non aliquet erat nibh non ex.</p>
									<p>Sed congue malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet, enim magna cursus auctor lacinia nunc ex blandit augue. Ut vitae neque fermentum, luctus elit fermentum, porta augue. Nullam ultricies, turpis at fermentum iaculis, nunc justo dictum dui, non aliquet erat nibh non ex. </p>
									<a href="#" class="button">Learn More</a>
								</div>
								<div class="col col1 first">
									<div class="image round fit">
										<a href="generic.html" class="link"><img src="resources/loginresources/css/images/pic02.jpg" alt="" /></a>
									</div>
								</div>
							</div>
						</div>
					</section>

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<header class="align-center">
								<h2>Aliquam ipsum purus dolor</h2>
								<p>Cras sagittis turpis sit amet est tempus, sit amet consectetur purus tincidunt.</p>
							</header>
							<div class="flex flex-3">
								<div class="col align-center">
									<div class="image round fit">
										<img src="resources/loginresources/css/images/pic03.jpg" alt="" />
									</div>
									<p>Sed congue elit malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet. </p>
									<a href="#" class="button">Learn More</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="resources/loginresources/css/images/pic05.jpg" alt="" />
									</div>
									<p>Sed congue elit malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet. </p>
									<a href="#" class="button">Learn More</a>
								</div>
								<div class="col align-center">
									<div class="image round fit">
										<img src="resources/loginresources/css/images/pic04.jpg" alt="" />
									</div>
									<p>Sed congue elit malesuada nibh, a varius odio vehicula aliquet. Aliquam consequat, nunc quis sollicitudin aliquet. </p>
									<a href="#" class="button">Learn More</a>
								</div>
							</div>
						</div>
					</section>

			</div>

		<!-- Footer -->
			<footer id="footer" class="footer-home"> <!-- FEDERICO agregué el clase footer-home -->
				<div class="copyright">
					<ul class="icons">
						<li><a href="https://www.facebook.com/cdashiot/" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-snapchat"><span class="label">Snapchat</span></a></li>
					</ul>
					<p>&copy; cDash. All rights reserved. Design: <a href="https://templated.co">TEMPLATED</a>. Images: <a href="https://unsplash.com">Unsplash</a>.</p>
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
