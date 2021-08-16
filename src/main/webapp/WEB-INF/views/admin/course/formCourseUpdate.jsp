<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1"/>
    <title>Cursos</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
        </div>
    </div>
</nav>

<form:form class="form-horizontal" modelAttribute="courseFormUpdate" method="post" action="${formAction}">
<div class="panel panel-default">
    <div class="panel-heading">
        <h1 class="panel-title">${formTitle}</h1>
    </div>
    <div class="panel-body">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">Nome</label>
            <div class="col-sm-7">
                <form:input type="text" class="form-control" id="name" name="name"
                            placeholder="Digite aqui o nome do curso" path="name"/>
                <form:errors path="name" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <label for="codeUrl" class="col-sm-2 control-label">Código</label>
            <div class="col-sm-7">
                <form:input type="text" class="form-control" id="codeUrl" name="codeUrl"
                            placeholder="Não use letras maiúsculas, acentos ou caracteres especiais."
                            path="codeUrl"/>
                <form:errors path="codeUrl" cssClass="error"/>
            </div>
        </div>
        <div class="form-check">
            <label for="flexRadioDefault1" class="col-sm-2 control-label">Visibilidade</label>
            <div class="col-sm-2">
                <input class="form-check-input" type="radio" name="visibility" id="flexRadioDefault1"
                       value="PUBLIC" ${courseFormUpdate.visibility== 'PUBLIC' ? "checked" : ""} />
                PÚBLICO
            </div>
            <div>
                <input class="form-check-input" type="radio" name="visibility" id="flexRadioDefault2"
                       value="PRIVATE" ${courseFormUpdate.visibility== 'PRIVATE' ? "checked" : ""} />
                PRIVADO
            </div>
            <div class="form-check">
            </div>
            <br>
            <div class="form-group">
                <label for="completionTimeInHours" class="col-sm-2 control-label">Tempo finalização</label>
                <div class="col-sm-7">
                    <form:input type="number" class="form-control" id="completionTimeInHours" name="completionTimeInHours"
                                placeholder="Um tempo estimado em horas(deverá estar entre 1 e 20 horas.)" path="completionTimeInHours"/>
                    <form:errors path="completionTimeInHours" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="description">Ementa</label>
                <div class="col-sm-7">
                    <form:textarea class="form-control" id="description" name="description" rows="9"
                                   placeholder="Uma descrição detalhada do que será abordado no curso" path="description"></form:textarea>
                    <form:errors path="description" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="targetAudience" class="col-sm-2 control-label">Público alvo</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="targetAudience" name="targetAudience"
                                placeholder="por exemplo: Pessoas desenvolvedoras, pessoas com foco na linguagem python." path="targetAudience"/>
                    <form:errors path="targetAudience" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="instructor" class="col-sm-2 control-label">Instrutor</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="instructor" name="instructor"
                                placeholder="Nome do(a) instrutor(a) que ministra o curso" path="instructor"/>
                    <form:errors path="instructor" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="developedSkills" class="col-sm-2 control-label">Habilidades desenvolvidas</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="developedSkills" name="developedSkills"
                                placeholder="Um texto sobre quais capacidades a pessoa que faz o curso terá exercitado"
                                path="developedSkills"/>
                    <form:errors path="developedSkills" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="subcategory" class="col-sm-2 control-label">Subcategoria</label>
                <div class="col-sm-7">
                    <form:select path="subcategory" class="form-control" id="subcategory">
                        <form:options items="${subcategories}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </div>
            </div>
            <form:input type="hidden" path="id"/>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Enviar</button>
                </div>
            </div>
        </div>
    </div>
    </form:form>
</body>
</html>