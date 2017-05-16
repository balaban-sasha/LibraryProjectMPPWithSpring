package main.com.bsuir.library.dao.implementation.newsPage.implementation;

import main.com.bsuir.library.bean.NewsPage;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.newsPage.INewsPageDao;

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
public class NewsPageDao extends AbstractDaoController<NewsPage, Integer> implements INewsPageDao {
    @Override
    public NewsPage getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public NewsPage update(NewsPage newsPage) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<NewsPage> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<NewsPage> result = new LinkedList<NewsPage>();
        try {
            while (rs.next()) {
                NewsPage newsPage = new NewsPage();
                newsPage.setId(rs.getInt("lib_news_page_id"));
                newsPage.setNewsId(rs.getInt("lib_news_id"));
                newsPage.setNewsStatus(rs.getInt("lib_news_page_status"));
                newsPage.setSectionId(rs.getInt("lib_section_id"));
                result.add(newsPage);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] newsPageStatus = parameterMap.get("newsPageStatus");
        String[] newsPageNewsId = parameterMap.get("newsPageNewsId");
        String[] newsPageSectionId = parameterMap.get("newsPageSectionId");
        String sql = "INSERT INTO lib_news_page(lib_news_page_status,lib_news_id,lib_section_id)" +
                "VALUES (?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(newsPageStatus[0]));
        stmt.setInt(2, Integer.parseInt(newsPageNewsId[0]));
        stmt.setInt(3, Integer.parseInt(newsPageSectionId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id = parameterMap.get("newsPageId");
        String[] newsPageStatus = parameterMap.get("newsPageStatus");
        String[] newsPageNewsId = parameterMap.get("newsPageNewsId");
        String[] newsPageSectionId = parameterMap.get("newsPageSectionId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkNewsPage");
        String sql = "UPDATE lib_news_page SET lib_news_page_status=?,lib_news_id=?,lib_section_id=? WHERE lib_news_page_id=?";
        for (String i : rowThatNeedToUpdate) {
            int j = Integer.valueOf(i) - 1;
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(newsPageStatus[j]));
            stmt.setInt(2, Integer.parseInt(newsPageNewsId[j]));
            stmt.setInt(3, Integer.parseInt(newsPageSectionId[j]));
            stmt.setInt(4, Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkNewsPage");
        String[] id = parameterMap.get("newsPageId");
        String sql = "DELETE FROM lib_news_page WHERE lib_news_page_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j - 1]));
            stmt.execute();

        }
    }
}
