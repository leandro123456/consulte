<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
 <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="apple-touch-icon" href="apple-touch-icon.png">

        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">-->


        <!--For Plugins external css-->
        <link rel="stylesheet" href="assets/css/plugins.css" />

        <link rel="stylesheet" href="assets/css/raleway-webfont.css" />

        <!--Theme custom css -->
        <link rel="stylesheet" href="assets/css/style.css">

        <!--Theme Responsive css-->
        <link rel="stylesheet" href="assets/css/responsive.css" />

        <script src="assets/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    </head>
    
<body>

<c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />
<c:set var = "visitor" value = "true" />

<c:if test="${auth == '[VISITOR]'}">
	<c:set var="visitor" value = "false" />
	<c:set var="homeVisitor" value = "true" />
</c:if>

<c:if test = "${visitor}">
<jsp:include page="header.jsp" />
</c:if>

<c:if test = "${homeVisitor}">
	<jsp:include page="headerVisitor.jsp" />
	
	<style type="text/css">
		#wrapper {
			padding-left: 0;
		}
	</style>
</c:if>

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Nticxs - Bienvenidos
            </h1>
        </div>
    </div>

    <c:if test = "${homeVisitor}">
	    <div class="alert alert-warning">
	    	Su cuenta fue creada con exito. Por favor contacte al administrador para completar el proceso 
	    	de autenticacion.
	    	Solo es necesario que envie un mail especificando el detalle de su usuario (si es alumno, padre o
	    	 directivo, y si necesita crear la cuenta por primera vez o no) en el campo de texto que se encuentra a continuacion.
	    	Saludos Coordiales 
	    </div>
	</c:if>
	
			<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Envio de correo</h3>
			</div>
			<div class="panel-body">
				<c:if test="${not empty msg1}">
					<div class="alert alert-warning">${msg1}</div>
				</c:if>
				<form role="form"
					action="<c:url value="home/" />"
					method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label>Para</label>
                                <input class="form-control" value="leandrogabrielguzman@gmail.com" disabled>
                                <p> </p>
                     	<label>Asunto</label>
                                <input class="form-control" value="Activacion de cuenta" disabled>
                                <p> </p>
						<label>Mensaje</label> <input name="Mensaje"
							class="form-control" placeholder="Por favor ingrese el texto del mensaje"  required>
					</div>
					<!--</fieldset>-->
					<button type="submit" class="btn btn-default">Enviar</button>
				</form>
				<p class="help-block">*mandatory</p>
			</div>
		</div>
		</body>
		<jsp:include page="footer.jsp" />
</html>
