<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<script src='<c:url value="https://unpkg.com/sweetalert/dist/sweetalert.min.js"/>'></script>
<link href='<c:url value="/resources/css/tcal.css" />'	rel="stylesheet">
<script src='<c:url value="/resources/js/tcal.js" />'></script>



<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">${materia} - Aprovisionamiento</h1>
	</div>
</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Carga de Documentos</h3>
			</div>
			<div class="panel-body">
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
				
				<form role="form"
					action="<c:url value="/home/" />${materia}/provisioning/document" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Nombre</label> <input name="name"
							class="form-control" required>
					</div>
					<div class="form-group">
						<label>Descripción</label> <input name="description"
							class="form-control">
					</div>
					<div class="form-group">
						<label>Documento*</label> <input name="document"
							type="file" required>
					</div>
					<button type="submit" class="btn btn-default">Cargar</button>
				</form>
				<p class="help-block">*Elementos mandatorios</p>
			</div>
		</div>


		<div class="panel panel-default" id="divInputFileUpload">
			<div class="panel-heading">
				<h3 class="panel-title">Carga de alumnos</h3>
			</div>
			<div class="panel-body">
				<p>Carga de todos los alumnos</p>
				<form role="form"
					action="<c:url value="/home/" />${materia}/provisioning/allstudents" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Archivo de Alumnos</label> <input name="file_template" type="file">
					</div>
					<button type="submit" class="btn btn-default">Cargar Todos los Alumnos</button>
				</form>
			</div>
			<div class="panel-body">
				<p>Carga un alumnno</p>
				<form role="form"
					action="<c:url value="/home/" />${materia}/provisioning/onestudent" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<input name="name" class="form-control" required> <label>Nombre y Apellido (separado por espacios)</label>
					</div>
					<button type="submit" class="btn btn-default">Cargar un Alumno</button>
				</form>
			</div>
		</div>

		
		<div class="panel panel-default" id="divProfileCompleteUpload">
			<div class="panel-heading">
				<h3 class="panel-title">Cargar Notas</h3>
			</div>
			<div class="panel-body">
				<p>Carga de todas las notas</p>
				<form role="form"
					action="<c:url value="/home/" />${materia}/provisioning/allnotes" method="post"				
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Descripcion</label> <input name="description"
							class="form-control" required>
					</div>
					<p></p>
					<div>
						<label>Tipo de Nota</label> 
						<select id="tipo" name="tipo" class="form-control"  onchange="enableSons()">
							<option value="none">Select</option>
							<option value="actividad">ACTIVIDADES</option>
							<option value="trabajo_practico">TRABAJO_PRACTICO</option>
							<option value="evaluacion">EVALUACION</option>
						</select>
					</div>
					<p> </p>
					<div class="container">
						<div><input id="fecha" name="fecha" type="text"  class="tcal" value="" /></div>
					</div>
					<p> </p>
					<div class="form-group">
						<label>Archivo de Notas</label> <input name="file_notas" type="file">
					</div>
					<button type="submit" class="btn btn-default">Cargar Todos las Notas</button>
				</form>
			</div>
		</div>

		<div class="panel panel-default" id="divProfileCompleteUpload">
			<div class="panel-heading">
				<h3 class="panel-title">Carga de Asistencia</h3>
			</div>
			<div class="panel-body">
				<p>Carga de toda la asistencia</p>
				<form role="form"
					action="<c:url value="/home/" />${materia}/provisioning/allassistance" method="post"				
					enctype="multipart/form-data">
					<div class="form-group">
						<label>Descripcion</label> <input name="description"
							class="form-control" required>
					</div>
					<p> </p>
					<div class="container">
						<div><input id="fecha" name="fecha" type="text"  class="tcal" value="" /></div>
					</div>
					<p> </p>
					<div class="form-group">
						<label>Archivo de Asistencia</label> <input name="file_assistance" type="file">
					</div>
					<button type="submit" class="btn btn-default">Cargar</button>
				</form>
			</div>
		</div>
<jsp:include page="footer.jsp" />
