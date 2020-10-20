<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="es">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>cDash</title>
  
  	<link rel='shortcut icon' href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon"/>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/style.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/c3.css" />'>
	<link rel="stylesheet" type="text/css" href='<c:url value="/resources/mqttResources/all.min.css" />'>
	<link rel="stylesheet" href='<c:url value="/resources/mqttResources/sb-admin-2.css" />'>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

	<script src='<c:url value="/resources/mqttResources/d3.v3.min.js" />'></script>
	<script src='<c:url value="/resources/mqttResources/c3.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery/jquery.js" />'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.js" />'></script>
	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.js" />'></script>
	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>
	<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.js" />'></script>
	<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
	<script async src="https://www.googletagmanager.com/gtag/js?id=G-6SSYQD4466"></script>

	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'G-6SSYQD4466');
	</script>

</head>

<c:if test="${param.msg != null}">
	<input type="hidden" id="mensaje1" value="${param.msg}">
	<script type="text/javascript">
		var x = document.getElementById('mensaje1').value;
		swal({
			title : x,
			icon : "info",
			timer : 5000,
			closeOnClickOutside : false,
			buttons : false,
		});
		setTimeout('window.location.href = "componentmyown";', 2000);
	</script>
</c:if>

<body id="page-top">
	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<div class="row">
								<div class="col-md-12">
									<h2 style="text-align: center;">Dispositivos</h2>
								</div>
							</div>
							<div class="row">
								<div class="col-md-1">
									<a class="nav-link collapsed" href="/" aria-expanded="true"
										aria-controls="collapseUtilities"
										style="color: #224A85; font-size: 2em; margin-top: -0.6em;">
										<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
									</a>
								</div>
							</div>
							<p></p>
							<input type="hidden" name="serial" id="serial" value=${serial } />
							<div class="table-responsive">
								<table class="table" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th scope="col">Numero de Serie</th>
											<th scope="col">Nombre</th>
											<th scope="col">Descripcion</th>
											<th scope="col">Compartido a Usuario</th>
											<th scope="col">Compartido a Administrador</th>
											<th scope="col">Acciones</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${devices}" var="devices">
											<tr class="tablain">
												<td>${devices.serial}</td>
												<td>${devices.name}</td>
												<td>${devices.description}</td>
												<td><c:choose>
														<c:when test="${devices.sharedhowuser eq true}">
															<i class="fa fa-check" aria-hidden="true"></i>
														</c:when>
														<c:otherwise>
															<i class="fa fa-times" aria-hidden="true"></i>
														</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${devices.sharedhowadmin eq true}">
															<i class="fa fa-check" aria-hidden="true"></i>
														</c:when>
														<c:otherwise>
															<i class="fa fa-times" aria-hidden="true"></i>
														</c:otherwise>
													</c:choose></td>
												<td>
													<%-- 	                      	<a style="padding-right: 1em;" href="/home/info/${devices.serial}"> --%>
													<!-- 					          <i class="fa fa-edit" aria-hidden="true"></i> -->
													<!-- 					        </a> --> <a href="#"
													data-whatever="${devices.serial}" data-toggle="modal"
													data-target="#deleteModal"> <i class="fa fa-trash"
														aria-hidden="true"></i>
												</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="footer.jsp" />


  <!-- Logout Modal-->
  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Borrar Dispositivo</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">¿Esta seguro de borrar el dispositivo?</div>
        <div class="modal-footer">
          <form role="form"  id="form_id" method="post" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Borrar</a>
          </form>
        </div>
      </div>
    </div>
  </div>
   	
  	<script type="text/javascript">
	  	$('#deleteModal').on('show.bs.modal', function (event) {
	  		  var button = $(event.relatedTarget)
		  	  var recipient = button.data('whatever') 
	  		  var action= "remove/"+recipient;
	  		  document.getElementById("form_id").action = action;
	  	})
  	</script>
 