package oop.ex7.common;

import oop.ex7.ValidationResult;

/**
 * Created by Nadav on 10/06/14.
 */
public interface Command{

	public ValidationResult isValid(String expression, Scope scope);
    public boolean isScope();
}
