
package oop.ex7.common;

import oop.ex7.commands.MethodDeclaration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nadav on 10/06/14.
 */
public class Scope {
	
	private List< Scope > subScopes;
	private HashMap< String, Variable > vars;
	private TermType returnType;
	private boolean returnedValue;
	
	private Scope parentScope;
	
	public Scope() {
		subScopes = new ArrayList< Scope >();
		vars = new HashMap< String, Variable >();
		returnType = null;
		returnedValue = false;
	}
	
	public Scope( Scope scope ) {
		this();
		parentScope = scope;
	}
	
	public Scope( Scope scope, Command command ) {
		this( scope );
		if ( command.getClass() == MethodDeclaration.class ) {
			returnType = ( (MethodDeclaration) command ).getReturnType();
		}
	}
	
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
	
	public Variable getVar( String name ) {
		if ( getVars().containsKey( name ) )
			return getVars().get( name );
		else if ( parentScope != null )
			return parentScope.getVar( name );
		else
			return null;
	}
	
	public TermType getReturnType() {
		return returnType != null ? returnType
				: ( parentScope.getReturnType() != null
						? parentScope.getReturnType() : null );
	}
	
	public boolean isReturnedValue() {
		return returnedValue;
	}
	
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
