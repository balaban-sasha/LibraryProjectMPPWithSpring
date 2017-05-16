<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 09.05.2017
  Time: 9:48
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
    <h1 class="main_content" style="text-align:center;">${section[0].bookName }</h1>
    <section>
        <article>
            <p>  ${section[0].bookDescription }
            <p>
            <font style="display: block;">Дата публикации: ${section[0].bookDate}</font>
            <a style="display: block;" href="${section[0].bookTextLink}">Перейти на книгу</a>
                <a href="author?authorId=${section[0].authorId}">Автор </a></p>
            <div class="input-group">
                <span class="input-group-addon">Текст комментария</span>
                <input id="books-id" hidden readonly value="${section[0].id}"/>
                <input id="message" type="text" class="form-control" placeholder="Сообщение"> <span
                    class="input-group-btn">
                  <button class="btn btn-default" type="button" id="add_new_comment">Отправить</button>
                 </span>
            </div>
        </article>
    </section>
    <hr color="black" style=" height: 3px;">
    <div class="books-comment-container"></div>
    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
<script src="js/book.js"></script>
</body>
</html>
