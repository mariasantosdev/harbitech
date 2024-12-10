<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Escolha seu modo de estudo</title>
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

        .button-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .btn {
            font-size: 1.2em;
            padding: 15px 30px;
            border: 2px solid #ccc;
            border-radius: 5px;
            background-color: #747c81;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: calc(50% - 10px);
            box-sizing: border-box;
        }

        @media (max-width: 768px) {
            .btn {
                width: 100%;
            }
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Escolha seu modo de estudo</h1>
    <div class="button-container">
        <button class="btn btn-see-all-courses" onclick="seeAllCourses()">Quero ver o catálogo completo de cursos
        </button>
        <button class="btn" onclick="platformHelpWithTheWay()">Quero uma jornada de aprendizado
            guiada pela plataforma
        </button>
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
