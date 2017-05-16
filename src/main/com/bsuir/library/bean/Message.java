package main.com.bsuir.library.bean;

import java.sql.Timestamp;

public class Message {
	private int id;
	private String text;
	private Timestamp publicateDate;
	private int recipientStatus;
	private int senderStatus;
	private int senderId;
	private int recipientId;
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
	public void setRecippientStatus(int recipientStatus)
	{
		this.recipientStatus=recipientStatus;
	}
	public void setSenderStatus(int recipientStatus)
	{
		this.senderStatus=senderStatus;
	}
	public void setSenderId(int senderId)
	{
		this.senderId=senderId;
	}
	public void setRecipientId(int recipientId)
	{
		this.recipientId=recipientId;
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
	public int getRecipientStatus()
	{
		return this.recipientStatus;
	}
	public int getSenderStatus()
	{
		return this.senderStatus;
	}
	public int getSenderId()
	{
		return this.senderId;
	}
	public int getRecipientId()
	{
		return this.recipientId;
	}
}
