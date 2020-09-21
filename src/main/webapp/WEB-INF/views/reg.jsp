<%--
  Created by IntelliJ IDEA.
  User: romanvohmin
  Date: 12.09.2020
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%--    <style>--%>
    <%--        <%@include file='../../css/base.css' %>--%>
    <%--        <%@include file='../../css/main.css' %>--%>
    <%--    </style>--%>
    <title>forum</title>
</head>
<body>
<div class="container">
    <div class="wrapper__content">
        <form class="col-md-6" name='login' action="<c:url value='/reg'/>" method='POST'>
            <div class="card text-center">
                <div class="card-header" style="color: red; font-weight: bold">
                    ВНИМАНИЕ! <br/> Храните регистрационные данные в надежном месте!
                </div>
                <div class="card-body">
                    <h4 class="card-title">Регистрация нового пользователя </h4>
                    <div class="">
                        <input type="text" class="form-control" name='username' placeholder="Логин">
                    </div>
                    <br/>
                    <div class="">
                        <input type="password" class="form-control" name='password'
                               placeholder="Пароль">
                    </div>
                    <br/>
                    <input class="btn btn-success col-md-5" name="submit" type="submit"
                           value="Зарегистрироваться"/>
                    <a class="btn btn-primary col-md-5"
                       href="<c:url value='/login'/>">Авторизоваться</a>
                </div>
                <div class="card-footer text-muted">
                    или авторизуйтесь
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>

            </div>
        </form>
    </div>
</div>
</body>
</html>
