package oop.ex7.Exceptions;

/**
 * Represent an expression syntax error
 * */
public class ExpressionSyntaxException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExpressionSyntaxException(String message){
        super(message);
    }
}
