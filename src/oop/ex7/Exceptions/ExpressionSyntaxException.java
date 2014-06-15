package oop.ex7.Exceptions;

/**
 * Represent an expression syntax error
 * */
public class ExpressionSyntaxException extends Exception {

    public ExpressionSyntaxException(String message){
        super(message);
    }
}
