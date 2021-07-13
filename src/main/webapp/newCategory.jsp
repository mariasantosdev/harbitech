<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
	
		<c:if test="${not empty category}">
			Empresa ${ category } cadastrada com sucesso!
		</c:if>
		
		<c:if test="${empty category}">
			Nenhuma categoria cadastrada!
		</c:if>

	</body>
</html>
