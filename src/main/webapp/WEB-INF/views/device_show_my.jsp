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

  <title>Devices - Dashboard</title>

  <!-- Custom fonts for this template-->
  <link href='<c:url value="/resources/mqttResources/all.min.css" />' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">

</head>

<body id="page-top">
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<jsp:include page="header.jsp" />
     <!-- Begin Page Content -->
     <div class="container-fluid">
        
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
				
          <!-- Page Heading -->
             <h1>LLEGO</h1>
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Devices</h6>
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
                  <tfoot>
                    <tr>
                      <th>Serial Number</th>
                      <th>Name</th>
                      <th>Description</th>
                      <th>Use Default Configuration</th>
                      <th>Shared How User</th>
                      <th>Shared How Admin</th>
                      <th>Actions</th>
                    </tr>
                  </tfoot>
                  <tbody>
                  	<c:forEach items="${devices}" var="device">
	                    <tr>
	                      <td>${device.serialnumber}</td>
	                      <td>${device.name}</td>
	                      <td>${device.description}</td>
	                      <td>
	                      <c:choose>
                      		<c:when test = "${device.defaultconfiguration}">
	                      		<span class="glyphicon glyphicon-ok"></span>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<span class="glyphicon glyphicon-remove"></span>
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      <td>
	                      <c:choose>
                      		<c:when test = "${device.sharedhowuser}">
	                      		<span class="glyphicon glyphicon-ok"></span>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<span class="glyphicon glyphicon-remove"></span>
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      
	                      <td>
	                      <c:choose>
                      		<c:when test = "${device.sharedhowadmin}">
	                      		<span class="glyphicon glyphicon-ok"></span>
	                      	</c:when>
	                      	<c:otherwise>
	                      		<span class="glyphicon glyphicon-remove"></span>
	                      	</c:otherwise>
	                      </c:choose>
	                      </td>
	                      <td>
	                      	<a href="/info/${device.serialnumber}">
					          <span class="glyphicon glyphicon-info-sign"></span>
					        </a>
					        <a href="/remove/${device.serialnumber}" data-toggle="modal" data-target="#deleteModal">
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
        <!-- /.container-fluid -->

<jsp:include page="footer.jsp" />


  <!-- Logout Modal-->
  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
          <form role="form" action="<c:url value="/remove/${device.serialnumber}"/>" method="post" enctype="multipart/form-data">
          	<button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          	<a class="btn btn-primary" href="javascript:;" onclick="parentNode.submit();">Logout</a>
          </form>
        </div>
      </div>
    </div>
  </div>
  