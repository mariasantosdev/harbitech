<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Harbitech</title>
</head>
<body>
<center><h1>Lista de categorias</h1> <br/></center>
<a href="/harbitech/formNewCategory.jsp">Cadastrar uma nova categoria</a><br><br>
<table border=1 frame=void rules=rows>

<tr align =center>
    <th scope="row">Nome</th>
    <th scope="row">Código da url</th>
    <th scope="row">Descrição</th>
    <th scope="row">Ícone</th>
    <th scope="row">Cor</th>
    <th scope="row">Status</th>
</tr>
    <tr>
        <c:forEach items="${categories}" var="category">
    <tr>
    <td scope="row">${category.name} <td/>
    <td scope="row">${category.codeUrl}<td/>
    <td scope="row">${category.description}<td/>
    <td scope="row">${category.iconPath}<td/>
    <td scope="row">${category.htmlHexColorCode}<td/>
    <td scope="row">${category.status}<td/>
    <a href="/harbitech/editaCategoria?id=${category.id }">edita</a><br><br>
    <a href="/harbitech/inativaCategoria?codeUrl=${category.codeUrl }">Tornar categoria inativa</a>
    </tr>
        </c:forEach>
    </td>

    <br>
    <br>

</tr>
</table>
</body>
</html>