<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

Possíveis próximos  passos
<c:forEach var="subcategory" items="${subcategories}">
    <div class="subcategory-box">
            ${subcategory.name}
    </div>
</c:forEach>