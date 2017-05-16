<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=User&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=User&createAction=GetAll&language=ru_RU">RU</a>
<h3>${user }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${login }"/>
<input type="text" readonly value="${password }"/>
<input type="text" readonly value="${name }"/>
<input type="text" readonly value="${female }"/>
<input type="text" readonly value="${gender }"/>
<input type="text" readonly value="${age }"/>
<input type="text" readonly value="${user_status }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=User&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <c:forEach items="${User}" var="user">
        <input type="text" name="table" value="User" hidden readonly/>
        <input type="text" name="userId" value="${user.id}" readonly/>
        <input type="text" name="userLogin" value="${user.login}"/>
        <input type="text" name="userPassword" value="${user.password}"/>
        <input type="text" name="userName" value="${user.name}"/>
        <input type="text" name="userSurname" value="${user.female}"/>
        <input type="text" name="userGenderOld" value="${user.gender}"readonly style="width: 120px"/>
        <select name="userGender">
            <option>1</option>
            <option>2</option>
        </select>
        <input type="number" name="userAge" value="${user.age}"/>
        <input type="text" name="userStatusOld" value="${user.status}" readonly style="width: 120px"/>
        <select name="userStatus">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>0</option>
        </select>
        <input type="checkbox" name="checkUser" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=User&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="User" hidden readonly/>
    <input type="text" name="userLogin" placeholder="Логин"/>
    <input type="text" name="userPassword" placeholder="Пароль"/>
    <input type="text" name="userName" placeholder="Имя"/>
    <input type="text" name="userSurname" placeholder="Фамилия"/>
    <select name="userGender">
        <option>1</option>
        <option>2</option>
    </select>
    <input type="number" name="userAge" placeholder="Возраст"/>
    <select name="userStatus">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>0</option>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
