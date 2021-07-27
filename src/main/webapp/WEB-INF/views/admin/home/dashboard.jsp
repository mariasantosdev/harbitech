<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <h1>APENAS TESTE</h1>
    <c:forEach items="${instructorsProjection}" var="instructor">
        <tr>
            <td class="text-center">${instructor.getName()} </td>
        </tr>
    </c:forEach>
</head>
<body>
</body>
</html>