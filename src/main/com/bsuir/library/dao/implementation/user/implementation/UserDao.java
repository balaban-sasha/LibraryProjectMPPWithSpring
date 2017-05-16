package main.com.bsuir.library.dao.implementation.user.implementation;

import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.user.IUserDao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class UserDao extends AbstractDaoController<User, Integer> implements IUserDao {
    @Override
    public void updateFileById(Map<String, String[]> parameterMap, String dbDataLanguage, int id) {

    }

    @Override
    public User getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<User> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setAge(rs.getInt("lib_user_age"));
                user.setFemale(rs.getString("lib_user_female"));
                user.setGender(rs.getInt("lib_user_gender"));
                user.setId(rs.getInt("lib_user_id"));
                user.setLogin(rs.getString("lib_user_login"));
                user.setName(rs.getString("lib_user_name"));
                user.setPassword(rs.getString("lib_user_password"));
                user.setStatus(rs.getInt("lib_user_status"));
                user.setUserLastOnlineDate(rs.getTimestamp("lib_user_online"));
                user.setUserAvatar(rs.getString("lib_user_avatar"));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException, NoSuchAlgorithmException {
        String[] userLogin = parameterMap.get("userLogin");
        String[] userPassword = parameterMap.get("userPassword");
        String[] userName = parameterMap.get("userName");
        String[] userSurname = parameterMap.get("userSurname");
        String[] userGender = parameterMap.get("userGender");
        String[] userAge = parameterMap.get("userAge");
        String[] userStatus = new String[1];
        if (parameterMap.containsKey("userStatus")) {
            userStatus = parameterMap.get("userStatus");
        } else
            userStatus[0] = "1";
        String sql = "INSERT INTO lib_user(lib_user_login,lib_user_password,lib_user_name,lib_user_female,lib_user_gender,lib_user_age,lib_user_status)" +
                "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(sql);
        StringBuffer code = new StringBuffer();
        stmt.setString(1, userLogin[0]);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte bytes[] = userPassword[0].getBytes();
        byte digest[] = messageDigest.digest(bytes); //create code
        for (int i = 0; i < digest.length; ++i) {
            code.append(Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1));
        }
        stmt.setString(2, String.valueOf(code));
        stmt.setString(3, userName[0]);
        stmt.setString(4, userSurname[0]);
        stmt.setInt(5, Integer.parseInt(userGender[0]));
        stmt.setInt(6, Integer.parseInt(userAge[0]));
        stmt.setInt(7, Integer.parseInt(userStatus[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id = parameterMap.get("userId");
        String[] userLogin = parameterMap.get("userLogin");
        String[] userPassword = parameterMap.get("userPassword");
        String[] userName = parameterMap.get("userName");
        String[] userSurname = parameterMap.get("userSurname");
        String[] userGender = parameterMap.get("userGender");
        String[] userAge = parameterMap.get("userAge");
        String[] userStatus = parameterMap.get("userStatus");
        String[] rowThatNeedToUpdate = parameterMap.get("checkUser");
        String sql = "UPDATE lib_user SET lib_user_login=?,lib_user_password=?,lib_user_name=?,lib_user_female=?,lib_user_gender=?,lib_user_age=?," +
                "lib_user_status=? WHERE lib_user_id=?";
        for (String i : rowThatNeedToUpdate) {
            int j = Integer.valueOf(i) - 1;
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, userLogin[j]);
            stmt.setString(2, userPassword[j]);
            stmt.setString(3, userName[j]);
            stmt.setString(4, userSurname[j]);
            stmt.setInt(5, Integer.parseInt(userGender[j]));
            stmt.setInt(6, Integer.parseInt(userAge[j]));
            stmt.setInt(7, Integer.parseInt(userStatus[j]));
            stmt.setInt(8, Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkUser");
        String[] id = parameterMap.get("userId");
        String sql = "DELETE FROM lib_user WHERE lib_user_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j - 1]));
            stmt.execute();

        }

    }
    @Override
    protected void updateData(String s,Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(s);
        stmt.execute();
    }
}
