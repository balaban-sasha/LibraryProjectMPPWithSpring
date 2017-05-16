package main.com.bsuir.library.dao.implementation.genre.implementation;

import main.com.bsuir.library.bean.Genre;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.genre.IGenreDao;

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
public class GenreDao extends AbstractDaoController<Genre,Integer> implements IGenreDao {
    @Override
    public Genre getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Genre update(Genre genre) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Genre> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<Genre> result = new LinkedList<Genre>();
        try {
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setGenre(rs.getString("lib_genre_name"+dbDataLanguage));
                genre.setId(rs.getInt("lib_genre_id"));
                result.add(genre);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] genreGenre=parameterMap.get("genreGenre");
        String[] genreGenreEn = parameterMap.get("genreGenreEn");
        String sql = "INSERT INTO lib_genre(lib_genre_name,lib_genre_name_en)VALUES (?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,genreGenre[0]);
        stmt.setString(2,genreGenreEn[0]);
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("genreId");
        String[] genreGenre=parameterMap.get("genreGenre");
        String[] rowThatNeedToUpdate = parameterMap.get("checkGenre");
        String sql = "UPDATE lib_genre SET lib_genre_name"+dbDataLanguage+"=? WHERE lib_genre_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,genreGenre[j]);
            stmt.setInt(2,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkGenre");
        String[] id=parameterMap.get("genreId");
        String sql = "DELETE FROM lib_genre WHERE lib_genre_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
