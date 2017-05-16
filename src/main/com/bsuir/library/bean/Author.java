package main.com.bsuir.library.bean;

import main.com.bsuir.library.controller.data.BookDataController;
import main.com.bsuir.library.controller.exception.DataControllerException;

import java.util.List;

public class Author extends Bean {
    private int id;
    private String authorName;
    private String authorFemale;
    private String authorPatronymic;
    private String authorBiography;
    private List<Book> bookList = null;

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorFemale(String female) {
        this.authorFemale = female;
    }

    public String getAuthorFemale() {
        return authorFemale;
    }

    public void setAuthorPatronymic(String patronymic) {
        this.authorPatronymic = patronymic;
    }

    public String getAuthorPatronymic() {
        return authorPatronymic;
    }

    public void setAuthorBiography(String biography) {
        this.authorBiography = biography;
    }

    public String getAuthorBiography() {
        return authorBiography;
    }

    public void setId(int id) throws DataControllerException {
        this.id = id;
        BookDataController bookDataController = new BookDataController();
        bookList = bookDataController.getBookByAuthorId(this.id);
    }

    public int getId() {
        return id;
    }
    public List<Book> getBookList()
    {
        return this.bookList;
    }
}
