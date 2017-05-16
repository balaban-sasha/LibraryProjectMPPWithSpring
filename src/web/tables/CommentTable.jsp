<%@ page import="main.com.bsuir.library.controller.data.BookDataController" %>
<%@ page import="main.com.bsuir.library.bean.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.bsuir.library.controller.data.UserDataController" %>
<%@ page import="main.com.bsuir.library.bean.User" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=Comment&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Comment&createAction=GetAll&language=ru_RU">RU</a>
<h3>${comment }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${sender_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Comment&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% BookDataController bookDataController = new BookDataController();
        List<Book> bookList = bookDataController.setTableContent("");
        UserDataController userDataController = new UserDataController();
        List<User> userList = userDataController.setTableContent("");
    %>
    <c:forEach items="${Comment}" var="comment">
        <input type="text" name="table" value="Comment" hidden readonly/>
        <input type="text" name="commentId" value="${comment.id}" readonly/>
        <input type="text" name="commentText" value="${comment.text}"/>
        <input type="text" name="commentPublicateDate" value="${comment.publicateDate}"/>
        <input type="text" name="commentBookIdOld"readonly value="${comment.bookId}"/>
        <select  name="commentBookId">
            <% for(Book book:bookList)
            {
                out.println("<option>"+book.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="commentSenderIdOld" readonly value="${comment.senderId}"/>
        <select  name="comentSenderId">
            <% for(User user:userList)
            {
                out.println("<option>"+user.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkComment" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Comment&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Comment" hidden readonly/>
    <input type="text" name="commentText" placeholder="Текст комментария"/>
    <input type="text" name="commentTextEn" placeholder="Текст комментария англ"/>
    <select  name="commentBookId">
        <% for(Book book:bookList)
        {
            out.println("<option>"+book.getId()+"</option>");
        }
        %>
    </select>
    <select  name="comentSenderId">
        <% for(User user:userList)
        {
            out.println("<option>"+user.getId()+"</option>");
        }
        %>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
