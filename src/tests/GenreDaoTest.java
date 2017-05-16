import main.com.bsuir.library.bean.Genre;
import main.com.bsuir.library.dao.implementation.genre.implementation.GenreDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 26.04.2017.
 */
public class GenreDaoTest {
    @Test
    public void parseResultSet() throws Exception
    {
        GenreDao genreDao = new GenreDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        genreDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Genre> genreList = genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id=1","");
        Assert.assertEquals(1, genreList.size());

    }

    @Test
    public void insertToTable() throws Exception
    {
        GenreDao genreDao = new GenreDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        genreDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] genreId=new String[1];
        genreId[0]="1";
        String[] genreName = new String[1];
        genreName[0]="Классика";
        String[] genreNameNew = new String[1];
        genreNameNew[0]="Новый жанр";
        String[] genreNameEn = new String[1];
        genreNameEn[0]="Classic";

        parameterMap.put("genreId",genreId);
        parameterMap.put("genreName",genreNameNew);
        parameterMap.put("genreNameEn",genreNameEn);

        genreDao.insert(parameterMap);

        List<Genre> genreList = genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id=1"+genreId[0]+"1","");
        Assert.assertEquals(1,genreList.size());
    }

    @Test
    public void updateTable() throws Exception
    {
        GenreDao genreDao = new GenreDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        genreDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Genre> genreList = genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id=1","");
        String genreNew;
        genreNew = genreList.get(0).getGenre();
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

        genreDao.updateAll(parameterMap,"");
        genreList=genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id=1","");
        Assert.assertEquals(genreNew,genreList.get(0).getGenre());
    }

    @Test
    public void deleteFromTable() throws Exception
    {
        GenreDao genreDao = new GenreDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        genreDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Genre> genreList = genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id=1","");

        String idThatNeedToDelete="5";
        Assert.assertEquals(1,genreList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("newsId",id);
        parametrMap.put("checkNews",checkId);

        genreDao.deleteAll(parametrMap);
        genreList=genreDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,genreList.size());
    }

}