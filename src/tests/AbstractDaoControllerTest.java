import junit.framework.AssertionFailedError;
import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.author.implementation.AuthorDao;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 24.04.2017.
 */
public class AbstractDaoControllerTest {
    @Test(expected = NullPointerException.class)
    public void testUserDaoGetTable() throws SQLException, DaoException {
        UserDao userDao = new UserDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), "eqwe", resourceBundle.getString("password"));
//        ExpectedException exception = ExpectedException.none();
//        exception.expect(NullPointerException.class);
        List<User> userList= userDao.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_id=1","");
    }

}