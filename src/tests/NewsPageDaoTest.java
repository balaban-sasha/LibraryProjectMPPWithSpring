import main.com.bsuir.library.bean.NewsPage;
import main.com.bsuir.library.dao.implementation.newsPage.implementation.NewsPageDao;
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
public class NewsPageDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        NewsPageDao newPage = new NewsPageDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newPage.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsPage> dataList = newPage.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id=1","");
        Assert.assertEquals(1, dataList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        NewsPageDao newPageDao = new NewsPageDao();
        String idThatNeedToDelete="2";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newPageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] newsPageId=new String[1];
        newsPageId[0]="1";
        String[] newsPageStatus = new String[1];
        newsPageStatus[0]="1";
        String[] newsPageStatusNew = new String[1];
        newsPageStatusNew[0]="2";
        String[] newsPageNewsId = new String[1];
        newsPageNewsId[0]="1";
        String[] newsPageSectionId=new String[1];
        newsPageSectionId[0]="4";

        parameterMap.put("newsPageId",newsPageId);
        parameterMap.put("newsPageStatus",newsPageStatusNew);
        parameterMap.put("newsPageNewsId",newsPageNewsId);
        parameterMap.put("newsPageSectionId",newsPageSectionId);

        newPageDao.insert(parameterMap);

        List<NewsPage> newsPageList = newPageDao.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id=1"+newsPageId[0]+"1","");
        Assert.assertEquals(1,newsPageList.size());
    }

    @Test
    public void updateTable() throws Exception {
        NewsPageDao newPageDao = new NewsPageDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newPageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsPage> newsPageList = newPageDao.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id=1","");
        String newsPageNewsId;
        newsPageNewsId = String.valueOf(newsPageList.get(0).getNewsId());
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] newsPageId= new String[1];
        newsPageId[0]="1";
        String[] newsPageStatus = new String[1];
        newsPageStatus[0]="2";
        String[] newsPageNewsIdNew=new String[1];
        newsPageNewsIdNew[0]="2";
        String[] newsPageSectionId = new String[1];
        newsPageSectionId[0]="4";

        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("newsPageId",newsPageId);
        parameterMap.put("newsPageStatus",newsPageStatus);
        parameterMap.put("newsPageNewsId",newsPageNewsIdNew);
        parameterMap.put("newsPageSectionId",newsPageSectionId);
        parameterMap.put("checkNewsPage",rowThatNeedToUpdate);

        newPageDao.updateAll(parameterMap,"");
        newsPageList=newPageDao.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id=1","");
        Assert.assertEquals(newsPageNewsId,newsPageList.get(0).getNewsId());
    }

    @Test
    public void deleteFromTable() throws Exception {
        NewsPageDao newsPageDao = new NewsPageDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsPageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<NewsPage> newsPageList = newsPageDao.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id=1","");
        Assert.assertEquals(1,newsPageList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("newsPageId",id);
        parametrMap.put("checkNewsPage",checkId);

        newsPageDao.deleteAll(parametrMap);
        newsPageList=newsPageDao.getDataFromDB("SELECT * FROM lib_news_page WHERE lib_news_page_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,newsPageList.size());
    }

}