package main.com.bsuir.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.com.bsuir.library.bean.Bean;
import main.com.bsuir.library.bean.Book;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.book.implementation.BookDao;

public class BookDataController extends AbstractBeanController<Book> {
	@Override
	public List<Book> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		BookDao dbConnection = new BookDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Book> bookList= new ArrayList<Book>();
		try {
			bookList=dbConnection.getDataFromDB("SELECT * FROM lib_book",dbDataLanguage);
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return bookList;
		//request.setAttribute("bookList", bookList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}

	public List<Book> getDataById(Integer bookId) throws DataControllerException {
		BookDao dbConnection = new BookDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<Book> bookList = new ArrayList<Book>();
		try {
			bookList = dbConnection.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=" + bookId, "");
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return bookList;
	}
	public List<Book> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
		BookDao dbConnection = new BookDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<Book> bookList = new ArrayList<Book>();
		int startNum=(page-1)*dataLimit;
		int endNum=page*dataLimit;
		try {
			bookList = dbConnection.getDataFromDB("SELECT * FROM lib_book ORDER BY lib_book_date DESC LIMIT "+startNum+"," + endNum, dbDataLanguage);
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return bookList;
	}

	public List<Book> getBookByAuthorId(int id) throws DataControllerException {
		BookDao dbConnection = new BookDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<Book> bookList = new ArrayList<Book>();
		try {
			bookList = dbConnection.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_author_id=" + id, "");
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return bookList;
	}
}
