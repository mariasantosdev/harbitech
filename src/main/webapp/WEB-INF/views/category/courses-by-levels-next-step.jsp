<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${empty subcategories}">
    Parabéns, sua jornada do herói dentro dessa categoria foi concluída com sucesso!
    Mas não se esqueça de que a jornada pelo conhecimento nunca termina. Para explorar outras categorias,
    <a href="/onboarding/study-mode">clique aqui</a>.
</c:if>

<div class="possible-next-step">Possíveis próximos passos</div>
<c:forEach var="subcategory" items="${subcategories}">
    <button class="subcategory-button" id="subcategory-button" data-code-url="${subcategory.codeUrl}">
            ${subcategory.name}
    </button>
</c:forEach>