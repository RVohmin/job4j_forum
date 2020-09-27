<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
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
        <%@include file='../../css/base.css' %>
        <%@include file='../../css/main.css' %>
    </style>


    <title>Форум job4j</title>
</head>
<body>
<div class="container">
    <div class="alert alert-success" role="alert">
        <a href="<c:url value='/logout'/>">Пользователь :
            ${username} | Выйти</a>
        <a href="<c:url value="/"/>"> На главную </a>
    </div>
    <h2>Тема: <c:out value="${topic.name}"/></h2>
    <table class="table">
        <thead>
        <tr>
            <th scope="col" class="col-md-4">заголовок поста</th>
            <th scope="col" class="col-md-4">текст поста</th>
            <th scope="col" class="col-md-4">автор</th>
            <th scope="col" class="col-md-4">дата</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${topic.posts}"
                   var="post">
            <tr>
                <td class="col-xs-3">
                    <a href='<c:url value="/posts/edit/${post.id}"/>'>
                        <i class="fa fa-edit mr-3"></i>
                    </a>
                    <c:out value="${post.name}"/>
                </td>
                <td class="col-md-3">
                    <c:out value="${post.description}"/>
                </td>
                <td class="col-xs-1">
                    <c:out value="${post.creator.username}"/>
                </td>
                <td class="col-md-1">
                    <fmt:formatDate value="${post.created.time}" type="date"
                                    dateStyle="short"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <form action="<c:url value="/posts/create/${topic.id}"/>" method="post">
        <div class="form-group col-md-3">
            <h5>Добавить сообщение</h5>
        </div>
        <input type="hidden" name="topicId" value="${topic.id}">
        <div class="form-group col-md-3">
            <label for="input">Заголовок поста</label>
            <input class="form-control" name="name" id="input">
        </div>
        <div class="form-group col-md-3">
            <label for="textarea">Текст поста</label>
            <textarea class="form-control" name="description" id="textarea"
                      rows="3"></textarea>
        </div>
<%--        <input type="hidden" name="topicId" value="${topic.id}">--%>
        <div class="form-group col-md-3">
            <input class="btn btn-info" type="submit" value="Добавить сообщение"/>
        </div>
    </form>
</div>
</body>
</html>
