import main.com.bsuir.library.bean.Book;
import main.com.bsuir.library.dao.implementation.book.implementation.BookDao;
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
public class BookDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        BookDao bookDao = new BookDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Book> bookList = bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=1","");
        Assert.assertEquals(1, bookList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        BookDao bookDao = new BookDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] bookId=new String[1];
        bookId[0]="1";
        String[] bookName = new String[1];
        bookName[0]="История ";
        String[] bookDate = new String[1];
        bookDate[0]="2017-02-11 18:19:24";
        String[] bookDescription = new String[1];
        bookDescription[0]="история. мб учебник какой..";
        String[] bookDescriptionNew = new String[1];
        bookDescriptionNew[0]="NN";
        String[] bookTextLink = new String[1];
        bookTextLink[0]="ссылка на русскую часть";
        String[] bookNameEn = new String[1];
        bookNameEn[0]="History";
        String[] bookDescriptionEn = new String[1];
        bookDescriptionEn[0]="it can be book for student";
        String[] bookTextLinkEn = new String[1];
        bookTextLinkEn[0]="link on english part";
        String[] bookAuthorId = new String[1];
        bookAuthorId[0]="1";


        parameterMap.put("bookId",bookId);
        parameterMap.put("bookName",bookName);
        parameterMap.put("bookDate",bookDate);
        parameterMap.put("bookDescription",bookDescriptionNew);
        parameterMap.put("bookTextLink",bookTextLink);
        parameterMap.put("bookNameEn",bookNameEn);
        parameterMap.put("bookDescriptionEn",bookDescriptionEn);
        parameterMap.put("bookTextLinkEn",bookTextLinkEn);
        parameterMap.put("bookAuthorId",bookAuthorId);

        bookDao.insert(parameterMap);

        List<Book> bookList = bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=1"+bookId[0]+"1","");
        Assert.assertEquals(1,bookList.size());
    }

    @Test
    public void updateTable() throws Exception {
        BookDao bookDao = new BookDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Book> bookList = bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=1","");
        String bookNew;
        bookNew = bookList.get(0).getBookDescription();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] bookId=new String[1];
        bookId[0]="1";
        String[] bookName = new String[1];
        bookName[0]="История ";
        String[] bookDate = new String[1];
        bookDate[0]="2017-02-11 18:19:24";
        String[] bookDescription = new String[1];
        bookDescription[0]="история. мб учебник какой..";
        String[] bookTextLink = new String[1];
        bookTextLink[0]="ссылка на русскую часть";
        String[] bookNameEn = new String[1];
        bookNameEn[0]="History";
        String[] bookDescriptionEn = new String[1];
        bookDescriptionEn[0]="it can be book for student";
        String[] bookTextLinkEn = new String[1];
        bookTextLinkEn[0]="link on english part";
        String[] bookAuthorId = new String[1];
        bookAuthorId[0]="1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("bookId",bookId);
        parameterMap.put("bookName",bookName);
        parameterMap.put("bookDate",bookDate);
        parameterMap.put("bookDescription",bookDescription);
        parameterMap.put("bookTextLink",bookTextLink);
        parameterMap.put("bookNameEn",bookNameEn);
        parameterMap.put("bookDescriptionEn",bookDescriptionEn);
        parameterMap.put("bookTextLinkEn",bookTextLinkEn);
        parameterMap.put("bookAuthorId",rowThatNeedToUpdate);
        parameterMap.put("checkBook",rowThatNeedToUpdate);

        bookDao.updateAll(parameterMap,"");
        bookList=bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=1","");
        Assert.assertEquals(bookNew,bookList.get(0).getBookDescription());
    }

    @Test
    public void deleteFromTable() throws Exception {
        BookDao bookDao = new BookDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Book> bookList = bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id=1","");

        String idThatNeedToDelete="2";
        Assert.assertEquals(1,bookList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("bookId",id);
        parametrMap.put("checkBook",checkId);

        bookDao.deleteAll(parametrMap);
        bookList=bookDao.getDataFromDB("SELECT * FROM lib_book WHERE lib_book_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,bookList.size());
    }

}