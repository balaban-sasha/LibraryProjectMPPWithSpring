<%@ page import="main.com.bsuir.library.controller.data.AuthorDataController" %>
<%@ page import="main.com.bsuir.library.bean.Author" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=Book&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Book&createAction=GetAll&language=ru_RU">RU</a>

<h3>${book }</h3>


<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_name }"/>
<input type="text" readonly value="${book_date }"/>
<input type="text" readonly value="${description }"/>
<input type="text" readonly value="${book_text_link }"/>
<input type="text" readonly value="${author_id }"/>
    <%--<c:if test="${!empty bookList}">--%>
<form accept-charset="UTF-8" action="tables?tableName=Book&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% AuthorDataController authorDataController = new AuthorDataController();
        List<Author> authors = authorDataController.setTableContent("");
    %>
    <c:forEach items="${Book}" var="book">
        <input type="text" name="table" value="Book" hidden readonly/>
        <input type="text" name="bookId" value="${book.id}" readonly/>
        <input type="text" name="bookName" value="${book.bookName}"/>
        <input type="text" name="bookDate" readonly value="${book.bookDate}"/>
        <input type="text" name="bookDescription" value="${book.bookDescription}"/>
        <input type="text" name="bookTextLink" value="${book.bookTextLink}"/>
        <input type="number" name="bookAuthorIdOld" value="${book.authorId}" readonly style="width:100px;"/>
        <select  name="bookAuthorId">
            <% for(Author author:authors)
                {
                    out.println("<option>"+author.getId()+"</option>");
                }
                %>
        </select>
        <input type="checkbox" name="checkBook" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Book&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Book" hidden readonly/>
    <input type="text" name="bookName" placeholder="Название книги"/>
    <input type="text" name="bookDescription" placeholder="Описание книги"/>
    <input type="text" name="bookTextLink" placeholder="Ссылка на книгу"/>
    <input type="text" name="bookNameEn" placeholder="Название книги англ"/>
    <input type="text" name="bookDescriptionEn" placeholder="Описание книги англ"/>
    <input type="text" name="bookTextLinkEn" placeholder="Ссылка на книгу англ"/>
    <select  name="bookAuthorId">
        <% for(Author author:authors)
        {
            out.println("<option>"+author.getId()+"</option>");
        }
        %>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>

    <%--</c:if>--%>
</body>
</html>
