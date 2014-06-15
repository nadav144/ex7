package oop.ex7.Exceptions;

/**
 * Represents a command syntax error.
 */
public class CommandSyntaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommandSyntaxException(String message){
        super(message);
    }
}
