<%@ page import="main.com.bsuir.library.controller.data.UserDataController" %>
<%@ page import="main.com.bsuir.library.bean.User" %>
<%@ page import="main.com.bsuir.library.bean.News" %>
<%@ page import="main.com.bsuir.library.controller.data.NewsDataController" %>
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
<a href="tables?tableName=NewsComment&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=NewsComment&createAction=GetAll&language=ru_RU">RU</a>
<h3>${news_comment }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${news_id }"/>
<input type="text" readonly value="${user_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=NewsComment&createAction=GetAll&language=${table_language}"
      method="POST" name="table">
    <% int index = 1;%>
    <% NewsDataController newsDataController = new NewsDataController();
        List<News> newsList = newsDataController.setTableContent("");
        UserDataController userDataController = new UserDataController();
        List<User> userList = userDataController.setTableContent("");
    %>
    <c:forEach items="${NewsComment}" var="newsComment">
    <input type="text" name="table" value="NewsComment" hidden readonly/>
    <input type="text" name="newsCommentId" value="${newsComment.id}" readonly/>
    <input type="text" name="newsCommentText" value="${newsComment.text}"/>
    <input type="text" readonly name="newsCommentPublicateDate" value="${newsComment.publicateDate}"/>
    <input type="text" readonly name="newsCommentNewsIdOld" value="${newsComment.newsId}" style="width: 85px"/>
    <select name="newsCommentNewsId">
        <% for (News news : newsList) {
            out.println("<option>" + news.getId() + "</option>");
        }
        %>
    </select>
    <input type="text" readonly name="newsCommentUserIdOld" value="${newsComment.userId}"style="width: 85px"/>
    <select name="newsCommentUserId">
                <% for(User user:userList)
            {
                out.println("<option>"+user.getId()+"</option>");
            }
            %>
        <input type="checkbox" name="checkNewsComment" value="<% out.print(index); %>"/><br>
                <% index += 1;%>
        </c:forEach>
        <input type="submit" value="delete" name="action"/>
        <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=NewsComment&createAction=GetAll&language=${table_language}" method="post" name="table"
      accept-charset="UTF-8">
    <input type="text" name="table" value="NewsComment" hidden readonly/>
    <input type="text" name="newsCommentText" placeholder="Текст комментария"/>
    <input type="text" name="newsCommentTextEn" placeholder="Текст комментария англ"/>
    <select name="newsCommentNewsId">
        <% for (News news : newsList) {
            out.println("<option>" + news.getId() + "</option>");
        }
        %>
    </select>
    <select name="newsCommentUserId">
            <% for(User user:userList)
            {
                out.println("<option>"+user.getId()+"</option>");
            }
            %>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
