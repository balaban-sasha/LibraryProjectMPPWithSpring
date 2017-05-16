package main.com.bsuir.library.bean;

public class BookRaiting {
	private int id;
	private int raitingCount;
	private int bookId;
	private int userId;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setRaitingCount(int raitingCount)
	{
		this.raitingCount=raitingCount;
	}
	public void setBookId(int bookId)
	{
		this.bookId=bookId;
	}
	public void setUserId(int userId)
	{
		this.userId=userId;
	}
	public int getId()
	{
		return this.id;
	}
	public int getRaitingCount()
	{
		return this.raitingCount;
	}
	public int getBookId()
	{
		return this.bookId;
	}
	public int getUserId()
	{
		return this.userId;
	}

}
