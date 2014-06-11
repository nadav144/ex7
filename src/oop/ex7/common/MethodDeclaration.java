package oop.ex7.common;

import oop.ex7.ValidationResult;

import java.util.LinkedList;
import java.util.regex.MatchResult;


public class MethodDeclaration implements Command {
	private LinkedList< Variable > params;
	private ReturnType returnType;
	private String name;

    public MethodDeclaration(MatchResult declaration){
        // TODO: Insert parameters and return type information
        name = declaration.group(2);
    }

	/**
	 * @return the params
	 */
	public LinkedList< Variable > getParams() {
		return params;
	}
	
	/**
	 * @return the returnType
	 */
	public ReturnType getReturnType() {
		return returnType;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


    @Override
    public boolean isScope() {
        return false;
    }

    @Override
    public ValidationResult isValid(String expression, Scope scope) {
        return null;
    }
}
