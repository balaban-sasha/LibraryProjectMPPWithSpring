<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 04.05.2017
  Time: 11:51
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

<div class="container">

    <div class="row " style="padding-top:40px;">
        <h3 class="text-center">Чат		</h3>
        <br><br>
        <div class="col-md-8">
            <div class="panel panel-info">
                <div class="panel-heading">
                    Сообщения
                </div>
                <div class="panel-body">
                    <ul class="media-list" style="max-height: 631px;overflow-y: auto;" id="message_box">


                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input type="text" id="chat_message" class="form-control" placeholder="Введите сообщение">
                        <span class="input-group-btn">
                                        <button class="btn btn-info" id="send_chat_message" type="button">Отправить</button>
                                    </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Пользователи онлайн
                </div>
                <div class="panel-body">
                    <ul class="media-list" id="online_users_box">


                    </ul>
                </div>
            </div>

        </div>
    </div>

    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->



<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
</body>
</html>
