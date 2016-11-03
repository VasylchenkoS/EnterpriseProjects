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
            <th><h3>ID</h3></th>
            <th><h3>Waiter</h3></th>
            <th><h3>Dishes</h3></th>
            <th><h3>Table №</h3></th>
            <th><h3>Date</h3></th>
            <th><h3>State</h3></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="current_order">
            <tr>
                <td>${current_order.id}</td>
                <td>${current_order.employee.name} ${current_order.employee.surname}</td>
                <td>
                    <c:forEach items="${current_order.dishList}" var="dish">
                        <div align="left">
                            <label> Name: ${dish.name} </label>
                            <label>        </label>
                            <label> Price: ${dish.price} </label>
                        </div>
                    </c:forEach>
                </td>
                <td>${current_order.table.number}</td>
                <td>${current_order.date}</td>
                <td>${current_order.orderState}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="jumbotron text-center">
        <button type="button" class="btn btn-danger" onclick="location.href='/orders/open'">Show Open</button>
        <button type="button" class="btn btn-danger" onclick="location.href='/orders/closed'">Show Closed</button>
        <button type="button" class="btn btn-danger" onclick="location.href='/orders'">Show All</button>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/bootstrap.min.js"></script>
</body>
</html>
