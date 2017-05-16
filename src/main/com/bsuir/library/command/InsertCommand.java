package main.com.bsuir.library.command;

import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.factory.DAOFactory;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Саша on 18.04.2017.
 */
public class InsertCommand extends Command {
    public InsertCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) {
        List<Entity> entities = null;
        DAOFactory daoFactory = new DAOFactory();
        String[] tablesName = parameterMap.get("table");
        if ((tablesName!=null)&&(tablesName[0].equals("Chat")) && (!parameterMap.containsKey("chatSenderId") && (sessionController.getUserId() != 0))) {
            int userId = sessionController.getUserId();
            String[] userIds = new String[1];
            userIds[0] = String.valueOf(userId);
            parameterMap.put("chatSenderId", userIds);
        }
        AbstractDaoController controller = daoFactory.getController(tablesName[0]);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        controller.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        try {
            controller.insert(parameterMap);
        } catch (Exception e) {
            new CommandException(e);
        }
        sessionController.setRequest("success");
        commandManager.insert(this);
        return null;
    }
}
