package oop.ex7.commands;

import java.util.LinkedList;

import oop.ex7.Exceptions.ExpressionSyntaxException;
import oop.ex7.common.Expression;
import oop.ex7.common.RegexUtils;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;

/**
 * Represent a method invocation expression
 */
public class MethodInvocationExpression implements Expression {
	
	private LinkedList< Expression > params;
	private String name;

    /**
     * Intialize a new instnace of MethodInvocationExpression
     * @param name name of the method to invoke
     * @param params params for the method invocation. seperated by ,
     * @throws ExpressionSyntaxException if there is an error with the mathod name or params
     */
	public MethodInvocationExpression( String name, String params ) throws ExpressionSyntaxException {
		this.name = name;
		
		this.params = new LinkedList< Expression >();

        if (!params.trim().equals(""))
            for ( String param : params.split( "," ) ) {
                this.params.add( ExpressionFactory.instance().create( param ) );
            }

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
		
		MethodDeclaration declatation =
				scope.getMainScope().getMethod( getName() );
		
		if ( declatation == null ) {
			result.setSuccessful( false );
			result.append( String.format(
					"Invoked method does not exist: '%s'", getName() ) );
		}
		
		if ( getName() == null
				|| !getName().matches( RegexUtils.METHOD_NAME_PATTERN ) ) {
			result.setSuccessful( false );
			result.append( String.format( "Illegal method name: '%s'",
					getName() ) );
		}
		
		if ( declatation.getParams().size() != getParams().size() ) {
			result.setSuccessful( false );
			result.append( String.format(
					"Invalid parameter number. Expected: '%s', Actual: '%s'",
					declatation.getParams().size(), getParams().size() ) );
			
		}
		
		for ( int i = 0 ; i < declatation.getParams().size() ; i++ ) {
			TermType currDeclare = declatation.getParams().get( i ).getType();
			TermType currInvoke = getParams().get( i ).getType( scope );
			if ( !currDeclare.equals( currInvoke ) ) {
				result.setSuccessful( false );
				result.append( String.format(
						"Invalid parameter type. Expected: '%s', Actual: '%s'",
						currDeclare, currInvoke ) );
			}
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

    /*
     * (non-Javadoc)
     *
     * @see oop.ex7.common.Expression#getType()
     */
	@Override
	public TermType getType( Scope scope ) {
		MethodDeclaration declatation =
				scope.getMainScope().getMethod( getName() );
		return declatation.getReturnType();
		
	}
	
	/**
	 * @return the params
	 */
	private LinkedList< Expression > getParams() {
		return params;
	}
	
	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}

}
