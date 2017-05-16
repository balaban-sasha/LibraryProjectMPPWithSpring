package main.com.bsuir.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import main.com.bsuir.library.bean.NewsPage;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.newsPage.implementation.NewsPageDao;

public class NewsPageDataController  extends AbstractBeanController<NewsPage> {

	public List<NewsPage> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		NewsPageDao dbConnection = new NewsPageDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<NewsPage> newsPageList=new ArrayList<NewsPage>();
		try{
			newsPageList=dbConnection.getDataFromDB("SELECT * FROM lib_news_page",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);	
		}
		return newsPageList;
		//request.setAttribute("newsPageList", newsPageList);

	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
}
