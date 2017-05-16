<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=Genre&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Genre&createAction=GetAll&language=ru_RU">RU</a>
<h3>${genre }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${genre }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Genre&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <c:forEach items="${Genre}" var="genre">
        <input type="text" name="table" value="Genre" hidden readonly/>
        <input type="text" name="genreId" value="${genre.id}" readonly/>
        <input type="text" name="genreGenre" value="${genre.genre}"/>
        <input type="checkbox" name="checkGenre" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Genre&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Genre" hidden readonly/>
    <input type="text" name="genreGenre" placeholder="Жанр"/>
    <input type="text" name="genreGenreEn" placeholder="Жанр англ"/>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
