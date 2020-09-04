<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	<head>
	
	<script data-ad-client="ca-pub-9090543026082195" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	
		<title>cDash - Ayuda - Teclado Virtual Alarma</title>
		<link rel='shortcut icon' href='/resources/images/favicon.ico' type="image/x-icon"/>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="resources/loginresources/css/main.css" />
		
		
		
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
					<li><a href="ayuda">Ayuda</a	></li>
					<li><a href="Hfaq">Preguntas Frecuentes</a></li>
				</ul>
			</nav>

		<!-- Main -->
			<div id="main">

				<!-- Section -->
					<section class="wrapper">
						<div class="inner">
							<header class="align-center">
								<h1>Teclado Virtual para Alarma</h1>
								<p>Lo siguiente son las intrucciones para utillizar el widget de teclado virtual con un Bridge Coiaca BRDSC01.</p>
							</header>
							<div class="flex flex-2">
									<h3>Las Secciones del Widget</h3>
									<p>El widget Teclado Virtual para alrmas, posee 5 secciones: La zona identificaci&oacuten, la pantalla con indicadores, el teclado numerico, los botones axiliares, y los idicadores de estado de las zonas.</p>
									<p><img class="image fit" src="resources/loginresources/css/images/BRDSC01/brdsc01-keypad.png" /></p>
									<p>En la parte superior derecha del widget se encuentra la zona de identificaci&oacuten donde se observa el nombre del dispositivo, configurado en el momento del alta y el tipo del dispositivo al que esta asociado el widget. En el ejemplo de esta ayuda, el nombre configurado es "Alarma 02" y se trata de un Bridge Coiaca BRDSC01 para alarmas DSC.</p>
									<p>En la parte superior derecha de la zona de identificaci&oacuten se encuentra el &iacutecono para acceder a la configuraci&oacuten del widget donde podr&aacute compartir el uso con otros usuarios, configurar alertas, personalizar las etiquetas de las zonas y obtener ayuda.</p>
									<h3>La Pantalla</h3>
									<p>La pantalla es la zona rectangular azul que se encuentra debajo de la zona de identificaci&oacuten y arriba del teclado num&eacuterico. Esta zona cambiar&aacute de azul a rojo cuando la alarma se dispara.</p>
									<p>En la parte superior derecha de la pantalla se encuentra el indicador de conexi&oacuten y el indicador de nivel de se&ntildeal Wifi.</p>
									<p>El indicador de conexi&oacuten informar&aacute los siguientes estados:</p>
									<table>
									<tr><td width=10% ><b>Estado</b></td><td><b>Descripci&oacuten</b></td></tr>
									<tr><td style="vertical-align:middle">online</td><td style="vertical-align:middle">El dispositivo se encuentra conectado y reportando con normalidad.</td></tr>
									<tr><td style="vertical-align:middle">offline</td><td style="vertical-align:middle">El dispositivo se encuentra desconectado. La causa puede ser falta de suministro el&eacutectrico o falla en la conexi&oacuten Wifi.</td></tr>
									<tr><td style="vertical-align:middle">Alarm Disconnected</td><td style="vertical-align:middle">El dispositivo se encuentra conectado y reportando pero ha perdido conexi&oacuten con el sistema de alarma.</td></tr>
									</table>
									<p>En la parte central de la pantalla se ubican el indicador de partici&oacuten activa y el indicador de estado de las particiones.</p>
									<p>Hay modelos de alarmas que soportan mas de una particion (Generalmente hasta 3). Cada partici&oacuten se controla de forma independiente, es por eso que es necesario saber cual es la partici&oacuten sobre la que se est&aacute operando. Las operaciones que realice con el teclado virtual impactaran solo sobre la particion activa. Los sistemas de alarma que soportan una sola partici&oacuten o aquellos que soportan m&aacutes pero solo ah sidos configurados con una, deber&aacuten ser operados siempre con la partici&oacuten activa en 1.</p>
									<p>Debajo del indicador de partici&oacuten activa, en latras m&aacutes grandes se encuentra en indicador del estado de la partici&oacuten. Los estados posibles sob los siguientes:</p>
									<table>
									<tr><td width=10% ><b>Estado</b></td><td><b>Descripci&oacuten</b></td></tr>
									<tr><td style="vertical-align:middle">Disarmed</td><td style="vertical-align:middle">El sistema de alarma se encuentra desarmado.</td></tr>	
									<tr><td style="vertical-align:middle">Armed Home</td><td style="vertical-align:middle">El sistema de alarma se ecuentra armado en modo STAY. Es decir que se exeptuan las zonas de cuando la opci&oacuten "estoy en casa" est&aacute activada.</td></tr>
									<tr><td style="vertical-align:middle">Armed Away</td><td style="vertical-align:middle">El sistema de alarma se encuentra armado en modo AWAY.</td></tr>
									<tr><td style="vertical-align:middle">Pending</td><td style="vertical-align:middle">El sistema de alarma est&aacute esperando el tiempo programado de retardo para el armado.</td></tr>
									<tr><td style="vertical-align:middle">Triggered</td><td style="vertical-align:middle">Se ha disparado la alarma y a&uacuten no se ha desarmado.</td></tr>
									</table>
									<p>A la derecha de la pantalla se encuentran tres indicadores adicionales: El indicador de alarma lista, el de armado y el de problema (Trouble). El comportamiento de estos indicadores es el siguiente:</p>
									<table>
									<tr><td width=10% ><b>Indicador</b></td><td><b>Descripci&oacuten</b></td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-ready.png" /></td><td style="vertical-align:middle">El indicador se pondr&aacute de color verde cuando la alarma est&aacute lista para ser armada. Es decir, cuando todas las zonas se encuentren inactivas.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-armed.png" /></td><td style="vertical-align:middle">El indicador se pondra de color gris cuando la alarma se encuentre desarmada y rojo cuando esta se encuentre armada. Durante los conteos (retrasos de armado) el indicador se pondr&aacute de color amarillo.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-trouble.png" /></td><td style="vertical-align:middle">El indicador permanecerá de color gris cuando no existan problemas. Si el sistema reporta alg&uacuten problema, este indicador se pondr&aacute de color amarillo.</td></tr>
									</table>
									
									<h3>Los Botones</h3>
									<p>Debajo de la panalla y a la izquierda del teclado num&eacuterico se ecuentran los botones auxiliares. Al igual que en el teclado real, cada bot&oacuten tiene su funcion espec&iacutefica:</p>
									<table>
									<tr><td width=10%><b>Bot&oacuten</b></td><td><b>Funci&oacuten</b></td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-away.png" /></td><td style="vertical-align:middle">Bot&oacuten para armado fuera de casa (away). Este bot&oacuten es el equivalente a un presionado largo del n&uacutemero 2 en los teclados antiguos.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-home.png" /></td><td style="vertical-align:middle">Bot&oacuten para armado en casa (away). Este bot&oacuten es el equivalente a un presionado largo del n&uacutemero 1 en los teclados antiguos.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-chime.png" /></td><td style="vertical-align:middle">Bot&oacuten para activar y desactivar el sonido de apertura de puertas.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-fire.png" /></td><td style="vertical-align:middle">Este bot&oacuten dispara la alarma de inendio. Si su sistema de alarma est&aacute monitoreada, se conectar&aacute con el sistema de monitoreo para informar el evento.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-police.png" /></td><td style="vertical-align:middle">Este bot&oacuten dispara la alarma de policial. Si su sistema de alarma est&aacute monitoreada, se conectar&aacute con el sistema de monitoreo para informar el evento.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-other.png" /></td><td style="vertical-align:middle">Este bot&oacuten dispara la alarma accesotia, frecuentemente utilizada para emergencias m&eacutedicas. Si su sistema de alarma est&aacute monitoreada, se conectar&aacute con el sistema de monitoreo para informar el evento.</td></tr>
									<tr><td style="vertical-align:middle"><img src="resources/loginresources/css/images/BRDSC01/brdsc01-partitionchange.png" /></td><td style="vertical-align:middle">Estos bonotes le permitir&aacuten cambiar de partici&oacuten, en el case de que su sistema soporte m&aacutes de una, y este correctamente configurado.</td></tr>
									</table>
									<h3>Los Indicadores de Zonas</h3>
									<p>En la parte inferior del widget se encuentran los idicadores de zonas. Aqu&iacute se mostrar&aacuten aquellas zonas que se encuentren configuradas y hayan reportado actividad en algun momento, desde que se agreg&aacute el widget al panel de control.</p>
									<p>Puede que no todas las zonas se muestren en esta secci&oacuten. Para optimizar el espacio ocupado en la pantalla, sobre todo cuando se utiliza el panel de control en dispositivos m&oacuteviles, solo se mostrar&aacuten las zonas que rerporten actividad. Si alguna zona no se muestra, es porque el widget no ha recibido a&uacuten actividad de esa zona. Exite alg&uacuten sensor de esa zona para que reporte actividad. Una vez que la zona ha reportado por primera vez, ya no desaparecer&aacute de la pantalla.</p>
									<p><img class="image fit" src="resources/loginresources/css/images/BRDSC01/brdsc01-zones.png" /></p>
									<p>Los indicadores de zona se colorear&aacuten de azul cuando alg&uacuten sensor de la zona se active y permanecer&aacuten en gris cuando la zona no presente actividad.</p>
									
									
							</div>
						</div>
					</section>
					

				<center><a href="/Hfaq" class="button" style="background-color:#000000;">Preguntas Frecuentes</a></center>
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