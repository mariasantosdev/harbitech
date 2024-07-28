<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Auto-avaliação</title>
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

        .subcategory {
            margin-bottom: 20px;
        }

        .subcategory input[type="radio"] {
            margin-right: 10px;
        }

        .subcategory label {
            font-size: 1.2em;
        }

        .subcategory ul {
            list-style-type: none;
            padding-left: 0;
        }

        .subcategory li {
            margin-bottom: 5px;
        }

        .btn {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Selecione por onde você quer começar</h1>
    <div class="select-container">
        <c:forEach items="${allActiveSubcategories}" var="subcategory">
            <div class="subcategory">
                <input type="radio" id="subcategory${subcategory.id}" name="subcategory" value="${subcategory.id}">
                <label for="subcategory${subcategory.id}">
                    Nível ${subcategory.level}: ${subcategory.name}
                    <c:if test="${subcategory.level == 0}">
                        * (caso você não saiba por onde começar comece por aqui)
                    </c:if>
                </label>
                <ul>
                    <h4>Cursos abordados no nível</h4>
                    <c:forEach items="${subcategory.courses}" var="course">
                        <li>${course.name}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </div>
    <button class="btn" onclick="handleButtonClick()">Seguir com a jornada</button>
</div>
</body>
<script>
    function handleButtonClick() {
        const selectedSubcategory = document.querySelector('input[name="subcategory"]:checked');
        if (selectedSubcategory) {
            const subcategoryId = selectedSubcategory.value;
            console.log('Subcategory ID selecionado:', subcategoryId);

            fetch('/user-self-assessment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({subcategoryId: parseInt(subcategoryId)})
            })
                .then(response => {
                    if (response.redirected) {
                        window.location.href = response.url;
                    } else {
                        return response.json();
                    }
                })
                .then(data => {
                    console.log('Success:', data);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        } else {
            alert('Selecione um nível para continuar');
        }
    }
</script>
</html>
