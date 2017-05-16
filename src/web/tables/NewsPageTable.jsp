<%@ page import="main.com.bsuir.library.controller.data.NewsDataController" %>
<%@ page import="main.com.bsuir.library.bean.News" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.bsuir.library.controller.data.SectionDataController" %>
<%@ page import="main.com.bsuir.library.bean.Section" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=NewsPage&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=NewsPage&createAction=GetAll&language=ru_RU">RU</a>
<h3>${news_page }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${news_status }"/>
<input type="text" readonly value="${news_id }"/>
<input type="text" readonly value="${section_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=NewsPage&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% NewsDataController newsDataController = new NewsDataController();
        List<News> newsList = newsDataController.setTableContent("");
        SectionDataController sectionDataController = new SectionDataController();
        List<Section> sections = sectionDataController.setTableContent("");
    %>
    <c:forEach items="${NewsPage}" var="newsPage">
        <input type="text" name="table" value="NewsPage" hidden readonly/>
        <input type="text" name="newsPageId" value="${newsPage.id}" readonly/>
        <input type="number" name="newsPageStatus" value="${newsPage.newsStatus}"/>
        <input type="text" name="newsPageNewsIdOld" value="${newsPage.newsId}" style="width:120px;"/>
        <select  name="newsPageNewsId">
            <% for(News news:newsList)
            {
                out.println("<option>"+news.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="newsPageSectionIdOld" value="${newsPage.sectionId}" style="width:120px;"/>
        <select  name="newsPageSectionId">
            <% for(Section section:sections)
            {
                out.println("<option>"+section.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkNewsPage" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=NewsPage&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="NewsPage" hidden readonly/>
    <input type="number" name="newsPageStatus" value="${newsPage.newsStatus}"/>
    <select  name="newsPageNewsId">
        <% for(News news:newsList)
        {
            out.println("<option>"+news.getId()+"</option>");
        }
        %>
    </select>
    <select  name="newsPageSectionId">
        <% for(Section section:sections)
        {
            out.println("<option>"+section.getId()+"</option>");
        }
        %>
    </select>
    <input type="submit" name="action" value="insert"/>
</form>
<%--</c:if>--%>

</body>
</html>
