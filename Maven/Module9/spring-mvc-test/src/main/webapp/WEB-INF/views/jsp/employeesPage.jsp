<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge;" />
    <title>Sample Spring MVC</title>

    <link href="<c:url value="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/css/bootstrap.min.css"/>" rel="stylesheet">
    <style>
        body {  padding-top: 50px;  }
    </style>
</head>
<body>


<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Position</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <!--td><a href="<c:url value="/employeePage?employeeName=${employee.surname}"/>">${employee.name}</a></td-->
                    <td>${employee.name}</td>
                    <td><a href="/employeePage/${employee.surname}">${employee.surname}</a></td>
                    <td>${employee.position}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/bootstrap.min.js"></script>
</body>
</html>
