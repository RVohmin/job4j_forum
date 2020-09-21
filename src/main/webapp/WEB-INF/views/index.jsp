<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
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
    <div class="container mt-3">
        <div class="row">
            <h4>Форум job4j</h4>
        </div>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col" class="col-md-4">Тема</th>
                    <th scope="col" class="col-md-4">Дата создания</th>
                    <th scope="col" class="col-md-4">Автор</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${topics}" var="topic">
                    <tr>
                        <td class="col-md-4">
                            <a href="${pageContext.request.contextPath}/post?id=${topic.id}">${topic.name}</a>
                        </td>
                        <td class="col-md-4">
                            <fmt:formatDate value="${topic.created.time}" type="date"
                                            dateStyle="long"/>
                        </td class="col-md-4">
                        <td>
                            <c:out value="${username}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <form action="<c:url value="/topic"/>" method="get">
                <input class="btn btn-info" type="submit" value="Создать тему"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>