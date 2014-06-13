
package oop.ex7.common;

import oop.ex7.ValidationResult;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.MatchResult;

public class MethodDeclaration implements Command {
	
	private LinkedList< Variable > params;
	private ReturnType returnType;
	private String[] declaration;
	
	public MethodDeclaration( MatchResult declaration ) {
		this.declaration = new String[3];
		
		this.declaration[0] = declaration.group( 1 );
		this.declaration[1] = declaration.group( 2 );
		this.declaration[2] = declaration.group( 3 );
		
		returnType = ReturnType.parse( this.declaration[0] );
		
		this.params = new LinkedList< Variable >();
		
		// TODO: params.length should equal to matchResults.length
		// not sure how to do this, other than save the number for later
		String[] params = this.declaration[2].split( "," );
		List< MatchResult > matchResults =
				RegexUtils.Match( RegexUtils.PARAM_PATTERN, params );
		for ( MatchResult result : matchResults ) {
			
			this.params.add( new Variable( result.group( 1 ), result.group( 2 ) ) );
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
	public ReturnType getReturnType() {
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
		return false;
	}

    @Override
    public void updateScope(Scope scope) {
        for (Variable var : getParams()){
            scope.getVars().add(var);
        }
    }

    @Override
	public ValidationResult isValid(Scope scope ) {
		ValidationResult result = new ValidationResult();
		if ( getReturnType() == null ) {
			result.setSuccessful( false );
			result.append( String.format( "Invalid method return type: '%s'",
					this.declaration[0] ) );
		}
		
		if ( getName() == null
				|| !getName().matches( RegexUtils.METHOD_NAME_PATTERN ) ) {
			result.setSuccessful( false );
			result.append( String.format( "Illegal method name: '%s'",
					getName() ) );
		}

        // TODO: check length of params

        for ( Variable param : getParams() ) {
            result.append( param.isValid(scope ) );
        }



		return result;
	}
}
