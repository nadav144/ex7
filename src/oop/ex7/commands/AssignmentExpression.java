/**
 * 
 */

package oop.ex7.commands;

import oop.ex7.common.*;

/**
 * @author Jirsch
 * 
 */
public class AssignmentExpression implements Command {
	
	protected Variable var;
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
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		result.append( getVar().isValid( scope ) );
		result.append( getExpression().isValid( scope ) );
		
		if ( result.getsuccessful() ) {
			
			if ( !TermType.canAssignTo( getVar().getType(),
					getExpression().getType( scope ) ) ) {
				result.fail(  String.format(
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
		return false;
	}
	
	@Override
	public void updateScope( Scope scope ) {
		getVar().setInited( true );
	}
	
	/**
	 * @return the var
	 */
	protected Variable getVar() {
		return var;
	}
	
	/**
	 * @return the expression
	 */
	protected Expression getExpression() {
		return expression;
	}
	
}
