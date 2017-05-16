<%@ page import="main.com.bsuir.library.controller.data.BookDataController" %>
<%@ page import="main.com.bsuir.library.bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.bsuir.library.controller.data.UserDataController" %>
<%@ page import="main.com.bsuir.library.bean.User" %>
<%@ page import="main.com.bsuir.library.bean.Genre" %>
<%@ page import="main.com.bsuir.library.controller.data.GenreDataController" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=BookGenre&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=BookGenre&createAction=GetAll&language=ru_RU">RU</a>
<h3>${book_raiting }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${genre_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=BookGenre&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% BookDataController bookDataController = new BookDataController();
        List<Book> bookList = bookDataController.setTableContent("");
        GenreDataController genreDataController = new GenreDataController();
        List<Genre> genreList = genreDataController.setTableContent("");
    %>
    <c:forEach items="${BookGenre}" var="bookGenre">
        <input type="text" name="table" value="BookGenre" hidden readonly/>
        <input type="text" name="bookGenreId" value="${bookGenre.id}" readonly/>
        <input type="text" name="bookGenreBookIdOld"readonly value="${bookGenre.bookId}" style="width:85px;"/>
        <select  name="bookGenreBookId">
            <% for(Book book:bookList)
            {
                out.println("<option>"+book.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="bookGenreGenreIdOld" readonly value="${bookGenre.genreId}"style="width:85px;"/>
        <select  name="bookGenreGenreId">
            <% for(Genre genre:genreList)
            {
                out.println("<option>"+genre.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkBookGenre" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=BookGenre&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="BookGenre" hidden readonly/>

    <select  name="bookGenreBookId">
        <% for(Book book:bookList)
        {
            out.println("<option>"+book.getId()+"</option>");
        }
        %>
    </select>
    <select  name="bookGenreGenreId">
        <% for(Genre genre:genreList)
        {
            out.println("<option>"+genre.getId()+"</option>");
        }
        %>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>