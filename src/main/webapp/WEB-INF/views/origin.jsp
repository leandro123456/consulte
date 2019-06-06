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

<!-- para la alarma -->

<style rel="stylesheet/scss" type="text/css">
      .btn {
        &:hover {
          background-color: #FFF !important;
          }
        }
    </style>

    <link rel="stylesheet" href="style.css" />



    <style type="text/css" media="screen">
      @import url('https://fonts.googleapis.com/css?family=IBM+Plex+Sans');
      @import url('https://fonts.googleapis.com/css?family=Codystar');


      html {
        font-size: 16px;
      }

      @media screen and (min-width: 955px) {
        body {
          font-size: 1.265rem !important;
        }
        button.keypad_button {
          width: 5rem !important;
          font-size: 1.2rem !important;
        }
        button.keypad_button_small {
          width: 2.3rem !important;
        }
        div.virtual_lcd {
          font-size: 2.36rem !important;
        }
        div#zones_list {
          font-size: 0.75rem !important;
        }
        i.dsc-icon.icon-star {
          font-size: 1.0rem !important;
        }

      }

      body {
        font-family: 'IBM Plex Sans', sans-serif;
        font-size: calc(0.38rem + 1.5vw);
      }

      .greenbullet {
        color: #28a745;
      }

      .redbullet {
        color: #dc3545;
      }

      p.state_title {
        display: inline-block;
      }

      p.state_title:not(:first-child) {
        margin-left: 10px;
      }

      button.keypad_button {
        width: calc(0.6rem + 10vw);
        border-radius: 1.5rem;
        font-weight: bold;
        font-size: calc(0.6rem + 1vw);
        padding: 0.2rem;
        max-width: 5rem;
      }

      button.keypad_button_small {
        width: 7.0vw;
        padding: 2px;
        max-width: 2.2rem;
        line-height: 1 !important;
      }

      button.keypad_button_slim {
        width: 14.5vw;
        padding: 2px;
        line-height: 1;
        max-width: 4.5rem;
        line-height: 1 !important;
      }

      button.keypad_button_control {
        width: 11vw;
        padding: 2px;
        font-size: calc(0.6rem + 1vw);
        line-height: 1 !important;
        max-width: 4.0rem;
      }


      div.virtual_lcd {
        background-color: #5f7cd8;
        color: #ffffff;
        font-family: 'Codystar', cursive;
        font-size: calc(1.3rem + 1.8vw);
        font-weight: bold;
        padding: 2px 10px;
        border-radius: 8px;
        flex: 1;
      }

      div#lcd_container {
        width: 100%;
        margin: 0 auto;
        border: 1px solid lightgrey;
        padding: 7px;
        background-color: whitesmoke;
        border-radius: 10px;
        white-space: nowrap;
        margin-bottom: 10px;
        display: flex;
      }

      div.keypad_button_row {
        margin: 12px 10px;
        text-align: center;
        white-space: nowrap;
      }

      div.container {
        border: 1px solid #939393;
        border-radius: 20px;
        padding: 10px;
        width: 100vw;
        background-color: #cacaca;
        max-width: 500px;
        min-width: 320px;
        margin-bottom: 10px;
      }

      div.inline_container {
        display: inline-block;
      }

      div.status_icons {
        text-align: center;
        padding: 0px;
        margin: 0px 0px 0px 5px;
        flex: 0;
        color: grey;
      }

      div.status_icons i {
        display: block;
        padding: 4px 0px;
        margin: 0px;
      }

      button.btn {
        background-color: #d9dcdf;
        border: 2px solid #898e94;
      }



      div#left_buttons,
      div#right_buttons,
      div#keypad_container {
        border: 1px solid lightgrey;
        padding: 7px 0px;
        border-radius: 8px;
        background-color: whitesmoke;
      }

      div#left_buttons {
        flex: 1;
        max-width: 5.9rem;
        line-height: 1 !important;
      }

      div#right_buttons div.keypad_button_row {
        margin: 8px 10px;
      }

      div#keypad_container {
        flex: 2;
        margin: 0px 10px;
        max-width: 17.5rem;
        line-height: 1.5 !important;
      }

      div#right_buttons {
        flex: 0;
        max-width: 5.8rem;
        line-height: 1 !important;
      }

      div#buttons_area {
        display: flex;
      }

      div.zones {
        background-color: whitesmoke;
      }

      div#zones_list {
        border-top: 1px solid grey;
        margin-top: 5px;
        padding-top: 5px;

        display: grid;
        grid-template-rows: repeat(16, auto);
        grid-gap: 10px;
        grid-auto-flow: column;
        font-size: 0.75rem;
      }

      div#regular_icons {
        display: flex;
        justify-content: space-between;
      }

      .green_circle {
        color: green;
      }

      .red_circle {
        color: red;
      }

      .orange_color {
        color: orange;
      }
      i.dsc-icon.icon-star {
        font-size: calc(0.45rem + 0.8vw);
        padding: 1rem 0px;

      }

      .alarm_zone {
        color: red;
      }
    </style>


<!-- para la alarma -->


</head>

<body id="page-top">
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div class="container-fluid">
			<div class="row" id="cargadora">
				<c:forEach items="${vistas}" var="vista">
	                ${vista}
	          </c:forEach>

<!-- alarma -->
<div class="col-lg-6 mb-4"> 
	<div class="card shadow mb-4"> 
		<div class="card-header py-3">	
			<h6 class="m-0 font-weight-bold text-primary">Alarm</h6>
		</div>
		<div class="card-body">


	<div class="col-lg-6">

      <div id="lcd_container">
        <div class="virtual_lcd">
          <div id="first_line">&nbsp;</div>
          <div id="second_line">&nbsp;</div>
        </div>
        <div class="status_icons">
          <i class="dsc-icon icon-check" id="ready_icon" title="Ready"></i>
          <i class="dsc-icon icon-armed" id="armed_icon" title="Armed"></i>
          <i class="dsc-icon icon-trouble" id="trouble_icon" title="System Trouble"></i>
          <i class="dsc-icon icon-ac" id="ac_icon" title="AC Present"></i>
        </div>
      </div>


      <div id="buttons_area">

        <div id="left_buttons">
          <div class="keypad_button_row">
            <button type="button" class="btn btn-outline-dark keypad_button keypad_button_small"><i class="fas fa-chevron-left"></i></button>
            <button type="button" class="btn btn-outline-dark keypad_button keypad_button_small"><i class="fas fa-chevron-right"></i></button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_f" class="btn btn-outline-dark keypad_button keypad_button_slim">
          <i class="dsc-icon icon-flame" title="Fire"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_a" class="btn btn-outline-dark keypad_button keypad_button_slim">
            <i class="dsc-icon icon-alert" title="Alert"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_p" class="btn btn-outline-dark keypad_button keypad_button_slim">
            <i class="dsc-icon icon-thief" title="Panic"></i>
          </button>
            <div class="keypad_button_row">
              <i class="fas fa-lightbulb" id="backlight_icon" title="Backlight"></i>
            </div>
          </div>



        </div>

        <div id="keypad_container">
          <div class="keypad_button_row">
            <button type="button" id="btn_1" class="btn btn-outline-dark keypad_button">1</button>
            <button type="button" id="btn_2" class="btn btn-outline-dark keypad_button">2</button>
            <button type="button" id="btn_3" class="btn btn-outline-dark keypad_button">3</button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_4" class="btn btn-outline-dark keypad_button">4</button>
            <button type="button" id="btn_5" class="btn btn-outline-dark keypad_button">5</button>
            <button type="button" id="btn_6" class="btn btn-outline-dark keypad_button">6</button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_7" class="btn btn-outline-dark keypad_button">7</button>
            <button type="button" id="btn_8" class="btn btn-outline-dark keypad_button">8</button>
            <button type="button" id="btn_9" class="btn btn-outline-dark keypad_button">9</button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_*" class="btn btn-outline-dark keypad_button"><i class="dsc-icon icon-star"></i></button>
            <button type="button" id="btn_0" class="btn btn-outline-dark keypad_button">0</button>
            <button type="button" id="btn_#" class="btn btn-outline-dark keypad_button">#</button>
          </div>
        </div>

        <div id="right_buttons">
          <div class="keypad_button_row">
            <button type="button" id="btn_s" class="btn btn-outline-dark keypad_button keypad_button_control">
            <i class="dsc-icon icon-stay_away"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_w" class="btn btn-outline-dark keypad_button keypad_button_control">
            <i class="dsc-icon icon-stay_empty"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" id="btn_c" class="btn btn-outline-dark keypad_button keypad_button_control">
          <i class="dsc-icon icon-bell"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" class="btn btn-outline-dark keypad_button keypad_button_control">
          <i class="dsc-icon icon-refresh"></i>
          </button>
          </div>
          <div class="keypad_button_row">
            <button type="button" class="btn btn-outline-dark keypad_button keypad_button_control">
            <i class="dsc-icon icon-exit"></i>
          </button>
          </div>

        </div>
      </div>
    </div>

    <div class="col-lg-6">
      <div id="regular_icons">
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
      <div id="zones_list">

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


<script type="text/javascript">
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




