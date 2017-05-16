<%@ page import="main.com.bsuir.library.controller.data.AuthorDataController" %>
<%@ page import="main.com.bsuir.library.bean.Author" %>
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
<a href="tables?tableName=AuthorCatalog&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=AuthorCatalog&createAction=GetAll&language=ru_RU">RU</a>
<h3>${author_catalog }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${author_status }"/>
<input type="text" readonly value="${author_id }"/>
<input type="text" readonly value="${section_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=AuthorCatalog&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% AuthorDataController authorDataController = new AuthorDataController();
        List<Author> authors = authorDataController.setTableContent("");
        SectionDataController sectionDataController = new SectionDataController();
        List<Section> sections = sectionDataController.setTableContent("");
    %>
    <c:forEach items="${AuthorCatalog}" var="authorCatalog">
        <input type="text" name="table" value="AuthorCatalog" hidden readonly/>
        <input type="text" name="authorCatalogId" value="${authorCatalog.id}" readonly/>
        <input type="number" name="authorCatalogStatus" value="${authorCatalog.authorStatus}"/>
        <input type="text" name="authorCatalogAuthorIdOld" value="${authorCatalog.authorId}" readonly style="width:115px;"/>
        <select  name="authorCatalogAuthorId">
            <% for(Author author:authors)
            {
                out.println("<option>"+author.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="authorCatalogSectionIdOld" value="${authorCatalog.sectionId}" readonly style="width:115px;"/>
        <select  name="authorCatalogSectionId">
            <% for(Section section:sections)
            {
                out.println("<option>"+section.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkAuthorCatalog" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=AuthorCatalog&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="AuthorCatalog" hidden readonly/>
    <input type="number" name="authorCatalogStatus" placeholder="Статус автора в секции"/>
    <select  name="authorCatalogAuthorId">
        <% for(Author author:authors)
        {
            out.println("<option>"+author.getId()+"</option>");
        }
        %>
    </select>
    <select  name="authorCatalogSectionId">
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
