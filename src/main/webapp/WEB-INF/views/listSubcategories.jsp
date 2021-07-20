<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Harbitech</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
        </div>
    </div>
</nav>

<div class="panel-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th class="text-center col-md-2">Subcategorias</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${subcategories}" var="subcategorie">
            <tr>
                <td class = "text-center">${subcategorie.name} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>