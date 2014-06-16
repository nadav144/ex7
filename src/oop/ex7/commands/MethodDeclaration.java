
package oop.ex7.commands;

import oop.ex7.common.*;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.regex.MatchResult;

public class MethodDeclaration implements Command {
	
	private LinkedList< Variable > params;
	private TermType returnType;
	private String[] declaration;
	private int numOfParams;
	
	public MethodDeclaration( MatchResult declaration ) throws IllegalArgumentException{
		this.declaration = new String[3];
		
		this.declaration[0] = (declaration.group(2) == null) ? declaration.group( 1 ) : declaration.group(1)
                + declaration.group(2);
		this.declaration[1] = declaration.group( 3 );
		this.declaration[2] = declaration.group( 4 );
		
		returnType = TermType.parse( this.declaration[0] );

		
		this.params = new LinkedList< Variable >();
		
		if ( !this.declaration[2].trim().equals( "" ) ) {
			String paramsString = this.declaration[2].trim();
			String[] params = paramsString.split( "," );
			this.numOfParams = params.length;
			List< MatchResult > matchResults =
					RegexUtils.Match( RegexUtils.PARAM_PATTERN, params );
			for ( MatchResult result : matchResults ) {
				
				this.params.add( new Variable( (result.group(2) == null) ? result.group( 1 ) :
                        result.group(1) + result.group(2), result.group( 3 ) ) );
			}
		}
		else {
			this.numOfParams = 0;
		}
		
	}
	
	/**
	 * @return the params
	 */
	public LinkedList< Variable > getParams() {
		return params;
	}
	
	/**
	 * @return the returnType
	 */
	public TermType getReturnType() {
		return returnType;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return declaration[1];
	}
	
	@Override
	public boolean isScope() {
		return true;
	}
	
	@Override
	public void updateScope( Scope scope ) {
		for ( Variable var : getParams() ) {
			var.setInited(true);
			scope.getVars().put(var.getName(), var);
		}
	}
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();
		if ( getReturnType() == null ) {
			result.fail( String.format( "Invalid method return type: '%s'",
					this.declaration[0] ) );
		}
		
		if ( getName() == null
				|| !getName().matches( RegexUtils.METHOD_NAME_PATTERN ) ) {
			result.fail( String.format( "Illegal method name: '%s'", getName() ) );
		}
		
		if ( getParams().size() != getNumOfParams() ) {
			result.fail( String.format(
					"Declaration has invalid params. Method: '%s', Params: '%s'",
					getName(), declaration[2] ) );
		}
		Set< String > names = new HashSet< String >();
		for ( Variable param : getParams() ) {
			result.append( param.isValid( scope ) );
			names.add( param.getName() );
		}
		
		if ( names.size() != getParams().size() ) {
			result.fail( "Method declaration cannot have two parameters with the same name" );
		}
		
		return result;
	}
	
	/**
	 * @return the numOfParams
	 */
	private int getNumOfParams() {
		return numOfParams;
	}
}
