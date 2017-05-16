package main.com.bsuir.library.dao.implementation.bookRaiting.implementation;

import main.com.bsuir.library.bean.BookRaiting;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.bookRaiting.IBookRaitingDao;

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
public class BookRaitingDao extends AbstractDaoController<BookRaiting, Integer> implements IBookRaitingDao {
    @Override
    public BookRaiting getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public BookRaiting update(BookRaiting bookRaiting) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<BookRaiting> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<BookRaiting> result = new LinkedList<BookRaiting>();
        try {
            while (rs.next()) {
                BookRaiting bookRaiting = new BookRaiting();
                bookRaiting.setBookId(rs.getInt("lib_book_id"));
                bookRaiting.setId(rs.getInt("lib_book_raiting_id"));
                bookRaiting.setRaitingCount(rs.getInt("lib_book_raiting_count"));
                bookRaiting.setUserId(rs.getInt("lib_user_id"));
                result.add(bookRaiting);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String[] bookRaitingRaitingCount=parameterMap.get("bookRaitingRaitingCount");
        String[] bookRaitingBookId = parameterMap.get("bookRaitingBookId");
        String[] bookRaitingUserId = parameterMap.get("bookRaitingUserId");
        String sql = "INSERT INTO lib_book_raiting(lib_book_raiting_count,lib_book_id,lib_user_id)VALUES (?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setInt(1,Integer.parseInt(bookRaitingRaitingCount[0]));
        stmt.setInt(2,Integer.parseInt(bookRaitingBookId[0]));
        stmt.setInt(3,Integer.parseInt(bookRaitingUserId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("bookRaitingId");
        String[] bookRaitingRaitingCount=parameterMap.get("bookRaitingRaitingCount");
        String[] bookRaitingBookId = parameterMap.get("bookRaitingBookId");
        String[] bookRaitingUserId = parameterMap.get("bookRaitingUserId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkBookRaiting");
        String sql = "UPDATE lib_book_raiting SET lib_book_raiting_count=?,lib_book_id=?,lib_user_id=? WHERE lib_book_raiting_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(bookRaitingRaitingCount[j]));
            stmt.setInt(2,Integer.parseInt(bookRaitingBookId[j]));
            stmt.setInt(3,Integer.parseInt(bookRaitingUserId[j]));
            stmt.setInt(4,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection)
            throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkBookRaiting");
        String[] id=parameterMap.get("bookRaitingId");
        String sql = "DELETE FROM lib_book_raiting WHERE lib_book_raiting_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
