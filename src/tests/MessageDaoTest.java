import main.com.bsuir.library.bean.Message;
import main.com.bsuir.library.dao.implementation.message.implementation.MessageDao;
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
public class MessageDaoTest {
    @Test
    public void parseResultSet() throws Exception
    {
        MessageDao messageDao = new MessageDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        messageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Message> messageList = messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id=1","");
        Assert.assertEquals(1, messageList.size());
    }

    @Test
    public void insertToTable() throws Exception
    {
        MessageDao messageDao = new MessageDao();
        String idThatNeedToDelete="2";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        messageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] messageId=new String[1];
        messageId[0]="1";
        String[] messageText = new String[1];
        messageText[0]="Привет, как твои дела?";
        String[] messageTextNew = new String[1];
        messageTextNew[0]="NEW TEXT НОВЫЙ ТЕКСТ";
        String[] messageDate=new String[1];
        messageDate[0]="2017-02-11 18:15:16";
        String[] messageRecipientStatus = new String[1];
        messageRecipientStatus[0]="1";
        String[] messageSenderStatus = new String[1];
        messageSenderStatus[0]="1";
        String[] messageSenderId = new String[1];
        messageSenderId[0]="1";
        String[] messageUserRecipientId = new String[1];
        messageUserRecipientId[0]="1";

        parameterMap.put("messageId",messageId);
        parameterMap.put("messageText",messageTextNew);
        parameterMap.put("messageDate",messageDate);
        parameterMap.put("messageRecipientStatus",messageRecipientStatus);
        parameterMap.put("messageSenderStatus",messageSenderStatus);
        parameterMap.put("messageSenderId",messageSenderId);
        parameterMap.put("messageUserRecipientId",messageUserRecipientId);

        messageDao.insert(parameterMap);

        List<Message> messageList = messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id=1"+messageId[0]+"1","");
        Assert.assertEquals(1,messageList.size());
    }

    @Test
    public void updateTable() throws Exception
    {
        MessageDao messageDao = new MessageDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        messageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Message> messageList = messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id=1","");
        String messageText;
        messageText = messageList.get(0).getText();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] messageId=new String[1];
        messageId[0]="1";
        String[] messageTextNew = new String[1];
        messageTextNew[0]="Привет, как твои дела?";
        String[] messageDate=new String[1];
        messageDate[0]="2017-02-11 18:15:16";
        String[] messageRecipientStatus = new String[1];
        messageRecipientStatus[0]="1";
        String[] messageSenderStatus = new String[1];
        messageSenderStatus[0]="1";
        String[] messageSenderId = new String[1];
        messageSenderId[0]="1";
        String[] messageUserRecipientId = new String[1];
        messageUserRecipientId[0]="1";        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("messageId",messageId);
        parameterMap.put("messageText",messageTextNew);
        parameterMap.put("messageDate",messageDate);
        parameterMap.put("messageRecipientStatus",messageRecipientStatus);
        parameterMap.put("messageSenderStatus",messageSenderStatus);
        parameterMap.put("messageSenderId",messageSenderId);
        parameterMap.put("messageUserRecipientId",rowThatNeedToUpdate);
        parameterMap.put("checkMessage",rowThatNeedToUpdate);

        messageDao.updateAll(parameterMap,"");
        messageList=messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id=1","");
        Assert.assertEquals(messageText,messageList.get(0).getText());

    }

    @Test
    public void deleteFromTable() throws Exception
    {
        MessageDao messageDao = new MessageDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        messageDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Message> messageList = messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id=1","");
        Assert.assertEquals(1,messageList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("messageId",id);
        parametrMap.put("checkMessage",checkId);

        messageDao.deleteAll(parametrMap);
        messageList=messageDao.getDataFromDB("SELECT * FROM lib_message WHERE lib_message_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,messageList.size());
    }

}