<%@tag language="java" pageEncoding="UTF-8" %>

<%@attribute name="title" required="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>${title}</title>
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
<jsp:doBody/>
</body>
</html>


