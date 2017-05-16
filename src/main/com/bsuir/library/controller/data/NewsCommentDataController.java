package main.com.bsuir.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.com.bsuir.library.bean.NewsComment;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.newsComment.implementation.NewsCommentDao;

public class NewsCommentDataController extends AbstractBeanController<NewsComment>  {

	public List<NewsComment> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		NewsCommentDao dbConnection = new NewsCommentDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<NewsComment> newsCommentList=new ArrayList<NewsComment>();
		try{
			newsCommentList=dbConnection.getDataFromDB("SELECT * FROM lib_news_comment",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);	
		}
		return newsCommentList;
		//request.setAttribute("newsCommentList", newsCommentList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	} public List<NewsComment> getLimitedData(String dbDataLanguage, String s, int dataLimit, int id, int page) throws DataControllerException {
		NewsCommentDao dbConnection = new NewsCommentDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<NewsComment> newsList = new ArrayList<NewsComment>();
		try {
			if(id==0)
			newsList = dbConnection.getDataFromDB("SELECT * FROM lib_news_comment ORDER BY lib_news_comment_date DESC LIMIT 0," + dataLimit, dbDataLanguage);
			else
				newsList = dbConnection.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_id="+id+" ORDER BY lib_news_comment_date DESC LIMIT 0," + dataLimit, dbDataLanguage);
		} catch (Exception e) {
			throw new DataControllerException(e);
		}
		return newsList;
	}
}
