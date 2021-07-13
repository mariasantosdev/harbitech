<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<c:if test="${not empty category}">
    O status da categoria  ${ category } foi alterado!
</c:if>

<c:if test="${empty category}">
    Nenhuma categoria alterada!
</c:if>

</body>
</html>
