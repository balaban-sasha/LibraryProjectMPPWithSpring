package main.com.bsuir.library.bean;

import main.com.bsuir.library.controller.data.UserDataController;
import main.com.bsuir.library.controller.exception.DataControllerException;

import java.sql.Timestamp;

public class NewsComment {
	private int id;
	private String text;
	private String userName = "guest";
	private Timestamp publicateDate;
	private int newsId;
	private int userId;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setText(String text)
	{
		this.text=text;
	}
	public void setPublicateDate(Timestamp publicateDate)
	{
		this.publicateDate=publicateDate;
	}
	public void setNewsId(int newsId)
	{
		this.newsId=newsId;
	}
	public void setUserId(int userId) throws DataControllerException {
		this.userId=userId;
		UserDataController userDataController = new UserDataController();
		this.userName = userDataController.getUserLogin(this.userId);
	}
	
	public int getId()
	{
		return this.id;
	}
	public String getText()
	{
		return this.text;
	}
	public Timestamp getPublicateDate()
	{
		return this.publicateDate;
	}
	public int getNewsId()
	{
		return this.newsId;
	}
	public int getUserId()
	{
		return this.userId;
	}
}
