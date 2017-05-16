package main.com.bsuir.library.controller.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;

public class UserDataController extends AbstractBeanController<User> {
    @Override
    public List<User> setTableContent(String dbDataLanguage) throws DataControllerException, DaoException, SQLException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user", dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        //request.setAttribute("userList", userList);
        return userList;

    }

    @Override
    public List<User> getDataIfExist(String userName, String userPassword) throws DataControllerException, NoSuchAlgorithmException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        StringBuffer code = new StringBuffer();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte bytes[] = userPassword.getBytes();
        byte digest[] = messageDigest.digest(bytes); //create code
        for (int i = 0; i < digest.length; ++i) {
            code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
        }
        userPassword = String.valueOf(code);
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE `lib_user_login`='" + userName + "' and lib_user_password='" + userPassword + "'", "");
            if (!userList.isEmpty()) {
                dbConnection.updateRequest("UPDATE lib_user SET lib_user_online=CURRENT_TIMESTAMP where lib_user_id=" + userList.get(0).getId());
            }
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        //request.setAttribute("userList", userList);
        return userList;
    }

    public String getUserLogin(int id) throws DataControllerException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE `lib_user_id`=" + id, "");
        } catch (Exception e) {
            throw new DataControllerException(e);
        }

        if (userList.isEmpty())
            return "guest";
        else
            return userList.get(0).getLogin();
    }

    public Timestamp getLastUpdateDate(int id) throws DataControllerException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE `lib_user_id`=" + id, "");
        } catch (Exception e) {
            throw new DataControllerException(e);

        }
        Timestamp timestamp = null;
        if (userList.isEmpty())
            return null;
        else
            return userList.get(0).getUserLastOnlineDate();
    }

    public String getUserAvatar(int id) throws DataControllerException {

        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE `lib_user_id`=" + id, "");
        } catch (Exception e) {
            throw new DataControllerException(e);

        }
        Timestamp timestamp = null;
        if (userList.isEmpty())
            return null;
        else
            return userList.get(0).getUserAvatar();
    }

    public void updateUserOnline(int userId) throws DataControllerException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            dbConnection.updateRequest("UPDATE lib_user SET lib_user_online=CURRENT_TIMESTAMP where lib_user_id=" + userId);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
    }

    @Override
    public List<User> getContentByTime(String dbDataLanguage, Timestamp date) throws SQLException, DaoException, DataControllerException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_online>(CURRENT_TIMESTAMP-300) ORDER by lib_user_status DESC", dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return userList;
    }

    @Override
    public List<User> getContentByLastId(String dbDataLanguage, int lastId) throws DataControllerException {
        UserDao dbConnection = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<User> userList = new ArrayList<User>();
        try {
            userList = dbConnection.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_id="+lastId, dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return userList;
    }


}
