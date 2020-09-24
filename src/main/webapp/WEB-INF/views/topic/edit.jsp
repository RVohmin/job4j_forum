<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: romanvohmin
  Date: 20.09.2020
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700,900&display=swap&subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file='../../../css/base.css' %>
        <%@include file='../../../css/main.css' %>
    </style>
    <title>forum</title>
</head>
<body>
<div class="alert alert-success" role="alert">
    <a href="<c:url value='/logout'/>">Пользователь :
        ${username} | Выйти</a>
    <a href="<c:url value="/"/>"> На главную </a>
</div>
<div class="container">
    <h2>Создание новой темы</h2>
    <form action="<c:url value="/topic/update"/>" class="form-inline" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group mx-sm-3 mb-2">
        <label for="newTheme" class="sr-only">Название новой темы</label>
        <input type="text" class="form-control" name="name" id="newTheme"
               placeholder="Тема" value="${topic.name}">
    </div>
        <%--    <div class="form-group col-md-5">--%>
        <%--        <label for="textarea">Описание</label>--%>
        <%--        <textarea class="form-control" name="describe" id="textarea"--%>
        <%--                  rows="2"></textarea>--%>
        <%--    </div>--%>
    <input type="hidden" name="id" value="${topic.id}">
    <button type="submit" class="btn btn-primary mb-2">Создать</button>
    </form>
</div>
</body>
</html>