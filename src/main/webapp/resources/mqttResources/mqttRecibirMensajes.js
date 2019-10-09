      function startSocket(direccion, topico) {
          $(window).on('beforeunload', function() {
              if (ws != null) {
                ws.close();
              }
            });
    	var ws = null;
    	var cnn_string = direccion;//"mqtt.coiaca.com:8080";//document.location.host;
//        if (location.protocol == "file:") {
//          cnn_string = "mqtt.coiaca.com:8080";
//        }
    	//topico = '/DSC010000000001/dsc/Get/Partition1'

        ws = new WebSocket('ws://' + cnn_string + topico);
        ws.binaryType = "arraybuffer";
        ws.onopen = function(e) {
          //addMessage("Connected");
          console.log("esta conectada al topico" +'ws://' + cnn_string + topico );
          
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
            console.log("llego un mensaje: "+msg);
          } else {
            if (IsJsonString(e.data)) {
              try {
                var obj = JSON.parse(e.data);
              } catch (err) {
                console.log(e.data);
              }
              if (obj instanceof Object) {
                console.log("parseo de data: "+e.data);
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
      


      mqttClient = new Paho.MQTT.Client("mqtt.coiaca.com",8080,"webio4mqttexample123");
      var topic= "";
      var message="";
      
      
      function EnviarSonoffSimulatePushbutton(host,port,user,password,topico,swith,serial){
    	  
    	//Nueva version
    	  var urlsendInformation = $(location).attr('pathname') + "/sendCommand/"+serial+"/"+"simulatepushbutton"+"/"+swith;
  			$.ajax({ url : urlsendInformation,
  			contentType: "application/json",
  			dataType: 'json',
  			success: function(data){
  				console.log(data.result);
  			}});
    	  
    	  //
    	//  Connecttotal(host,port,user,password,topico,swith,serial,"simulatepushbutton");
      }
      
      function EnviarSonoff(host,port,user,password,topico,swith,serial,accion){
    	 
    	  
    	//Nueva version
    	  var urlsendInformation = "";
    		  
    	  var acs = document.getElementById(accion).firstChild.nodeValue;
    	  if (acs == "Apagado" || acs == "Switched_off"){
    		  urlsendInformation =$(location).attr('pathname') + "/sendCommand/"+serial+"/"+"enviaron"+"/"+swith;
    	  } else {
    		  urlsendInformation =$(location).attr('pathname') + "/sendCommand/"+serial+"/"+"enviaroff"+"/"+swith;
    	  }

    	  $.ajax({ url : urlsendInformation,
  			contentType: "application/json",
  			dataType: 'json',
  			success: function(data){
  				console.log(data.result);
  			}});
    	  
    	  
    		//  Connecttotal(host,port, user,password,topico,swith,serial,"enviaron");
    		//  Connecttotal(host,port, user,password,topico,swith,serial,"enviaroff");
    	  }
      }

      function Connecttotal(host,port,user,password,topico,swith,serial,message1){
    	  
    	  //Nueva version
    	  var urlsendInformation = $(location).attr('pathname') + "/sendCommand/"+serial+"/"+message1+"/"+"none";
  			$.ajax({ url : urlsendInformation,
  			contentType: "application/json",
  			dataType: 'json',
  			success: function(data){
  				console.log(data.result);
  			}});
    	  
    	  //
    	  
    	  
          var username = user;
          var password = password;
          
          console.log("este es el mensaje: "+ message1);
          if(message1=="enviaron"){
        	  if(swith=="switchone")
        		  message='{"SW1":"ON"}';
        	  //message='{"pwd":"coiaca","param1":"SW1":"On","command":"switchAction"}';
        	  else
        		  message='{"SW2":"ON"}';
        	  topic=topico;
        	  
          }if(message1=="enviaroff"){
        	  if(swith=="switchone")
        		  message='{"SW1":"OFF"}';
        	  else
        		  message='{"SW2":"OFF"}';
        	  topic=topico;
        	  
          }if(message1=="simulatepushbutton"){
        	  if(swith=="switchone")
        		  message='{"command":"simulateButtonPush","pwd":"coiaca","param1":"PB1"}';
        	  else
        		  message='{"command":"simulateButtonPush","pwd":"coiaca","param1":"PB2"}';
        	  topic=topico;
          }if(message1.includes("alarm-")){
        	  var texto=message1.replace("alarm-","");
        	  var partition = "";
        	  if(swith=="particion")
        		  partition = "1";
        	  if(texto=="armarzona")
        		  texto=partition+"S";
        	  if(texto=="armartotal")
                  texto=partition+"A";
        	  message=texto;
        	  topic=serial+"/cmd";
          }
          
    	  if(mqttClient.isConnected()==false){
		      	mqttClient.connect({
		      		onSuccess: Connected121,
		      		onFailure: ConnectionFailed,
		      		keepAliveInterval: 10,
		      		userName: user,
		      		useSSL: false,
		      		password: password	
		      	});
    	  }
    	  else{
    		  console.log("ya esta conectada!!");
    		  Connected121();
    	  }
      }

      function Connected121() {
        console.log("Connecto exitoso");
        console.log("esta conectada?"+mqttClient.isConnected());
        console.log("este es el mensaje "+message+"; este es el topico: "+topic);
        mqttClient.subscribe(topic);
        envioComandostotales(message,topic);
      }
      
      function envioComandostotales(message,topic){
    	var message = new Paho.MQTT.Message(message);
  		message.destinationName = topic;
  		mqttClient.send(message);
  		console.log("se envio mensaje final");
      }

      
      function ConnectionFailed(res) {
      	console.log("Connect failed:" + res.errorMessage);
      }
      
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