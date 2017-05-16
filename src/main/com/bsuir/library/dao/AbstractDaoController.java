package main.com.bsuir.library.dao;

import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.Map;


public abstract class AbstractDaoController<Entity, PrimaryKey> implements DaoController<Entity, PrimaryKey> {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    protected AbstractDaoController() {
    }

    protected abstract List<Entity> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException, DataControllerException;

    private String url;
    private String user;
    private String password;

    public void startConnectionToDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public List<Entity> getDataFromDB(String sql, String dbDataLanguage) throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return parseResultSet(rs, dbDataLanguage);

        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (!connection.isClosed())
                connection.close();
        }
    }

    public void updateAll(Map<String, String[]> parameterMap, String dbDataLanguage) throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            updateTable(parameterMap, connection,dbDataLanguage);

        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (!connection.isClosed())
                connection.close();
        }

    }

    public void insert(Map<String, String[]> parameterMap) throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            insertToTable(parameterMap, connection);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (!connection.isClosed())
                connection.close();
        }
    }

    protected abstract void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException, NoSuchAlgorithmException;

    protected abstract void updateTable(Map<String, String[]> parameterMap, Connection stmt, String dbDataLanguage) throws SQLException;

    public void deleteAll(Map<String, String[]> parameterMap)throws DaoException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            deleteFromTable(parameterMap, connection);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (!connection.isClosed())
                connection.close();
        }}

    protected abstract void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException;

    public void updateRequest(String s) throws SQLException, DaoException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            updateData(s,connection);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            if (!connection.isClosed())
                connection.close();
        }

    }

    protected void updateData(String s,Connection connection) throws SQLException {
    }

    public void updateFileById(Map<String, String[]> parameterMap, String dbDataLanguage, int id) {
    }
}
