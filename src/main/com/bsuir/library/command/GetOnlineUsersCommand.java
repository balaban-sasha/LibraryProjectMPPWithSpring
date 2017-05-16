package main.com.bsuir.library.command;

import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.data.AbstractBeanController;
import main.com.bsuir.library.controller.data.BeanControllerFactory;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 06.05.2017.
 */
public class GetOnlineUsersCommand extends Command {
    public GetOnlineUsersCommand(CommandManager commandManager) {
        super();
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
        String[] tableName = parameterMap.get("tableName");
            Timestamp date = null;
        List<Entity> entities = null;
        if ((tableName != null)) {
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName[0]);
            entities = getEntityList(beanController, dbDataLanguage,date);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, Timestamp date) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getContentByTime(dbDataLanguage,date);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
