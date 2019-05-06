<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

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
	<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>	
  <title>Devices - Dashboard</title>
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
</head>

<body id="page-top">
  	  <div id="wrapper">
		<jsp:include page="header.jsp" />
        <div class="container-fluid">
          <div class="row" id ="cargadora">
	           <c:forEach items="${vistas}" var="vista">
	                ${vista}
	          </c:forEach> 
          </div>
          <div class="fixed" >
             <a href="/mqttmanagment/home/newdevice">
                <i class="fa fa-plus-circle fa-3x" aria-hidden="true"></i>
             </a>
          </div>
        </div>
      </div>
      
      <div class="visible">
      <a class="scroll-to-top rounded" href="#page-top">
	    <i class="fas fa-angle-up"></i>
	  </a>
      </div>
      
      <jsp:include page="footer.jsp" />
</body>


<script type="text/javascript">
	$(document).ready(function(){
                updateWiget();
                animateprogress("humedad",50);
                animateprogress("temperaturac",25);
				animateprogress("sensacionc",27);
				animateprogress("temperaturaf",77);
				animateprogress("sensacionf",80.6);
		//startConnect();
	});
	
	//document.querySelector ('#boton').addEventListener ('click', function() { 
//		animateprogress("humedad",49);   
// 	});
</script>

<script type="text/javascript">
// 	$(document).ready(function() {
// 		//cargarDivs();
// 		getElements();
// 		function getElements() {
// 			var mail = "${user.email}";
// 			var _url = $(location).attr('pathname') +mail+"/elements";
// 			$.ajax({ url : _url,
// 				contentType: "application/json",
// 				dataType: 'json',
// 				success: function(data){
// 					for(var i = 0; i < data.deviceserial.length ; i++){
// 						var item=data.deviceserial[i];
// 						console.log(item);
// 						var vara = "${vistas[i]}";
// 						console.log("hello: "+ vara);
// 						var vara = '4564dsd';
// 						console.log(data.item[0]);
// 						for(var j=0; j< data."4564dsd".length; j++){
// 							var conjunto = data.item[j];
// 							 console.log(conjunto);
// 						}
// 						var item = "${deviceserial[0]}";
// 						console.log("item: "+item);
// 						var valor = "${item}";
// 						console.log("valor: "+valor);
// 						$("cargadora").append(vara);
// 					}
// 				}
// 			});
// 		}   
// 	})	
	
// 	function test(a) {
// 		console.log(data.a.length);
// 	}
</script>


