
package oop.ex7.common;

import oop.ex7.commands.MethodDeclaration;
import oop.ex7.commands.VariableDeclarationCommand;
import java.util.HashMap;

/**
 * Created by Nadav on 10/06/14.
 */
public class MainScope extends Scope {
	
	private HashMap< String, MethodDeclaration > methodDeclarations;
	private HashMap< String, VariableDeclarationCommand > varDeclarations;
	
	public MainScope() {
		super();
		methodDeclarations = new HashMap< String, MethodDeclaration >();
		varDeclarations = new HashMap< String, VariableDeclarationCommand >();
	}
	
	public void addMethod( MethodDeclaration declaration ) throws Exception {
		if ( methodDeclarations.containsKey( declaration.getName() ) ) {
			throw new Exception( "Method defined twice" );
		}
		methodDeclarations.put( declaration.getName(), declaration );
	}
	
	public MethodDeclaration getMethod( String name ) {
		return methodDeclarations.get( name );
	}
	
	public void addVariable( VariableDeclarationCommand var ) throws Exception {
		if ( varDeclarations.containsKey( var.getVar().getName() ) ) {
			throw new Exception( "Global Variable defined twice" );
		}
		varDeclarations.put( var.getVar().getName(), var );
	}
	
	public VariableDeclarationCommand getVariable( String name ) {
		return varDeclarations.get( name );
	}
	
}
