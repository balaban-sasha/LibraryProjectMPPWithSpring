package main.com.bsuir.library.controller;

import main.com.bsuir.library.controller.data.*;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Саша on 28.03.2017.
 */
public class ViewController {

    public void getView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DataControllerException, DaoException, SQLException {

        HttpSession session = request.getSession(true);
        LocaleController sessionLocale = (LocaleController)session.getAttribute("locale");
        String temp = request.getParameter("language");
        String language=null;
        String country=null;
        if(temp!=null)
        {
            String[] planguage = temp.split("_");
            language = planguage[0];
            country = planguage[1];
        }
        else
        {
            language="ru";
            country="RU";
        }
        if( (sessionLocale==null) || (!sessionLocale.getLanguage().equals(language)))
        {
            if(sessionLocale==null)
            {
                sessionLocale = new LocaleController();
                sessionLocale.changeLocale(language, country);
                session.setAttribute("locale", sessionLocale);
            }
            else
            {
                sessionLocale.changeLocale(language, country);
                session.setAttribute("locale", sessionLocale);
            }
        }
        sessionLocale.loadData(request);
        SessionController sessionController = new SessionController();
        NavigationController navigationController = new NavigationController();
        navigationController.setAuthFieldsCondition(request,sessionController);
        String dbDataLanguage="";
        if(!language.equals("ru"))
        {
            dbDataLanguage="_"+language;
        }
        if(request.getRequestURI().equals("/tables"))
        {
       /* AuthorDataCatalogController authorCatalogController = new AuthorDataCatalogController();
        authorCatalogController.setAuthorCatalogTableContent(request, dbDataLanguage);
		AuthorDataController authorController = new AuthorDataController();
		authorController.setAuthorTableContent(request, dbDataLanguage);
		BookCatalogDataController bookCatalogController = new BookCatalogDataController();
		bookCatalogController.setBookCatalogTableContent(request, dbDataLanguage);
		BookDataController bookController = new BookDataController();
		bookController.setBookTableContent(request, dbDataLanguage);
		BookGenreDataController bookGenreController = new BookGenreDataController();
		bookGenreController.setBookGenreTableContent(request, dbDataLanguage);
		BookRaitingDataController bookRaitingController = new BookRaitingDataController();
		bookRaitingController.setBookRaitingTableContent(request, dbDataLanguage);
		ChatDataController chatController = new ChatDataController();
		chatController.setChatTableContent(request, dbDataLanguage);
		CommentDataController commentController = new CommentDataController();
		commentController.setCommentTableContent(request, dbDataLanguage);
		GenreDataController genreController = new GenreDataController();
		genreController.setGenreTableContent(request, dbDataLanguage);
		MessageDataController messageController = new MessageDataController();
		messageController.setMessageTableContent(request, dbDataLanguage);
		NewsCommentDataController newsCommentController = new NewsCommentDataController();
		newsCommentController.setNewsCommentTableContent(request, dbDataLanguage);
		NewsDataController newsController = new NewsDataController();
		newsController.setNewsTableContent(request, dbDataLanguage);
		NewsPageDataController newsPageController = new NewsPageDataController();
		newsPageController.setNewsPageTableContent(request, dbDataLanguage);
		SectionDataController sectionController = new SectionDataController();
		sectionController.setSectionTableContent(request, dbDataLanguage);
		UserDataController userController = new UserDataController();
		userController.setUserTableContent(request, dbDataLanguage);*/
        request.getRequestDispatcher("tables.jsp").forward(request, response);}
        else
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

}