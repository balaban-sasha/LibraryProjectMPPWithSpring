package main.com.bsuir.library.dao.implementation.authorCatalog.implementation;

import main.com.bsuir.library.bean.AuthorCatalog;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.authorCatalog.IAuthorCatalogDao;

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
public class AuthorCatalogDao extends AbstractDaoController<AuthorCatalog,Integer> implements IAuthorCatalogDao {

    public AuthorCatalogDao() {
        super();
    }


    @Override
    public AuthorCatalog getByPrimaryKey(Integer key) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AuthorCatalog update(AuthorCatalog entity) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Integer key) throws DaoException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<AuthorCatalog> parseResultSet(ResultSet rs,String dbDataLanguage) throws DaoException {
        LinkedList<AuthorCatalog> result = new LinkedList<AuthorCatalog>();
        try {
            while (rs.next()) {
                AuthorCatalog authorCatalog = new AuthorCatalog();
                authorCatalog.setAuthorId(rs.getInt("lib_author_id"));
                authorCatalog.setAuthorStatus(rs.getInt("lib_author_catalog_status"));
                authorCatalog.setId(rs.getInt("lib_author_catalog_id"));
                authorCatalog.setSectionId(rs.getInt("lib_section_id"));
                result.add(authorCatalog);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] authorCatalogStatus=parameterMap.get("authorCatalogStatus");
        String[] authorCatalogAuthorId = parameterMap.get("authorCatalogAuthorId");
        String[] authorCatalogSectionId = parameterMap.get("authorCatalogSectionId");
        String sql = "INSERT INTO lib_author_catalog(lib_author_catalog_status,lib_author_id,lib_section_id)" +
                "VALUES (?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,Integer.parseInt(authorCatalogStatus[0]));
        stmt.setInt(2,Integer.parseInt(authorCatalogAuthorId[0]));
        stmt.setInt(3,Integer.parseInt(authorCatalogSectionId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] rowThatNeedUpdate=parameterMap.get("checkAuthorCatalog");
        String[] authorCatalogStatus=parameterMap.get("authorCatalogStatus");
        String[] authorCatalogAuthorId = parameterMap.get("authorCatalogAuthorId");
        String[] authorCatalogSectionId = parameterMap.get("authorCatalogSectionId");
        String[] id = parameterMap.get("authorCatalogId");
        String sql = "UPDATE lib_author_catalog SET lib_author_catalog_status=?,lib_author_id=?,lib_section_id=? WHERE lib_author_catalog_id=?";
        for(String i:rowThatNeedUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(authorCatalogStatus[j]));
            stmt.setInt(2,Integer.parseInt(authorCatalogAuthorId[j]));
            stmt.setInt(3,Integer.parseInt(authorCatalogSectionId[j]));
            stmt.setInt(4,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String[] rowThatNeedDelete=parameterMap.get("checkAuthorCatalog");
        String[] id=parameterMap.get("authorCatalogId");
        String sql = "DELETE FROM lib_comment WHERE lib_comment_id=?";
        for(String i:rowThatNeedDelete)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
