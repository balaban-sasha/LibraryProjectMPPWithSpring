package main.com.bsuir.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import main.com.bsuir.library.bean.Comment;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.comment.implementation.CommentDao;

public class CommentDataController  extends AbstractBeanController<Comment> {

	public List<Comment> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		CommentDao dbConnection = new CommentDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Comment> commentList=new ArrayList<Comment>();
		try{
			commentList=dbConnection.getDataFromDB("SELECT * FROM lib_comment",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);	
		}
		return commentList;
		//request.setAttribute("commentList", commentList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	} public List<Comment> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
		CommentDao dbConnection = new CommentDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<Comment> commentList = new ArrayList<Comment>();
		try {
			if(id==0)
				commentList = dbConnection.getDataFromDB("SELECT * FROM lib_comment ORDER BY lib_comment_date DESC LIMIT 0," + dataLimit, dbDataLanguage);
			else
				commentList = dbConnection.getDataFromDB("SELECT * FROM lib_comment WHERE lib_book_id="+id+" ORDER BY lib_comment_date DESC LIMIT 0," + dataLimit, dbDataLanguage);
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return commentList;
	}
}
