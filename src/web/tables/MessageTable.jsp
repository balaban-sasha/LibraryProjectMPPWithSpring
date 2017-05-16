<%@ page import="main.com.bsuir.library.bean.User" %>
<%@ page import="main.com.bsuir.library.controller.data.UserDataController" %>
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
<a href="tables?tableName=Message&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=Message&createAction=GetAll&language=ru_RU">RU</a>
<h3>${message }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${text }"/>
<input type="text" readonly value="${publicate_date }"/>
<input type="text" readonly value="${recipient_status }"/>
<input type="text" readonly value="${sender_status }"/>
<input type="text" readonly value="${sender_id }"/>
<input type="text" readonly value="${recipient_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=Message&createAction=GetAll&language=${table_language}"
      method="POST" name="table">
    <% int index = 1;%>
    <% UserDataController userDataController = new UserDataController();
        List<User> userList = userDataController.setTableContent("");
    %>
    <c:forEach items="${Message}" var="message">
        <input type="text" name="table" value="Message" hidden readonly/>
        <input type="text" name="messageId" value="${message.id}" readonly/>
        <input type="text" name="messageText" value="${message.text}"/>
        <input type="text" readonly name="messagePublicateDate" value="${message.publicateDate}"/>
        <input type="text" name="messageRecipientStatusOld" readonly value="${message.recipientStatus}"
               style="width: 85px"/>
        <select name="messageRecipientStatus">
            <option>0</option>
            <option>1</option>
        </select>
        <input type="text" name="messageSenderStatusOld" readonly value="${message.senderStatus}" style="width: 85px"/>
        <select name="messageSenderStatus">
            <option>0</option>
            <option>1</option>
        </select>
        <input type="text" name="messageSenderIdOld" readonly value="${message.senderId}" style="width: 85px"/>
        <select name="messageSenderId">
            <% for (User user : userList) {
                out.println("<option>" + user.getId() + "</option>");
            }
            %>
        </select>
        <input type="text" name="messageRecipientIdOld" readonly value="${message.recipientId}" style="width: 85px"/>
        <select name="messageRecipientId">
            <% for (User user : userList) {
                out.println("<option>" + user.getId() + "</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkMessage" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=Message&createAction=GetAll&language=${table_language}" method="post" name="table"
      accept-charset="UTF-8">
    <input type="text" name="table" value="Message" hidden readonly/>
    <input type="text" name="messageText" placeholder="Текст сообщения"/>
    <select name="messageRecipientStatus">
        <option>0</option>
        <option>1</option>
    </select>
    <select name="messageSenderStatus">
        <option>0</option>
        <option>1</option>
    </select>
    <select name="messageSenderId">
        <% for (User user : userList) {
            out.println("<option>" + user.getId() + "</option>");
        }
        %>
    </select>
    <select name="messageRecipientId">
        <% for (User user : userList) {
            out.println("<option>" + user.getId() + "</option>");
        }
        %>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
