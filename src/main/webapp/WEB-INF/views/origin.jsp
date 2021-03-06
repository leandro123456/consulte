<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/style.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/c3.min.css" />'>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>     
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/mqttRecibirMensajes.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/alarma/coneccionAlarma.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>	
	<link href='<c:url value="/resources/mqttResources/estiloalarma.css" />' rel="stylesheet" type="text/css">
  <title>cDash</title>
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
<!--   boton check -->
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<!-- boton check -->
</head>

<body id="page-top" onresize="modificarTamanoAlarma()">
	<div id="wrapper">	
		<jsp:include page="header.jsp" />
		<div class="container-fluid">
			
			<c:if test="${not empty msg}">
				 	<input type="hidden" id ="mensaje" value="${msg}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  title: x,
							  icon: "success",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
				</c:if>
				<c:if test="${not empty msg1}">
				 	<input type="hidden" id ="mensaje1" value="${msg1}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje1').value;
						swal({
							  title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
				</c:if>	
			
			
			<div class="row" id="cargadora">
				<c:forEach items="${vistas}" var="vista">
	                ${vista}
	          </c:forEach>
			</div>
			<div class="fixed">
				<a href="/mqttmanagment/home/newdevice"> <i
					class="fa fa-plus-circle fa-3x" aria-hidden="true"></i>
				</a>
			</div>
		</div>
	</div>
	<div class="visible">
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>
	</div>

	<jsp:include page="footer.jsp" />
</body>


<!-- script alarama -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<!-- script alarma -->


<script type="text/javascript">
	$(document).ready(function() {
		if(${cantidadSensores}!=0)
			startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr","mqttpwd",${topicos});
		if(${cantidadAlarma}!=0)
		startConnectAlarma("${hostalarma}","${puertoalarma}",${sslalarma},"${usuarioalarma}","${passalarma}",${topicosalarmas});
	});
</script>

<script type="text/javascript">
	function modificarTamanoAlarma(){
		 var w = window.outerWidth;
		  var h = window.outerHeight;
		  var txt = "Window size: width=" + w + ", height=" + h;
		  if(w<980 ){
			  console.log("la pantalla se redujo" + txt);
// 			  var content1 = document.getElementById('columnaderechaDSC010000000002').innerHTML;
// 			  var content2 = document.getElementById('columnaizquierdaDSC010000000002').innerHTML;

// 			  var combined = document.createElement('div');
// 			  content1.innerHTML = content2;			  
// 			  document.getElementById('columnaizquierdaDSC010000000002').innerHTML=content1 + " " + content2;
// 			  document.getElementById('columnaderechaDSC010000000002').style.display = 'none';
		  }
	} 
</script>




