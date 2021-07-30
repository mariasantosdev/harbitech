<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Subcategorias</title>
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
    <h4>${category.name}</h4>
<h1>Subcategorias</h1> <br/>
    <a class="btn btn-primary" href="/admin/subcategories/new">Nova Subcategoria</a><br/><br/><br/>
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

     <c:forEach items="${subcategories}" var="subcategory">
        <tr>
        <td class="text-center">${subcategory.name} </td>
        <td class="text-center">${subcategory.codeUrl}</td>
        <td class="text-center">${subcategory.statusDescription}</td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/courses/${subcategory.categoryCodeUrl}/${subcategory.codeUrl}">Cursos</a></td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/subcategories/${subcategory.categoryCodeUrl}/${subcategory.codeUrl}">Editar</a></td>
        </tr>
     </c:forEach>
     <c:if test="${empty subcategories}">
         <td class="text-center">Ops! Não há nenhum registro nessa lista!</td>
     </c:if>
    </tbody>
</table>
</body>
</html>