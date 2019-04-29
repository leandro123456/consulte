<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
	<link href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css" />' rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	<link href='<c:url value="/resources/css/sb-admin-2.min.css" />' rel="stylesheet">
	<link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
	<link href='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.css" />' rel="stylesheet">
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
</head>

<body id="page-top"> 	
  <div id="wrapper">
  	<jsp:include page="header.jsp" />
<!--     <div id="content-wrapper" class="d-flex flex-column"> -->
<!--       <div id="content"> -->
        <div class="container-fluid">
          <div class="card shadow mb-4">
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
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">My Devices</h6>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Serial Number</th>
                      <th>Name</th>
                      <th>Description</th>
                      <th>Use Default Configuration</th>
                      <th>Shared How User</th>
                      <th>Shared How Admin</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach items="${devices}" var="devices">
	                    <tr>
	                      <td>${devices.serial}</td>
	                      <td>${devices.name}</td>
	                      <td>${devices.description}</td>
	                      <td>
	                      <c:choose>
                      		<c:when test="${devices.defaultconfiguration eq true}">
	                      		yes
	                      	</c:when>
	                      	<c:otherwise>
	                      		no
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      <td>
	                      <c:choose>
                      		<c:when test="${devices.sharedhowuser eq false}">
	                      		yes
	                      	</c:when>
	                      	<c:otherwise>
	                      		no
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      <td>
	                      <c:choose>
                      		<c:when test="${devices.sharedhowadmin eq true}">
	                      		yes
	                      	</c:when>
	                      	<c:otherwise>
	                      		no
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      <td>
	                      	<a href="/mqttmanagment/home/info/${devices.serial}">
					          <span class="glyphicon glyphicon-info-sign"></span>
					        </a>
					        <a href="/mqttmanagment/home/remove/${devices.serial}" data-toggle="modal" data-target="#deleteModal">
	                      	 <span class="glyphicon glyphicon-trash"></span>
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
<!--       </div> -->
<!--     </div> -->
  </div>
  <jsp:include page="footer.jsp" />
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="deleteModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="/mqttmanagment/home/remove/">Delete</a>
        </div>
      </div>
    </div>
  </div>
  
  
    <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
  	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>
</body>
