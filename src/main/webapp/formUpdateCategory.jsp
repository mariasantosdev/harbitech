<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/alteraCategoria" var="linkNewCategoryServlet"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Harbitech</title>
</head>
<body>

<form action="${linkNewCategoryServlet }" method="post">

  Nome: <input type="text" name="name" value="${category.name }" /><br><br>
  Código url: <input type="text" name="codeUrl" value="${category.codeUrl }"/><br><br>
  Descrição: <input type="text" name="description" value="${category.description }" /><br><br>
  Guia de estudos: <input type="text" name="studyGuide" value="${category.studyGuide }" /><br><br>
  Status: <select  name="status" value="${category.status }">
  <option>ACTIVE</option>
  <option>INACTIVE</option>
</select><br><br>
  Ordem de visualização: <input type="number" name="orderVisualization" value="${category.orderVisualization }"/><br><br>
  Caminho do icone: <input type="text" name="iconPath" value="${category.iconPath }"/><br><br>
  Cor em hexa-decimal: <input type="text" name="htmlHexColorCode" value="${category.htmlHexColorCode }"/><br><br>
  <input type="hidden" name="id" value="${category.id }">
  <input type="submit" name ="Atualizar"/>
</form>

</body>
</html>
