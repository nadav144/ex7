package oop.ex7.common;


/**
 * Created by Nadav on 10/06/14.
 */
public interface Command{

	public ValidationResult isValid(Scope scope);
    public boolean isScope();
    public void updateScope(Scope scope);
}
