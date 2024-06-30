<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo de cursos</title>
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

        .login__form label {
            display: block;
            text-align: left;
            font-size: 1.1em;
        }

        .login__form input {
            border: 1px solid #eee;
            border-radius: 3px;
            margin-bottom: 1em;
            padding: 1em;
            width: 100%;
        }

        .categories {
            margin-top: 1.5em;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .category-card {
            border: 5px solid hsla(0, 0%, 100%, .3);
            margin-right: 5px;
            margin-left: 5px;
            margin-bottom: 1em;
            width: 45%;
        }

        .category-card__link {
            color: inherit;
            text-decoration: none;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
            width: 100%;
            padding: 1em;
        }

        .category-card__icon {
            opacity: .6;
            width: 100%;
            max-width: 128px;
        }

        .category-card__icon img {
            max-width: 128px;
        }

        .category-card__title {
            font-size: 1em;
            font-weight: 700;
            width: 100%;
            margin-top: .5em;
        }

        .category-card__details {
            font-size: .95em;
            opacity: .6;
            margin-top: .25em;
        }

        @media (min-width: 1024px) {
            .category-card {
                max-width: 180px;
            }
        }

        @media (min-width: 540px) {
            .category-card {
                width: 30%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <ul class="categories">
        <c:forEach items="${categories}" var="category">
            <li class="category-card">
                <a href="/${category.codeUrl}" class="category-card__link">
                                <span class="category-card__icon">
                                    <img src="${category.getIconPath()}">
                                </span>
                    <h3 class="category-card__title">${category.getName()}</h3>
                    <c:forEach begin="0" end="2" items="${category.getSubCategories()}" var="subcategory">
                        <p class="category-card__details">
                                ${subcategory.getName()}
                        </p>
                    </c:forEach>
                </a>
                <c:if test="${category.getSubCategories().size() > 3}">
                    e mais....
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>
</body>