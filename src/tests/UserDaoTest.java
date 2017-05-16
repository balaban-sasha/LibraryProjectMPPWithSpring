import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.hamcrest.*;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Саша on 24.04.2017.
 */
public class UserDaoTest {
    @Test
    public void testUserDaoCreate() throws SQLException, DaoException {
        UserDao userDao = new UserDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();
        String[] userLogin=new String[1];
        userLogin[0]="Gamrr";
        String[] userPassword = new String[1];
        userPassword[0]="12345";
        String[] userNameNew = new String[1];
        userNameNew[0]="Alexander";
        String[] userSurname=new String[1];
        userSurname[0]="Балабан";
        String[] userGender = new String[1];
        userGender[0]="1";
        String[] userAge = new String[1];
        userAge[0]="20";
        String[] userStatus = new String[1];
        userStatus[0]="3";
        parameterMap.put("userLogin",userLogin);
        parameterMap.put("userPassword",userPassword);
        parameterMap.put("userName",userNameNew);
        parameterMap.put("userSurname",userSurname);
        parameterMap.put("userGender",userGender);
        parameterMap.put("userAge",userAge);
        parameterMap.put("userStatus",userStatus);
        userDao.insert(parameterMap);
        List<User> userList = userDao.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_login='"+userLogin[0]+"'","");
        Assert.assertEquals(6,userList.size());
    }
    @Test(expected = NullPointerException.class)
    public void testUserDaoGetTable() throws SQLException, DaoException {
            UserDao userDao = new UserDao();
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
            userDao.startConnectionToDB(resourceBundle.getString("url"), "eqwe", resourceBundle.getString("password"));
        ExpectedException exception = ExpectedException.none();
        exception.expect(NullPointerException.class);
       List<User> userList= userDao.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_id=1","");
    }
}
