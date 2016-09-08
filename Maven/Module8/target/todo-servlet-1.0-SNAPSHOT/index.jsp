<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge;" />
    <title>Azure DocumentDB Java Sample</title>

    <!-- Bootstrap -->
    <link href="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Add padding to body for fixed nav bar */
        body {  padding-top: 50px;  }
    </style>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">My Tasks</a>
        </div>
    </div>
</div>

<div class="container">
    <h1>My ToDo List</h1>
    <hr/>
    <div class = "todoList">
        <form action="todoServlet" method="post" class="form-horizontal" role="form">
        <table class="table table-bordered table-striped" id="todoItems">
            <thead>
            <tr style="background-color: gray;">
                <th>Name</th><th>Category</th><th>Complete</th>
            </tr>
            </thead>

            <c:set var="list" value="${sessionScope.itemList}"/>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.category}</td>
                    <td>
                        <input type="checkbox" name="${item.name}_state" id="${item.name}_state" ${item.state ? "checked=checked" : ""} title="${item.name}_state"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="todoUpdatePanel">
                <button type="submit" name="updateButton" id="updateButton" class="btn btn-primary">Update Tasks</button>
                <button type="submit" name="deleteTask" id="deleteTask" class="btn btn-primary">Delete Task</button>
        </div>
        </form>
    </div>
    <hr/>
    <div class="todoForm">
        <form action="todoServlet" method="post" class="form-horizontal" role="form">
            <div class="form-group">
                <label for="inputItemName" class="col-sm-2">Task Name</label>
                <div class="col-sm-10">
                    <input type="text" name="inputItemName" class="form-control" id="inputItemName" placeholder="Enter name">
                </div>
            </div>
            <div class="form-group">
                <label for="inputItemCategory" class="col-sm-2">Task Category</label>
                <div class="col-sm-10">
                    <input type="text" name="inputItemCategory" class="form-control" id="inputItemCategory" placeholder="Enter category">
                </div>
            </div>
            <button type="submit" name="addTask" id="addTask" class="btn btn-primary">Add Task</button>
            <button type="submit" name="clear" id="clear" class="btn btn-primary">Clear</button>
        </form>
    </div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.min.js"></script>
<script src="//ajax.aspnetcdn.com/ajax/bootstrap/3.2.0/bootstrap.min.js"></script>
<script src="assets/todo.js"></script>
</body>
</html>