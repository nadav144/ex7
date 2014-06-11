package oop.ex7.common;

import java.util.LinkedList;
import java.util.regex.MatchResult;


public class MethodDecleration implements Command {
	private LinkedList< Variable > params;
	private ReturnType returnType;
	private String name;

    public MethodDecleration(MatchResult decleration){
        name = decleration.group(2);
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
	
	
}
