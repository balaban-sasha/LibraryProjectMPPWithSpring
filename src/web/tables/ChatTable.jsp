<%@ page import="main.com.bsuir.library.controller.data.UserDataController" %>
<%@ page import="main.com.bsuir.library.bean.User" %>
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
<a href="tables?tableName=Chat&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Chat&createAction=GetAll&language=ru_RU">RU</a>
<h3>${chat }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${sender_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Chat&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% UserDataController userDataController = new UserDataController();
        List<User> userList = userDataController.setTableContent("");
    %>
    <c:forEach items="${Chat}" var="chat">
        <input type="text" name="table" value="Chat" hidden readonly/>
        <input type="text" name="chatId" value="${chat.id}" readonly/>
        <input type="text" name="chatText" value="${chat.text}"/>
        <input type="text" name="chatPublicateDate" value="${chat.publicateDate}"/>
        <input type="text" name="chatSenderIdOld" value="${chat.senderId}" style="width: 120px"/>
        <select  name="chatSenderId">
            <% for(User user:userList)
            {
                out.println("<option>"+user.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkChat" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Chat&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="Chat" hidden readonly/>
    <input type="text" name="chatText" placeholder="Текст"/>
    <input type="text" name="chatTextEn" placeholder="Текст англ"/>
    <select  name="chatSenderId">
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
