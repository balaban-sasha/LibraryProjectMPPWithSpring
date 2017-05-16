package main.com.bsuir.library.command.exception;

/**
 * Created by Саша on 20.04.2017.
 */
public class CommandException extends Exception {
    public CommandException(Exception e) {
        super(e);
    }
}
