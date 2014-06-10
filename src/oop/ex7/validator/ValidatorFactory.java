/**
 * 
 */
package oop.ex7.validator;

import oop.ex7.common.CommandValidator;


/**
 * @author Jirsch
 *
 */
public class ValidatorFactory {
	
	private static ValidatorFactory instance;
	
	/**
	 * 
	 */
	private ValidatorFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static ValidatorFactory instance(){
		if (instance==null){
			instance=new ValidatorFactory();
		}
		return instance;
	}
	
	public CommandValidator create(String expression){
		return null;
	}
}
