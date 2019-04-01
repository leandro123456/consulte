<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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