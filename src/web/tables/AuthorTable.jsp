<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=Author&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Author&createAction=GetAll&language=ru_RU">RU</a>
<h3>${author }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${author_name }"/>
<input type="text" readonly value="${author_female }"/>
<input type="text" readonly value="${author_patronymic }"/>
<input type="text" readonly value="${author_biography }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Author&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <c:forEach items="${Author}" var="author">
        <input type="text" name="table" value="Author" hidden readonly/>
        <input type="text" name="authorId" value="${author.id}" readonly/>
        <input type="text" name="authorName" value="${author.authorName}"/>
        <input type="text" name="authorSurname" value="${author.authorFemale}"/>
        <input type="text" name="authorPatronymic" value="${author.authorPatronymic}"/>
        <input type="text" name="authorBiography" value="${author.authorBiography}"/>
        <input type="checkbox" name="checkAuthor" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Author&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Author" hidden readonly/>
    <input type="text" name="authorName" placeholder="Имя автора"/>
    <input type="text" name="authorSurname" placeholder="Фамилия автора"/>
    <input type="text" name="authorPatronymic" placeholder="Отчество автора"/>
    <input type="text" name="authorBiography" placeholder="Биография автора"/>
    <input type="text" name="authorNameEn" placeholder="Имя автора англ"/>
    <input type="text" name="authorSurnameEn" placeholder="Фамилия автора англ"/>
    <input type="text" name="authorPatronymicEn" placeholder="Отчество автора англ"/>
    <input type="text" name="authorBiographyEn" placeholder="Биография автора англ"/>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
