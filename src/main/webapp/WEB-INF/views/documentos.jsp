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
                        <h1 class="page-header"> ${materia} - Documentos </h1>
                    </div>
                </div>
                <div class="row">
                <div class="col-lg-12">
                  <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Todos los Documentos</h3>
                            </div>
                            
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>Fecha</th>
                                                <th>Nombre</th>
                                                <th>Descripcion</th>
                                                <th>Accion</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${documents}" var="document">
                                            <tr>
                                                <td>${document.fecha}</td>
                                                <td>${document.name}</td>
                                                <td>${document.descripcion}</td>
                                                <td>
                                                	<a href='<c:url value="/home/" />${materia}/explorer/document/edit' data-toggle="tooltip" title="Editar documento"><i class="fa fa-arrow-right"></i></a>
                                                	<a href='<c:url value="/home/" />${materia}/explorer/document/delete' data-toggle="tooltip" title="Borrar documento"><i class="fa fa-arrow-right"></i></a>
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

<jsp:include page="footer.jsp" />