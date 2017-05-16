package main.com.bsuir.library.dao.implementation.book.implementation;

import main.com.bsuir.library.bean.Book;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.book.IBookDao;

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
public class BookDao extends AbstractDaoController<Book,Integer> implements IBookDao {
    @Override
    public Book getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Book update(Book book) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Book> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Book> result = new LinkedList<Book>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setBookName(rs.getString("lib_book_name"+dbDataLanguage));
                book.setBookDate(rs.getTimestamp("lib_book_date"));
                book.setBookDescription(rs.getString("lib_book_description"+dbDataLanguage));
                book.setBookTextLink(rs.getString("lib_book_text_link"+dbDataLanguage));
                book.setAuthorId(rs.getInt("lib_book_author_id"));
                book.setId(rs.getInt("lib_book_id"));
                result.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] bookName = parameterMap.get("bookName");
        String[] bookDescription = parameterMap.get("bookDescription");
        String[] bookTextLink = parameterMap.get("bookTextLink");
        String[] bookNameEn=parameterMap.get("bookNameEn");
        String[] bookDescriptionEn = parameterMap.get("bookDescriptionEn");
        String[] bookTextLinkEn = parameterMap.get("bookTextLinkEn");
        String[] bookAuthorId=parameterMap.get("bookAuthorId");
        String sql = "INSERT INTO lib_book(lib_book_name,lib_book_description,lib_book_text_link,lib_book_name_en,lib_book_description_en,lib_book_text_link_en,lib_book_author_id)" +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,bookName[0]);
        stmt.setString(2,bookDescription[0]);
        stmt.setString(3,bookTextLink[0]);
        stmt.setString(4,bookNameEn[0]);
        stmt.setString(5,bookDescriptionEn[0]);
        stmt.setString(6,bookTextLinkEn[0]);
        stmt.setInt(7,Integer.parseInt(bookAuthorId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] bookAuthorId = parameterMap.get("bookAuthorId");
        String[] bookName = parameterMap.get("bookName");
        String[] bookDescription = parameterMap.get("bookDescription");
        String[] bookTextLink = parameterMap.get("bookTextLink");
        String[] bookId = parameterMap.get("bookId");
        String[] checkedBook = parameterMap.get("checkBook");
        String sql = "UPDATE lib_book SET lib_book_name" + dbDataLanguage + " =?,lib_book_description" + dbDataLanguage + "=?,lib_book_text_link" + dbDataLanguage + "=?,lib_book_author_id" + dbDataLanguage + "=? WHERE lib_book_id=?";
        for (String i : checkedBook) {
            int j = Integer.valueOf(i) - 1;
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, bookName[j]);
            stmt.setString(2, bookDescription[j]);
            stmt.setString(3, bookTextLink[j]);
            stmt.setInt(4, Integer.parseInt(bookAuthorId[j]));
            stmt.setInt(5, Integer.parseInt(bookId[j]));
            stmt.execute();

        }
        }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {

        String[] rowThatNeedDelete=parameterMap.get("checkBook");
        String[] id=parameterMap.get("bookId");
        String sql = "DELETE FROM lib_book WHERE lib_book_id=?";
        for(String i:rowThatNeedDelete)
        {
            int j= Integer.valueOf(i);
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
