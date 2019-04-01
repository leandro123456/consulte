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

    <!-- Bootstrap Core CSS -->
    <link href='<c:url value="/resources/css/bootstrap.min.css" />' rel="stylesheet">

      <!-- Morris Charts CSS -->
    <link href='<c:url value="/resources/css/plugins/test.css" />' rel="stylesheet">

    <!-- Custom Fonts -->
    <link href='<c:url value="/resources/font-awesome/css/font-awesome.min.css" />' rel="stylesheet" type="text/css">

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
                
                <form action="<c:url value="/twoauthentication" />" method="post" class="form-signin">
                    <input name="sixKey" pattern="\d*" type="text" class="form-control" placeholder="Google Authenticator Verification Code" required autofocus minlength="6" maxlength="6">
                    <p> </p>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
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

    <script src='<c:url value="/resources/js/plugins/morris/raphael.min.js" />'></script>

    <script src='<c:url value="/resources/js/plugins/morris/morris.min.js" />'></script>

    <script src='<c:url value="/resources/js/plugins/morris/morris-data.js" />'></script>

    


</body>

</html>
