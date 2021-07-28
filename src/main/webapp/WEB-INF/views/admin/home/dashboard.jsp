<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>home</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />

    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
            </div>
        </div>
    </nav>

    <div class="col-sm-4">
    <div class="panel panel-default">
        <div class="panel-heading">Cursos por categoria</div>
        <table class="table">
            <thead>
            <tr>
                <th class="col-md-2">Nome da categoria</th>
                <th class="col-md-2 text-left">Quantidade de cursos</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${categoriesFromCourseProjection}" var="categories">
            <tr>
                <td>${categories.getName()}</td>
                <td class="col-md-2 text-left">${categories.getAmount()}</td>
            </tr>
            </c:forEach>

        </table>
    </div>
    </div>

    <div class="col-sm-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Instrutor com mais cursos</h3>
            </div>
            <div class="panel-body">
                <c:forEach items="${instructorsProjection}" var="instructor">
                    <p>${instructor.getInstructor()}</p>
                    <p>Total de cursos: ${instructor.getAmount()}</p>
                </c:forEach>
            </div>
        </div>
    </div>
</head>
<body>
</body>
</html>