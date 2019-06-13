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
      
/*       esto es del archivo */
@font-face {
  font-family: 'dsc_icons';
  src:  url('/mqttmanagment/resources/fontsalarma/dsc_icons.eot?o9xp94');
  src:  url('/mqttmanagment/resources/fontsalarma/dsc_icons.eot?o9xp94#iefix') format('embedded-opentype'),
    url('/mqttmanagment/resources/fontsalarma/dsc_icons.ttf?o9xp94') format('truetype'),
    url('/mqttmanagment/resources/fontsalarma/dsc_icons.woff?o9xp94') format('woff'),
    url('/mqttmanagment/resources/fontsalarma/dsc_icons.svg?o9xp94#dsc_icons') format('svg');
  font-weight: normal;
  font-style: normal;
}

div.container {
  margin-top: 5px;
}

i.dsc-icon {
  /* use !important to prevent issues with browser extensions that change fonts */
  font-family: 'dsc_icons' !important;
  speak: none;
  font-style: normal;
  font-weight: normal;
  font-variant: normal;
  text-transform: none;
  line-height: 1;

  /* Better Font Rendering =========== */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-star:before {
  content: "\e905";
}
.icon-ac:before {
  content: "\e90d";
}
.icon-trouble:before {
  content: "\e90e";
}
.icon-armed:before {
  content: "\e90f";
}
.icon-check:before {
  content: "\e910";
}
.icon-thief:before {
  content: "\e90b";
}
.icon-alert:before {
  content: "\e90c";
}
.icon-sleep:before {
  content: "\e90a";
}
.icon-bypass:before {
  content: "\e909";
}
.icon-programming:before {
  content: "\e908";
}
.icon-flame:before {
  content: "\e907";
}
.icon-info:before {
  content: "\e906";
}
.icon-stay_away:before {
  content: "\e904";
}
.icon-stay_empty:before {
  content: "\e903";
}
.icon-bell:before {
  content: "\e900";
}
.icon-refresh:before {
  content: "\e902";
}
.icon-exit:before {
  content: "\e901";
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


							<div class="row">

								<div class="col-xs-12" id="lcd_container">
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


								<div class="row" id="buttons_area">

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
												class="btn btn-outline-dark keypad_button keypad_button_slim">
												<i class="dsc-icon icon-flame" title="Fire"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_a"
												class="btn btn-outline-dark keypad_button keypad_button_slim">
												<i class="dsc-icon icon-alert" title="Alert"></i>
											</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_p"
												class="btn btn-outline-dark keypad_button keypad_button_slim">
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
												class="btn btn-outline-dark keypad_button">1</button>
											<button type="button" id="btn_2"
												class="btn btn-outline-dark keypad_button">2</button>
											<button type="button" id="btn_3"
												class="btn btn-outline-dark keypad_button">3</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_4"
												class="btn btn-outline-dark keypad_button">4</button>
											<button type="button" id="btn_5"
												class="btn btn-outline-dark keypad_button">5</button>
											<button type="button" id="btn_6"
												class="btn btn-outline-dark keypad_button">6</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_7"
												class="btn btn-outline-dark keypad_button">7</button>
											<button type="button" id="btn_8"
												class="btn btn-outline-dark keypad_button">8</button>
											<button type="button" id="btn_9"
												class="btn btn-outline-dark keypad_button">9</button>
										</div>
										<div class="keypad_button_row">
											<button type="button" id="btn_*"
												class="btn btn-outline-dark keypad_button">
												<i class="dsc-icon icon-star"></i>
											</button>
											<button type="button" id="btn_0"
												class="btn btn-outline-dark keypad_button">0</button>
											<button type="button" id="btn_#"
												class="btn btn-outline-dark keypad_button">#</button>
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

	<div class="container-fluid">
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
<script type="text/javascript">
      var ws = null;
      var cnn_string = "mqtt.coiaca.com";//document.location.host;

      $(window).on('beforeunload', function() {
        if (ws != null) {
          ws.close();
        }
      });

      function startSocket() {

        if (location.protocol == "file:") {
          //for testing on local
          cnn_string = "mqtt.coiaca.com";
        }

        ws = new WebSocket('ws://' + cnn_string + '/PS3S1P120190323/swcmd');
        ws.binaryType = "arraybuffer";
        ws.onopen = function(e) {
          //addMessage("Connected");
          
        };
        ws.onclose = function(e) {
          console.log("WS disconnected");
          ws = null;
        };
        ws.onerror = function(e) {
          console.log("ws error", e);
        };
        ws.onmessage = function(e) {
          var msg = "";
          if (e.data instanceof ArrayBuffer) {
            msg = "BIN:";
            var bytes = new Uint8Array(e.data);
            for (var i = 0; i < bytes.length; i++) {
              msg += String.fromCharCode(bytes[i]);
            }
            console.log(msg);
          } else {
            if (IsJsonString(e.data)) {
              try {
                var obj = JSON.parse(e.data);
              } catch (err) {
                console.log(e.data);
              }
              if (obj instanceof Object) {
                //console.log(e.data);
                if ("open_zone_0" in obj) {
                  //console.log(Object.keys(obj));
                  var obj_keys = Object.keys(obj);
                  for (var j = 0; j < obj_keys.length; j++) {
                    for (var z = 0; z < 8; z++) {
                      var zone_id = "zone_" + ((z + 1) + (j * 8));
                      //console.log(zone_id);
                      //console.log($("#zone_"+zone_id+" > i").attr("class"));
                      if ((obj[obj_keys[j]] >> z) % 2 != 0) {
                        $("#" + zone_id + " > i").removeClass("far").addClass("fas").addClass("green_circle").removeClass("red_circle");
                      } else {
                        $("#" + zone_id + " > i").removeClass("fas").addClass("far").removeClass("green_circle");
                      }

                    }
                  }
                } else if ("alarm_zone_0" in obj) {
                  //console.log(Object.keys(obj));
                  var obj_keys = Object.keys(obj);
                  for (var j = 0; j < obj_keys.length; j++) {
                    for (var z = 0; z < 8; z++) {
                      var zone_id = "zone_" + ((z + 1) + (j * 8));
                      //console.log(zone_id);
                      //console.log($("#zone_"+zone_id+" > i").attr("class"));
                      if ((obj[obj_keys[j]] >> z) % 2 != 0 && !$("#" + zone_id + "").hasClass("alarm_zone")) {
                        $("#" + zone_id + "").addClass("alarm_zone");
                      } else {
                        $("#" + zone_id + "").removeClass("alarm_zone");
                      }

                    }
                  }
                } else if ("status_packet" in obj) {
                  var dsc_status = parseInt(obj.status_packet);

                  console.log("Status: " + dsc_status);

                  //ready icon logic
                  if ((dsc_status & 0x01) != 0 && !$("#ready_icon").hasClass("green_circle")) {
                    $("#ready_icon").addClass("green_circle");
                  } else if ((dsc_status & 0x01) == 0 && $("#ready_icon").hasClass("green_circle")) {
                    $("#ready_icon").removeClass("green_circle");
                  }

                  //armed icon logic
                  if ((dsc_status & 0x02) != 0 && !$("#armed_icon").hasClass("red_circle")) {
                    $("#armed_icon").addClass("red_circle");
                  } else if ((dsc_status & 0x02) == 0 && $("#armed_icon").hasClass("red_circle")) {
                    $("#armed_icon").removeClass("red_circle");
                  }

                  //memory light logic
                  if ((dsc_status & 0x04) != 0 && !$("#memory_icon").hasClass("orange_color")) {
                    $("#memory_icon").removeClass("far").addClass("fas").addClass("orange_color");
                  } else if ((dsc_status & 0x04) == 0 && $("#memory_icon").hasClass("orange_color")) {
                    $("#memory_icon").removeClass("fas").addClass("far").removeClass("orange_color");
                  }

                  //bypass light logic
                  if ((dsc_status & 0x08) != 0 && !$("#bypass_icon").hasClass("orange_color")) {
                    $("#bypass_icon").removeClass("far").addClass("fas").addClass("orange_color");
                  } else if ((dsc_status & 0x08) == 0 && $("#bypass_icon").hasClass("orange_color")) {
                    $("#bypass_icon").removeClass("fas").addClass("far").removeClass("orange_color");
                  }

                  //trouble light logic
                  if ((dsc_status & 0x10) != 0 && !$("#trouble_icon").hasClass("orange_color")) {
                    $("#trouble_icon").addClass("orange_color");
                  } else if ((dsc_status & 0x10) == 0 && $("#trouble_icon").hasClass("orange_color")) {
                    $("#trouble_icon").removeClass("orange_color");
                  }

                  //program light logic
                  if ((dsc_status & 0x20) != 0 && !$("#program_icon").hasClass("green_circle")) {
                    $("#program_icon").addClass("green_circle");
                  } else if ((dsc_status & 0x20) == 0 && $("#program_icon").hasClass("green_circle")) {
                    $("#program_icon").removeClass("green_circle");
                  }

                  //fire light logic
                  if ((dsc_status & 0x40) != 0 && !$("#fire_icon").hasClass("green_circle")) {
                    $("#fire_icon").addClass("green_circle");
                  } else if ((dsc_status & 0x40) == 0 && $("#fire_icon").hasClass("green_circle")) {
                    $("#fire_icon").removeClass("green_circle");
                  }

                  //backlight logic
                  if ((dsc_status & 0x80) != 0 && !$("#backlight_icon").hasClass("orange_color")) {
                    //backlight on
                    $("#backlight_icon").addClass("orange_color");
                  } else if ((dsc_status & 0x80) == 0 && $("#backlight_icon").hasClass("orange_color")) {
                    //backlight off
                    $("#backlight_icon").removeClass("orange_color");
                  }

                } else if ("lcd_lower" in obj) {
                  $("#second_line").html(obj.lcd_lower);
                } else if ("lcd_upper" in obj) {
                  $("#first_line").html(obj.lcd_upper);
                } else {
                  console.log(obj);
                }
              } else {
                console.log(obj);
              }
            } else {
              console.log(e.data);
            }
          }
        };

      }

      $(document).ready(function() {


        startSocket();

        $(".btn").click(function(event) {
          // Removes focus of the button.
          $(this).blur();
          beep();
        });

        for (var i = 1; i <= 64; i++) {
          $("#zones_list").append('<div class="zone" id="zone_' + i + '"><i class="far fa-circle"></i> Zone ' + i + '</div>');
        }

        $(".btn").click(function(e) {
          action = {
            'btn_single_click': $(this).attr("id")
          };
          ws.send(JSON.stringify(action));
          console.log(action);
        });



      });

      function IsJsonString(MyTestStr) {
        try {
          var MyJSON = JSON.stringify(MyTestStr);
          var json = JSON.parse(MyJSON);
          if (typeof(MyTestStr) == 'string')
            if (MyTestStr.length == 0)
              return false;
        } catch (e) {
          return false;
        }
        return true;
      }

      function beep() {
        console.log("beep");
        var snd = new Audio(
           "data:audio/wav;base64,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qJrx4KOQjahZxWbcZgztj2c49nKmkId44S71j0c8eV9yDK6uPRzx5X18eDvjvQ6yKo9ZSS6l//8elePK/Lf//IInrOF/FvDoADYAGBMGb7FtErm5MXMlmPAJQVgWta7Zx2go+8xJ0UiCb8LHHdftWyLJE0QIAIsI+UbXu67dZMjmgDGCGl1H+vpF4NSDckSIkk7Vd+sxEhBQMRU8j/12UIRhzSaUdQ+rQU5kGeFxm+hb1oh6pWWmv3uvmReDl0UnvtapVaIzo1jZbf/pD6ElLqSX+rUmOQNpJFa/r+sa4e/pBlAABoAAAAA3CUgShLdGIxsY7AUABPRrgCABdDuQ5GC7DqPQCgbbJUAoRSUj+NIEig0YfyWUho1VBBBA//uQZB4ABZx5zfMakeAAAAmwAAAAF5F3P0w9GtAAACfAAAAAwLhMDmAYWMgVEG1U0FIGCBgXBXAtfMH10000EEEEEECUBYln03TTTdNBDZopopYvrTTdNa325mImNg3TTPV9q3pmY0xoO6bv3r00y+IDGid/9aaaZTGMuj9mpu9Mpio1dXrr5HERTZSmqU36A3CumzN/9Robv/Xx4v9ijkSRSNLQhAWumap82WRSBUqXStV/YcS+XVLnSS+WLDroqArFkMEsAS+eWmrUzrO0oEmE40RlMZ5+ODIkAyKAGUwZ3mVKmcamcJnMW26MRPgUw6j+LkhyHGVGYjSUUKNpuJUQoOIAyDvEyG8S5yfK6dhZc0Tx1KI/gviKL6qvvFs1+bWtaz58uUNnryq6kt5RzOCkPWlVqVX2a/EEBUdU1KrXLf40GoiiFXK///qpoiDXrOgqDR38JB0bw7SoL+ZB9o1RCkQjQ2CBYZKd/+VJxZRRZlqSkKiws0WFxUyCwsKiMy7hUVFhIaCrNQsKkTIsLivwKKigsj8XYlwt/WKi2N4d//uQRCSAAjURNIHpMZBGYiaQPSYyAAABLAAAAAAAACWAAAAApUF/Mg+0aohSIRobBAsMlO//Kk4soosy1JSFRYWaLC4qZBYWFRGZdwqKiwkNBVmoWFSJkWFxX4FFRQWR+LsS4W/rFRb/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VEFHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAU291bmRib3kuZGUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAwNGh0dHA6Ly93d3cuc291bmRib3kuZGUAAAAAAAAAACU="
        );

        //var snd = new Audio("beepas1.mp3");

        snd.play();
      }
    </script>
<!-- script alarma -->

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




