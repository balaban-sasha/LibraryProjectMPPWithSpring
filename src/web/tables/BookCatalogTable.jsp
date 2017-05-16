<%@ page import="main.com.bsuir.library.controller.data.NewsDataController" %>
<%@ page import="main.com.bsuir.library.bean.News" %>
<%@ page import="java.util.List" %>
<%@ page import="main.com.bsuir.library.controller.data.SectionDataController" %>
<%@ page import="main.com.bsuir.library.bean.Section" %>
<%@ page import="main.com.bsuir.library.controller.data.BookDataController" %>
<%@ page import="main.com.bsuir.library.bean.Book" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="tables?tableName=BookCatalog&createAction=GetAll&language=en_US">EN</a>
<a href="tables?tableName=BookCatalog&createAction=GetAll&language=ru_RU">RU</a>
<h3>${book_catalog }</h3>
<input type="text" readonly value="${id }"/>
<input type="text" readonly value="${book_status }"/>
<input type="text" readonly value="${book_id }"/>
<input type="text" readonly value="${section_id }"/>
<%--<c:if test="${!empty authorList}">--%>

<form accept-charset="UTF-8" action="tables?tableName=BookCatalog&createAction=GetAll&language=${table_language}" method="POST" name="table">
    <% int index = 1;%>
    <% BookDataController bookDataController = new BookDataController();
        List<Book> bookList = bookDataController.setTableContent("");
        SectionDataController sectionDataController = new SectionDataController();
        List<Section> sections = sectionDataController.setTableContent("");
    %>
    <c:forEach items="${BookCatalog}" var="bookCatalog">
        <input type="text" name="table" value="BookCatalog" hidden readonly/>
        <input type="text" name="bookCatalogId" value="${bookCatalog.id}" readonly/>
        <input type="number" name="bookCatalogStatus" value="${bookCatalog.bookStatus}"/>
        <input type="text" name="bookCatalogBookIdOld" value="${bookCatalog.bookId}" style="width:120px;"/>
        <select  name="bookCatalogBookId">
            <% for(Book book:bookList)
            {
                out.println("<option>"+book.getId()+"</option>");
            }
            %>
        </select>
        <input type="text" name="bookCatalogSectionIdOld" value="${bookCatalog.sectionId}" style="width:120px;"/>
        <select  name="bookCatalogSectionId">
            <% for(Section section:sections)
            {
                out.println("<option>"+section.getId()+"</option>");
            }
            %>
        </select>
        <input type="checkbox" name="checkBookCatalog" value="<% out.print(index); %>"/><br>
        <% index += 1;%>
    </c:forEach>
    <input type="submit" value="delete" name="action"/>
    <input type="submit" value="update" name="action"/>
</form>
<form action="tables?tableName=BookCatalog&createAction=GetAll&language=${table_language}" method="post" name="table" accept-charset="UTF-8">
    <input type="text" name="table" value="BookCatalog" hidden readonly/>

    <input type="number" name="bookCatalogStatus" placeholder="Статус каталога"/>
    <select  name="bookCatalogBookId">
        <% for(Book book:bookList)
        {
            out.println("<option>"+book.getId()+"</option>");
        }
        %>
    </select>
    <select  name="bookCatalogSectionId">
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
