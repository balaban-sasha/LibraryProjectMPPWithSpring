package main.com.bsuir.library.dao.implementation.comment.implementation;

import main.com.bsuir.library.bean.Comment;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.comment.ICommentDao;

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
public class CommentDao extends AbstractDaoController<Comment,Integer> implements ICommentDao {
    @Override
    public Comment getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Comment> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException {
        LinkedList<Comment> result = new LinkedList<Comment>();
        try {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setBookId(rs.getInt("lib_book_id"));
                comment.setId(rs.getInt("lib_comment_id"));
                comment.setSenderId(rs.getInt("lib_sender_id"));
                comment.setText(rs.getString("lib_comment_text"+dbDataLanguage));
                comment.setPublicateDate(rs.getTimestamp("lib_comment_date"));
                result.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }


    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] commentText=parameterMap.get("commentText");
        String[] commentTextEn = parameterMap.get("commentTextEn");
        String[] commentBookId = parameterMap.get("commentBookId");
        String[] comentSenderId = parameterMap.get("comentSenderId");
        String sql = "INSERT INTO lib_comment(lib_comment_text,lib_comment_text_en,lib_book_id,lib_sender_id)VALUES (?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,commentText[0]);
        stmt.setString(2,commentTextEn[0]);
        stmt.setInt(3,Integer.parseInt(commentBookId[0]));
        stmt.setInt(4,Integer.parseInt(comentSenderId[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("commentId");
        String[] commentText=parameterMap.get("commentText");
        String[] commentBookId = parameterMap.get("commentBookId");
        String[] comentSenderId = parameterMap.get("comentSenderId");
        String[] rowThatNeedToUpdate = parameterMap.get("checkComment");
        String sql = "UPDATE lib_comment SET lib_comment_text"+dbDataLanguage+"=?,lib_book_id=?,lib_sender_id=? WHERE lib_comment_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,commentText[j]);
            stmt.setInt(2,Integer.parseInt(commentBookId[j]));
            stmt.setInt(3,Integer.parseInt(comentSenderId[j]));
            stmt.setInt(4,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkComment");
        String[] id=parameterMap.get("commentId");
        String sql = "DELETE FROM lib_comment WHERE lib_comment_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
