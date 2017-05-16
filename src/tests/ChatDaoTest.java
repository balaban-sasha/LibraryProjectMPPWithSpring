import main.com.bsuir.library.bean.Chat;
import main.com.bsuir.library.dao.implementation.chat.implementation.ChatDao;
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
public class ChatDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        ChatDao chatDao = new ChatDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        chatDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Chat> chatList = chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id=1","");
        Assert.assertEquals(1, chatList.size());

    }

    @Test
    public void insertToTable() throws Exception {
        ChatDao chatDao = new ChatDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        chatDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] chatId=new String[1];
        chatId[0]="1";
        String[] chatMessageText = new String[1];
        chatMessageText[0]="Всем привет, как вы тут?";
        String[] chatMessageTextNew = new String[1];
        chatMessageTextNew[0]="Новый текст";
        String[] chatMessageTextEn = new String[1];
        chatMessageTextEn[0]="";
        String[] chatMessageDate = new String[1];
        chatMessageDate[0]="2017-02-11 18:22:22";
        String[] chatMessageSenderId = new String[1];
        chatMessageSenderId[0]="1";

        parameterMap.put("chatId",chatId);
        parameterMap.put("chatMessageText",chatMessageTextNew);
        parameterMap.put("chatMessageTextEn",chatMessageTextEn);
        parameterMap.put("chatMessageDate",chatMessageDate);
        parameterMap.put("chatMessageSenderId",chatMessageSenderId);

        chatDao.insert(parameterMap);

        List<Chat> chatList = chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id=1"+chatId[0]+"1","");
        Assert.assertEquals(1,chatList.size());
    }

    @Test
    public void updateTable() throws Exception {
        ChatDao chatDao = new ChatDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        chatDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Chat> chatList = chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id=1","");
        String chatNew;
        chatNew = chatList.get(0).getText();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] chatId=new String[1];
        chatId[0]="1";
        String[] chatMessageText = new String[1];
        chatMessageText[0]="Всем привет, как вы тут?";
        String[] chatMessageTextEn = new String[1];
        chatMessageTextEn[0]="";
        String[] chatMessageDate = new String[1];
        chatMessageDate[0]="2017-02-11 18:22:22";
        String[] chatMessageSenderId = new String[1];
        chatMessageSenderId[0]="1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("chatId",chatId);
        parameterMap.put("chatMessageText",chatMessageText);
        parameterMap.put("chatMessageTextEn",chatMessageTextEn);
        parameterMap.put("chatMessageDate",chatMessageDate);
        parameterMap.put("chatMessageSenderId",rowThatNeedToUpdate);
        parameterMap.put("checkChat",rowThatNeedToUpdate);

        chatDao.updateAll(parameterMap,"");
        chatList=chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id=1","");
        Assert.assertEquals(chatNew,chatList.get(0).getText());
    }

    @Test
    public void deleteFromTable() throws Exception {
        ChatDao chatDao = new ChatDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        chatDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Chat> chatList = chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id=1","");

        String idThatNeedToDelete="2";
        Assert.assertEquals(1,chatList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("chatId",id);
        parametrMap.put("checkChat",checkId);

        chatDao.deleteAll(parametrMap);
        chatList=chatDao.getDataFromDB("SELECT * FROM lib_chat WHERE lib_chat_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,chatList.size());
    }

}