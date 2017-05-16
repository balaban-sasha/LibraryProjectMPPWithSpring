package main.com.bsuir.library.command;

import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.controller.exception.DataControllerException;
import main.com.bsuir.library.dao.exception.DaoException;

import javax.swing.text.html.parser.Entity;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 18.04.2017.
 */
public class CommandInvoker {
    private HashMap<String,Command> commandMap = new HashMap<String,Command>();
    public CommandInvoker(CommandManager commandManager)
    {
        commandMap.put("insert",new InsertCommand(commandManager));
        commandMap.put("update",new UpdateCommand(commandManager));
        commandMap.put("updateUserOnline",new UpdateUserOnlineCommand(commandManager));
        commandMap.put("delete",new DeleteCommand(commandManager));
        commandMap.put("GetAll",new CreateCommand(commandManager));
        commandMap.put("GetChatMessages",new GetChatMessagesCommand(commandManager));
        commandMap.put("getOnlineUsers",new GetOnlineUsersCommand(commandManager));
        commandMap.put("auth",new AuthCommand(commandManager));
        commandMap.put("closeSession",new CloseSessionCommand(commandManager));
        commandMap.put("getOneSection",new GetOneSectionDataCommand(commandManager));
        commandMap.put("getLimitedNumber",new GetLimitedNumberCommand(commandManager));
        commandMap.put("updateUserAvatar",new UpdateUserAvatarCommand(commandManager));
        commandMap.put("getUserById",new GetUserByIdCommand(commandManager));
    }
    public Command getCommand(String action)
    {
        return commandMap.get(action);
    }
    public List<Entity> invoke(String command, Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws CommandException, DaoException, DataControllerException, SQLException, NoSuchAlgorithmException {
            return commandMap.get(command).invoke(parameterMap,dbDataLanguage,sessionController);
    }
}
