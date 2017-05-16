import main.com.bsuir.library.bean.Comment;
import main.com.bsuir.library.dao.implementation.comment.implementation.CommentDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 27.04.2017.
 */
public class CommentDaoTest {
    @Test
    public void parseResultSet() throws Exception
    {
        CommentDao commentDao = new CommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        commentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Comment> commentList = commentDao.getDataFromDB("SELECT * FROM lib_comment WHERE lib_comment_id=1","");
        Assert.assertEquals(1, commentList.size());
    }

    @Test
    public void insertToTable() throws Exception
    {
        CommentDao commentDao = new CommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        commentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] commentId=new String[1];
        commentId[0]="1";
        String[] commentText = new String[1];
        commentText[0]="Отличная книга, советую прочитать";
        String[] commentTextEn = new String[1];
        commentTextEn[0]="";
        String[] commentTextEnNew = new String[1];
        commentTextEnNew[0]="SMTH";
        String[] commentDate = new String[1];
        commentDate[0]="2017-02-11 18:22:34";
        String[] commentBookId = new String[1];
        commentBookId[0]="1";
        String[] commentSenderId = new String[1];
        commentSenderId[0]="1";

        parameterMap.put("commentId",commentId);
        parameterMap.put("commentText",commentText);
        parameterMap.put("commentTextEn",commentTextEnNew);
        parameterMap.put("commentDate",commentDate);
        parameterMap.put("commentBookId",commentBookId);
        parameterMap.put("commentSenderId",commentSenderId);

        commentDao.insert(parameterMap);

        List<Comment> commentList = commentDao.getDataFromDB("SELECT * FROM lib_comment WHERE lib_comment_id="+commentId[0]+"1","");
        Assert.assertEquals(1,commentList.size());
    }

    @Test
    public void updateTable() throws Exception
    {
        CommentDao commentDao = new CommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        commentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Comment> commentList = commentDao.getDataFromDB("SELECT * FROM lib_comment WHERE lib_comment_id=1","");
        String commentTextNew;
        commentTextNew = commentList.get(0).getText();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] commentId=new String[1];
        commentId[0]="1";
        String[] commentText = new String[1];
        commentText[0]="Отличная книга, советую прочитать";
        String[] commentTextEn = new String[1];
        commentTextEn[0]="";
        String[] commentDate = new String[1];
        commentDate[0]="2017-02-11 18:22:34";
        String[] commentBookId = new String[1];
        commentBookId[0]="1";
        String[] commentSenderId = new String[1];
        commentSenderId[0]="1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";


        parameterMap.put("commentId",commentId);
        parameterMap.put("commentText",commentText);
        parameterMap.put("commentTextEn",commentText);
        parameterMap.put("commentDate",commentDate);
        parameterMap.put("commentBookId",commentBookId);
        parameterMap.put("commentSenderId",rowThatNeedToUpdate);
        parameterMap.put("checkComment",rowThatNeedToUpdate);

        commentDao.updateAll(parameterMap,"");
        commentList=commentDao.getDataFromDB("SELECT * FROM lib_comment WHERE lib_comment_id=1","");
        Assert.assertEquals(commentTextNew,commentList.get(0).getText());
    }

    @Test
    public void deleteFromTable() throws Exception
    {

        CommentDao commentDao = new CommentDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        commentDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Comment> commentList = commentDao.getDataFromDB("SELECT * FROM lib_comment WHERE lib_comment_id=1","");

        String idThatNeedToDelete="5";
        Assert.assertEquals(1,commentList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("commentId",id);
        parametrMap.put("checkComment",checkId);

        commentDao.deleteAll(parametrMap);
        commentList=commentDao.getDataFromDB("SELECT * FROM lib_genre WHERE lib_genre_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,commentList.size());
    }

}