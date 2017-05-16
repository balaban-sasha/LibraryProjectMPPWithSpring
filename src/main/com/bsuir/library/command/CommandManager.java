package main.com.bsuir.library.command;

import java.util.LinkedList;

/**
 * Created by Саша on 18.04.2017.
 */
public class CommandManager {
    private LinkedList<Command>commandHistory = new LinkedList<Command>();
    public void insert(Command command)
    {
        recordHistory(command);
    }
    public void delete(Command command)
    {
        recordHistory(command);
    }
    public void update(Command command)
    {
        recordHistory(command);
    }
    public void create(Command command)
    {
        recordHistory(command);
    }
    private void recordHistory(Command command)
    {
        commandHistory.addFirst(command);
    }
    public static void printHistory(CommandManager commandManager)
    {
        System.out.println(commandManager.commandHistory.toString());
    }
}
