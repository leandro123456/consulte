<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
</head>


<body id="page-top">
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<div id="wrapper">
<jsp:include page="header.jsp" />
     <!-- Begin Page Content -->
     <div class="container-fluid">
        
			<c:if test="${not empty msg}">
				 	<input type="hidden" id ="mensaje" value="${msg}">
					<script type="text/javascript">
						var x= document.getElementById('mensaje').value;
						swal({
							  //title: x,
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
							  //title: x,
							  icon: "error",
							  timer: 5000,
							  closeOnClickOutside: false,
							  buttons: false,
							});
					</script>
				</c:if>	
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h5 class="m-0 font-weight-bold text-primary">New Device</h5>
            </div>
            <div class="card-body">
                   <div class="form-group">
                       <h3>General Information</h3>
                       <b>Serial Number</b>
                       <input id="serialnumber" class="form-control">
                       <b>Name</b>
                       <input id="namedevice" class="form-control">
                       <p> </p>
                       <b>Description</b>
                       <input id="description" class="form-control">
                       <p> </p>
                       	<div>
							<b>Type of Device</b> 
							<select id="tipodevice"  class="form-control"  onchange="enableType()">
								<option value="none">Select</option>
								<option value="thermometer">Thermometer</option>
								<option value="alarm">Alarm</option>
								<option value="sonoff">Sonoff</option>
							</select>
						</div>
						<p> </p>
						<div id="vistastermometro">
							<b>Type of View</b> 
							<select id="tipovistatermometro" class="form-control"  onchange="changeTipoVistaTermometro()">
								<option value="none">Select</option>
								<option value="watches">Watches</option>
								<option value="bars">Bars</option>
							</select>
						</div>
						<p> </p>
						<form id="parametrostermometro">
						<b>Thermometer show parameters</b>
						<table class="table table-sm">
							  <tbody>
							    <tr class="tablain">
							      <td>Humidity</td>
							      <td><input type="checkbox" id="humedadtermometro" data-toggle="toggle"></td>
							    </tr>
							    <tr class="tablain">
							      <td>Temperature Centigrade</td>
							      <td><input type="checkbox" id="tempctermometro" data-toggle="toggle"></td>
							    </tr>
							    <tr class="tablain">
							      <td>Sensation Centigrade</td>
							      <td><input type="checkbox" id="sensacionctermometro" data-toggle="toggle"></td>
							    </tr>
							    <tr class="tablain">
							      <td>Temperature Fahrenheit</td>
							      <td><input type="checkbox" id="tempftermometro" data-toggle="toggle"></td>
							    </tr>
							    <tr class="tablain">
							      <td>Sensation Fahrenheit</td>
							      <td><input type="checkbox" id="sensacionftermometro" data-toggle="toggle"></td>
							    </tr>
							  </tbody>
							</table>
						 </form>
						 <p> </p>
						<b>Default Configuration for Connections Parameters <input id="toggle-paramconects" checked type="checkbox" data-toggle="toggle" onchange="checkconfiguration()" data-style="slow"></b>
						<p> </p>
						<form id="parametersConexion" onkeypress=checkPassword()>
							<h5>Listen Information</h5>
							<div class="form-group row ">
								<b>Hostname or IP Address</b> <input type="text"
									class="form-control form-control-user" id="iphostescuchar"
								    placeholder="Hostname">
								<b>Port</b> <input type="text"
									class="form-control form-control-user" id="portescuchar"
									placeholder="Number Port">
								<b>Topic to Listen:</b><input type="text"
									class="form-control form-control-user " id="topiclisten"
									placeholder="Topic to Listen">
								<b>User name</b> <input type="text"
									class="form-control form-control-user" id="userescuchar"
									placeholder="User name">
								<b>Password</b> <input type="password"
									class="form-control form-control-user" id="passescuchar"
									placeholder="Password">
								<b>Confirm Password</b> <input type="password"
								class="form-control form-control-user" id="confirpassescuchar"
								placeholder="Confirm Password">
							</div>
							
							
							<h5>Write Information</h5>
							<div class="form-group row ">
								<b>Hostname or IP Address</b> <input type="text"
									class="form-control form-control-user" id="iphostescribir"
									placeholder="Hostname">
								<b>Port</b> <input type="text"
									class="form-control form-control-user" id="portescribir"
									placeholder=" Number Port">
								<b>Topic to Write:</b><input type="text"
									class="form-control form-control-user " id="topicwrite"
									placeholder="Topic to Write">
								<b>User name</b> <input type="text"
									class="form-control form-control-user" id="userescribir"
									placeholder="User name">
								<b>Password</b> <input type="password"
									class="form-control form-control-user" id="passescribir"
									placeholder="Password">
								<b>Confirm Password</b> <input type="password"
									class="form-control form-control-user" id="confirpassescribir"
									placeholder="Confirm Password">
							</div>
							
							<h5>Listen Information Remote Configuration</h5>
							<div class="form-group row ">
								<b>Hostname or IP Address</b> <input type="text"
									class="form-control form-control-user" id="iphostescucharremote"
									placeholder="Hostname">
								<b>Port</b> <input type="text"
									class="form-control form-control-user" id="portescucharremote"
									placeholder="Number Port">
								<b>Topic to Listen:</b><input type="text"
									class="form-control form-control-user " id="topiclistenremote"
									placeholder="Topic to Listen">
								<b>User name</b> <input type="text"
									class="form-control form-control-user" id="userescucharremote"
									placeholder="User name">
								<b>Password</b> <input type="password" 
									class="form-control form-control-user" id="passescucharremote"
									placeholder="Password">
								<b>Confirm Password</b> <input type="password"
									class="form-control form-control-user" id="confirpassescucharremote"
									placeholder="Confirm Password">
							</div>
							
							<h5>Write Information Remote Configuration</h5>
							<div class="form-group row ">
								<b>Hostname or IP Address</b> <input type="text"
									class="form-control form-control-user" id="iphostescribirremote"
									placeholder="Hostname">
								<b>Port</b> <input type="text"
									class="form-control form-control-user" id="portescribirremote"
									placeholder="Number Port">
								<b>Topic to Write:</b><input type="text"
									class="form-control form-control-user " id="topicwriteremote"
									placeholder="Topic to Write">
								<b>User name</b> <input type="text"
									class="form-control form-control-user" id="userescribirremote"
									placeholder="User name">
								<b>Password</b> <input type="password"
									class="form-control form-control-user" id="passescribirremote"
									placeholder="Password">
								<b>Confirm Password</b> <input type="password"
									class="form-control form-control-user" id="confirmpassescribirremote"
									placeholder="Confirm Password">
							</div>
						</form>
                     </div>
                     <div class="col-md-5 centered">
                     </div>
                     <p> </p>
                     <hr class="sidebar-divider my-0">
                     <p> </p>
						<div class="row">
               				<a data-whatever="save" data-toggle="modal" data-target="#ModalConfirm" name="action" value="save" class="btn btn-primary btn-user btn-block">Create Device</a>
               			</div>
            </div>
          </div>
        </div>
</div>
<jsp:include page="footer.jsp" />


  <!-- Logout Modal-->
  <div class="modal fade" id="ModalConfirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Create Device</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Are you sure to create a new device?</div>
        <div class="modal-footer">
          <form role="form"  id="form_id" method="post" enctype="multipart/form-data">
          	
          	<input type="hidden" name="serialnumber" id="serialnumber1"/>
          	<input type="hidden" name="namedevice" id="namedevice1"/>
          	<input type="hidden" name="descriptiondevice"  id="descriptiondevice1"/>
          	<input type="hidden" name="tipodevice"  id="tipodevice1"/>
          	<input type="hidden" name="tipovistatermometro"  id="tipovistatermometro1"/>
          	<input type="hidden" name="humedadtermometro"  id="humedadtermometro1"/>
          	<input type="hidden" name="tempctermometro"  id="tempctermometro1"/>
          	<input type="hidden" name="sensacionctermometro"  id="sensacionctermometro1"/>
          	<input type="hidden" name="tempftermometro"  id="tempftermometro1"/>
          	<input type="hidden" name="sensacionftermometro"  id="sensacionftermometro1"/>
          	
          	<input type="hidden" name="iphostescuchar" id="iphostescuchar1"/>
          	<input type="hidden" name="portescuchar" id="portescuchar1"/>
          	<input type="hidden" name="topiclisten" id="topiclisten1"/>
          	<input type="hidden" name="userescuchar" id="userescuchar1"/>
          	<input type="hidden" name="passescuchar" id="passescuchar1"/>
          	
          	<input type="hidden" name="iphostescribir" id="iphostescribir1"/>
          	<input type="hidden" name="portescribir" id="portescribir1"/>
          	<input type="hidden" name="topicwrite" id="topicwrite1"/>
          	<input type="hidden" name="userescribir" id="userescribir1"/>
          	<input type="hidden" name="passescribir" id="passescribir1"/>
          	
          	<input type="hidden" name="iphostescucharremote" id="iphostescucharremote1"/>
          	<input type="hidden" name="portescucharremote" id="portescucharremote1"/>
          	<input type="hidden" name="topiclistenremote" id="topiclistenremote1"/>
          	<input type="hidden" name="userescucharremote" id="userescucharremote1"/>
          	<input type="hidden" name="passescucharremote" id="passescucharremote1"/>
          	
          	<input type="hidden" name="iphostescribirremote" id="iphostescribirremote1"/>
          	<input type="hidden" name="portescribirremote" id="portescribirremote1"/>
          	<input type="hidden" name="topicwriteremote" id="topicwriteremote1"/>
          	<input type="hidden" name="userescribirremote" id="userescribirremote1"/>
          	<input type="hidden" name="passescribirremote" id="passescribirremote1"/>
          	
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Create</a>
          </form>
        </div>
      </div>
    </div>
  </div>
  
  <STYLE type="text/css">
   .tablain {
   		border-color: #e3e6f
   		border-width: 0px;
   		border: transparent; 
   		text-align: left;
   		color: #6c6e7e;
   		background-color: transparent;
   		}
  </STYLE>
 
    <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
  	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>
  	
  	<script type="text/javascript">
	  	$('#ModalConfirm').on('show.bs.modal', function (event) {
	  		  var button = $(event.relatedTarget)
		  	  var recipient = button.data('whatever') 
	  		  var serial=document.getElementById('serialnumber').value;
		  	  var action= "/mqttmanagment/home/create/"+serial;
	  		  document.getElementById("form_id").action = action;
	  		  
	  		if(document.getElementById("iphostescuchar") != null)
        	  	document.getElementById("iphostescuchar1").value = document.getElementById("iphostescuchar").value;
        	  if(document.getElementById("portescuchar") != null)
          	  	document.getElementById("portescuchar1").value = document.getElementById("portescuchar").value;
        	  if(document.getElementById("topiclisten") != null)
          	  	document.getElementById("topiclisten1").value = document.getElementById("topiclisten").value;
        	  if(document.getElementById("userescuchar") != null)
          	  	document.getElementById("userescuchar1").value = document.getElementById("userescuchar").value;
        	  if(document.getElementById("passescuchar") != null)
          	  	document.getElementById("passescuchar1").value = document.getElementById("passescuchar").value;
        	  
        	  if(document.getElementById("iphostescribir") != null)
          	  	document.getElementById("iphostescribir1").value = document.getElementById("iphostescribir").value;
        	  if(document.getElementById("portescribir") != null)
          	  	document.getElementById("portescribir1").value = document.getElementById("portescribir").value;
        	  if(document.getElementById("topicwrite") != null)
          	  	document.getElementById("topicwrite1").value = document.getElementById("topicwrite").value;
        	  if(document.getElementById("userescribir") != null)
          	  	document.getElementById("userescribir1").value = document.getElementById("userescribir").value;
        	  if(document.getElementById("passescribir") != null)
          	  	document.getElementById("passescribir1").value = document.getElementById("passescribir").value;
        	  
        	  if(document.getElementById("iphostescucharremote") != null)
          	  	document.getElementById("iphostescucharremote1").value = document.getElementById("iphostescucharremote").value;
          	  if(document.getElementById("portescucharremote") != null)
            	  	document.getElementById("portescucharremote1").value = document.getElementById("portescucharremote").value;
          	  if(document.getElementById("topiclistenremote") != null)
            	  	document.getElementById("topiclistenremote1").value = document.getElementById("topiclistenremote").value;
          	  if(document.getElementById("userescucharremote") != null)
            	  	document.getElementById("userescucharremote1").value = document.getElementById("userescucharremote").value;
          	  if(document.getElementById("passescucharremote") != null)
            	  	document.getElementById("passescucharremote1").value = document.getElementById("passescucharremote").value;
          	  
          	  if(document.getElementById("iphostescribirremote") != null)
            	  	document.getElementById("iphostescribirremote1").value = document.getElementById("iphostescribirremote").value;
          	  if(document.getElementById("portescribirremote") != null)
            	  	document.getElementById("portescribirremote1").value = document.getElementById("portescribirremote").value;
          	  if(document.getElementById("topicwriteremote") != null)
            	  	document.getElementById("topicwriteremote1").value = document.getElementById("topicwriteremote").value;
          	  if(document.getElementById("userescribirremote") != null)
            	  	document.getElementById("userescribirremote1").value = document.getElementById("userescribirremote").value;
          	  if(document.getElementById("passescribirremote") != null)
            	  	document.getElementById("passescribirremote1").value = document.getElementById("passescribirremote").value;
        	  
        	  
          	  
          	  
	  	})
  	</script>
  	
  	<script type="text/javascript">
  	$(document).ready(function () {
  		checkconfiguration();
  		enableType();
  	});
  	</script>
  	<script type="text/javascript">
	  function checkconfiguration() {
		  var checkBox=document.getElementById("toggle-paramconects");
		  if (checkBox.checked == true){
			    document.getElementById('parametersConexion').style.display = 'none';
			  } else {
				  document.getElementById('parametersConexion').style.display = 'inline';
			  }
		  
	  }
	</script>
	<script type="text/javascript">
	function enableType(){
		var seleccion=document.getElementById('tipodevice');
        var valuetype = seleccion.options[seleccion.selectedIndex].value;
        if(valuetype == "thermometer"){
        	document.getElementById('vistastermometro').style.display = 'inline';
        	document.getElementById('parametrostermometro').style.display = 'inline';
        }if(valuetype == "alarm"){
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        }if(valuetype == "sonoff"){
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        }if(valuetype != "thermometer" && valuetype != "alarm" && valuetype != "sonoff"){
        	document.getElementById('vistastermometro').style.display = 'none';
        	document.getElementById('parametrostermometro').style.display = 'none';
        }
        
	}
	</script>
	
	
	<script type="text/javascript">
    function checkPassword(){
	        var passescuchar = document.getElementById('passescuchar').value;
	        var confirpassescuchar = document.getElementById('confirpassescuchar').value;
	        if(confirpassescuchar.length==0){ 
	            console.log("Esta vacio e indefinido: "+confirpassescuchar);
	            document.getElementById("passescuchar").style.background = "transparent";
	            document.getElementById("confirpassescuchar").style.background = "transparent";
	       }
	        if (confirpassescuchar.length!=0 && passescuchar.length!=0 && passescuchar != confirpassescuchar){ 
	        	console.log("sondistintos, se deberia escribir: "+ confirpassescuchar);
	            document.getElementById("confirpassescuchar").style.background = "red";
	        }
			if(confirpassescuchar.length!=0 && passescuchar.length!=0 && (passescuchar == confirpassescuchar)){ 
	   			console.log("ya termino!!");
	   			document.getElementById("confirpassescuchar").style.background = "green";
		} 
	}  
	</script>
	
    <script type="text/javascript"> 
       function checkPassword1() { 
    	   var passescuchar = document.getElementById('passescuchar').value; 
    	   var confirpassescuchar = document.getElementById('confirpassescuchar').value;
    	   var passescribir = document.getElementById('passescribir').value; 
    	   var confirpassescribir = document.getElementById('confirpassescribir').value; 
    	   var passescucharremote = document.getElementById('passescucharremote').value; 
    	   var confirpassescucharremote = document.getElementById('confirpassescucharremote').value; 
    	   var passescribirremote = document.getElementById('passescribirremote').value; 
    	   var confirmpassescribirremote = document.getElementById('confirmpassescribirremote').value; 
                 
    	   if(confirpassescuchar.length==0){ 
	            document.getElementById("confirpassescuchar").style.background = "transparent";
	       }
	        if (confirpassescuchar.length!=0 && passescuchar.length!=0 && passescuchar != confirpassescuchar){ 
	            document.getElementById("confirpassescuchar").style.background = "red";
	        }
			if(confirpassescuchar.length!=0 && passescuchar.length!=0 && (passescuchar == confirpassescuchar)){ 
	   			document.getElementById("confirpassescuchar").style.background = "green";
			} 
			
			
			
			if(confirpassescribir.length==0){ 
	            document.getElementById("confirpassescribir").style.background = "transparent";
	        }
	        if (confirpassescribir.length!=0 && passescribir.length!=0 && passescribir != confirpassescribir){ 
	            document.getElementById("confirpassescribir").style.background = "red";
	        }
			if(confirpassescribir.length!=0 && passescribir.length!=0 && (passescribir == confirpassescribir)){ 
	   			document.getElementById("confirpassescribir").style.background = "green";
			} 
			
			
			if(confirpassescucharremote.length==0){ 
	            document.getElementById("confirpassescucharremote").style.background = "transparent";
	        }
	        if (confirpassescucharremote.length!=0 && passescucharremote.length!=0 && passescucharremote != confirpassescucharremote){ 
	            document.getElementById("confirpassescucharremote").style.background = "red";
	        }
			if(confirpassescucharremote.length!=0 && passescucharremote.length!=0 && (passescucharremote == confirpassescucharremote)){ 
	   			document.getElementById("confirpassescucharremote").style.background = "green";
			}
			
			
			
			if(confirmpassescribirremote.length==0){ 
	            document.getElementById("confirmpassescribirremote").style.background = "transparent";
	        }
	        if (confirmpassescribirremote.length!=0 && passescribirremote.length!=0 && passescribirremote != confirmpassescribirremote){ 
	            document.getElementById("confirmpassescribirremote").style.background = "red";
	        }
			if(confirmpassescribirremote.length!=0 && passescribirremote.length!=0 && (passescribirremote == confirmpassescribirremote)){ 
	   			document.getElementById("confirmpassescribirremote").style.background = "green";
			}

       } 
   </script> 
  	