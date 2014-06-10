/**
 * 
 */
package oop.ex7.common;


import oop.ex7.ValidationResult;

/**
 * @author Jirsch
 *
 */
public interface CommandValidator {



	public ValidationResult isValid(String expression, Scope scope);
	
}
