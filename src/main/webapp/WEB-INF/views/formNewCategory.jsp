<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cadastro de categoria</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
        </div>
    </div>
</nav>
<form:form class="form-horizontal" modelAttribute="category" method="post">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h1 class="panel-title">Nova categoria</h1>
        </div>

        <div class="panel-body">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Nome</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="name" name="name"
                                placeholder="Digite o nome da categoria" path="name"/>
                    <td><form:errors path="name" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="codeUrl" class="col-sm-2 control-label">Código</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="codeUrl" name="codeUrl"
                                placeholder="por exemplo: desenvolvimento, mobile (não use letras maiúsculas, acentos ou caracteres especiais)"
                                path="codeUrl"/>
                    <td><form:errors path="codeUrl" cssClass="error"/>
                </div>
            </div>

            <div class="col-sm-offset-2 col-sm-10">
                <div class="form-check">
                    <input class="status" type="checkbox" id="status" name="status" <c:if test="${category.status=='ACTIVE'}">checked=checked</c:if> class="switch-input"/>
                    <label class="form-check-label" for="status">
                        Categoria ativa?
                    </label>
                    <h6>Mostra ou deixa de mostrar a categoria na listagem dos alunos, de formações etc</h6>
                </div>
            </div>
            <div class="form-group">
                <label for="orderVisualization" class="col-sm-2 control-label">Ordem da categoria</label>
                <div class="col-sm-7">
                    <form:input type="number" class="form-control" id="orderVisualization" name="orderVisualization"
                           placeholder="por exemplo: categorias de ordem 1 aparece antes da categoria ordem 2" path="orderVisualization"/>
                    <td><form:errors path="orderVisualization" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="studyGuide">Guias de estudo</label>
                <div class="col-sm-7">
                <form:textarea class="form-control" id="studyGuide" name="studyGuide" rows="9"
                          placeholder="Um texto apontando para formações para ajudar pessoas perdidas" path="studyGuide"></form:textarea>
                    <td><form:errors path="studyGuide" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <label for="iconPath" class="col-sm-2 control-label">Caminho do ícone</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="iconPath" name="iconPath"
                                placeholder="por exemplo: /images/categorias/programacao.svg" path="iconPath"/>
                    <td><form:errors path="iconPath" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <label for="htmlHexColorCode" class="col-sm-2 control-label">Cor</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="htmlHexColorCode" name="htmlHexColorCode"
                           placeholder="por exemplo: #fcc14a" path="htmlHexColorCode"/>
                    <td><form:errors path="htmlHexColorCode" cssClass="error"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Descrição</label>
                <div class="col-sm-7">
                    <form:input type="text" class="form-control" id="description" name="description"
                           placeholder="por exemplo: IOS, Android, PhoneGap e mais..." path="description"/>
                    <td><form:errors path="htmlHexColorCode" cssClass="error"/>
                </div>
            </div>
            <form:input type="hidden" name="id" path="id"/>
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