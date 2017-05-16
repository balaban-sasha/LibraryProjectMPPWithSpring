package main.com.bsuir.library.dao.implementation.newsComment.implementation;

import main.com.bsuir.library.bean.NewsComment;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.newsComment.INewsCommentDao;

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
public class NewsCommentDao extends AbstractDaoController<NewsComment,Integer> implements INewsCommentDao {
    @Override
    public NewsComment getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public NewsComment update(NewsComment newsComment) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }


    @Override
    protected List<NewsComment> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<NewsComment> result = new LinkedList<NewsComment>();
        try {
            while (rs.next()) {
                NewsComment newsComment = new NewsComment();
                newsComment.setId(rs.getInt("lib_news_comment_id"));
                newsComment.setNewsId(rs.getInt("lib_news_id"));
                newsComment.setPublicateDate(rs.getTimestamp("lib_news_comment_date"));
                newsComment.setText(rs.getString("lib_news_comment_text"+dbDataLanguage));
                newsComment.setUserId(rs.getInt("lib_user_id"));
                result.add(newsComment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] newsCommentText=parameterMap.get("newsCommentText");
        String[] newsCommentTextEn = parameterMap.get("newsCommentTextEn");
        String[] newsCommentNewsId = parameterMap.get("newsCommentNewsId");
        String[] newsCommentUserId = parameterMap.get("newsCommentUserId");
        String sql = "INSERT INTO lib_news_comment(lib_news_comment_text,lib_news_comment_text_en,lib_news_id,lib_user_id)VALUES (?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,newsCommentText[0]);
        stmt.setString(2,newsCommentTextEn[0]);
        stmt.setInt(3,Integer.parseInt(newsCommentNewsId[0]));
        stmt.setInt(4,Integer.parseInt(newsCommentUserId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {

        String[] id=parameterMap.get("newsCommentId");
        String[] newsCommentText=parameterMap.get("newsCommentText");
        String[] newsCommentNewsId = parameterMap.get("newsCommentNewsId");
        String[] newsCommentUserId = parameterMap.get("newsCommentUserId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkNewsComment");
        String sql = "UPDATE lib_news_comment SET lib_news_comment_text"+dbDataLanguage+"=?,lib_news_id=?,lib_user_id=? WHERE lib_news_comment_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,newsCommentText[j]);
            stmt.setInt(2,Integer.parseInt(newsCommentNewsId[j]));
            stmt.setInt(3,Integer.parseInt(newsCommentUserId[j]));
            stmt.setInt(4,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection)throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkNewsComment");
        String[] id=parameterMap.get("newsCommentId");
        String sql = "DELETE FROM lib_news_comment WHERE lib_news_comment_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
