package main.com.bsuir.library.bean;

import java.util.List;

/**
 * Created by Саша on 28.03.2017.
 */
public class AuthorCatalog {
    private int id;
    private int authorStatus;
    private int sectionId;
    private int authorId;
    public void setId(int id)
    {
        this.id=id;
    }
    public void setAuthorStatus(int authorStatus)
    {
        this.authorStatus=authorStatus;
    }
    public void setSectionId(int sectionId)
    {
        this.sectionId=sectionId;
    }
    public void setAuthorId(int authorId)
    {
        this.authorId=authorId;
    }
    public int getId()
    {
        return this.id;
    }
    public int getAuthorStatus()
    {
        return this.authorStatus;
    }
    public int getSectionId()
    {
        return this.sectionId;
    }
    public int getAuthorId()
    {
        return this.authorId;
    }
}
