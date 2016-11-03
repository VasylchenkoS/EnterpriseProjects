<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>404 error page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <link href="<c:url value="/static/css/error-style.css"/>" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
<!-----start-wrap--------->
<div class="wrap">
    <!-----start-content--------->
    <div class="content">
        <!-----start-logo--------->
        <div class="logo">
            <h1><a href="#"><img src="<c:url value="/static/images/error/logo.png"/>"/></a></h1>
            <span><img src="<c:url value="/static/images/error/signal.png"/>"/>Oops! The Page you requested was not found!</span>
        </div>
        <!-----end-logo--------->
    </div>
    <!----copy-right-------------->
    <footer>
        <p class="copy_right">&#169; 2014 Template by<a href="http://w3layouts.com" target="_blank">&nbsp;w3layouts</a> </p>
    </footer>
</div>

<!---------end-wrap---------->
</body>
</html>