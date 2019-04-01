<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>eReach Campaign Service</title>

    <!-- Favicon -->
<%--     <link rel='shortcut icon' href='<c:url value="/resources/images/nticxs-logo.png" />' type="image/x-icon"/> --%>

    <!-- Bootstrap Core CSS -->
    <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">

    <!-- Custom CSS -->
 	<link href='/alumnos/resources/css/test.css' rel="stylesheet">

    <!-- Morris Charts CSS -->
<%--     <link href='<c:url value="/resources/css/plugins/morris.css" />' rel="stylesheet"> --%>

    <!-- Custom Fonts -->
    <link href='<c:url value="/resources/font-awesome/css/font-awesome.min.css" />' rel="stylesheet" type="text/css">

    <!-- Custom jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
    <div class="panel-body">
        <div class="row">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-2 col-md-offset-2">
                            <img id="profile-img" class="profile-img-card" src="resources/images/nticxs-logo.png" />
                        </div>
                    </div>
                    
                    <c:if test="${not empty msg1}">
                        <div class="alert alert-warning">
                        	${msg1}
                    	</div>
                    </c:if>

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid user name and password.</p>
                        </div>
                    </c:if>
                    
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty msg2}">
                        <div class="alert alert-success" id="msg2Div">
                        	${msg2}
                    	</div>
                    </c:if>
                    
                    <c:if test="${not empty imgQR}">
                    	<div class="alert alert-success" id="imgQRDiv">
    	                	<form action='https://chart.googleapis.com/chart' method='POST' target="_blank">
    						  <input type='hidden' name='cht' value='qr' />
    						  <input type='hidden' name='chs' value='300x300' />
    						  <input type='hidden' name='chl' value='<c:url value="${imgQR}" />'/>
    						  <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" id="codeQR">Created QR Code</button>
    						</form>
    					</div>

                        <script src='<c:url value="/resources/js/jqueryLogin.js" />'></script>
                    </c:if>
                    
                    <form action="<c:url value="/login" />" method="post" class="form-signin">
                        <p> </p>
                        <input name="user" id = "userName" type="text" class="form-control" placeholder="User" required autofocus>
                        <p> </p>
                        <input name="password" id = "userPassword" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                        <!--<div id="remember" class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me"> Remember me
                            </label>
                        </div>-->
                        <p> </p>
                        <a href="<c:url value="/signup" />" id="signup">Sign up</a>
                        <p> </p>
                        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit" id="sign">Sign in</button>
                    </form>
                </div>
                <div class="col-md-4">
                </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src='<c:url value="/resources/js/jquery.js" />'></script>

    <!-- Bootstrap Core JavaScript -->
    <script src='<c:url value="/resources/js/bootstrap.min.js" />'></script>

    <!-- Morris Charts JavaScript -->
<%--     <script src='<c:url value="/resources/js/plugins/morris/raphael.min.js" />'></script> --%>
<%--     <script src='<c:url value="/resources/js/plugins/morris/morris.min.js" />'></script> --%>
<%--     <script src='<c:url value="/resources/js/plugins/morris/morris-data.js" />'></script> --%>

</body>

</html>
