package main.com.bsuir.library.bean;

public class Section {

	private int id;
	private String name;
	private String header;
	private String description;
	private int number;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setHeader(String header)
	{
		this.header=header;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
	public void setNumber(int number)
	{
		this.number=number;
	}
	public int getId()
	{
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
	public String getDescription()
	{
		return this.description;
	}
	public int getNumber()
	{
		return this.number;
	}
	public String getHeader()
	{
		return this.header;
	}
}
