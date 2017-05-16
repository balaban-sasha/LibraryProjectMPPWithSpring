<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=Section&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Section&createAction=GetAll&language=ru_RU">RU</a>
<h3>${section }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${name }"/>
<input type="text" readonly value="${header_of_smth }"/>
<input type="text" readonly value="${description }"/>
<input type="text" readonly value="${number }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Section&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <c:forEach items="${Section}" var="section">
        <input type="text" name="table" value="Section" hidden readonly/>
        <input type="text" name="sectionId" value="${section.id}" readonly/>
        <input type="text" name="sectionName" value="${section.name}"/>
        <input type="text" name="sectionHeader" value="${section.header}"/>
        <input type="text" name="sectionDescription" value="${section.description}"/>
        <input type="number" name="sectionNumber" value="${section.number}"/>
        <input type="checkbox" name="checkSection" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Section&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Section" hidden readonly/>
    <input type="text" name="sectionName" placeholder="Название раздела"/>
    <input type="text" name="sectionHeader" placeholder="Заголовок раздела"/>
    <input type="text" name="sectionDescription" placeholder="Описание раздела"/>
    <input type="text" name="sectionNameEn" placeholder="Название раздела англ"/>
    <input type="text" name="sectionHeaderEn" placeholder="Заголовок раздела англ"/>
    <input type="text" name="sectionDescriptionEn" placeholder="Описание раздела англ"/>
    <input type="number" name="sectionNumber" placeholder="Порядковый номер раздела"/>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
