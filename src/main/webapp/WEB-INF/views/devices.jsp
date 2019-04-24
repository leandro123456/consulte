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

 	<!-- Custom styles for this template-->
	<link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
  
   	<!-- Custom styles for this template-->
	<link href='<c:url value="/resources/css/sb-admin-2.min.css" />' rel="stylesheet">
	<link href='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.css" />' rel="stylesheet">


</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">
  	<jsp:include page="header.jsp" />
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">My Devices</h6>
            </div>
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
                    <tr>
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>61</td>
                      <td>2011/04/25</td>
                      <td>$320,800</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>63</td>
                      <td>2011/07/25</td>
                      <td>$170,750</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>66</td>
                      <td>2009/01/12</td>
                      <td>$86,000</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>22</td>
                      <td>2012/03/29</td>
                      <td>$433,060</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>33</td>
                      <td>2008/11/28</td>
                      <td>$162,700</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Brielle Williamson</td>
                      <td>Integration Specialist</td>
                      <td>New York</td>
                      <td>61</td>
                      <td>2012/12/02</td>
                      <td>$372,000</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Herrod Chandler</td>
                      <td>Sales Assistant</td>
                      <td>San Francisco</td>
                      <td>59</td>
                      <td>2012/08/06</td>
                      <td>$137,500</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Rhona Davidson</td>
                      <td>Integration Specialist</td>
                      <td>Tokyo</td>
                      <td>55</td>
                      <td>2010/10/14</td>
                      <td>$327,900</td>
                      <td>$320,800</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
          <a class="btn btn-primary" href="login.html">Logout</a>
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
