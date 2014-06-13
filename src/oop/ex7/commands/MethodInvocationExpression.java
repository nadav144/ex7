
package oop.ex7.commands;

import java.util.LinkedList;
import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.MethodDeclaration;
import oop.ex7.common.RegexUtils;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

public class MethodInvocationExpression implements Expression {
	
	private LinkedList< Expression > params;
	private String name;
	
	public MethodInvocationExpression( String name, String params ) throws Exception {
		this.name = name;
		
		this.params = new LinkedList< Expression >();
		
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
			VarType currDeclare = declatation.getParams().get( i ).getType();
			VarType currInvoke = getParams().get( i ).getType( scope );
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

    }

    /*
     * (non-Javadoc)
     *
     * @see oop.ex7.common.Expression#getType()
     */
	@Override
	public VarType getType( Scope scope ) {
		MethodDeclaration declatation =
				scope.getMainScope().getMethod( getName() );
		return VarType.parse( declatation.getReturnType().name() );
		
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
