package main.com.bsuir.library.dao.implementation.news.implementation;

import main.com.bsuir.library.bean.News;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.news.INewsDao;

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
public class NewsDao extends AbstractDaoController<News, Integer> implements INewsDao {
    @Override
    public News getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public News update(News news) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<News> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<News> result = new LinkedList<News>();
        try {
            while (rs.next()) {
                News news = new News();
                news.setHeader(rs.getString("lib_news_header" + dbDataLanguage));
                news.setId(rs.getInt("lib_news_id"));
                news.setPublicateDate(rs.getTimestamp("lib_news_date"));
                news.setText(rs.getString("lib_news_text" + dbDataLanguage));
                result.add(news);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] newsHeader = parameterMap.get("newsHeader");
        String[] newsHeaderEn = parameterMap.get("newsHeaderEn");
        String[] newsText = parameterMap.get("newsText");
        String[] newsTextEn=parameterMap.get("newsTextEn");
        String sql = "INSERT INTO lib_news(lib_news_header,lib_news_text,lib_news_header_en,lib_news_text_en) VALUES (" +
                "?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,newsHeader[0]);
        stmt.setString(2,newsText[0]);
        stmt.setString(3,newsHeaderEn[0]);
        stmt.setString(4,newsTextEn[0]);
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id = parameterMap.get("newsId");
        String[] newsHeader = parameterMap.get("newsHeader");
        String[] newsText = parameterMap.get("newsText");
        String[] checkedBook = parameterMap.get("checkNews");
        String sql = "UPDATE lib_news SET lib_news_header"+dbDataLanguage+"=?,lib_news_text"+dbDataLanguage+"=? WHERE lib_news_id=?";
        for (String i : checkedBook) {
            int j = Integer.valueOf(i) - 1;
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, newsHeader[j]);
            stmt.setString(2, newsText[j]);
            stmt.setInt(3, Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkNews");
        String[] id=parameterMap.get("newsId");
        String sql = "DELETE FROM lib_news WHERE lib_news_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
