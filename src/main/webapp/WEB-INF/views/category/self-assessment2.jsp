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
    </style>
</head>
<body>
<div class="container">
    <h1>Selecione qual seu nível</h1>
    <div class="select-container">
        <c:forEach items="${allActiveSubcategories}" var="subcategory">
            <div class="subcategory">
                <input type="radio" id="subcategory${subcategory.id}" name="subcategory" value="${subcategory.id}">
                <label for="subcategory${subcategory.id}">Nível ${subcategory.level}: ${subcategory.name}</label>
                <ul>
                    <h4>Cursos abordados no nível</h4>
                    <c:forEach items="${subcategory.courses}" var="course">
                        <li>${course.name}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function seeAllCourses() {
        fetch('/all-courses', {
            method: 'GET',
            headers: {
                'Content-Type': 'text/html'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.text();
            })
            .then(html => {
                console.log('Success:', html);
                document.write(html);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    function platformHelpWithTheWay() {
        fetch('/all-subcategories', {
            method: 'GET',
            headers: {
                'Content-Type': 'text/html'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.text();
            })
            .then(html => {
                console.log('Success:', html);
                document.write(html);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
</script>
</body>
</html>
