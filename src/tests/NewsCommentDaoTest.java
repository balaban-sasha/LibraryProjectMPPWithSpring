import main.com.bsuir.library.bean.NewsComment;
import main.com.bsuir.library.dao.implementation.newsComment.implementation.NewsCommentDao;
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
public class NewsCommentDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        NewsCommentDao newsCommentDao = new NewsCommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsCommentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsComment> dataList = newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id=1","");
        Assert.assertEquals(1, dataList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        NewsCommentDao newsCommentDao = new NewsCommentDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsCommentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();
        String[] newsCommentId=new String[1];
        newsCommentId[0]="1";
        String[] newsCommentText = new String[1];
        newsCommentText[0]="Отличная новость. Читал, аж дух захватывало";
        String[] newsCommentTextNew = new String[1];
        newsCommentTextNew[0]="FAIL LOL !! ! !";
        String[] newsCommentTextEn=new String[1];
        newsCommentTextEn[0]="WOW";
        String[] newsCommentDate = new String[1];
        newsCommentDate[0]="2017-02-12 23:44:57";
        String[] newsCommentNewsId = new String[1];
        newsCommentNewsId[0] = "2";
        String[] newsCommentUserId = new String[1];
        newsCommentUserId[0] = "1";


        parameterMap.put("newsCommentId",newsCommentId);
        parameterMap.put("newsCommentText",newsCommentTextNew);
        parameterMap.put("newsCommentTextEn",newsCommentTextEn);
        parameterMap.put("newsCommentDate",newsCommentDate);
        parameterMap.put("newsCommentNewsId",newsCommentNewsId);
        parameterMap.put("newsCommentUserId",newsCommentUserId);

        newsCommentDao.insert(parameterMap);


        List<NewsComment> newsCommentList = newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id=1"+newsCommentId[0]+"1","");
        Assert.assertEquals(1,newsCommentList.size());
    }

    @Test
    public void updateTable() throws Exception {
        NewsCommentDao newsCommentDao = new NewsCommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsCommentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsComment> newsCommentList = newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id=1","");
        String newCommentTextNew;
        newCommentTextNew = newsCommentList.get(0).getText();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] newsCommentId= new String[1];
        newsCommentId[0]="1";
        String[] newsCommentTextNew = new String[1];
        newsCommentTextNew[0]="ВАУ!";
        String[] newsCommentTextEn=new String[1];
        newsCommentTextEn[0]="WOW";
        String[] newsCommentDate = new String[1];
        newsCommentDate[0]="2017-02-12 23:44:57";
        String[] newsCommentNewsId = new String[1];
        newsCommentNewsId[0] = "2";
        String[] newsCommentUserId = new String[1];
        newsCommentUserId[0] = "1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("newsCommentId",newsCommentId);
        parameterMap.put("newsCommentText",newsCommentTextNew);
        parameterMap.put("newsCommentDate",newsCommentDate);
        parameterMap.put("newsCommentTextEn",newsCommentTextEn);
        parameterMap.put("newsCommentNewsId",newsCommentNewsId);
        parameterMap.put("newsCommentUserId",newsCommentUserId);

        parameterMap.put("newsCommentNumber",rowThatNeedToUpdate);
        parameterMap.put("checkNewsComment",rowThatNeedToUpdate);

        newsCommentDao.updateAll(parameterMap,"");
        newsCommentList=newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id=1","");
        Assert.assertEquals(newCommentTextNew,newsCommentList.get(0).getText());
    }

    @Test
    public void deleteFromTable() throws Exception {
        NewsCommentDao newsCommentDao = new NewsCommentDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsCommentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsComment> newsCommentList = newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id="+idThatNeedToDelete,"");
        Assert.assertEquals(1,newsCommentList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();

        String[] newsCommentId= new String[1];
        newsCommentId[0]="1";

        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("newsCommentId",newsCommentId);
        parametrMap.put("checkNewsComment",checkId);

        newsCommentDao.deleteAll(parametrMap);
        newsCommentList=newsCommentDao.getDataFromDB("SELECT * FROM lib_news_comment WHERE lib_news_comment_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,newsCommentList.size());
    }

}
