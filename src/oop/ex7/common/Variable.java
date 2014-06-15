/**
 * 
 */

package oop.ex7.common;


/**
 * Represent a variable*
 */
public class Variable implements Command {
	
	private TermType type;
	private String typeString;
	private String name;
	private boolean inited;


    /**
     * Initzlie a new instance of Variable
     * @param type type of the variable
     * @param name name of the variable
     * @throws IllegalArgumentException if type cannot be parsed
     */
	public Variable( String type, String name ) throws IllegalArgumentException{
		
		typeString = type;
		this.type = TermType.parse( type );
		this.name = name;
		this.inited = false;
		
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
	public TermType getType() {
		return type;
	}
	
	/**
	 * @return the isArray
	 */
	public boolean isArray() {
		return getType().isArray();
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
	 * @param inited
	 *            the inited to set
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
	public ValidationResult isValid(Scope scope ) {
		
		ValidationResult result = new ValidationResult();
		if ( getType() == null ) {
			result.setSuccessful( false );
			result.append( String.format( "Invalid variable type: '%s'",
					this.typeString ) );
		}
		
		if ( getName() == null
				|| !getName().matches( RegexUtils.VARIABLE_NAME_PATTERN ) ) {
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

    @Override
    public void updateScope(Scope scope) {
        // nothing to do here
    }

}
