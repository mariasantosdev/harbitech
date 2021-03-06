<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Lista Cursos">
<div class="col-sm-offset--1 col-sm-10">
    <h4>${subcategory.name}</h4>
<h1>Cursos</h1> <br/>
    <a class="btn btn-primary" href="/admin/courses/new">Novo curso</a><br/><br/><br/>
</div>
<div class="panel-body">
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <th class="text-center col-md-2">Nome</th>
        <th class="text-center col-md-2">Código da url</th>
        <th class="text-center col-md-2">Status</th>
        <th class="text-center col-md-2"></th>
    </tr>
    </thead>
    <tbody>

     <c:forEach items="${courses}" var="course">
        <tr>
        <td class="text-center">${course.name} </td>
        <td class="text-center">${course.codeUrl}</td>
        <td class="text-center">${course.visibilityDescription}</td>
            <td class="text-center"><a class="btn btn-link aw-link-panel" href="/admin/courses/${course.categoryCodeUrl}/${course.subcategoryCodeUrl}/${course.codeUrl}">Editar</a></td>
        </tr>
     </c:forEach>
    </tbody>
         <ul class="pagination">
             <li class="disabled">
                 <c:if test="${courses.size() > 0}">
                     <c:forEach begin="0" end="${totalPages-1}" var="page">
                        <li class="page-item">
                            <a href="/admin/courses/${category}/${subcategory.codeUrl}?page=${page}"
                               class="page-link">${page+1}
                            </a>
                         </li>
                     </c:forEach>
                </c:if>
             </li>
         </ul>
     <c:if test="${empty courses}">
         <td class = "text-center">Ops! Não há nenhum registro nessa lista!</td>
     </c:if>

</table>
</div>
</templates:admin-template>