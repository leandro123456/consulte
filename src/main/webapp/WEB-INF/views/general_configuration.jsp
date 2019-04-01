<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
      <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            eReach - General Configuration
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                		<script src='<c:url value="/resources/js/jqueryGeneralConfiguration.js" />'></script>
                        <ul class="nav nav-tabs" id="tabGenelConfiguration">
                            <li class="active"><a  href="#1" data-toggle="tab">Users</a></li>
                            <li ><a  href="#2" data-toggle="tab">Roles</a></li>
                            <li id="lotsLi"><a  href="#3" data-toggle="tab">Lots</a></li>
                            <li id="cardLotsLi"><a  href="#4" data-toggle="tab">Lots of Cards</a></li>
                        </ul>

                    <div class="tab-content " id="genelConfigurationDiv">
                        <div class="tab-pane active" id="1">
                            <div class="row">
                                <div class="col-lg-12"></br>
                                    <div class="panel-body">

                                        <c:if test="${not empty msg1}">
                                            <div class="alert alert-warning">
                                                ${msg1}
                                            </div>
                                        </c:if>

                                        <jsp:include page="msg.jsp" />

                                        <c:if test="${not empty imgQR}">
                                           <script type="text/javascript">
                                                $(window).on('load',function(){
                                                    $('#myModal').modal({
                                                        show: true,
                                                        backdrop: 'static',
                                                        keyboard: false,
                                                    });
                                                });
                                            </script>

                                            <!-- Modal content-->
                                            <div class="modal fade" id="myModal" role="dialog">
                                                <div class="modal-dialog modal-sm">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <center>
                                                                <h4 class="modal-title">Create QR Code</h4>
                                                            </center>
                                                        </div>

                                                        <div class="modal-body">
                                                            <center>
                                                                <c:if test="${not empty msgQR}">
                                                                    <div class="alert alert-success">
                                                                        ${msgQR}
                                                                    </div>
                                                                </c:if>

                                                                <form action='https://chart.googleapis.com/chart' method='POST' target="_blank">
                                                                    <input type='hidden' name='cht' value='qr' />
                                                                    <input type='hidden' name='chs' value='300x300' />
                                                                    <input type='hidden' name='chl' value='<c:url value="${imgQR}" />'/>
                                                                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" id="codeQR">Created QR Code</button>
                                                                    
                                                                    <script type="text/javascript">
                                                                        $("#codeQR").click(function(){
                                                                            $('#myModal').modal('hide');
                                                                        });    
                                                                    </script>
                                                                </form>   
                                                            </center>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- End modal content-->
                                        </c:if>
                
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>User</th>
                                                        <th>Role</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${user}" var="user">
                                                        <c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />
                                                        <c:set var="comp" value="false"></c:set>
                                                        
                                                        <c:if test="${auth == '[ADMIN]' || auth == '[SUPERADMIN]'}">
                                                            <c:set var="comp" value="true"></c:set>
                                                        </c:if>

                                                        <c:if test="${pageContext.request.userPrincipal.name == user.name}">
                                                            <c:set var="comp" value="true"></c:set>
                                                        </c:if>

                                                        <c:if test="${user.role == 'SUPERADMIN' && auth != '[SUPERADMIN]'}">
                                                            <c:set var="comp" value="false"></c:set>
                                                        </c:if>

                                                        <c:if test="${comp}">
                                                            <script src='<c:url value="/resources/js/jqueryGeneralConfiguration-Users.js" />'></script>
                                                            <tr>
                                                                <td>${user.name}</td>
                                                                <td>${user.role}</td>
                                                                <td>
                                                                    <form id="formUser_${user.id}" role="form" action="<c:url value='/home/general/configuration/user/edit/' />${user.id}">

                                                                    <select id = "selectUser_${user.id}" class="form-control" style="width: auto; display: -webkit-inline-box;">
                                                                        <option id="edit">Edit User</option>
                                                                        <option id="delete">Delete User</option>
                                                                        <option id="newKey">New Key for TSA</option>
                                                                        <option>View all Lots</option>
                                                                        <option>View all Card Lots</option>
                                                                        <option id="addLots">Add Lots to User</option>
                                                                        <option id="deleteLots">Delete Lots to User</option>
                                                                        <option id="addCardLots">Add Card Lots to User</option>
                                                                        <option id="deleteCardlots">Delete Card Lots to User</option>
                                                                    </select>
                                                                   
                                                                        <button type="submit" id="buttonProceedUser_${user.id}"
                                                                        style="padding: 3px;  padding-left: 8px; padding-right: 8px; margin-left: 10px" 
                                                                        class="btn btn-default" data-toggle="modal" data-target="">Proceed</button>    
                                                                    </form>
                                                                    

                                                                    <!-- Modal content-->
                                                                    <div class="modal fade" id="myModal${user.id}" role="dialog">
                                                                        <div class="modal-dialog modal-sm">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <center>
                                                                                        <h4 class="modal-title">Confirm action</h4>
                                                                                    </center>
                                                                                </div>

                                                                                <div class="modal-body">
                                                                                    <center>
                                                                                        <form id="formComfirmUser_${user.id}">
                                                                                            <button type="submit" class="btn btn-default btn-sm btn-block">Yes</button>
                                                                                            
                                                                                            <button style="background-color: #777777; border: #777777"
                                                                                            type="button" class="btn btn-primary btn-sm btn-block"
                                                                                            data-dismiss="modal">No</button>    
                                                                                        </form>
                                                                                    </center>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <!-- End modal content-->
                                                                    
                                                                    <script type="text/javascript">
                                                                        $(document).ready(function() {
                                                                            $("#selectUser_${user.id}").click(function(){
                                                                                /* Edit User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Edit User") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/edit/' />${user.id}");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* Delete User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Delete User") {
                                                                                    $("#formUser_${user.id}").attr("action", "");

                                                                                    $("#formComfirmUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/delete/' />${user.id}");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "button");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "#myModal${user.id}");
                                                                                };

                                                                                /* New Key for TSA */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "New Key for TSA") {
                                                                                    $("#formUser_${user.id}").attr("action", "");

                                                                                    $("#formComfirmUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/createnewkey/' />${user.id}");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "button");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "#myModal${user.id}");
                                                                                };

                                                                                /* View all Lots */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "View all Lots") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/view/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* View all Card Lots */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "View all Card Lots") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/card/view/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* Add Lots to User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Add Lots to User") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/add/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* Add Lots to User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Delete Lots to User") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/delete/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* Add Card Lots to User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Add Card Lots to User") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/card/add/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };

                                                                                /* Delete Card Lots to User */
                                                                                if($("#selectUser_${user.id} option:selected").text() == "Delete Card Lots to User") {
                                                                                    $("#formUser_${user.id}").attr("action", "<c:url value='/home/general/configuration/user/lots/card/delete/${user.id}' />");

                                                                                    $("#buttonProceedUser_${user.id}").attr("type", "submit");
                                                                                    $("#buttonProceedUser_${user.id}").attr("data-target", "");
                                                                                };
                                                                            });
                                                                        });
                                                                    </script>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        

                        <div class="tab-pane " id="2">
                            <div class="row">
                                <div class="col-lg-12"></br>
                                    <div class="panel-body">
                                        <c:if test="${not empty msg1}">
                                            <div class="alert alert-warning">
                                                ${msg1}
                                            </div>
                                        </c:if>

                                        <jsp:include page="msg.jsp" />

                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Names Roles</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${roleFound}" var="rol">
                                                    <tr>
                                                        <td>${rol.nameRole}</td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />
                                        
                                    <c:if test ="${auth == '[SUPERADMIN]'}">
                                        <form role="form" action="<c:url value="/home/general/configuration/role/add" />" method="get">
                                            <button id="addRole" type="submit" class="btn btn-default">Add Role</button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>



                        <div class="tab-pane " id="3">
                            <div class="row">
                                <div class="col-lg-12"></br>
                                    <div class="panel-body">
                                        <c:if test="${not empty msg1}">
                                            <div class="alert alert-warning">
                                                ${msg1}
                                            </div>
                                        </c:if>

                                        <jsp:include page="msg.jsp" />

                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Names Lots</th>
                                                        <th>Add Lot</th>
                                                        <th>Delete Lot</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${loteFound}" var="lotes">
                                                        <tr>
                                                            <td>${lotes.nameOfLote}</td>
                                                            <td>
                                                                <select id="select_add_${lotes.nameOfLote}" class="form-control" style="width: auto; display: -webkit-inline-box;">
                                                                    <option>to Users</option>
                                                                    <option>to Templates</option>
                                                                    <option>to IPPs</option>
                                                                </select>

                                                                <a id="proceed_add_${lotes.nameOfLote}" href="<c:url value='/home/general/configuration/lot/users/add/${lotes.nameOfLote}' />" style="padding: 3px;  padding-left: 8px; padding-right: 8px; margin-left: 10px" class="btn btn-default" data-toggle="tooltip">Proceed </a>

                                                                <script type="text/javascript">
                                                                    $(document).ready(function(){
                                                                        $("#select_add_${lotes.nameOfLote}").click(function(){
                                                                            /* To Users*/
                                                                            if($("#select_add_${lotes.nameOfLote} option:selected").text() == "to Users") {
                                                                            $("#proceed_add_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/users/add/${lotes.nameOfLote}' />");
                                                                            };

                                                                            /* To Templates*/
                                                                            if($("#select_add_${lotes.nameOfLote} option:selected").text() == "to Templates") {
                                                                            $("#proceed_add_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/templates/add/${lotes.nameOfLote}' />");
                                                                            };

                                                                            /* To IPPs*/
                                                                            if($("#select_add_${lotes.nameOfLote} option:selected").text() == "to IPPs") {
                                                                            $("#proceed_add_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/ipps/add/${lotes.nameOfLote}' />");
                                                                            };
                                                                        });
                                                                    });
                                                                </script>
                                                            </td>
                                                            <td>
                                                                <select id="select_delete_${lotes.nameOfLote}" class="form-control" style="width: auto; display: -webkit-inline-box;">
                                                                    <option>to Users</option>
                                                                    <option>to Templates</option>
                                                                    <option>to IPPs</option>
                                                                </select>

                                                                <a id="proceed_delete_${lotes.nameOfLote}" href="<c:url value='/home/general/configuration/lot/users/delete/${lotes.nameOfLote}' />" style="padding: 3px;  padding-left: 8px; padding-right: 8px; margin-left: 10px" class="btn btn-default" data-toggle="tooltip">Proceed </a>

                                                                <script type="text/javascript">
                                                                    $(document).ready(function(){
                                                                        $("#select_delete_${lotes.nameOfLote}").click(function(){
                                                                            /* To Users*/
                                                                            if($("#select_delete_${lotes.nameOfLote} option:selected").text() == "to Users") {
                                                                            $("#proceed_delete_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/users/delete/${lotes.nameOfLote}' />");
                                                                            };

                                                                            /* To Templates*/
                                                                            if($("#select_delete_${lotes.nameOfLote} option:selected").text() == "to Templates") {
                                                                            $("#proceed_delete_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/templates/delete/${lotes.nameOfLote}' />");
                                                                            };

                                                                            /* To IPPs*/
                                                                            if($("#select_delete_${lotes.nameOfLote} option:selected").text() == "to IPPs") {
                                                                            $("#proceed_delete_${lotes.nameOfLote}").attr("href", "<c:url value='/home/general/configuration/lot/ipps/delete/${lotes.nameOfLote}' />");
                                                                            };
                                                                        });
                                                                    });
                                                                </script>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />
                                        
                                    <c:if test ="${auth == '[SUPERADMIN]'}">
                                        <form role="form" action="<c:url value="/home/general/configuration/lot/add" />" method="get">
                                            <button id="addLote" type="submit" class="btn btn-default">Add Lot</button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>



                        <div class="tab-pane " id="4">
                            <div class="row">
                                <div class="col-lg-12"></br>
                                    <div class="panel-body">
                                        <c:if test="${not empty msg1}">
                                            <div class="alert alert-warning">
                                                ${msg1}
                                            </div>
                                        </c:if>

                                        <jsp:include page="msg.jsp" />

                                        <div class="table-responsive">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Names Card Lots</th>
                                                        <th>Add Card Lot</th>
                                                        <th>Delete Card Lot</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${lotsCardsFound}" var="lots">
                                                        <tr>
                                                            <td>${lots.nameLot}</td>
                                                            <td>
                                                                <a style="padding: 3px;  padding-left: 8px; padding-right: 8px;" href="<c:url value='/home/general/configuration/lot/card/user/add/${lots.nameLot}' />" class="btn btn-default">${lots.nameLot} to Users</a>

                                                                <a style="padding: 3px;  padding-left: 8px; padding-right: 8px;" href="<c:url value='/home/general/configuration/lot/card/euicc/add/${lots.nameLot}' />" class="btn btn-default">${lots.nameLot} to eUICCs</a>
                                                            </td>
                                                            <td>
                                                                <a style="padding: 3px;  padding-left: 8px; padding-right: 8px;" href="<c:url value='/home/general/configuration/lot/card/user/delete/${lots.nameLot}' />" class="btn btn-default">${lots.nameLot} to Users</a>

                                                                <a style="padding: 3px;  padding-left: 8px; padding-right: 8px;" href="<c:url value='/home/general/configuration/lot/card/euicc/delete/${lots.nameLot}' />" class="btn btn-default">${lots.nameLot} to eUICCs</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                    <c:set var = "auth" value = "${pageContext.request.userPrincipal.authorities}" />
                                        
                                    <c:if test ="${auth == '[SUPERADMIN]'}">
                                        <form role="form" action="<c:url value="/home/general/configuration/lot/card/add" />" method="get">
                                            <button id="addLote" type="submit" class="btn btn-default">Add Card Lots</button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>



                        <div class="tab-pane" id="8">
                            <div class="row">
                                <div class="panel-heading">
                                    
                                </div>
                                <div class="panel-heading">
                                    
                                </div>
                            </div>
                        </div>
                    </div>

<jsp:include page="footer.jsp" />
