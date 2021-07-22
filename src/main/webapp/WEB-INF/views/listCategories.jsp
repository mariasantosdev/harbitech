<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<div class="col-sm-offset--1 col-sm-10">
<h1>Categorias</h1> <br/>
    <a class="btn btn-primary" href="/admin/categories/new">Nova Categoria</a><br/><br/><br/>
</div>
<div class="panel-body">
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th class="text-center col-md-2">Nome</th>
        <th class="text-center col-md-2">Código da url</th>
        <th class="text-center col-md-2">Status</th>
        <th class="text-center col-md-2"></th>
        <th class="text-center col-md-2"></th>
    </tr>
    </thead>
    <tbody>

     <c:forEach items="${categories}" var="category">
        <tr>
        <td class="text-center">${category.name} </td>
        <td class="text-center">${category.codeUrl}</td>
        <td class="text-center">${category.status.description}</td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/categories/${category.codeUrl}/subcategories">Subcategorias</a></td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/categories/${category.codeUrl}">Editar</a></td>
        </tr>
     </c:forEach>
     <c:if test="${empty categories}">
         <td class = "text-center">Ops! Não há nenhum registro nessa lista!</td>
     </c:if>
    </tbody>
</table>
</body>
</html>