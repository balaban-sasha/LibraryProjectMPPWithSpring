package main.com.bsuir.library.dao.implementation.author.implementation;

import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.author.IAuthorDao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class AuthorDao extends AbstractDaoController<Author,Integer> implements IAuthorDao {

    public AuthorDao() {
        super();
    }

    @Override
    public List<Author> parseResultSet(ResultSet rs,String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Author> result = new LinkedList<Author>();
        try {
            while (rs.next()) {
                Author author = new Author();
                author.setAuthorName(rs.getString("lib_author_name"+dbDataLanguage));
                author.setAuthorFemale(rs.getString("lib_author_female"+dbDataLanguage));
                author.setAuthorBiography(rs.getString("lib_author_biography"+dbDataLanguage));
                author.setAuthorPatronymic(rs.getString("lib_author_patronymic"+dbDataLanguage));
                author.setId(rs.getInt("lib_author_id"));
                result.add(author);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] rowThatNeedUpdate=parameterMap.get("checkAuthor");
        String[] authorName=parameterMap.get("authorName");
        String[] authorSurname = parameterMap.get("authorSurname");
        String[] authorPatronymic = parameterMap.get("authorPatronymic");
        String[] authorBiography = parameterMap.get("authorBiography");
        String[] authorId = parameterMap.get("authorId");
        String sql = "UPDATE lib_author SET lib_author_name"+dbDataLanguage+" =?,lib_author_female"+dbDataLanguage+"=?,lib_author_patronymic"+dbDataLanguage+"=?,lib_author_biography"+dbDataLanguage+"=? WHERE lib_author_id=?";
        for(String i:rowThatNeedUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,authorName[j]);
            stmt.setString(2,authorSurname[j]);
            stmt.setString(3,authorPatronymic[j]);
            stmt.setString(4,authorBiography[j]);
            stmt.setInt(5,Integer.parseInt(authorId[j]));
            stmt.execute();

        }

    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete=parameterMap.get("checkAuthor");
        String[] id=parameterMap.get("authorId");
        String sql = "DELETE FROM lib_author WHERE lib_author_id=?";
        for(String i:rowThatNeedDelete)
        {
            int j= Integer.valueOf(i);
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }

    @Override
    public Author getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Author update(Author author) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    public void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] authorName=parameterMap.get("authorName");
        String[] authorSurname = parameterMap.get("authorSurname");
        String[] authorPatronymic = parameterMap.get("authorPatronymic");
        String[] authorBiography = parameterMap.get("authorBiography");
        String[] authorNameEn=parameterMap.get("authorNameEn");
        String[] authorSurnameEn = parameterMap.get("authorSurnameEn");
        String[] authorPatronymicEn = parameterMap.get("authorPatronymicEn");
        String[] authorBiographyEn = parameterMap.get("authorBiographyEn");
        String sql = "INSERT INTO lib_author(lib_author_name,lib_author_female,lib_author_patronymic,lib_author_biography,lib_author_name_en,lib_author_female_en,lib_author_patronymic_en,lib_author_biography_en)"+
                " VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,authorName[0]);
        stmt.setString(2,authorSurname[0]);
        stmt.setString(3,authorPatronymic[0]);
        stmt.setString(4,authorBiography[0]);
        stmt.setString(5,authorNameEn[0]);
        stmt.setString(6,authorSurnameEn[0]);
        stmt.setString(7,authorPatronymicEn[0]);
        stmt.setString(8,authorBiographyEn[0]);
            stmt.execute();

    }
}
