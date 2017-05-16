import main.com.bsuir.library.bean.News;
import main.com.bsuir.library.dao.implementation.news.implementation.NewsDao;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 25.04.2017.
 */
public class NewsDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        NewsDao newsDao = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=1", "");
        Assert.assertEquals(1, newsList.size());
    }

    @Test(timeout = 1000)
    public void tryParseByAnyTime() throws Exception {
        NewsDao newsDao = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        for (int i = 0; i < 30; i++) {
            List<News> newsList = newsDao.getDataFromDB("SELECT * FROM lib_news", "");
        }

    }

    @Test
    public void insertToTable() throws Exception {
        NewsDao newsDao = new NewsDao();
        String idThatNeedToDelete = "2";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();

        String[] news_id = new String[1];
        news_id[0] = "1";
        String[] news_header = new String[1];
        news_header[0] = "Факт про Джорджа Оруэлла";
        String[] news_text = new String[1];
        news_text[0] = "Несмотря на то, что в произведениях Оруэлла многие усматривают сатиру тоталитарного строя, самого писателя власти долгое время подозревали в тесных связях с коммунистами. Как показало рассекреченное в 2007 году досье на писателя, британская контрразведка MI-5 с 1929 года и почти до самой смерти писателя в 1950 году вело за ним слежку. Например, в одной из записок досье, датированной 20 января 1942 года, агент сержант Эйвинг (англ. Sgt Ewing) описывает Оруэлла следующим образом: «Этот человек распространяет коммунистические убеждения, и его некоторые индийские друзья говорят, что часто видели его на собраниях коммунистов. Он богемно одевается как на работе, так и в часы досуга» (англ. \"This man has advanced communist views, and several of his Indian friends say that they have often seen him at communist meetings. He dresses in bohemian fashion both at his office and in his leisure hours\"). Согласно документам, писатель действительно принимал участие в таких собраниях, и в характеристике он проходил как «симпатизирующий коммунистам».";
        String[] news_text_New = new String[1];
        news_text_New[0] = "Какая то новая инфа про какую то инфу";
        String[] news_header_en = new String[1];
        news_header_en[0] = "The fact about George Orwell";
        String[] news_text_en = new String[1];
        news_text_en[0] = "Despite the fact that in the works of George Orwell, many perceive a satire of a totalitarian regime, the writer of power for a long time suspected of close ties with the Communists. As demonstrated in 2007, declassified dossier on the writer, the British counterintelligence service MI-5 in 1929, and almost to the writer's death in 1950 led him under surveillance. For example, in a note by the dossier, dated January 20, 1942, the agent Sergeant Eyving (. English Sgt Ewing) describes Orwell as follows: \"This man is spreading communist convictions, and some Indian friends say they have often seen him at Communist meetings. He bohemian worn both at work and during leisure time \"(eng.\" This man has advanced communist views, and several of his Indian friends say they have often seen him at communist meetings. He dresses in a bohemian fashion both at his office and in his leisure hours \"). According to the documents, the writer actually participated in these meetings, and in the characterization of it was held as a \"communist sympathizer\".";
        String[] news_date = new String[1];
        news_date[0] = "2017-02-11 18:12:57";

        parameterMap.put("newsId", news_id);
        parameterMap.put("newsHeader", news_header);
        parameterMap.put("newsText", news_text_New);
        parameterMap.put("newsHeaderEn", news_header_en);
        parameterMap.put("newsTextEn", news_text_en);
        parameterMap.put("newsDate", news_date);

        newsDao.insert(parameterMap);

        List<News> newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=1" + news_id[0] + "1", "");
        Assert.assertEquals(1, newsList.size());
    }

    @Test
    public void updateTable() throws Exception {
        NewsDao newsDao = new NewsDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=1", "");
        String newsText;
        newsText = newsList.get(0).getText();
        Map<String, String[]> parameterMap = new HashMap<String, String[]>();

        String[] news_id = new String[1];
        news_id[0] = "1";
        String[] news_header = new String[1];
        news_header[0] = "Факт про Джорджа Оруэлла";
        String[] news_text = new String[1];
        news_text[0] = "Несмотря на то, что в произведениях Оруэлла многие усматривают сатиру тоталитарного строя, самого писателя власти долгое время подозревали в тесных связях с коммунистами. Как показало рассекреченное в 2007 году досье на писателя, британская контрразведка MI-5 с 1929 года и почти до самой смерти писателя в 1950 году вело за ним слежку. Например, в одной из записок досье, датированной 20 января 1942 года, агент сержант Эйвинг (англ. Sgt Ewing) описывает Оруэлла следующим образом: «Этот человек распространяет коммунистические убеждения, и его некоторые индийские друзья говорят, что часто видели его на собраниях коммунистов. Он богемно одевается как на работе, так и в часы досуга» (англ. \"This man has advanced communist views, and several of his Indian friends say that they have often seen him at communist meetings. He dresses in bohemian fashion both at his office and in his leisure hours\"). Согласно документам, писатель действительно принимал участие в таких собраниях, и в характеристике он проходил как «симпатизирующий коммунистам».";
        String[] news_header_en = new String[1];
        news_header_en[0] = "The fact about George Orwell";
        String[] news_text_en = new String[1];
        news_text_en[0] = "Despite the fact that in the works of George Orwell, many perceive a satire of a totalitarian regime, the writer of power for a long time suspected of close ties with the Communists. As demonstrated in 2007, declassified dossier on the writer, the British counterintelligence service MI-5 in 1929, and almost to the writer's death in 1950 led him under surveillance. For example, in a note by the dossier, dated January 20, 1942, the agent Sergeant Eyving (. English Sgt Ewing) describes Orwell as follows: \"This man is spreading communist convictions, and some Indian friends say they have often seen him at Communist meetings. He bohemian worn both at work and during leisure time \"(eng.\" This man has advanced communist views, and several of his Indian friends say they have often seen him at communist meetings. He dresses in a bohemian fashion both at his office and in his leisure hours \"). According to the documents, the writer actually participated in these meetings, and in the characterization of it was held as a \"communist sympathizer\".";
        String[] news_date = new String[1];
        news_date[0] = "2017-02-11 18:12:57";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0] = "1";

        parameterMap.put("newsId", news_id);
        parameterMap.put("newsHeader", news_header);
        parameterMap.put("newsText", news_text);
        parameterMap.put("newsHeaderEn", news_header_en);
        parameterMap.put("newsTextEn", news_text_en);
        parameterMap.put("newsDate", rowThatNeedToUpdate);
        parameterMap.put("checkNews", rowThatNeedToUpdate);

        newsDao.updateAll(parameterMap, "");
        newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=1", "");
        Assert.assertEquals(newsText, newsList.get(0).getText());

    }

    @Test
    public void deleteFromTable() throws Exception {
        NewsDao newsDao = new NewsDao();
        String idThatNeedToDelete = "5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        newsDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<News> newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=" + idThatNeedToDelete, "");
        Assert.assertEquals(1, newsList.size());
        Map<String, String[]> parametrMap = new HashMap<String, String[]>();
        String[] id = new String[1];
        id[0] = idThatNeedToDelete;
        String[] checkId = new String[1];
        checkId[0] = "1";
        parametrMap.put("newsId", id);
        parametrMap.put("checkNews", checkId);

        newsDao.deleteAll(parametrMap);
        newsList = newsDao.getDataFromDB("SELECT * FROM lib_news WHERE lib_news_id=" + idThatNeedToDelete, "");
        Assert.assertNotEquals(1, newsList.size());
    }
}