package main.com.bsuir.library.command;

import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.command.exception.CommandException;
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
public class CloseSessionCommand extends Command{
        public CloseSessionCommand(CommandManager commandManager) {
            this.commandManager = commandManager;
        }

        @Override
        List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws DaoException, CommandException, DataControllerException, SQLException {
                sessionController.setUserId(0);
                sessionController.setRequest("success");
            return null;
        }
}
