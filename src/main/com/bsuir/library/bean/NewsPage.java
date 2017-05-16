package main.com.bsuir.library.bean;

public class NewsPage {

	private int id;
	private int newsStatus;
	private int newsId;
	private int sectionId;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setNewsStatus(int newsStatus)
	{
		this.newsStatus=newsStatus;
	}
	public void setNewsId(int newsId)
	{
		this.newsId=newsId;
	}
	public void setSectionId(int sectionId)
	{
		this.sectionId=sectionId;
	}
	public int getId()
	{
		return this.id;
	}
	public int getNewsStatus()
	{
		return this.newsStatus;
	}
	public int getNewsId()
	{
		return this.newsId;
	}
	public int getSectionId()
	{
		return this.sectionId;
	}
}
