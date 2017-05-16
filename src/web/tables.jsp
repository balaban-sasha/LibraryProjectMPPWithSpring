<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="tables/main.css">
</head>
<body>
<div class="main">
    <div class="nav-bar">
        <ul class="navbar">
            <li><a href="tables?tableName=Book&createAction=GetAll&language=${table_language}">Книги</a></li>
            <li><a href="tables?tableName=Author&createAction=GetAll&language=${table_language}">Авторы</a></li>
            <li><a href="tables?tableName=AuthorCatalog&createAction=GetAll&language=${table_language}">Каталог
                авторов</a></li>
            <li><a href="tables?tableName=User&createAction=GetAll&language=${table_language}">Пользователи</a></li>
            <li><a href="tables?tableName=Section&createAction=GetAll&language=${table_language}">Разделы</a></li>
            <li><a href="tables?tableName=NewsPage&createAction=GetAll&language=${table_language}">Страницы новостей</a>
            </li>
            <li><a href="tables?tableName=News&createAction=GetAll&language=${table_language}">Новости</a></li>
            <li><a href="tables?tableName=Chat&createAction=GetAll&language=${table_language}">Чат</a></li>
            <li><a href="tables?tableName=NewsComment&createAction=GetAll&language=${table_language}">Комментарии
                новостей</a></li>
            <li><a href="tables?tableName=Message&createAction=GetAll&language=${table_language}">Сообщения</a></li>
            <li><a href="tables?tableName=Genre&createAction=GetAll&language=${table_language}">Жанр</a></li>
            <li><a href="tables?tableName=Comment&createAction=GetAll&language=${table_language}">Комментарии</a></li>
            <li><a href="tables?tableName=BookRaiting&createAction=GetAll&language=${table_language}">Рейтинг книг</a></li>
            <li><a href="tables?tableName=BookGenre&createAction=GetAll&language=${table_language}">Жанр книг</a></li>
            <li><a href="tables?tableName=BookCatalog&createAction=GetAll&language=${table_language}">Каталог книг</a></li>

        </ul>
    </div>
    <div class="content">
        <jsp:include page="tables/${TableName }.jsp"/>
    </div>
</div>
</body>
</html>