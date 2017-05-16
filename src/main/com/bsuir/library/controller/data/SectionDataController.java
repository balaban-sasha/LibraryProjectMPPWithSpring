package main.com.bsuir.library.controller.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import main.com.bsuir.library.bean.Section;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.section.implementation.SectionDao;

public class SectionDataController  extends AbstractBeanController<Section> {

	public List<Section> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		SectionDao dbConnection = new SectionDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Section> sectionList=new ArrayList<Section>();
		try{
			sectionList=dbConnection.getDataFromDB("SELECT * FROM lib_section",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return sectionList;
		//request.setAttribute("sectionList", sectionList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
	@Override
	public List<Section> getOneRowData(String dbDataLanguage, String s) throws DataControllerException {
		SectionDao dbConnection = new SectionDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Section> sectionList=new ArrayList<Section>();
		try{
			sectionList=dbConnection.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_name='"+s+"'",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return sectionList;
	}
}
