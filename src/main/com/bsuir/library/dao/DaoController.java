package main.com.bsuir.library.dao;

import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.IDao;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public interface DaoController<Entity, PrimaryKey> extends IDao {
    Entity getByPrimaryKey(PrimaryKey key) throws DaoException;
    Entity update(Entity entity) throws DaoException;
    boolean delete(PrimaryKey key) throws DaoException;
    void insert(Map<String, String[]> parameterMap) throws DaoException,SQLException;
    void updateAll(Map<String, String[]> parameterMap, String dbDataLanguage) throws DaoException, SQLException;
    void deleteAll(Map<String, String[]> parameterMap)throws DaoException, SQLException;
}