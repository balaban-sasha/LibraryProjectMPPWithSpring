package main.com.bsuir.library.controller.data;

import main.com.bsuir.library.bean.AuthorCatalog;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.authorCatalog.implementation.AuthorCatalogDao;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Саша on 28.03.2017.
 */
public class AuthorDataCatalogController extends AbstractBeanController {

    @Override
    public List<AuthorCatalog> setTableContent(String dbDataLanguage)
            throws DataControllerException, DaoException, SQLException {
        AuthorCatalogDao dbConnection = new AuthorCatalogDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        dbConnection.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"),
                resourceBundle.getString("password"));
        List<AuthorCatalog> authorCatalogList;
        try {
            authorCatalogList = dbConnection.getDataFromDB("SELECT * FROM lib_author_catalog",dbDataLanguage);
        } catch (Exception e) {
            throw new DataControllerException(e);
        }
        return authorCatalogList;
        //request.setAttribute("authorCatalogList", authorCatalogList);

    }

    @Override
    public List getDataIfExist(String userLogin, String userPassword) throws DataControllerException {
        return null;
    }
}
