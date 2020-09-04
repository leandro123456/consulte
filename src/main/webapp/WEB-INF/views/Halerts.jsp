<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
	<title>cDash - Ayuda sobre alertas</title>
	<link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />   
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link type="text/css" rel="stylesheet" href="resources/loginresources/css/main.css">

	<script data-ad-client="ca-pub-9090543026082195" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>	
<script async src="https://www.googletagmanager.com/gtag/js?id=G-6SSYQD4466"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-6SSYQD4466');
</script>
		
		
	</head>
	<body class="subpage">

		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="home">cDash<span> - Control for your IoT devices</span></a></div>
				<a href="#menu">Menu</a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links">
					<li><a href="home">Inicio</a></li>
					<li><a href="signup">Crear Cuenta</a></li>
					<li><a href="ayuda">Ayuda</a></li>
					<li><a href="Hfaq">Preguntas Frecuentes</a></li>
				</ul>
			</nav>

		<!-- Main -->
			<div id="main">

				<!-- Section -->
					<section class="wrapper">
						<div class="inner">
							<header class="align-center">
								<h1>Configuraci&oacuten de alertas</h1>
								<p>Aprende c&oacutemo configurar las alertas de tus dispositivos.</p>
							</header>
							<!-- <div class="flex flex-2"> -->
									<!-- <h3>Agrega un dispositivo</h3> -->
									<!-- <p>Agregar un dispositivo al panel de control es simple. Selecciona el simbolo + que se encuentra en la parte superior derecha de la pantalla para iniciar el proceso.</p> -->
									<!-- <p><img class="image fit" src="images/ayuda/devices01.jpg" /></p> -->
									<!-- <p>Selecciona el tipo de dispositivo que deseas agregar.</p> -->
									<!-- <p><img class="image fit" src="images/ayuda/devices02.jpg" /></p> -->
									<!-- <p>Luego, ompleta los datos y selecciona <b>"Continuar"</b>. Puedes completar libremente los campos "Nombre del dispositivo" y "Descripción", ya que utilizarás esta información para diferenciar este dispositiv de otros en el panel de control. <b>Es muy importante que ingreses correctamente el numero de serie del dispositivo</b> para poder identficarlo y que funcione correctamente. Por ejemplo, para un dispositivo Bridge Coiaca BRDCS01 para paneles de alarma DCS, el numero de serie es del tipo DSC010000000xxx</p> -->
									<!-- <p><img class="image fit" src="images/ayuda/devices04.jpg" /></p> -->
									<!-- <p>Al ingresar los datos del dispositivo de forma correcta se incluirá en el panel de control el wiget correspondiente y si el equipo se encuentra encendido y configurado, comenzarás a ver la iformación en pantalla y, dependiendo del modelo del dispositivo, también podrás controlarlo.</p> -->
										
									
									<!-- <p>- Consulta las <a href="/Hfaq"><b>preguntas frecuentes</b></a> para más información</p> -->
									
							<!-- </div> -->
						<!-- </div> -->
					</section>
					

				<!-- Section -->
					<section class="wrapper style1">
						<div class="inner">
							<header class="align-center">
								<h2>M&aacutes informaci&oacuten</h2>
								<!-- <p>Encuentra aquí algunas guías para la utilización de la aplicación.</p> -->
							</header>
							<div class="flex flex-3">
								<div class="col align-center">
									<!-- <div class="image round fit"> -->
										<!-- <img src="images/pic03.jpg" alt="" /> -->
									<!-- </div> -->
									<p>El primer paso para controlar tus dispositivos es crear una cuenta personal en cDash. </p>
									<a href="/HsignUp" class="button">Sobre las cuentas</a>
								</div>
								<div class="col align-center">
									<!-- <div class="image round fit"> -->
										<!-- <img src="images/pic05.jpg" alt="" /> -->
									<!-- </div> -->
									<p>Si ya tienes tu cuenta, el pr&oacuteximo paso es agregar tus dispositivos al panel de control. </p>
									<a href="/Hadd" class="button">Sobre los dispositivos</a>
								</div>
								<div class="col align-center">
									<!-- <div class="image round fit"> -->
										<!-- <img src="images/pic04.jpg" alt="" /> -->
									<!-- </div> -->
									<p>Aprende a configurar alertas y ent&eacuterate de todo, d&oacutende sea que te encuentres. </p>
									<a href="Halerts" class="button">Sobre las alertas</a>
								</div>
							</div>
						</div>
					</section><center><a href="/Hfaq" class="button" style="background-color:#000000;">Preguntas Frecuentes</a></center>
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
</html>