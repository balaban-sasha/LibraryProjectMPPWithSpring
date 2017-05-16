package main.com.bsuir.library.dao.factory;

import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.DaoController;
import main.com.bsuir.library.dao.implementation.author.implementation.AuthorDao;
import main.com.bsuir.library.dao.implementation.authorCatalog.implementation.AuthorCatalogDao;
import main.com.bsuir.library.dao.implementation.book.implementation.BookDao;
import main.com.bsuir.library.dao.implementation.bookCatalog.implementation.BookCatalogDao;
import main.com.bsuir.library.dao.implementation.bookGenre.implementation.BookGenreDao;
import main.com.bsuir.library.dao.implementation.bookRaiting.implementation.BookRaitingDao;
import main.com.bsuir.library.dao.implementation.chat.implementation.ChatDao;
import main.com.bsuir.library.dao.implementation.comment.implementation.CommentDao;
import main.com.bsuir.library.dao.implementation.genre.implementation.GenreDao;
import main.com.bsuir.library.dao.implementation.message.implementation.MessageDao;
import main.com.bsuir.library.dao.implementation.news.implementation.NewsDao;
import main.com.bsuir.library.dao.implementation.newsComment.implementation.NewsCommentDao;
import main.com.bsuir.library.dao.implementation.newsPage.implementation.NewsPageDao;
import main.com.bsuir.library.dao.implementation.section.implementation.SectionDao;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.logging.Handler;

/**
 * Created by Саша on 20.04.2017.
 */
public class DAOFactory {
    private HashMap<String,AbstractDaoController> daoList = new HashMap<String,AbstractDaoController>();
    public DAOFactory()
    {
        daoList.put("Author", new AuthorDao());
        daoList.put("AuthorCatalog",new AuthorCatalogDao());
        daoList.put("Book",new BookDao());
        daoList.put("BookCatalog",new BookCatalogDao());
        daoList.put("BookGenre",new BookGenreDao());
        daoList.put("BookRaiting",new BookRaitingDao());
        daoList.put("Chat",new ChatDao());
        daoList.put("Comment",new CommentDao());
        daoList.put("Genre",new GenreDao());
        daoList.put("Message",new MessageDao());
        daoList.put("News",new NewsDao());
        daoList.put("NewsPage",new NewsPageDao());
        daoList.put("NewsComment",new NewsCommentDao());
        daoList.put("Section",new SectionDao());
        daoList.put("User",new UserDao());
    }
    public AbstractDaoController getController(String tableName)
    {
        return daoList.get(tableName);
    }
}
