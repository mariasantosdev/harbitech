<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>

<div class="col-sm-offset--1 col-sm-10">
    <h1>Diferenças entre as categorias</h1> <br/>
</div>
<div class="panel-body">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th class="text-center col-md-2">Descricao</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${categories}" var="category">
            <tr>
                <td class="text-center">${category.name} </td>
                <td class="text-center">${category.codeUrl}</td>
                <td class="text-center">${category.description}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty categories}">
            <td class="text-center">Ops! Não encontramos nenhuma categoria por aqui!</td>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
