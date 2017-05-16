package main.com.bsuir.library.bean;

import java.sql.Timestamp;

public class News {

	private int id;
	private String header;
	private String text;
	private Timestamp publicateDate;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setHeader(String header)
	{
		this.header=header;
	}
	public void setText(String text)
	{
		this.text=text;
	}
	public void setPublicateDate(Timestamp publicateDate)
	{
		this.publicateDate=publicateDate;
	}
	public int getId()
	{
		return this.id;
	}
	public String getHeader()
	{
		return this.header;
	}
	public String getText()
	{
		return this.text;
	}
	public Timestamp getPublicateDate()
	{
		return this.publicateDate;
	}
}
