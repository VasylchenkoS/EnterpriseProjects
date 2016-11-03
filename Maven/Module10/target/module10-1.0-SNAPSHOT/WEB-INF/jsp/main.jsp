<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge;" />
    <title>Sample Spring MVC</title>

    <link href="<c:url value="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/css/bootstrap.min.css"/>" rel="stylesheet">
    <style>
        body {  padding-top: 50px;  }
    </style>
</head>
<body>
    <h1 align="center">RESTful API TEST</h1>
    <br/>

    <h3 align="center"><a href="${pageContext.request.contextPath}/employees">Show All Employee</a></h3>
    <h3 align="center"><a href="${pageContext.request.contextPath}/menus">Show All Menu</a></h3>
    <h3 align="center"><a href="${pageContext.request.contextPath}/orders">Show All Orders</a></h3>

    <!-- Placed at the end of the document so the pages load faster -->
    <script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/bootstrap.min.js"></script>
</body>
</html>
