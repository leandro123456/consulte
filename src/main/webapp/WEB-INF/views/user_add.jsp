<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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