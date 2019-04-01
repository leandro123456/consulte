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
                        <div class="panel-heading">
                            <h3 class="panel-title">User Details</h3>
                        </div>
                        <div class="panel-body">
                        <form role="form" action="<c:url value='/home/general/configuration/user/edit/' />${user.id}" method="post" enctype="multipart/form-data" autocomplete="off">
                            <div class="form-group">
                                <label>User ID</label>
                                <input name="id" class="form-control" value="${user.id}" disabled>
                                <p> </p>
                                <label>Name</label>
                                <input name="name" class="form-control" value="${user.name}">
                                <p> </p>
                                <label>Category</label>

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

                                <script src='<c:url value="/resources/js/jqueryPassword.js" />'></script>

                                <div class="form-group">
                                    <div class="col-md-6">
                                        <div class="radio">
                                          <label>
                                            <input type="radio" name="passwordChangeorKeep" id="1" value="false">
                                            Change Password
                                            </label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="radio">
                                          <label>
                                            <input type="radio" name="passwordChangeorKeep" id="2" value="true" checked>
                                            Keep Password
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <script src='<c:url value="/resources/js/jqueryUserEdit.js" />'></script>

                                <div class="row" id="password">
                                    <div class="col-lg-2">
                                    </div>
                                    <div class="col-lg-8">
                                        <div  class="panel panel-default">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Modify password</h3>
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
                                                <label>Current password</label>
                                                <input type="password" name="passOld" class="form-control" value="">
                                                <p> </p>
                                                <label>New password</label>
                                                <input id="newPassword" type="password" name="passNew1" class="form-control" autocomplete="off">
                                                <p> </p>
                                                <input id="randomPassword" class="btn btn-default" value="Random Password">
                                                <p> </p>
                                                <input id="viewPassword" class="btn btn-default" value="View Password">
                                                <p> </p>
                                                <label>Confirm new password</label>
                                                <input id="confirmNewPassword" type="password" name="passNew2" class="form-control" autocomplete="off">
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