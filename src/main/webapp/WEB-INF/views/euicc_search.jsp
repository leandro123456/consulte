<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            eReach - eUICC Search
                        </h1>
                    </div>
                </div>
                <!-- /.row -->

                <jsp:include page="msg.jsp" />

                <c:if test="${not empty msg1}">
                    <div class="alert alert-warning">
                        ${msg1}
                    </div>
                </c:if>

                <div class="row">
                    <div class="col-lg-6">

                        <div class="panel panel-default" id="euiccSearch">
                            <div class="panel-heading">
                                <h3 class="panel-title">eUICC search</h3>
                            </div>
                            <div class="panel-body">
                                <form role="form" action="<c:url value="/home/euicc/results" />" method="post">
                                    <div class="form-group">
                                        <input class="form-control" name="searchText" placeholder="If this empty returns all">
                                    </div> 
                                    <div class="form-group">
                                        <label>Classification</label>
                                        <div class="row">
                                            <div class="col-md-5">
                                                <select name="searchBy" class="form-control">
                                                    <option value="eid">EID</option>
                                                    <option value="msisdn">MSISDN</option>
                                                    <option value="iccid">ICCID</option>
                                                    <option value="imsi">IMSI</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-default">Search</button>
                                </form>
                            </div>
                        </div>



                        <script src='<c:url value="/resources/js/jqueryEuiccSearch.js" />'></script>
                        <div class="panel panel-default" id="euiccSMSRChange">
                            <div class="panel-heading">
                                <h3 class="panel-title">eUICC SM-SR Change</h3>
                            </div>
                            <div class="panel-body">
                       			<form role="form" action="<c:url value="/home/euicc/commands/srchange" />" method="get">
                      				<button type="submit" class="btn btn-default">SR Change</button>
           						</form>
                            </div>
						</div>                        
                    </div>




                    <div class="col-lg-6">
                        <div class="panel panel-default" id="addNewEuiccManually">
                            <div class="panel-heading">
                                <h3 class="panel-title">Add new eUICC manually</h3>
                            </div>
                            <div class="panel panel-body">
                                <h3 class="panel-title">Output to this Server</h3>
                                <div class="panel-body">
                                    <c:if test="${not empty msg2}">
                                    <div class="alert alert-warning">
                                    ${msg2}
                                    </div>
                                    </c:if>
                                    <form role="form" action="<c:url value="/home/euicc/search" />" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label>File name</label>
                                            <input type="file" name="file">
                                        </div>
                                        <div class="form-group">
                                        	<label>Keys DP</label>
	                                        <select name="dpname" class="form-control">
	                                        	<c:forEach items="${dp}" var="dp">
	                                            	<option value="${dp}">${dp}</option>
	                                        	</c:forEach>
	                                        </select>
	                                    </div>                 
                                        <button type="submit" class="btn btn-default">Upload</button>
                                    </form>
                                </div>
                            </div>
                            </hr>
                        </div>

                        <div class="panel panel-default" id="cardLotseUICC">
                            <div class="panel-heading">
                                <h3 class="panel-title">Card Lots of eUICC</h3>
                            </div>

                            <div class="panel-body">
                                <a href="<c:url value='/home/euicc/add/lots' />" class=" btn btn-default"> Add Card Lots to eUICC</a>
                                <a href="<c:url value='/home/euicc/delete/lots' />" class=" btn btn-default"> Delete Card Lots of eUICC</a>
                                <a href="<c:url value='/home/euicc/view/lots' />" class=" btn btn-default"> View Card Lots of eUICC</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

<jsp:include page="footer.jsp" />

<!-- <div class="panel panel-body">
                                <h3 class="panel-title">Third Party SM-SR</h3>
                                <div class="panel-body">
                                <fieldset disabled>
                                    <div class="form-group">
                                        <label>EID*</label>
                                        <input class="form-control">
                                    </div>  
                                    <div class="row"> 
                                        <div class="col-md-6">                              
                                            <div class="form-group">
                                                <label>EUM</label>
                                                <select class="form-control">
                                                    <option>Valid</option>
                                                    <option>Gemalto</option>
                                                    <option>G&D</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>SM-SR ID*</label>
                                                <select class="form-control">
                                                    <option>SR01ABC</option>
                                                    <option>SR02ABC</option>
                                                    <option>SR01XYZ</option>
                                                </select>
                                            </div> 
                                        </div> 
                                    </div>
                                        <button type="submit" class="btn btn-default">Add</button>
                                </fieldset>
                                </div>
                                <p class="help-block">*mandatory</p>
                            </div>-->
