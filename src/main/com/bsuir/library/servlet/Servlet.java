package main.com.bsuir.library.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.bean.Book;
import main.com.bsuir.library.bean.News;
import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.command.CommandInvoker;
import main.com.bsuir.library.command.CommandManager;
import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.config.guice.RouteConfig;
import main.com.bsuir.library.controller.LocaleController;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.ViewController;
import main.com.bsuir.library.controller.data.AuthorDataController;
import main.com.bsuir.library.controller.data.BookDataController;
import main.com.bsuir.library.controller.data.NewsDataController;
import main.com.bsuir.library.controller.data.UserDataController;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import netscape.javascript.JSObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by Саша on 28.03.2017.
 */
@WebServlet("/library")
public class Servlet extends javax.servlet.http.HttpServlet {


    static {
        try {
            System.out.print("11");
            Injector injector = Guice.createInjector(new RouteConfig());
            viewController = injector.getInstance(ViewController.class);
            //viewController = new ViewController();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final ViewController viewController;
    private CommandInvoker commandInvoker;
    private CommandManager commandManager;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            executeRequest(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, DataControllerException, DaoException, ServletException, IOException, CommandException, NoSuchAlgorithmException, FileUploadException {
        commandManager = new CommandManager();
        commandInvoker = new CommandInvoker(commandManager);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        LocaleController sessionLocale = (LocaleController) session.getAttribute("locale");
        String temp = request.getParameter("language");
        String language = null;
        String country = null;
        if (temp != null) {
            String[] planguage = temp.split("_");
            language = planguage[0];
            country = planguage[1];
        } else {
            language = "ru";
            country = "RU";
        }
        if ((sessionLocale == null) || (!sessionLocale.getLanguage().equals(language))) {
            if (sessionLocale == null) {
                sessionLocale = new LocaleController();
                sessionLocale.changeLocale(language, country);
                session.setAttribute("locale", sessionLocale);
            } else {
                sessionLocale.changeLocale(language, country);
                session.setAttribute("locale", sessionLocale);
            }
        }
        sessionLocale.loadData(request);
        SessionController sessionController = new SessionController();
        if (session.getAttribute("session") != null) {
            sessionController = (SessionController) session.getAttribute("session");
        }
        String url = request.getRequestURI();
        String action = request.getParameter("action");
        String dbDataLanguage = "";
        if (!language.equals("ru")) {
            dbDataLanguage = "_" + language;
        }
        Map<String, String[]> parameterMap = new HashMap<String, String[]>(request.getParameterMap());
        List<Entity> entityList = null;
        String servletResponse = "";
        if (url.equals("/tables")) {
            if (action != null) {
                entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                session.setAttribute("session", sessionController);
                PrintWriter out = response.getWriter();
                out.print(sessionController.getRequest());
            }
            String createAction = request.getParameter("createAction");
            if (createAction != null) {
                entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                if (entityList != null) {
                    session.setAttribute("session", sessionController);
                    response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                    String tableName = parameterMap.get("tableName")[0];
                    request.setAttribute(tableName, entityList);
                    request.setAttribute("TableName", tableName + "Table");
                    request.getRequestDispatcher("tables.jsp").forward(request, response);
                }
            }
        } else if (url.equals("/library")) {
            String createAction = request.getParameter("createAction");
            if (action != null) {
                entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                if (entityList == null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().println(sessionController.getRequest());
                    response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                } else {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    String json = gson.toJson(entityList);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().println(json);
                }
                response.getWriter().close();
            }
            if (createAction != null) {
                entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                request.setAttribute("section", entityList);
            }
            session.setAttribute("session", sessionController);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (url.equals("/chat")) {
            if (sessionController.getUserId() == 0)
                response.sendRedirect("http://localhost:8080/library");
            else {
                String createAction = request.getParameter("createAction");
                if (action != null) {
                    entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                }
                if (createAction != null) {
                    JSONObject jsObject = new JSONObject();
                    entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

                    String json = gson.toJson(entityList);

                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().println(json);
                    response.getWriter().close();
                }
                session.setAttribute("session", sessionController);
                request.getRequestDispatcher("chat.jsp").forward(request, response);
            }
        } else if (url.equals("/news")) {

            String createAction = request.getParameter("createAction");
            String newsId = request.getParameter("newsId");
            if (newsId != null) {
                NewsDataController newsDataController = new NewsDataController();
                List<News> newsList = newsDataController.getDataById(Integer.valueOf(newsId));
                if (newsList != null) {
                    request.setAttribute("section", newsList);
                    session.setAttribute("session", sessionController);
                    request.getRequestDispatcher("oneNews.jsp").forward(request, response);
                }
            } else {
                if (action != null) {
                    if ((sessionController.getUserId() != 0) && (action.equals("insert"))) {
                        String[] commentSenderId = new String[1];
                        commentSenderId[0] = String.valueOf(sessionController.getUserId());
                        parameterMap.put("newsCommentUserId", commentSenderId);
                        response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                        entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    } else if (!action.equals("insert"))
                        entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    if (entityList == null) {
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(sessionController.getRequest());
                        response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                    } else {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        String json = gson.toJson(entityList);
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(json);
                    }
                    response.getWriter().close();
                }
                if (createAction != null) {
                    entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                    request.setAttribute("section", entityList);
                }
                session.setAttribute("session", sessionController);
                request.getRequestDispatcher("news.jsp").forward(request, response);
            }
        } else if (url.equals("/books")) {

            String createAction = request.getParameter("createAction");
            String bookId = request.getParameter("bookId");
            if (bookId != null) {
                BookDataController bookDataController = new BookDataController();
                List<Book> bookList = bookDataController.getDataById(Integer.valueOf(bookId));
                if (bookList != null) {
                    request.setAttribute("section", bookList);
                    session.setAttribute("session", sessionController);
                    request.getRequestDispatcher("oneBook.jsp").forward(request, response);
                }
            } else {
                if (action != null) {
                    if ((sessionController.getUserId() != 0) && (action.equals("insert"))) {
                        String[] commentSenderId = new String[1];
                        commentSenderId[0] = String.valueOf(sessionController.getUserId());
                        parameterMap.put("comentSenderId", commentSenderId);
                        response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                        entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    } else if (!action.equals("insert"))
                        entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    if (entityList == null) {
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(sessionController.getRequest());
                        response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                    } else {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        String json = gson.toJson(entityList);
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(json);
                    }
                    response.getWriter().close();
                }
                if (createAction != null) {
                    entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                    request.setAttribute("section", entityList);
                }
                session.setAttribute("session", sessionController);
                request.getRequestDispatcher("book.jsp").forward(request, response);
            }
        } else if (url.equals("/author")) {

            String createAction = request.getParameter("createAction");
            String authorId = request.getParameter("authorId");
            if (authorId != null) {
                AuthorDataController authorDataController = new AuthorDataController();
                List<Author> authorList = authorDataController.getDataById(Integer.valueOf(authorId));
                if (authorList != null) {
                    request.setAttribute("section", authorList);
                    session.setAttribute("session", sessionController);
                    request.getRequestDispatcher("oneAuthor.jsp").forward(request, response);
                }
            } else {
                if (action != null) {
                    entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                    if (entityList == null) {
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(sessionController.getRequest());
                        response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                    } else {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        String json = gson.toJson(entityList);
                        response.setContentType("application/json;charset=utf-8");
                        response.getWriter().println(json);
                    }
                    response.getWriter().close();
                }
                if (createAction != null) {
                    entityList = commandInvoker.invoke(createAction, parameterMap, dbDataLanguage, sessionController);
                    request.setAttribute("section", entityList);
                }
                session.setAttribute("session", sessionController);
                request.getRequestDispatcher("author.jsp").forward(request, response);
            }
        } else if (url.equals("/personalroom")) {
            String createAction = request.getParameter("createAction");
            /*if ((action!=null)&&(action.equals("updateUserAvatar"))) {
                int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
                int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
                int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(MEMORY_THRESHOLD);
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_FILE_SIZE);
                upload.setSizeMax(MAX_REQUEST_SIZE);
                List<FileItem> formItems = upload.parseRequest(request);
                sessionController.setFileItem(formItems);
                sessionController.setContext(getServletContext().getRealPath("./"));
            }*/
            if (action != null) {
                entityList = commandInvoker.invoke(action, parameterMap, dbDataLanguage, sessionController);
                if (entityList == null) {
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().println(sessionController.getRequest());
                    response.setHeader(sessionController.getRequest(), sessionController.getRequest());
                } else {
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    String json = gson.toJson(entityList);
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().println(json);
                }
                response.getWriter().close();
            }
            if ((sessionController != null) && (sessionController.getUserId() != 0)) {
                UserDataController userDataController = new UserDataController();
                List<User> userList = userDataController.getContentByLastId("", (sessionController.getUserId()));
                request.setAttribute("section", userList);
                session.setAttribute("session", sessionController);
                request.getRequestDispatcher("personalRoom.jsp").forward(request, response);
            } else
                response.sendRedirect("http://localhost:8080/library");

        } else
            response.sendRedirect("http://localhost:8080/library");

    }
}