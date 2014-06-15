
package oop.ex7.commands;

import oop.ex7.ValidationResult;
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
	
	public MethodDeclaration( MatchResult declaration ) {
		this.declaration = new String[3];
		
		this.declaration[0] = declaration.group( 1 );
		this.declaration[1] = declaration.group( 2 );
		this.declaration[2] = declaration.group( 3 );
		
		returnType = TermType.parse( this.declaration[0] );
		
		this.params = new LinkedList< Variable >();
		
		// TODO: params.length should equal to matchResults.length
		// not sure how to do this, other than save the number for later
		if ( !this.declaration[2].trim().equals( "" ) ) {
			String paramsString = this.declaration[2].trim();
			String[] params = paramsString.split( "," );
			this.numOfParams = params.length;
			List< MatchResult > matchResults =
					RegexUtils.Match( RegexUtils.PARAM_PATTERN, params );
			for ( MatchResult result : matchResults ) {
				Variable param= new Variable( result.group( 1 ),
						result.group( 2 ) ) ;
				param.setInited( true );
				this.params.add(param);
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
			scope.getVars().put( var.getName(), var );
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
		
		// TODO: check length of params
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
