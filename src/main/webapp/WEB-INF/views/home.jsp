<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<head>
    <title>Pantalla Principal</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="apple-touch-icon" href="apple-touch-icon.png">
    <link href='<c:url value="/resources/assets/css/bootstrap.min.css"/>' rel="stylesheet">
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/assets/css/plugins.css" />'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/assets/css/raleway-webfont.css" />'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/assets/css/style.css"/>'>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/assets/css/responsive.css" />'>
    <script src='<c:url value="/resources/assets/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />'></script>    
    <script src='<c:url value="/resources/vendor/jquery/jquery.min.js" />'></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <link href="../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <div class='preloader'><div class='loaded'>&nbsp;</div></div>
        <nav class="navbar navbar-default navbar-fixed-top">             
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value='/home/' />"><img alt="Nticxs" width="50%" 
                    src='<c:url value="/resources/images/nticxs-logo.png" />'></a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">                      
                        <li class="active"><a href="#home">Inicio</a></li>
                        <li><a href="#clases">Clases</a></li>
<!--                         <li><a href="#foro">Foro</a></li> -->
                        <li><a href="#notas">Notas</a></li>
                        <li><a href="#faltas">Faltas</a></li>
                        <li><a href="#contacto">Contacto</a></li>                                 
                    </ul>
                </div>
                <ul class="navbar-top-links navbar-right"> 
                    <li class="navbar-top-links navbar-right">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i> Mensajes <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>DE quien vino</strong>
                                        <span class="pull-right text-muted">
                                            <em>Dia</em>
                                        </span>
                                    </div>
                                    <div>Contenido del mail</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Leer todos los mensajes</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="navbar-top-links navbar-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-user"></i>${usuario}<i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="fa fa-user fa-fw"></i> Perfil</a>
                            </li>
                            <li><a href="<c:url value='/home/general/configuration' />" data-toggle="tooltip" title="General settings"><i class="fa fa-gear fa-fw"></i> Configuracion</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                 <a href="<c:url value="/logoutsession" />"><i class="fa fa-fw fa-power-off"></i>Salir</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <header id="home" class="home">
            <div class="overlay-img">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="home-content">
                                <h1>Materia <span>NTICXs</span></h1>
                                <h4>Ser Piero Da Vinci</h4>
                                <h5>Curso  <span>4to</span></h5>
                                <h6>Docente Guzman Leandro</h6>      
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
        </header>
        <section id="clases" class="sections">
        <div class="heading-content text-center">
            <div class="heading-title">
                <h3>Clases</h3>
                <div class="separator"></div>
            </div>
            <div class="heading-separator"></div>
            <div class="heading-details">
                <h3>En esta seccion se agregaran notas y documentacion correspondientes a las clases</h3>
            </div>
        </div>
        <div id="cd-timeline" class="cd-container">
        	
        	<c:forEach items="${documentos}" var="doc" varStatus="counter">	
	        	<div class="cd-timeline-block">
	                <div class="cd-timeline-img cd-location">
	                </div>
	                <div class="cd-timeline-content">
	                	<p>${doc.fecha}</p>
	                    <h2>${doc.descripcion}</h2>
	                	<table class="table table-hover">
				    		<thead>
					      		<tr>
							        <th>${doc.name}</th>
							        <th width="100"></th>
								</tr>
					    	</thead>
				    		<tbody>
								<tr>
									<td><a href="<c:url value='download/document/${doc.id}' />" class="btn btn-success custom-width">Descargar</a></td>
								</tr>
				    		</tbody>
			    		</table>
			    		<span class="cd-date"><img src='<c:url value="/resources/assets/images/timeline/timeline.png" />' alt="timeline" /></span>
	                </div>
	            </div>
            </c:forEach>
        </div>
    </section>
<!--     <section id="foro" class="sections different-bg"> -->
<!--         <div class="container text-center"> -->
<!--             <div class="row"> -->
<!--                 <div class="container text-center"> -->
<!--                     <div class="row"> -->
<!--                         <div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12"> -->
<!--                             <div class="heading-title"> -->
<!--                                 <h3>Foro</h3> -->
<!--                                 <div class="separator"></div> -->
<!--                             </div> -->
<!--                             <div class="heading-separator"></div> -->
<!--                             <div class="heading-details"> -->
<!--                                 <p>Esta seccion permite cargar preguntas y respuestas de temas relacionadoas a la materia.</p> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12"> -->
<!--                 <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="3000"> -->
<!--                     <div class="carousel-inner" role="listbox"> -->
<!--                         <div class="item active"> -->
<!--                             <div class="client-content"> -->
<!--                                 <h3>What Our Clients are saying</h3> -->
<!--                                 <p> -->
<!--                                     “It was a pleasure to work with Imran. He is very dedicated and professional.  -->
<!--                                     He worked very hard to satisfy our requirements and the communication was great.” -->
<!--                                 </p> -->
<!--                                 <div class="client-basicinfo"> -->
<!--                                     <h6>John Doe</h6> -->
<!--                                     <a href="#">www.yourwebsite.zt</a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div>      -->
<!--                         <div class="item"> -->
<!--                             <div class="client-content"> -->
<!--                                 <h3>What Our Clients are saying</h3> -->
<!--                                 <p> -->
<!--                                     “It was a pleasure to work with Imran. He is very dedicated and professional.  -->
<!--                                     He worked very hard to satisfy our requirements and the communication was great.”  -->
<!--                                 </p> -->
<!--                                 <div class="client-basicinfo"> -->
<!--                                     <h6>John Doe</h6> -->
<!--                                     <a href="#">www.yourwebsite.zt</a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                         <div class="item"> -->
<!--                             <div class="client-content"> -->
<!--                                 <h3>What Our Clients are saying</h3> -->
<!--                                 <p> -->
<!--                                     “It was a pleasure to work with Imran. He is very dedicated and professional.  -->
<!--                                     He worked very hard to satisfy our requirements and the communication was great.” -->
<!--                                 </p> -->
<!--                                 <div class="client-basicinfo"> -->
<!--                                     <h6>John Doe</h6> -->
<!--                                     <a href="#">www.yourwebsite.zt</a> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <ol class="carousel-indicators"> -->
<!--                         <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li> -->
<!--                         <li data-target="#carousel-example-generic" data-slide-to="1"></li> -->
<!--                         <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
<!--                     </ol> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div>      -->
<!--     </section> -->
    <section id="notas" class="skills skill-bg">
<!-- 	<section id="notas" class="sections"> -->
        <div class="overlay-img">
            <div class="heading-content text-center">
                <h3>Notas</h3>
                <div class="separator"></div>
                <h3>En esta seccion se muestran las notas de cada alumno</h3>
            </div>
            <div class="col-sm-3 text-center">
                 <div class="main-skill">
                     <div class="chart-round">
                         <div class="chart" data-percent="${promediotareas}">
                             <span class="percent"></span>
                         </div>
                     </div>
                     <div class="skills-text">Promedio de Tareas</div>
                 </div>
             </div>
                    <div class="col-sm-3 text-center">
                        <div class="main-skill">
                            <div class="chart-round">
                                <div class="chart" data-percent="${promediotp}">
                                    <span class="percent"></span>
                                </div>
                            </div>
                            <div class="skills-text">Promedio de Trabajos Practicos</div>
                        </div>
                    </div>
                    <div class="col-sm-3 text-center">
                        <div class="main-skill">
                            <div class="chart-round">
                                <div class="chart" data-percent="${promedioev}">
                                    <span class="percent"></span>
                                </div>
                            </div>
                            <div class="skills-text">Promedio de Evaluaciones</div>
                        </div>
                    </div>
                    <div class="col-sm-3 text-center">
                        <div class="main-skill">
                            <div class="chart-round">
                                <div class="chart" data-percent="${promediotrimestre}">
                                    <span class="percent"></span>
                                </div>
                            </div>
                            <div class="skills-text">Promedio del Trimestre</div>
                        </div>
                    </div>
            <div class="row">
               <div class="panel-heading"><span class="lead">Lista de notas del Alumno </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>Trimestre</th>
					        <th>Tipo</th>
					        <th>Fecha</th>
					        <th>Descripcion</th>
					        <th>Valor</th>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${notas}" var="nota" varStatus="counter">
						<tr>
							<td>${nota.trimestre}</td>
							<td>${nota.tipo}</td>
							<td>${nota.fecha}</td>
							<td>${nota.descripcion}</td>
							<td>${nota.valor}</td>
						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
            </div>
        </div>      
    </section>
    <section id="faltas" class="sections">
        <div class="overlay-img">
            <div class="container sections text-center">
                <div class="heading-content text-center">
                    <h3>Asistencia</h3>
                    <div class="separator"></div>
                    <p>En esta seccion se muestra la asistencia de cada alumno</p>
                </div>
                <div class="col-sm-3 text-center">
                 <div class="main-skill">
                     <div class="chart-round">
                         <div class="chart" data-percent="${asistenciaPresente}">
                             <span class="percent"></span>
                         </div>
                     </div>
                     <div class="skills-text">Cantidad de Asistencia Presente</div>
                 </div>
             </div>
                    <div class="col-sm-3 text-center">
                        <div class="main-skill">
                            <div class="chart-round">
                                <div class="chart" data-percent="${asistenciaFaltas}">
                                    <span class="percent"></span>
                                </div>
                            </div>
                            <div class="skills-text">Cantidad de Asistencia Ausente</div>
                        </div>
                    </div>
                    <div class="col-sm-3 text-center">
                        <div class="main-skill">
                            <div class="chart-round">
                                <div class="chart" data-percent="15%">
                                    <span class="percent"></span>
                                </div>
                            </div>
                            <div class="skills-text">Promedio de Inasistencia Disponible</div>
                        </div>
                    </div>
                <div class="row">
                    <div class="panel-heading"><span class="lead">Tabla con la asistencia del Alumno </span></div>
			  	<div class="tablecontainer">
					<table class="table table-hover">
			    		<thead>
				      		<tr>
						        <th>Tipo</th>
						        <th>Fecha</th>
						        <th>Descripcion</th>
							</tr>
				    	</thead>
			    		<tbody>
						<c:forEach items="${asistencias}" var="falta" varStatus="counter">
							<tr>
								<td>${falta.tipo}</td>
								<td>${falta.fecha}</td>
								<td>${falta.descripcion}</td>
							</tr>
						</c:forEach>
			    		</tbody>
			    	</table>
			    </div>
                </div> 
            </div>
        </div>
    </section>
    <section id="contacto" class="sections lightbg">
        <div class="container">
            <div class="heading-content text-center">
                <div class="heading-title">
                    <h3>Contacto</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
                    <form>
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Nombre*">
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email*">
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="form-group">
                                <textarea class="form-control txt-area" rows="5" placeholder="Mensaje . . ."></textarea>
                            </div>
                        </div>
                        <div class="submit-btn"><button type="submit" class="btn btn-default abt-btn">Enviar</button></div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <div class="scroll-top">
        <div class="scrollup">
            <i class="fa fa-angle-double-up"></i>
        </div>
    </div>
    <script src='<c:url value="/resources/assets/js/vendor/jquery-1.11.2.min.js" />'></script>
    <script src='<c:url value="/resources/assets/js/vendor/bootstrap.min.js" />'></script>
    <script src='<c:url value="/resources/assets/js/jquery.easypiechart.min.js" />'></script>
    <script src='<c:url value="/resources/assets/js/plugins.js" />'></script>
    <script src='<c:url value="/resources/assets/js/modernizr.js" />'></script>
    <script src='<c:url value="/resources/assets/js/main.js" />'></script>
</body>
<jsp:include page="footer.jsp" /> 
 