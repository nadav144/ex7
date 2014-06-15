
package oop.ex7.common;

import oop.ex7.commands.MethodDeclaration;
import java.util.HashMap;

/**
 * Represent a scope in the program. a scope is a isolated sandbox of parameters and context.
 * the scope can get data from his parent scope, but not from other scopes.
 * each scope contains all the necessary data for the scope operation and execution in the program
 */
public class Scope {
	
	private HashMap< String, Variable > vars;
	private TermType returnType;
	private boolean returnedValue;
	
	private Scope parentScope;

    /**
     * Intialize a new instance of Scope
     */
	public Scope() {
		vars = new HashMap< String, Variable >();
		returnType = null;
		returnedValue = false;
	}

    /**
     * Initalize a new instance of Scope
     * @param scope parent scope
     */
	public Scope( Scope scope ) {
		this();
		parentScope = scope;
	}

    /**
     * Intialize a new instance of scope
     * this constuctor will calculate the return type if the scope is crated by any
     * return type command
     * @param scope parent scope
     * @param command command that created the scope
     */
	public Scope( Scope scope, Command command ) {
		this( scope );
		if ( command.getClass() == MethodDeclaration.class ) {
			returnType = ( (MethodDeclaration) command ).getReturnType();
		}
	}

    /**
     * @return the main scope.
     */
	public MainScope getMainScope() {
		
		if ( getClass() == MainScope.class )
			return (MainScope) this;
		
		Scope parent = parentScope;
		while ( parent != null && parent.getClass() != MainScope.class ) {
			parent = parent.parentScope;
		}
		
		if ( parent.getClass() == MainScope.class )
			return (MainScope) parent;
		return null;
		
	}
	
	/**
	 * @return the vars
	 */
	public HashMap< String, Variable > getVars() {
		return vars;
	}

    /**
     * @param name var name
     * @return the var matching that name from that scope, or from any parent scope.
     *          will return null if no param was found.
     */
	public Variable getVar( String name ) {
		if ( getVars().containsKey( name ) )
			return getVars().get( name );
		else if ( parentScope != null )
			return parentScope.getVar( name );
		else
			return null;
	}

    /**
     * @return the return type of that scope
     */
	public TermType getReturnType() {
		return returnType != null ? returnType
				: ( parentScope.getReturnType() != null
						? parentScope.getReturnType() : null );
	}

    /**
     * @return True if value was returned in the scope. False otherwise
     */
	public boolean isReturnedValue() {
		return returnedValue;
	}

    /**
     * @param returned True if value was return. False otherwise. sets the value.
     */
	public void setReturnedValue( boolean returned ) {
		returnedValue = returned;
	}
	
	/**
	 * @return the parentScope
	 */
	public Scope getParentScope() {
		return parentScope;
	}
}
