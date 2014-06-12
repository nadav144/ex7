/**
 * 
 */

package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.*;

/**
 * @author Jirsch
 * 
 */
public class AssignmentExpression implements Command {
	
	private Variable var;
	private Expression expression;
	
	/**
	 * 
	 */
	public AssignmentExpression( Variable var, Expression expression ) {
		this.var = var;
		this.expression = expression;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String,
	 * oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		result.append( getVar().isValid( expression, scope ) );
		result.append( getExpression().isValid( expression, scope ) );
		
		if ( result.getsuccessful() ) {
			if ( !getVar().getType().equals( getExpression().getType( scope ) ) ) {
				result.setSuccessful( false );
				result.append( String.format(
						"Invalid assignment type. Expected: '%s', Actual: '%s'",
						getVar().getType(), getExpression().getType( scope ) ) );
				
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
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return the var
	 */
	private Variable getVar() {
		return var;
	}
	
	/**
	 * @return the expression
	 */
	private Expression getExpression() {
		return expression;
	}
	
}
