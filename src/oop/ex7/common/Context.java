/**
 * 
 */
package oop.ex7.common;

import java.io.LineNumberReader;
import java.util.Stack;


/**
 * @author Jirsch
 *
 */
public class Context {
	private Stack< Variable > stack;
	private LineNumberReader reader;

	
	/**
	 * 
	 */
	public Context() {
		// TODO Auto-generated constructor stub
	}
	

	
	/**
	 * @return the stack
	 */
	public Stack< Variable > getStack() {
		return stack;
	}

	
	/**
	 * @return the reader
	 */
	public LineNumberReader getReader() {
		return reader;
	}

	
}
