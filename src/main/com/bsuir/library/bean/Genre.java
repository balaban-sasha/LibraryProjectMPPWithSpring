package main.com.bsuir.library.bean;

public class Genre {
	private int id;
	private String genre;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setGenre(String genre)
	{
		this.genre=genre;
	}
	public int getId()
	{
		return this.id;
	}
	public String getGenre()
	{
		return this.genre;
	}
}
