import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.bean.AuthorCatalog;
import main.com.bsuir.library.dao.implementation.author.implementation.AuthorDao;
import main.com.bsuir.library.dao.implementation.authorCatalog.implementation.AuthorCatalogDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 25.04.2017.
 */
public class AuthorCatalogDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        AuthorCatalogDao userDao = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<AuthorCatalog> dataList = userDao.getDataFromDB("SELECT * FROM lib_author_catalog WHERE lib_author_catalog_id=1","");
        Assert.assertEquals(1, dataList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        AuthorCatalogDao authorCatalogDao = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        authorCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] AuthorCatalogId=new String[1];
        AuthorCatalogId[0]="1";
        String[] AuthorCatalogStatus = new String[1];
        AuthorCatalogStatus[0]="1";
        String[] AuthorCatalogAuthorId = new String[1];
        AuthorCatalogAuthorId[0]="1";
        String[] AuthorCatalogAuthorIdNew = new String[1];
        AuthorCatalogAuthorIdNew[0]="2";
        String[] AuthorCatalogSectionId = new String[1];
        AuthorCatalogSectionId[0]="2";

        parameterMap.put("authorCatalogId",AuthorCatalogId);
        parameterMap.put("authorCatalogStatus",AuthorCatalogStatus);
        parameterMap.put("authorCatalogAuthorId",AuthorCatalogAuthorIdNew);
        parameterMap.put("authorCatalogSectionId",AuthorCatalogSectionId);


        authorCatalogDao.insert(parameterMap);

        List<AuthorCatalog> genreList = authorCatalogDao.getDataFromDB("SELECT * FROM lib_author_catalog WHERE lib_author_catalog_id=1"+AuthorCatalogId[0]+"1","");
        Assert.assertEquals(1,genreList.size());
    }

    @Test
    public void updateTable() throws Exception
    {
        AuthorCatalogDao authorCatalogDao = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        authorCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<AuthorCatalog> authorCatalogList = authorCatalogDao.getDataFromDB("SELECT * FROM lib_author_catalog WHERE lib_author_catalog_id=1","");
        int lib_author_catalog;
        lib_author_catalog = authorCatalogList.get(0).getId();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] AuthorCatalogId=new String[1];
        AuthorCatalogId[0]="1";
        String[] AuthorCatalogStatus = new String[1];
        AuthorCatalogStatus[0]="1";
        String[] AuthorCatalogAuthorId = new String[1];
        AuthorCatalogAuthorId[0]="1";
        String[] AuthorCatalogSectionId = new String[1];
        AuthorCatalogSectionId[0]="2";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("authorCatalogId",AuthorCatalogId);
        parameterMap.put("authorCatalogStatus",AuthorCatalogStatus);
        parameterMap.put("authorCatalogAuthorId",AuthorCatalogAuthorId);
        parameterMap.put("authorCatalogSectionId",rowThatNeedToUpdate);
        parameterMap.put("checkAuthorCatalog",rowThatNeedToUpdate);

        authorCatalogDao.updateAll(parameterMap,"");
        authorCatalogList=authorCatalogDao.getDataFromDB("SELECT * FROM lib_author_catalog WHERE lib_author_catalog_id=1","");
        Assert.assertEquals(lib_author_catalog,authorCatalogList.get(0).getId());
    }

    @Test
    public void deleteFromTable() throws Exception
    {
        AuthorCatalogDao authorCatalogDao = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        authorCatalogDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<AuthorCatalog> authorCatalogList = authorCatalogDao.getDataFromDB("SELECT * FROM lib_author_catalog WHERE lib_author_catalog_id=1","");

        String idThatNeedToDelete="5";
        Assert.assertEquals(1,authorCatalogList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("authorCatalogId",id);
        parametrMap.put("checkAuthorCatalog",checkId);

        authorCatalogDao.deleteAll(parametrMap);
        authorCatalogList=authorCatalogDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,authorCatalogList.size());
    }

}