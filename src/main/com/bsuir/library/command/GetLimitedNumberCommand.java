package main.com.bsuir.library.command;

import com.sun.javafx.image.IntPixelGetter;
import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.data.AbstractBeanController;
import main.com.bsuir.library.controller.data.BeanControllerFactory;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 07.05.2017.
 */
public class GetLimitedNumberCommand extends Command {
    public GetLimitedNumberCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException, NoSuchAlgorithmException {
        String[] tableName = parameterMap.get("tableName");
        String[] limit = parameterMap.get("limit");
        String[] pages = parameterMap.get("page");
        int page=1;
        if(pages!=null)
            page= Integer.valueOf(pages[0]);
        int dataLimit = 10;
        if (limit != null)
            dataLimit = Integer.valueOf(limit[0]);
        List<Entity> entities = null;
        if ((tableName != null)) {
            String[] ids=null;
            int id=0;
            if(parameterMap.containsKey("Id"))
                ids=parameterMap.get("Id");
            if(ids!=null)
                id=Integer.valueOf(ids[0]);
            BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
            AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName[0]);
            entities = getEntityList(beanController, dbDataLanguage, tableName[0], dataLimit,id,page);
        }
        return entities;
    }

    List<Entity> getEntityList(AbstractBeanController beanController, String dbDataLanguage, String s, int dataLimit, int id, int page) throws DaoException, DataControllerException, SQLException {
        List<Entity> entityList = beanController.getLimitedData(dbDataLanguage, s, dataLimit,id,page);
        //request.getRequestDispatcher("tables.jsp").forward(request, response);}
        return entityList;
    }
}
