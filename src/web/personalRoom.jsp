<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 10.05.2017
  Time: 1:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Электронная библиотека</title>
</head>
<body>
<jsp:include page="template/header.jsp"/>
<jsp:include page="template/navigation.jsp"/>


    <div class="container" style="width: 100%;"><h2 style="text-align: center;" class="main_content">Добро пожаловать ${section[0].login}</h2><div class="row">
        <div class="col-xs-6 col-md-3">
            <a href="#" class="thumbnail">
                <img src="http://testtttttt.saoworld.ru/img/avatar/nobody-avatar.png" data-src="holder.js/100%x180" alt="аватар">
            </a>
        </div>
    </div>
        <form enctype="multipart/form-data" action="personalroom?action=updateUserAvatar&table=User" method="POST">
            <input type="hidden" name="MAX_FILE_SIZE" value="3000000">
            <input type="file" name="userFile">
            <input type="submit" name="send_file" value="Изменить">
        </form><br>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Имя</span>
            <input id="user_name" type="text" value="" class="form-control" placeholder="Имя" data-myvalue="Sashas"> <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="change_name">изменить</button>
      </span>
        </div>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Возраст</span>
            <input id="user_age" type="text" value="0" class="form-control" placeholder="Возраст" data-myvalue="Sashas"> <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="change_age">изменить</button>
      </span>
        </div>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Адрес</span>
            <input id="user_address" type="text" value="" class="form-control" placeholder="Адрес" data-myvalue="Sashas"> <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="change_address">изменить</button>
      </span>
        </div>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Пол</span><select id="select_sex" data-myvalue="Sashas" class="form-control">
            <option></option>
            <option>мужской</option>
            <option>женский</option></select>
            <span class="input-group-btn"><button class="btn btn-default" type="button" id="change_sex">изменить</button></span>
        </div>
        <div id="errors" style="max-width:350px;color:red;max-width:350px;font-size:17px;"></div>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Старый пароль</span>
            <input id="old_password" type="text" class="form-control" placeholder="Старый пароль"> <span class="input-group-btn">
      </span>
        </div>
        <div class="input-group" style="max-width:350px">
            <span class="input-group-addon">Новый пароль</span>
            <input id="new_password" type="text" class="form-control" placeholder="Новый пароль"> <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="change_password">изменить</button>
      </span>
        </div>
    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->



<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
</body>
</html>
