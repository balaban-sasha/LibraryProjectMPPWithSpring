<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=News&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=News&createAction=GetAll&language=ru_RU">RU</a>
<h3>${news }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${header_of_smth }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=News&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <c:forEach items="${News}" var="news">
        <input type="text" name="table" value="News" hidden readonly/>
        <input type="text" name="newsId" value="${news.id}" readonly/>
        <input type="text" name="newsHeader" value="${news.header}"/>
        <input type="text" name="newsText" value="${news.text}"/>
        <input type="text" name="newsPublicateDate"readonly value="${news.publicateDate}"/>
        <input type="checkbox" name="checkNews" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=News&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="News" hidden readonly/>
    <input type="text" name="newsHeader" placeholder="Заголовок новости"/>
    <input type="text" name="newsHeaderEn" placeholder="Заголовок новости англ"/>
    <input type="text" name="newsText" placeholder="Текст новости"/>
    <input type="text" name="newsTextEn" placeholder="Текст новости англ"/>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
