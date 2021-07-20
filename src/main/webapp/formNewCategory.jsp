<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/novaCategoria" var="linkServletNovaCategoria"/>

<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Harbitech</title>
</head>
<body>

<form action="${linkServletNovaCategoria }" method="post">

  Nome: <input type="text" name="name"  /><br><br>
  Código url: <input type="text" name="codeUrl"  /><br><br>
  Descrição: <input type="text" name="description"  /><br><br>
  Guia de estudos: <input type="text" name="studyGuide"  /><br><br>
  Status: <select  name="status">
    <option>ACTIVE</option>
    <option>INACTIVE</option>
  </select><br><br>
  Ordem de visualização: <input type="number" name="orderVisualization" /><br><br>
  Caminho do icone: <input type="text" name="iconPath" /><br><br>
  Cor em hexa-decimal: <input type="text" name="htmlHexColorCode" /><br><br>

  <input type="submit" name ="Criar"/>
</form>

</body>
</html>