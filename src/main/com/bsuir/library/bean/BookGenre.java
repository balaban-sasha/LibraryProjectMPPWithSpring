package main.com.bsuir.library.bean;

import main.com.bsuir.library.controller.data.GenreDataController;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.implementation.genre.implementation.GenreDao;

public class BookGenre {
    private int id;
    private int bookId;
    private int genreId;
    private String genreName = "";

    public void setId(int id) {
        this.id = id;

    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setGenreId(int genreId) throws DataControllerException {
        this.genreId = genreId;
        GenreDataController genreDataController = new GenreDataController();
        genreName=genreDataController.getNameById(this.genreId);
    }

    public int getId() {
        return this.id;
    }
public String getBookGenreName(){return this.genreName;}
    public int getBookId() {
        return this.bookId;
    }

    public int getGenreId() {
        return this.genreId;
    }
}
