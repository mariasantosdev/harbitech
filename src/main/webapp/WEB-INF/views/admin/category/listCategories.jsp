<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Lista Categorias">

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
        <td class="text-center">${category.statusDescription}</td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/categories/${category.codeUrl}/subcategories">Subcategorias</a></td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/categories/${category.codeUrl}">Editar</a></td>
        </tr>
     </c:forEach>
     <c:if test="${empty categories}">
         <td class = "text-center">Ops! Não há nenhum registro nessa lista!</td>
     </c:if>
    </tbody>
</table>
</templates:admin-template>