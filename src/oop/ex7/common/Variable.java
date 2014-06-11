/**
 * 
 */

package oop.ex7.common;

import java.util.regex.MatchResult;
import oop.ex7.ValidationResult;

/**
 * @author Jirsch
 * 
 */
public class Variable implements Command {
	
	private VarType type;
	private boolean isArray;
	private String name;
	private boolean inited;
	private int lineDeclared;
	
	public Variable(MatchResult declaration){
		// int a
		// int[] a
		
		String type=declaration.group(1) ;
		if (type.endsWith( "[]" )){
			type=type.substring( 0, type.length()-2 );
			isArray=true;
		}
		
		this.type=VarType.parse( );
		name=declaration.group(2);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object arg0 ) {
		if ( arg0 == null ) {
			return false;
		}
		if ( arg0 instanceof String ) {
			return getName().equals( arg0 );
		}
		if ( arg0 instanceof Variable ) {
			return getName().equals( ( (Variable) arg0 ).getName() );
		}
		return false;
	}
	
	/**
	 * @return the type
	 */
	public VarType getType() {
		return type;
	}
	
	/**
	 * @return the isArray
	 */
	public boolean isArray() {
		return isArray;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the inited
	 */
	public boolean isInited() {
		return inited;
	}
	
	/**
	 * @return the lineDeclared
	 */
	public int getLineDeclared() {
		return lineDeclared;
	}
	

	/* (non-Javadoc)
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String, oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see oop.ex7.common.Command#isScope()
	 */
	@Override
	public boolean isScope() {
		return false;
	}

}
