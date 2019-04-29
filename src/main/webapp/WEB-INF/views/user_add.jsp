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
	  
  	<link href='<c:url value="/resources/vendor/fontawesome-free/css/all.min.css" />' rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

 	<!-- Custom styles for this template-->
	<link href='<c:url value="/resources/mqttResources/sb-admin-2.min.css" />' rel="stylesheet">
  
   	<!-- Custom styles for this template-->
	<link href='<c:url value="/resources/css/sb-admin-2.min.css" />' rel="stylesheet">
	<link href='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.css" />' rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

    <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />'></script>
	<script src='<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />'></script>
	<script src='<c:url value="/resources/js/sb-admin-2.min.js" />'></script>
  
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/jquery.dataTables.min.js" />'></script>
  	<script src='<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.min.js" />'></script>
  	<script src='<c:url value="/resources/js/demo/datatables-demo.js" />'></script>
		<jsp:include page="header.jsp" />
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            eReach - User
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-default">
                    	<c:if test="${not empty msg1}">
	                        <div class="alert alert-warning">
	                            ${msg1}
	                        </div>
                        </c:if>
                        <div class="panel-heading">
                            <h3 class="panel-title">User Details</h3>
                        </div>
                        <div class="panel-body">
                        <form role="form" action="<c:url value="/home/general/configuration/user/add/"/>" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" class="form-control" placeholder="User">
                                <p> </p>
                                <label>Role</label>
                            	<select name="role" class="form-control">
                                	<c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />

                                    <c:forEach items="${roleFound}" var="rol">
                                        <c:if test ="${rol.nameRole == 'ADMIN' && auth == '[SUPERADMIN]'}">
                                           <option value="${rol.nameRole}" id="${rol.nameRole}">${rol.nameRole}</option>
                                        </c:if>

                                        <c:if test ="${rol.nameRole != 'ADMIN'}">
                                           <option value="${rol.nameRole}" id="${rol.nameRole}">${rol.nameRole}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <p> </p>

                                <script src='<c:url value="/resources/js/jqueryRoles.js" />'></script>

                                <div class="row">
                                    <div class="col-lg-2">
                                    </div>
                                    <div class="col-lg-8">
                                        <div  class="panel panel-default">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Password</h3>
                                            </div>
                                            <div class="panel-body">
                                                <label>Password must be 15 characters long</label>
                                                <p></p>
                                                <label>Your password must meet the following requirements:</label>
                                                <p></p>
                                                <label>-Three uppercase letters</label>
                                                <p></p>
                                                <label>-Three lowercase letters</label>
                                                <p></p>
                                                <label>-Three numbers</label>
                                                <p></p>
                                                <label>And in addition the previous characters do not have repetitions followed</label>
                                                <label>-Three of the following special characters:</label>
                                                <p></p>
                                                <label>!@#$&;.,</label>
                                                <p></p>
                                                <label>Password</label>
                                                <input name="pass" class="form-control" value="">
                                                <p> </p>
                                                <label>Confirm password</label>
                                                <input name="pass2" class="form-control" value="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <p> </p>
                            </div>
                            <div class="col-md-5 centered">
                            </div>
							<div class="col-md-6 centered">
                            	<button type="submit" name="action" value="save" class="btn btn-default">Save</button>

                            	<button type="submit" name="action" value="cancel" class="btn btn-default">Cancel</button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>

<jsp:include page="footer.jsp" />