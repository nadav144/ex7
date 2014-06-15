
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.*;

import java.util.List;
import java.util.LinkedList;
import java.util.regex.MatchResult;

public class MethodDeclaration implements Command {
	
	private LinkedList<Variable> params;
	private ReturnType returnType;
	private String[] declaration;
	
	public MethodDeclaration( MatchResult declaration ) throws Exception{
		this.declaration = new String[3];
		
		this.declaration[0] = (declaration.group(2) == null) ? declaration.group( 1 ) : declaration.group(1) + declaration.group(2);
		this.declaration[1] = declaration.group( 3 );
		this.declaration[2] = declaration.group( 4 );
		
		returnType = ReturnType.parse( this.declaration[0] );
		
		this.params = new LinkedList< Variable >();
		
		// TODO: params.length should equal to matchResults.length
		// not sure how to do this, other than save the number for later
        if (!this.declaration[2].equals("")){
            String paramsString = this.declaration[2].trim();
            String[] params = paramsString.split( "," );
            List< MatchResult > matchResults =
                    RegexUtils.Match(RegexUtils.PARAM_PATTERN, params);
            for ( MatchResult result : matchResults ) {
                boolean isArray = !(result.group(2).isEmpty());
                this.params.add( new Variable( result.group( 1 ), isArray, result.group( 3 ) ) );
                this.params.getLast().setInited(true);
            }
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
		return true;
	}

    @Override
    public void updateScope(Scope scope) {
        for (Variable var : getParams()){
            scope.getVars().put(var.getName(), var);
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
