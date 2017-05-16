package main.com.bsuir.library.command;

import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.data.AbstractBeanController;
import main.com.bsuir.library.controller.data.BeanControllerFactory;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 04.05.2017.
 */
public class GetChatMessagesCommand extends Command {
    public GetChatMessagesCommand(CommandManager commandManager) {
        super();
    }

    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, DataControllerException, SQLException {
        String tableName = parameterMap.get("tableName")[0];
        List<Entity> entities = null;
        if ((tableName != null)) {
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
            int lastId = 0;
            if (parameterMap.containsKey("id"))
                lastId = Integer.parseInt(parameterMap.get("id")[0]);
            entities = getEntityList(beanController, dbDataLanguage, lastId);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, int lastId) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getContentByLastId(dbDataLanguage,lastId);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
