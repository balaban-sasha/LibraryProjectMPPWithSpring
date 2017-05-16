package main.com.bsuir.library.dao.implementation.bookGenre.implementation;

import main.com.bsuir.library.bean.BookGenre;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.bookGenre.IBookGenreDao;

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
public class BookGenreDao extends AbstractDaoController<BookGenre, Integer> implements IBookGenreDao {
    @Override
    public BookGenre getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public BookGenre update(BookGenre bookGenre) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<BookGenre> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<BookGenre> result = new LinkedList<BookGenre>();
        try {
            while (rs.next()) {
                BookGenre bookGenre = new BookGenre();
                bookGenre.setBookId(rs.getInt("lib_book_id"));
                bookGenre.setGenreId(rs.getInt("lib_genre_id"));
                bookGenre.setId(rs.getInt("lib_book_genre_id"));
                result.add(bookGenre);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String[] bookGenreBookId=parameterMap.get("bookGenreBookId");
        String[] bookGenreGenreId = parameterMap.get("bookGenreGenreId");
        String sql = "INSERT INTO lib_book_genre(lib_book_id,lib_genre_id)VALUES (?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,Integer.parseInt(bookGenreBookId[0]));
        stmt.setInt(2,Integer.parseInt(bookGenreGenreId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("bookGenreId");
        String[] bookGenreBookId=parameterMap.get("bookGenreBookId");
        String[] bookGenreGenreId = parameterMap.get("bookGenreGenreId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkBookGenre");
        String sql = "UPDATE lib_book_genre SET lib_book_id=?,lib_genre_id=? WHERE lib_book_genre_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(bookGenreBookId[j]));
            stmt.setInt(2,Integer.parseInt(bookGenreGenreId[j]));
            stmt.setInt(3,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkBookGenre");
        String[] id=parameterMap.get("bookGenreId");
        String sql = "DELETE FROM lib_book_genre WHERE lib_book_genre_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j]));
            stmt.execute();

        }
    }
}
