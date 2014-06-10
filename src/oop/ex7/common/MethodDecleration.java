package oop.ex7.common;

import java.util.LinkedList;


public class MethodDecleration implements Command {
	private LinkedList< Variable > params;
	private ReturnType returnType;
	private String name;
	
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
