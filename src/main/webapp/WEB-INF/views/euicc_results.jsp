<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            eReach - eUICC Results
                        </h1>
                    </div>
                </div>
                <!--
                <div class="row">
                    <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Query results</h3>
                            </div>
                            
                            <div class="panel-body">
                                <div class="panel-heading">
                                    <div class="row">
                                        <button type="submit" class="btn btn-default"><a href="<c:url value="/home/euicc/add/" />${card.id}">Add eUICC </a></button>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>EID</th>
                                                <th>SM-SR ID</th>
                                                <th>EUM ID</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${cardsFound}" var="card">
                                            <tr>
                                                <td>${card.eid}</td>
                                                <td>${card.smSrId}</td>
                                                <td>${card.eumId}</td>
                                                <td><a href="<c:url value="/home/euicc/commands/" />${card.eid}"><i class="fa fa-info-circle"></i></a></td>
                                            </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                   <div class="col-lg-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">eUICC info</h3>
                            </div>
                            <ul class="list-group">
                              <li class="list-group-item"><span class="badge">info</span>EID: </li>
                              <li class="list-group-item"><span class="badge">info</span>Eum-id: </li>
                              <li class="list-group-item"><span class="badge">info</span>Eum: </li>
                              <li class="list-group-item"><span class="badge">info</span>Production Date: </li>
                              <li class="list-group-item"><span class="badge">info</span>Platform Type: </li>
                              <li class="list-group-item"><span class="badge">info</span>Platform Version: </li>
                              <li class="list-group-item"><span class="badge">info</span>Remaining Memory: </li>
                              <li class="list-group-item"><span class="badge">info</span>Available Memory for Profiles: </li>
                              <li class="list-group-item"><span class="badge">info</span>Smsr-Id: </li>
                              <li class="list-group-item"><span class="badge">info</span>ECASD: </li>
                              <li class="list-group-item"><span class="badge">info</span>eUICC-Capabilities: </li>
                              <li class="list-group-item"><span class="badge">info</span>CAT-TP Support: </li>
                              <li class="list-group-item"><span class="badge">info</span>CAT-TP Version: </li>
                              <li class="list-group-item"><span class="badge">info</span>HTTP Support: </li>
                              <li class="list-group-item"><span class="badge">info</span>HTTP Version: </li>
                              <li class="list-group-item"><span class="badge">info</span>Secure packet version: </li>
                              <li class="list-group-item"><span class="badge">info</span>Remote provisioning version: </li>
                              <li class="list-group-item"><span class="badge">info</span>Eum Certificated: </li>
                              <li class="list-group-item"><span class="badge">info</span>Signature Algorith: </li>
                              <li class="list-group-item"><span class="badge">info</span>Signature: </li>
                            </ul>
                        </div>                      
                    </div>
                </div>
                -->
                <div class="row">
                <div class="col-lg-12"></br>
                  <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Query results</h3>
                            </div>
                            
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>EID</th>
                                                <th>SM-SR ID</th>
                                                <th>EUM ID</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${cardsFound}" var="card">
                                            <tr>
                                                <td>${card.eid}</td>
                                                <td>${card.smSrId}</td>
                                                <td>${card.eumId}</td>
                                                <td><a href="<c:url value="/home/euicc/commands/" />${card.eid}" data-toggle="tooltip" title="Information and associated commands to EID"><i class="fa fa-arrow-right"></i></a></td>
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