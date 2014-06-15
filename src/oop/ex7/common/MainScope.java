
package oop.ex7.common;

import oop.ex7.Exceptions.CommandSyntaxException;
import oop.ex7.commands.MethodDeclaration;
import java.util.HashMap;

/**
 * Represents the main scope of the program. each program has a single main scope. the scope extends scope, and
 * contains methods declarions
 */
public class MainScope extends Scope {
	
	private HashMap< String, MethodDeclaration > methodDeclarations;

    /**
     * Initailize a new instance of MainScope
     */
	public MainScope() {
		super();
		methodDeclarations = new HashMap< String, MethodDeclaration >();
	}

    /**
     * Adds a new method declartion for this scope
     * @param declaration method to add
     * @throws CommandSyntaxException if method already exists in the scope
     */
	public void addMethod( MethodDeclaration declaration ) throws CommandSyntaxException{
		if ( methodDeclarations.containsKey( declaration.getName() ) ) {
			throw new CommandSyntaxException( "Method defined twice" );
		}
		methodDeclarations.put( declaration.getName(), declaration );
	}

    /**
     * @param name name of method
     * @return the method declaration by that name
     */
	public MethodDeclaration getMethod( String name ) {
		return methodDeclarations.get( name );
	}
	
}
