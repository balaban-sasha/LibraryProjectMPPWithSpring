package main.com.bsuir.library.bean;

public class BookCatalog {
	private int id;
	private int bookStatus;
	private int sectionId;
	private int bookId;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setBookStatus(int bookStatus)
	{
		this.bookStatus=bookStatus;
	}
	public void setSectionId(int sectionId)
	{
		this.sectionId=sectionId;
	}
	public void  setBookId(int bookId)
	{
		this.bookId=bookId;
	}
	public int getId()
	{
		return this.id;
	}
	public int getBookStatus()
	{
		return this.bookStatus;
	}
	public int getSectionId()
	{
		return this.sectionId;
	}
	public int getBookId()
	{
		return this.bookId;
	}
}
