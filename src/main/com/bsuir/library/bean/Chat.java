package main.com.bsuir.library.bean;

import main.com.bsuir.library.controller.data.UserDataController;
import main.com.bsuir.library.controller.exception.DataControllerException;

import java.sql.Timestamp;

public class Chat {

    private int id;
    private String text;
    private Timestamp publicateDate;
    private int senderId;
    private String userName = "guest";
    private String userAvatar = "/img/nobody-avatar.png";
    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPublicateDate(Timestamp publicateDate) {
        this.publicateDate = publicateDate;
    }

    public void setSenderId(int senderId) throws DataControllerException {
        this.senderId = senderId;
        UserDataController userDataController = new UserDataController();
        this.userName = userDataController.getUserLogin(this.senderId);
        String userAvatar=userDataController.getUserAvatar(this.senderId);
        if(userAvatar!=null)
            this.userAvatar=userAvatar;

    }

    public int getId() {
        return this.id;

    }

    public String getText() {
        return this.text;
    }

    public Timestamp getPublicateDate() {
        return this.publicateDate;
    }

    public int getSenderId() {
        return this.senderId;
    }

    public String getUserName() {
        return this.userName;
    }
}
