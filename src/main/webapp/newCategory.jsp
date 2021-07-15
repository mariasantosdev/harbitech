<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<body>
	<a href="/harbitech/listaCategorias">Ir para lista de categorias</a><br><br>
	
		<c:if test="${not empty category}">
			Categoria ${ category } cadastrada com sucesso!
		</c:if>
		
		<c:if test="${empty category}">
			Nenhuma categoria cadastrada!
		</c:if>

	</body>
</html>
