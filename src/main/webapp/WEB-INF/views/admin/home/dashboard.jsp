<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="templates" tagdir="/WEB-INF/tags/templates" %>

<templates:admin-template title="Dashboard">
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

            <c:forEach items="${categoriesFromCourseProjection}" var="category">
            <tr>
                <td>${category.getName()}</td>
                <td class="col-md-2 text-left">${category.getAmount()}</td>
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
                    <p>${instructorsProjection.getInstructor()}</p>
                    <p>Total de cursos: ${instructorsProjection.getAmount()}</p>
            </div>
        </div>
    </div>
    <a class="btn btn-link aw-link-panel" href="/admin/categories">Ir para Categorias</a>
</templates:admin-template>