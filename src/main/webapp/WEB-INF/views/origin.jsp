<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/style.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/c3.min.css" />'>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js" type="text/javascript"></script>     
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>	
  <title>Devices - Dashboard</title>
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
<!--   boton check -->
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<!-- boton check -->



<script>
	$(document).ready(function() {
		alert("esta listo");
		alert("cargue la: "+ "${sonoffserial0}");
		if("${sonoffserial1}"!="")
			alert("cargue la: "+ "${sonoffserial1}");
	});
</script>

<script type="text/javascript">
	function sendMQTTMessage(sonoffserial){
		var checkBox=document.getElementById("sonoffpower");
		  if (checkBox.checked == true){
		  	  	document.getElementById("sonoffpower1").value = true;
		  }else{
		  	  	document.getElementById("sonoffpower1").value = false;
		  }
		var formvalue="push"+sonoffserial;
		console.log("estevalor: "+ formvalue);
		document.getElementById(formvalue).submit();
	}
</script>




</head>

<body id="page-top">
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div class="container-fluid">
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

<script> 
// 	function cambiarsonoff(){
// 		var cantidad = ${sonoffcantidad};
// 		for(var i=0; i<cantidad; i++){
// 			alert("entro!!!");
// 			reeemplazar los "cambiarsonoff"+i =${sonoffserial+i};
//			console.log("obtuve la variable: "+ ${"sonoffserial"+i});
// 		}
// 	}
</script>

<script>
	$(document).ready(function() {
		updateWiget();
		animateprogress("humedad", 50);
		animateprogress("temperaturac", 25);
		animateprogress("sensacionc", 27);
		animateprogress("temperaturaf", 77);
		animateprogress("sensacionf", 80.6);
// 		cambiarsonoff();
		//startConnect();			
	});

	//document.querySelector ('#boton').addEventListener ('click', function() { 
	//		animateprogress("humedad",49);   
// 		});
</script>




