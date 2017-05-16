package main.com.bsuir.library.dao.implementation.message.implementation;

import main.com.bsuir.library.bean.Message;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.message.IMessageDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class MessageDao extends AbstractDaoController<Message, Integer> implements IMessageDao {
    @Override
    public Message getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Message update(Message message) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<Message> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<Message> result = new LinkedList<Message>();
        try {
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("lib_message_id"));
                message.setPublicateDate(rs.getTimestamp("lib_message_date"));
                message.setRecipientId(rs.getInt("lib_user_recipient_id"));
                message.setRecippientStatus(rs.getInt("lib_message_recipient_status"));
                message.setSenderId(rs.getInt("lib_user_sender_id"));
                message.setText(rs.getString("lib_message_text"));
                message.setSenderStatus(rs.getInt("lib_message_sender_status"));
                result.add(message);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String[] messageText=parameterMap.get("messageText");
        String[] messageRecipientStatus = parameterMap.get("messageRecipientStatus");
        String[] messageSenderStatus = parameterMap.get("messageSenderStatus");
        String[] messageSenderId = parameterMap.get("messageSenderId");
        String[] messageRecipientId = parameterMap.get("messageRecipientId");
        String sql = "INSERT INTO lib_message(lib_message_text,lib_message_recipient_status,lib_message_sender_status,lib_user_sender_id," +
                "lib_user_recipient_id)VALUES (?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,messageText[0]);
        stmt.setInt(2,Integer.parseInt(messageRecipientStatus[0]));
        stmt.setInt(3,Integer.parseInt(messageSenderStatus[0]));
        stmt.setInt(4,Integer.parseInt(messageSenderId[0]));
        stmt.setInt(5,Integer.parseInt(messageRecipientId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("messageId");
        String[] messageText=parameterMap.get("messageText");
        String[] messageRecipientStatus = parameterMap.get("messageRecipientStatus");
        String[] messageSenderStatus = parameterMap.get("messageSenderStatus");
        String[] messageSenderId = parameterMap.get("messageSenderId");
        String[] messageRecipientId = parameterMap.get("messageRecipientId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkMessage");
        String sql = "UPDATE lib_message SET lib_message_text=?,lib_message_recipient_status=?,lib_message_sender_status=?," +
                "lib_user_sender_id=?,lib_user_recipient_id=? WHERE lib_message_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,messageText[j]);
            stmt.setInt(2,Integer.parseInt(messageRecipientStatus[j]));
            stmt.setInt(3,Integer.parseInt(messageSenderStatus[j]));
            stmt.setInt(4,Integer.parseInt(messageSenderId[j]));
            stmt.setInt(5,Integer.parseInt(messageRecipientId[j]));
            stmt.setInt(6,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkMessage");
        String[] id=parameterMap.get("messageId");
        String sql = "DELETE FROM lib_message WHERE lib_message_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
