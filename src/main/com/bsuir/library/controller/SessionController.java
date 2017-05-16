package main.com.bsuir.library.controller;

import org.apache.commons.fileupload.FileItem;

import javax.persistence.Enumerated;
import java.util.List;

/**
 * Created by Саша on 02.04.2017.
 */
public class SessionController {
    private List<FileItem> fileItem;
    private String context;
    private int userId = 0;
    private String request = "";
    public void setUserId(int id)
    {
        this.userId=id;
    }
    public void setRequest(String request)
    {
        this.request=request;
    }
    public int getUserId()
    {
     return this.userId;
    }
    public String getRequest(){return this.request;}
    public boolean ifExistUserId()
    {
        if(this.userId!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setFileItem(List<FileItem> fileItem) {
        this.fileItem = fileItem;
    }

    public List<FileItem> getFileItem() {
        return fileItem;
    }
}
