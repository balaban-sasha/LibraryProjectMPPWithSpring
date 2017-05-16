package main.com.bsuir.library.dao.implementation.bookCatalog.implementation;

import main.com.bsuir.library.bean.BookCatalog;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.bookCatalog.IBookCatalogDao;

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
public class BookCatalogDao extends AbstractDaoController<BookCatalog,Integer> implements IBookCatalogDao {
    @Override
    public BookCatalog getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public BookCatalog update(BookCatalog bookCatalog) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<BookCatalog> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<BookCatalog> result = new LinkedList<BookCatalog>();
        try {
            while (rs.next()) {
                BookCatalog bookCatalog = new BookCatalog();
                bookCatalog.setBookId(rs.getInt("lib_book_id"));
                bookCatalog.setBookStatus(rs.getInt("lib_book_catalog_status"));
                bookCatalog.setId(rs.getInt("lib_book_catalog_id"));
                bookCatalog.setSectionId(rs.getInt("lib_section_id"));
                result.add(bookCatalog);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
            String[] bookCatalogStatus=parameterMap.get("bookCatalogStatus");
            String[] bookCatalogBookId = parameterMap.get("bookCatalogBookId");
            String[] bookCatalogSectionId = parameterMap.get("bookCatalogSectionId");
            String sql = "INSERT INTO lib_book_catalog(lib_book_catalog_status,lib_book_id,lib_section_id)" +
                    "VALUES (?,?,?)";
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(bookCatalogStatus[0]));
            stmt.setInt(2,Integer.parseInt(bookCatalogBookId[0]));
            stmt.setInt(3,Integer.parseInt(bookCatalogSectionId[0]));
            stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("bookCatalogId");
        String[] bookCatalogStatus=parameterMap.get("bookCatalogStatus");
        String[] bookCatalogBookId = parameterMap.get("bookCatalogBookId");
        String[] bookCatalogSectionId = parameterMap.get("bookCatalogSectionId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkBookCatalog");
        String sql = "UPDATE lib_book_catalog SET lib_book_catalog_status=?,lib_book_id=?,lib_section_id=? WHERE lib_book_catalog_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(bookCatalogStatus[j]));
            stmt.setInt(2,Integer.parseInt(bookCatalogBookId[j]));
            stmt.setInt(3,Integer.parseInt(bookCatalogSectionId[j]));
            stmt.setInt(4,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete=parameterMap.get("checkBookCatalog");
        String[] id=parameterMap.get("bookCatalogId");
        String sql = "DELETE FROM lib_book_catalog WHERE lib_book_catalog_id=?";
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
