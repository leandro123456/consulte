<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/mqttResources/style.css" />'>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/mqttResources/c3.min.css" />'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.2/mqttws31.min.js"
	type="text/javascript"></script>
<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
<script src='<c:url value="/resources/mqttResources/demo.js" />'></script>
<script
	src='<c:url value="/resources/mqttResources/cargarElementos.js" />'></script>
<script src='<c:url value="/resources/mqttResources/progreso.js" />'></script>
<script src='<c:url value="/resources/mqttResources/cargaReloj.js" />'></script>
<title>Devices - Dashboard</title>
<link href='<c:url value="/resources/mqttResources/all.min.css" />'
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />'
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">

<!-- esto para el reloj -->
<link rel="stylesheet" type="text/css" href='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.css" />'>

<style type="text/css">
.navbar h3 {
	color: #f5f5f5;
	margin-top: 14px;
}
.hljs-pre {
	background: #f8f8f8;
	padding: 3px;
}
.footer {
	border-top: 1px solid #eee;
	margin-top: 40px;
	padding: 40px 0;
}
.input-group {
	width: 110px;
	margin-bottom: 10px;
}
.pull-center {
	margin-left: auto;
	margin-right: auto;
}
@media (min-width: 768px) {
  .container {
    max-width: 730px;
  }
}
@media (max-width: 767px) {
  .pull-center {
    float: center;
  }
}
</style>
<!-- esto para el reloj -->



<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<style type="text/css">
.tablain {
	border-color: #e3e6f border-width: 0px;
	border: transparent;
	text-align: left;
	color: #6c6e7e;
	background-color: transparent;
}
</style>

</head>


<body id="page-top">
	<script
		src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<!-- Begin Page Content -->
		<div class="container-fluid">

			<c:if test="${not empty msg}">
				<input type="hidden" id="mensaje" value="${msg}">
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
				<input type="hidden" id="mensaje1" value="${msg1}">
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
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h5 class="m-0 font-weight-bold text-primary">Timer String Format</h5>
				</div>
				<div class="card-body">
					<div class="form-group">
							<h3>Sonoff ${deviceserial}</h3>
							<p></p>

						<div id="timerString" class="text-lefth">
							<a href="" class="btn btn-primary btn-rounded mb-6"
								data-toggle="modal" data-target="#modalTimerString">Add
								Timer String</a>
						</div>
						<form class="panel panel-primary" id="tabletimerstring">
							<div class="panel-heading">
								<p> </p>
								<h6 class="panel-title">Timer String Parameters</h6>
							</div>
							<div class="panel-body">
								<table class="table table-sm" id="dataTable">
										<tr>
											<th>Days of the Week</th>
											<th>Hour</th>
											<th>Action</th>
											<th>Switch</th>
											<th>Actions</th>
										</tr>
									<tbody id="contenidotablatimerstring">
									</tbody>
								</table>
							</div>
						</form>
					</div>
					<div class="col-md-5 centered"></div>
					<p></p>
					<hr class="sidebar-divider my-0">
					<p></p>
					<div class="row">
						<a data-whatever="save" data-toggle="modal"
							data-target="#ModalConfirm" name="action" value="save"
							class="btn btn-primary btn-user btn-block">Create Timer String</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />


	<div class="modal fade" id="modalTimerString" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Timer String</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body mx-3">

					<div class="md-form mb-5">
						<form id="timerString">
							<div class="container">
								<div class="row">
									<div class="col-lg-6">
										<div class="form-group">
											<label for="exampleFormControlSelect2">Days of the
												Week</label> <select id="dias" multiple="multiple" class="form-control"
												id="exampleFormControlSelect2">
												<option value="monday">Monday</option>
												<option value="tuesday">Tuesday</option>
												<option value="wednesday">Wednesday</option>
												<option value="thursday">Thursday</option>
												<option value="friday">Friday</option>
												<option value="saturday">Saturday</option>
												<option value="sunday">Sunday</option>
											</select>
										</div>
									</div>
									<div class="input-group clock col-lg-6">
										<div class="clearfix">
											<label for="exampleFormControlSelect2">Hour</label>
											<div class="input-group clockpicker pull-center"
												data-placement="center" data-align="top"
												data-autoclose="true">
												<input id="hora" type="text" class="form-control" value="00:00">
<!-- 												<span class="input-group-addon"> <span -->
<!-- 													class="glyphicon glyphicon-time"></span> -->
<!-- 												</span> -->
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<label for="exampleFormControlSelect2">Action</label>
										<div class="form-group">
											<div class="radio">
												<label><input type="radio" name="radiopower" value="on" checked>Power
													On</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="off" name="radiopower">Power
													Off</label>
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<label for="exampleFormControlSelect2">Switch</label>
										<div class="form-group">
											<div class="radio">
												<label><input type="radio" value="All" 
												name="radioencendido" checked>All</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="1" name="radioencendido">1</label>
											</div>
											<div class="radio">
												<label><input type="radio" value="2" name="radioencendido">2</label>
											</div>
										</div>
									</div>
								</div>
							</div>

						</form>

					</div>
				</div>
				<div class="modal-footer d-flex justify-content-center">
					<a class="btn btn-primary btn-lg btn-block" id="botoncreatefile">Create</a>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="ModalConfirm" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Timer String Format</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Are you sure to save timer string format?</div>
				<div class="modal-footer">
					<form role="form" id="form_id" method="post" enctype="multipart/form-data">
						<input type="hidden" name="defaultconfiguration" id="defaultconfiguration" /> 
						<input type="hidden" name="serialnumber" id="serialnumber1" />
						<input type="hidden" name="timerstringsonoff" id="timerstringsonoff" />
						<button class="btn btn-secondary" type="button"	data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Create</a>
					</form>
				</div>
			</div>
		</div>
	</div>



	<script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
	<script
		src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>

	<script type="text/javascript" src='<c:url value="/resources/reloj/dist/bootstrap-clockpicker.min.js" />'></script>
	<script type="text/javascript">
	$('.clockpicker').clockpicker()
	var input = $('#single-input').clockpicker({
	placement: 'bottom',
	align: 'left',
	autoclose: true,
	'default': 'now'
	});

	</script>


	<script type="text/javascript">
		$("#botoncreatefile").click(function () {	 
			var raswith=$('input:radio[name=radioencendido]:checked').val();
			var raaction=$('input:radio[name=radiopower]:checked').val();
			var hora = document.getElementById("hora").value;
			var x=document.getElementById("dias");
			var totaldia = "";
			  for (var i = 0; i < x.options.length; i++) {
			     if(x.options[i].selected ==true){
			    	 if(totaldia=="")
			    		 totaldia = x.options[i].value;
			    	 else
			    	 totaldia= totaldia+"-"+ x.options[i].value;
			      }
			  }
			  
			 document.getElementById("contenidotablatimerstring").innerHTML += '<tr> <td>'+totaldia+'</td> <td>'+hora+'</td>  <td>'+raaction+'</td>   <td>'+raswith+'</td> <td>	<a data-whatever="${devices.serial}" data-toggle="modal" data-target="#deletefila"> 	<i class="fa fa-trash" aria-hidden="true"></i>  </a>    </td>  </tr>';
            $('.modal-backdrop').hide();    
            $("#modalTimerString").modal('hide');
			});
	</script>

	<script type="text/javascript">
	  	$('#ModalConfirm').on('show.bs.modal', function (event) {
	  		  var button = $(event.relatedTarget)
		  	  var recipient = button.data('whatever') 
	  		  //var serial=document.getElementById('serialnumber').value;
		  	  var action= "/mqttmanagment/home/settimerString/"+'${deviceserial}';
	  		  document.getElementById("form_id").action = action;
	  		  
	  		  if(document.getElementById('dataTable') != null){
	  		  var textos = "";
	  		     for (var i=1;i < document.getElementById('dataTable').rows.length; i++){
	  		             for (var j=0; j<4; j++){
	  		                    textos = textos + document.getElementById('dataTable').rows[i].cells[j].innerHTML;
	  		                    textos = textos +"&"
	  		             }
	  		             textos= textos+"@";
	  		     } 
	  		   console.log(textos);
	  		   document.getElementById("timerstringsonoff").value = textos;
	  			}	  
	  	})
  	</script>


