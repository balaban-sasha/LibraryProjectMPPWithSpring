import main.com.bsuir.library.bean.BookRaiting;
import main.com.bsuir.library.dao.implementation.bookRaiting.implementation.BookRaitingDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 29.04.2017.
 */
public class BookRaitingDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        BookRaitingDao bookRaitingDao = new BookRaitingDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookRaitingDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookRaiting> bookRaitingList = bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id=1","");
        Assert.assertEquals(1, bookRaitingList.size());

    }

    @Test
    public void insertToTable() throws Exception {
        BookRaitingDao bookRaitingDao = new BookRaitingDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookRaitingDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] bookRaitingId=new String[1];
        bookRaitingId[0]="1";
        String[] bookRaitingCount = new String[1];
        bookRaitingCount[0]="5";
        String[] bookRaitingCountNew = new String[1];
        bookRaitingCountNew[0]="7";
        String[] bookRaitingBookId = new String[1];
        bookRaitingBookId[0]="1";
        String[] bookRaitingUserId = new String[1];
        bookRaitingUserId[0]="1";


        parameterMap.put("bookRaitingId",bookRaitingId);
        parameterMap.put("bookRaitingCount",bookRaitingCountNew);
        parameterMap.put("bookRaitingBookId",bookRaitingBookId);
        parameterMap.put("bookRaitingUserId",bookRaitingUserId);

        bookRaitingDao.insert(parameterMap);

        List<BookRaiting> bookRaitingList = bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id=1"+bookRaitingId[0]+"1","");
        Assert.assertEquals(1,bookRaitingList.size());
    }

    @Test
    public void updateTable() throws Exception {
        BookRaitingDao bookRaitingDao = new BookRaitingDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookRaitingDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookRaiting> bookRaitingList = bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id=1","");
        int bookRaitingNew;
        bookRaitingNew = bookRaitingList.get(0).getBookId();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] bookRaitingId=new String[1];
        bookRaitingId[0]="1";
        String[] bookRaitingCount = new String[1];
        bookRaitingCount[0]="5";;
        String[] bookRaitingBookId = new String[1];
        bookRaitingBookId[0]="1";
        String[] bookRaitingUserId = new String[1];
        bookRaitingUserId[0]="1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("bookRaitingId",bookRaitingId);
        parameterMap.put("bookRaitingCount",bookRaitingCount);
        parameterMap.put("bookRaitingBookId",bookRaitingBookId);
        parameterMap.put("bookRaitingUserId",rowThatNeedToUpdate);
        parameterMap.put("checkBookRaiting",rowThatNeedToUpdate);

        bookRaitingDao.updateAll(parameterMap,"");
        bookRaitingList=bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id=1","");
        Assert.assertEquals(bookRaitingNew,bookRaitingList.get(0).getBookId());
    }

    @Test
    public void deleteFromTable() throws Exception {
        BookRaitingDao bookRaitingDao = new BookRaitingDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookRaitingDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookRaiting> bookRaitingList = bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id=1","");

        String idThatNeedToDelete="5";
        Assert.assertEquals(1,bookRaitingList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("bookRaitingId",id);
        parametrMap.put("checkBookRaiting",checkId);

        bookRaitingDao.deleteAll(parametrMap);
        bookRaitingList=bookRaitingDao.getDataFromDB("SELECT * FROM lib_book_raiting WHERE lib_book_raiting_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,bookRaitingList.size());
    }

}