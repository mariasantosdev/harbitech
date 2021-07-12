<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, br.com.harbitech.school.category.Category"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Harbitech</title>
</head>
<body>

<tr>
    <th scope="col">Nome</th>
    <th scope="col">Código da url</th>
    <th scope="col">Descrição</th>
    <th scope="col">Ícone</th>
    <th scope="col">Cor</th>
    <%
        List<Category> categories = (List<Category>)request.getAttribute("categories");
        for (Category c: categories) {
    %>
    <th><%= c.getName() %><th/>
    <th><%= c.getCodeUrl() %><th/>
    <th><%= c.getDescription() %><th/>
    <th><%= c.getIconPath() %><th/>
    <th><%= c.getHtmlHexColorCode() %><th/>
    <br>
    <br>
    <%
        }
    %>

</tr>
</body>
</html>