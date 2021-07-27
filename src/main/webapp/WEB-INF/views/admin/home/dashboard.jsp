<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>home</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <h1>APENAS TESTE</h1>
    <c:forEach items="${instructorsProjection}" var="instructor">
            <p>${instructor.getAmount()}</p>
        <p>${instructor.getInstructor()}</p>
    </c:forEach>

    <c:forEach items="${categoriesFromCourseProjection}" var="categories">
        <p>${categories.getName()}</p>
        <p>${categories.getAmount()}</p>
    </c:forEach>
</head>
<body>
</body>
</html>