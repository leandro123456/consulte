<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

				<!-- Mensajes si se agregan lots -->
                <c:if test="${not empty msgEdit}">
                    <div class="alert alert-success">
                        <c:forEach items="${msgEdit}" var="edit">
                            <p>${edit}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${not empty msgView}">
                    <div class="alert alert-success">
                        <c:forEach items="${msgView}" var="view">
                            <p>${view}</p>
                        </c:forEach>
                    </div>
                </c:if>



                <!-- Mensajes si se remueven lots -->
                <c:if test="${not empty msgEditRemove}">
                    <div class="alert alert-warning">
                        <c:forEach items="${msgEditRemove}" var="edit">
                            <p>${edit}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${not empty msgViewRemove}">
                    <div class="alert alert-warning">
                        <c:forEach items="${msgViewRemove}" var="view">
                            <p>${view}</p>
                        </c:forEach>
                    </div>
                </c:if>



                <!-- Mensajes si se intenta agregar un lot que ya existe -->
                <c:if test="${not empty msgEditRepeated}">
                    <div class="alert alert-warning">
                        <c:forEach items="${msgEditRepeated}" var="edit">
                            <p>${edit}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${not empty msgViewRepeated}">
                    <div class="alert alert-warning">
                        <c:forEach items="${msgViewRepeated}" var="view">
                            <p>${view}</p>
                        </c:forEach>
                    </div>
                </c:if>



                <!-- Mensajes si se borran lots -->
                <c:if test="${not empty deleteLotsEdit}">
                    <div class="alert alert-success">
                        <c:forEach items="${deleteLotsEdit}" var="edit">
                            <p>${edit}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${not empty deleteLotsView}">
                    <div class="alert alert-success">
                        <c:forEach items="${deleteLotsView}" var="view">
                            <p>${view}</p>
                        </c:forEach>
                    </div>
                </c:if>




                <!-- Mensajes si se intenta borrar lots que no se tiene -->
                <c:if test="${not empty neverDeleteEdit}">
                    <div class="alert alert-danger">
                        <c:forEach items="${neverDeleteEdit}" var="edit">
                            <p>${edit}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <c:if test="${not empty neverDeleteView}">
                    <div class="alert alert-danger">
                        <c:forEach items="${neverDeleteView}" var="view">
                            <p>${view}</p>
                        </c:forEach>
                    </div>
                </c:if>