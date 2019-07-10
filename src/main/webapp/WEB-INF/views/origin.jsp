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

<!-- boton check -->
</head>

<body id="page-top">
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div class="container-fluid">
		
			<div class="row" id="cargadora">
				<c:forEach items="${vistas}" var="vista">
	                ${vista}
	          </c:forEach>


<script>
function toggleText(button_id, variable) 
{
   var el = document.getElementById(button_id);
   if (variable == "yes"){
       el.firstChild.data = "Encendido";
   }
   if (variable == "no"){
     el.firstChild.data = "Apagado";
   }
   else
	   console.log("no encontro el nombre de la variable: "+ variable +" iddelboton: "+ button_id);
}
</script>
<!-- alarma -->
<div class="col-lg-6 mb-4"> 
	<div class="card shadow mb-4"> 
		<div class="card-header py-3">	
			<h6 class="m-0 font-weight-bold text-primary">Alarm</h6>
		</div>
		<div class="card-body">


							<div class="col-xs-12">

								<div id="lcd_container">
									<div class="virtual_lcd">
										<div id="first_line">&nbsp;</div>
										<div id="second_line">&nbsp;</div>
									</div>
									<div class="status_icons">
										<i class="dsc-icon icon-check" id="ready_icon" title="Ready"></i>
										<i class="dsc-icon icon-armed" id="armed_icon" title="Armed"></i>
										<i class="dsc-icon icon-trouble" id="trouble_icon"
											title="System Trouble"></i> <i class="dsc-icon icon-ac"
											id="ac_icon" title="AC Present"></i>
									</div>
								</div>


								<div class="col-xs-12" id="buttons_area">

									<div id="left_buttons">
										<div class="keypad_button_row">
											<button type="button"
												class="btn btn-outline-dark keypad_button keypad_button_small">
												<i class="fas fa-chevron-left"></i>
											</button>
											<button type="button"
												class="btn btn-outline-dark keypad_button keypad_button_small">
												<i class="fas fa-chevron-right"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_f"
												class="btn btn-outline-dark keypad_button keypad_button_slim" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-fire')">
												<i class="dsc-icon icon-flame" title="Fire"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_a"
												class="btn btn-outline-dark keypad_button keypad_button_slim" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-alert')">
												<i class="dsc-icon icon-alert" title="Alert"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_p"
												class="btn btn-outline-dark keypad_button keypad_button_slim" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-panic')">
												<i class="dsc-icon icon-thief" title="Panic"></i>
											</button>
											<div class="keypad_button_row">
												<i class="fas fa-lightbulb" id="backlight_icon"
													title="Backlight"></i>
											</div>
										</div>
									</div>

									<div id="keypad_container">
										<div class="keypad_button_row">
											<button type="button" id="btn_1"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-1')">1</button>
											<button type="button" id="btn_2"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-2')">2</button>
											<button type="button" id="btn_3"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-3')">3</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_4"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-4')">4</button>
											<button type="button" id="btn_5"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-5')">5</button>
											<button type="button" id="btn_6"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-6')">6</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_7"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-7')">7</button>
											<button type="button" id="btn_8"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-8')">8</button>
											<button type="button" id="btn_9"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-9')">9</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_*"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-*')">
												<i class="dsc-icon icon-star"></i>
											</button>
											<button type="button" id="btn_0"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-0')">0</button>
											<button type="button" id="btn_#"
												class="btn btn-outline-dark keypad_button" onclick="Connecttotal('mqtt.coiaca.com','8080','mqttusr','mqttpwd','DSC010000000001','alarm-#')">#</button>
										</div>
									</div>

									<div id="right_buttons">
										<div class="keypad_button_row">
											<button type="button" id="btn_s"
												class="btn btn-outline-dark keypad_button keypad_button_control">
												<i class="dsc-icon icon-stay_away"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_w"
												class="btn btn-outline-dark keypad_button keypad_button_control">
												<i class="dsc-icon icon-stay_empty"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_c"
												class="btn btn-outline-dark keypad_button keypad_button_control">
												<i class="dsc-icon icon-bell"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button"
												class="btn btn-outline-dark keypad_button keypad_button_control">
												<i class="dsc-icon icon-refresh"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button"
												class="btn btn-outline-dark keypad_button keypad_button_control">
												<i class="dsc-icon icon-exit"></i>
											</button>
										</div>

									</div>
								</div>
							</div>

	<div class="col-xs-12">
		<p> </p>
      <div class="row" id="regular_icons">
        <div class="zone inline_container">
          <i class="far fa-circle" id="fire_icon"></i> Fire
        </div>
        <div class="zone inline_container">
          <i class="far fa-circle" id="memory_icon"></i> Memory
        </div>
        <div class="zone inline_container">
          <i class="far fa-circle" id="bypass_icon"></i> Bypass
        </div>
        <div class="zone inline_container">
          <i class="far fa-circle" id="program_icon"></i> Program
        </div>
        <div class="zone inline_container">
          <i class="far fa-circle" id="pgm_icon"></i> PGM
        </div>
      </div>
      <div class="row" id="zones_list">

      </div>

    </div>

		</div>
	</div>
</div>

<!-- alarma -->
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
// 	function sendMQTTMessage(sonoffserial){
// 		var checkBox=document.getElementById("sonoffpower");
// 		  if (checkBox.checked == true){
// 		  	  	document.getElementById("sonoffpower1").value = true;
// 		  }else{
// 		  	  	document.getElementById("sonoffpower1").value = false;
// 		  }
// 		var formvalue="push"+sonoffserial;
// 		console.log("estevalor: "+ formvalue);
// 		document.getElementById(formvalue).submit();
// 	}
</script>


<script type="text/javascript">
// 	function sleep(milliseconds) {
// 	 var start = new Date().getTime();
// 	 for (var i = 0; i < 1e7; i++) {
// 	  if ((new Date().getTime() - start) > milliseconds) {
// 	   break;
// 	  }
// 	 }
// 	}

	$(document).ready(function() {
		updateWiget();
		animateprogress("humedad", 50);
		animateprogress("temperaturac", 25);
		animateprogress("sensacionc", 27);
		animateprogress("temperaturaf", 77);
		animateprogress("sensacionf", 80.6);
		//startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr", "mqttpwd","/DSC010000000001/dsc/Get/Partition1");
		//sleep(20000);
		//console.log("esta es la lista de conexiones: "+ '${topicos}');
		//var top= ${topicos}[3];
// 		${topicos}.forEach(myFunction);

// 		function myFunction(item, index) { 
// 		  startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr","mqttpwd",item);
// 		}

		startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr","mqttpwd",${topicos});
		//startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr", "mqttpwd","RConfig/debug");
		//startConnectSonoff("mqtt.coiaca.com", 8080, false, "mqttusr", "mqttpwd","RConfig/WTHUSB000000001");
	});
</script>




