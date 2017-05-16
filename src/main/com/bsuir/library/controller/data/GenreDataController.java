package main.com.bsuir.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import main.com.bsuir.library.bean.BookRaiting;
import main.com.bsuir.library.bean.Genre;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.genre.implementation.GenreDao;

public class GenreDataController extends AbstractBeanController<Genre>  {

	public List<Genre> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		GenreDao dbConnection = new GenreDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Genre> genreList=new ArrayList<Genre>();
		try{
			genreList=dbConnection.getDataFromDB("SELECT * FROM lib_genre",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return genreList;
		//request.setAttribute("genreList", genreList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}

    public String getNameById(int genreId) throws DataControllerException {
		GenreDao dbConnection = new GenreDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
		List<Genre> genreList = new ArrayList<Genre>();
		try {
			genreList = dbConnection.getDataFromDB("SELECT * FROM lib_genre WHERE `lib_genre_id`=" + genreId, "");
		} catch (Exception e) {
			throw new DataControllerException(e);
		}

		if (genreList.isEmpty())
			return "";
		else
			return genreList.get(0).getGenre();
    }
}
