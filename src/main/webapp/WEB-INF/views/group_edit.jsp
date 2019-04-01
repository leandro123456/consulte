<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            eReach - Group
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Group Details</h3>
                        </div>
                        <div class="panel-body">
                        <form role="form" action="<c:url value="/home/amddp/group/edit/" />${group.id}/" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>Group ID</label>
                                <input name="identifier" class="form-control" value="${group.id}" disabled>
                                <p> </p>
                                <label>Name</label>
                                <input name="name" class="form-control" value="${group.name}">
                                <p> </p>
                                <label>Description</label>
                                <input name="description" class="form-control" value="${group.description}">
                                <p> </p>
                                <c:choose>
							        <c:when test="${group.enabled eq 'false'}">
							           <div class="form-group">
			                                <div class="col-md-6">
				                                <div class="radio">
				                                  <label>
				                                    <input type="radio" name="active" id="1" value="true">
				                                    Active
				                                  </label>
				                                </div>
											</div>
											<div class="col-md-6">
				                                <div class="radio">
				                                  <label>
				                                    <input type="radio" name="active" id="2" value="false" checked>
				                                    Inactive
				                                  </label>
				                                </div>
				                            </div>
				                        </div>
							        </c:when>
							        <c:otherwise>
						             	<div class="form-group">
			                                <div class="col-md-6">
				                                <div class="radio">
				                                  <label>
				                                    <input type="radio" name="active" id="1" value="true" checked>
				                                    Active
				                                  </label>
				                                </div>
											</div>
											<div class="col-md-6">
				                                <div class="radio">
				                                  <label>
				                                    <input type="radio" name="active" id="2" value="false">
				                                    Inactive
				                                  </label>
				                                </div>
				                            </div>
				                        </div>
							        </c:otherwise>
						    	</c:choose>
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