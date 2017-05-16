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
    <div class="content-container">

    </div>
    <div class="pagination-display">
        <ul class="pagination">
        </ul>
    </div>
    <jsp:include page="template/footer.jsp"/>
</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="template/jsfiles.jsp"/>
<script src="js/news.js"></script>
</body>
</html>
