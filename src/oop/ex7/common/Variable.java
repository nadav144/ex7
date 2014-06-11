/**
 * 
 */
	
package oop.ex7.common;

import oop.ex7.ValidationResult;

/**
 * @author Jirsch
 * 
 */
public class Variable implements Command {
	
	
	

	private VarType type;
	private String typeString;
	private boolean isArray;
	private String name;
	private boolean inited;
	
	public Variable( String type, String name ) {
		
		typeString = type;
		
		if ( type != null && type.endsWith( "[]" ) ) {
			type = type.substring( 0, type.length() - 2 );
			isArray = true;
		}
		
		this.type = VarType.parse( type );
		this.name = name;
		this.inited=false;
		
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
	 * @param inited the inited to set
	 */
	public void setInited( boolean inited ) {
		this.inited = inited;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String,
	 * oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		
		ValidationResult result = new ValidationResult();
		if ( getType() == null ) {
			result.setSuccessful( false );
			result.append( String.format( "Invalid variable type: '%s'",
					this.typeString ) );
		}
		
		if ( getName() == null || !getName().matches( "[_a-zA-Z]\\w*" ) ) {
			result.setSuccessful( false );
			result.append( String.format( "Illegal variable name: '%s'",
					getName() ) );
		}
		return result;
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.Command#isScope()
	 */
	@Override
	public boolean isScope() {
		return false;
	}
	
}
