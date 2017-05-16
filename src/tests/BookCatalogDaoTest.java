import main.com.bsuir.library.bean.BookCatalog;
import main.com.bsuir.library.dao.implementation.bookCatalog.implementation.BookCatalogDao;
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
public class BookCatalogDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        BookCatalogDao bookCatalogDao = new BookCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookCatalog> bookCatalogList = bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id=1","");
        Assert.assertEquals(1, bookCatalogList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        BookCatalogDao bookCatalogDao = new BookCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] bookCatalogId=new String[1];
        bookCatalogId[0]="1";
        String[] bookCatalogStatus = new String[1];
        bookCatalogStatus[0]="1";
        String[] bookCatalogStatusNew = new String[1];
        bookCatalogStatusNew[0]="2";
        String[] bookCatalogBookId = new String[1];
        bookCatalogBookId[0]="1";
        String[] bookCatalogSectionId = new String[1];
        bookCatalogSectionId[0]="3";

        parameterMap.put("bookCatalogId",bookCatalogId);
        parameterMap.put("bookCatalogStatus",bookCatalogStatusNew);
        parameterMap.put("bookCatalogBookId",bookCatalogBookId);
        parameterMap.put("bookCatalogSectionId",bookCatalogSectionId);

        bookCatalogDao.insert(parameterMap);

        List<BookCatalog> bookCatalogList = bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id=1"+bookCatalogId[0]+"1","");
        Assert.assertEquals(1,bookCatalogList.size());
    }

    @Test
    public void updateTable() throws Exception {
        BookCatalogDao bookCatalogDao = new BookCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookCatalog> bookCatalogList = bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id=1","");
        int GG;
        GG = bookCatalogList.get(0).getBookId();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] genreId=new String[1];
        genreId[0]="1";
        String[] genreName = new String[1];
        genreName[0]="Классика";
        String[] genreNameEn = new String[1];
        genreNameEn[0]="Classic";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("genreId",genreId);
        parameterMap.put("genreName",genreName);;
        parameterMap.put("genreNameEn",rowThatNeedToUpdate);
        parameterMap.put("checkNews",rowThatNeedToUpdate);

        bookCatalogDao.updateAll(parameterMap,"");
        bookCatalogList=bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id=1","");
        Assert.assertEquals(GG,bookCatalogList.get(0).getBookId());
    }

    @Test
    public void deleteFromTable() throws Exception {
        BookCatalogDao bookCatalogDao = new BookCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        bookCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<BookCatalog> bookCatalogList = bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id=1","");

        String idThatNeedToDelete="5";
        Assert.assertEquals(1,bookCatalogList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("bookCatalogId",id);
        parametrMap.put("checkBookCatalog",checkId);

        bookCatalogDao.deleteAll(parametrMap);
        bookCatalogList=bookCatalogDao.getDataFromDB("SELECT * FROM lib_book_catalog WHERE lib_book_catalog_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,bookCatalogList.size());
    }

}