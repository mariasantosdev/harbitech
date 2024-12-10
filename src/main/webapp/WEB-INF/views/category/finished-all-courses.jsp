<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category.name} | Cursos online de tecnologia</title>
    <style>
        *, :after, :before {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            background-color: #f9fbfd;
        }

        body, input, select, textarea {
            font: normal 14px sans-serif;
        }

        a {
            outline: none;
        }

        ul {
            list-style: none;
        }

        .container {
            margin: 0 auto;
            max-width: 1200px;
            width: 96%;
        }

        .category-banner__wrapper {
            background-color: #a1b0c0;
        }

        .category-banner {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: flex-start;
        }

        .category-banner__icon img {
            max-height: 32px;
        }

        .category-banner__title {
            margin-left: 10px;
            display: inline-block;
            font-size: 2.4em;
            padding: 1em 0;
            letter-spacing: -1.2px;
            font-weight: 900;
            text-transform: uppercase;
            color: #fff;
        }
    </style>
</head>
<body>
<section class="category-banner__wrapper">
    <div class="container category-banner">
            <span class="category-banner__icon">
                <img src="${category.iconPath}">
            </span>
        <h1 class="category-banner__title">${category.name}</h1>
    </div>
</section>
<main class="container">
    <div id="congratulations-message" style="margin-top: 20px; font-size: 16px;">
        Parabéns, sua jornada do herói dentro dessa categoria foi concluída com sucesso!
        Mas não se esqueça de que a jornada pelo conhecimento nunca termina. Para explorar outras categorias, <a href="/onboarding/study-mode">clique aqui</a>.
    </div>
</main>
</body>
</html>