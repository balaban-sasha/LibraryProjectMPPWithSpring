package main.com.bsuir.library.command;

import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.data.AbstractBeanController;
import main.com.bsuir.library.controller.data.BeanControllerFactory;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 25.04.2017.
 */
public class AuthCommand extends Command {
    public AuthCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException, NoSuchAlgorithmException {
        List<User> entities=null;
        String tableName = parameterMap.get("table")[0];
        BeanControllerFactory beanControllerFactory = new BeanControllerFactory();
        AbstractBeanController beanController = beanControllerFactory.getBeanController(tableName);
        entities =beanController.getDataIfExist(parameterMap.get("userLogin")[0],parameterMap.get("userPassword")[0]);
        if(!entities.isEmpty())
        {
            int userId = 0;
            for(User entity : entities)
            {
                userId=entity.getId();
            }
            sessionController.setUserId(userId);
            sessionController.setRequest("success");
        }
        return null;
    }
}
