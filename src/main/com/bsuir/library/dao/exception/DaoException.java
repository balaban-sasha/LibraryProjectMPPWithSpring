package main.com.bsuir.library.dao.exception;

/**
 * Created by Саша on 28.03.2017.
 */
public class DaoException extends Exception{
    public DaoException(Exception e){
        super(e);
    }
}