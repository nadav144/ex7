package oop.ex7.Exceptions;

/**
 * Represents a command syntax error.
 */
public class CommandSyntaxException extends Exception {

    public CommandSyntaxException(String message){
        super(message);
    }
}
