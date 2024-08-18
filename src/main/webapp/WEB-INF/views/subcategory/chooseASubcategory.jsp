<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Escolha a categoria</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9fbfd;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            width: 96%;
            margin: 50px auto;
            padding: 30px;
            border: 5px solid hsla(0, 0%, 100%, .3);
        }

        h1 {
            font-size: 2em;
            margin-bottom: 30px;
            font-weight: 700;
        }

        .category-select {
            display: block;
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            appearance: none;
            background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 4 5'%3E%3Cpath fill='%23343a40' d='M2 0L0 2h4zm0 5L0 3h4z'/%3E%3C/svg%3E");
            background-repeat: no-repeat;
            background-position: right 10px center;
            background-size: 8px 10px;
        }

        .help-link {
            display: block;
            margin-top: 10px;
            font-size: 0.9em;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Escolha a categoria que você quer aprender</h1>

    <select class="category-select">
        <option>Selecione uma categoria</option>
        <c:forEach items="${categories}" var="category">
            <option class="category-select" value="${category.codeUrl}">${category.name}</option>
        </c:forEach>
    </select>

    <a href="/differences-between-categories" class="help-link" target="_blank">Entenda quais as diferenças entre as categorias</a>
</div>
<script>
    document.querySelector('.category-select').addEventListener('change', function () {
        console.log(this.value)
        window.location.href = '/' + this.value + '/self-assessment';
    });
</script>
</body>
</html>
