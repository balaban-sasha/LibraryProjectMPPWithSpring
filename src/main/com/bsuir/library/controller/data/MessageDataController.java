package main.com.bsuir.library.controller.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

import main.com.bsuir.library.bean.Message;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.message.implementation.MessageDao;

public class MessageDataController  extends AbstractBeanController<Message> {

	public List<Message> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException
	{
		MessageDao dbConnection = new MessageDao();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
		dbConnection.startConnectionToDB(resourceBundle.getString("url"),resourceBundle.getString("login"),resourceBundle.getString("password"));
		List<Message> messageList=new ArrayList<Message>();
		try{
			messageList=dbConnection.getDataFromDB("SELECT * FROM lib_message",dbDataLanguage);
		}catch(Exception e)
		{
			throw new DataControllerException(e);
		}
		return messageList;
		//request.setAttribute("messageList", messageList);
		
	}
	@Override
	public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
		return null;
	}
}
