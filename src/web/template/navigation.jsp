<%@ page import="main.com.bsuir.library.controller.SessionController" %><%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 29.03.2017
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Электронная библиотека</a>
        </div>
        <div class="collapse navbar-collapse navbar-content">
            <ul class="nav navbar-nav">
                <li><a href="library">Главная</a></li>
                <li><a href="news">Новости</a></li>
                <li><a href="author">Авторы</a></li>
                <li><a href="books">Книги</a></li>
                <li><a href="chat">Чат</a></li>
                <% SessionController sessionController= (SessionController) session.getAttribute("session");
                    String templateName="";
                    if(sessionController.getUserId()!=0) {
                    templateName = "personalRoom.jsp";
                } else {
                    templateName = "authAndRegistry.jsp";
                }
                %>
                 <jsp:include page="<%=templateName %>"/>
                ${temp }
            </ul>
        </div><!-- /.nav-collapse -->
    </div><!-- /.container -->
</div><!-- /.navbar -->
<div id="myModal" class="modal fade in" style="display: none; padding-right: 16px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                <h4 class="modal-title">Регистрация</h4>
            </div>
            <div class="modal-body"> <div class="registration_fields">
                <div id="errors"></div>
                <div class="r_user_login"><font style="margin-left:10px">Логин:<font style="color:red">*</font></font><input type="text" placeholder="Ваш логин" id="r_user_login"></div>
                <div class="r_user_name"><font style="margin-left:10px">Имя:<font style="color:red">*</font></font><input type="text" placeholder="Ваше Имя" id="r_user_name"></div>
                <div class="r_user_surname"><font style="margin-left:10px">Фамилия:<font style="color:red">*</font></font><input type="text" placeholder="Ваше Имя" id="r_user_surname"></div>
                <div class="r_user_age"><font style="margin-left:10px">Возраст:<font style="color:red">*</font></font><input type="number" placeholder="Возраст" id="r_user_age"></div>
                <select class="span1" id="r_user_gender">
                    <option value="1">Мужской</option>
                    <option value="1">Женский</option>
                </select>
                <div class="r_user_password"><font style="margin-left:10px">Пароль:<font style="color:red">*</font></font><input type="password" id="r_user_password" placeholder="Пароль"></div>
                <div class="r_user_password_try"><font style="margin-left:10px">Повторите пароль:<font style="color:red">*</font></font><input type="password" id="r_user_password_try" placeholder="Повторите пароль"></div>
                <div class="admin_authorisation_button"><input type="button" class="btn btn-success" id="user_registry_button" value="Зарегестрироваться"></div>
            </div>
                <div class="modal-footer"><button class="btn btn-default" type="button" data-dismiss="modal">Закрыть</button></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
