package oop.ex7.commands;

import oop.ex7.common.*;

/**
 * Represent a variable assignment. this class is the main class for any assignment of variables.
 * the assignment contains variable to assign, and expression to assign to.
 */
public class AssignmentExpression implements Command {
	
	protected Variable var;
	private Expression expression;

    /**
     * Initialize a new instance of AssignmentExpression
     * @param var variable to assign to
     * @param expression expression to assign to the variable.
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

        // validate the var and the expression first.
		result.append( getVar().isValid( scope ) );
		result.append( getExpression().isValid( scope ) );
		
		if ( result.getSuccessful() ) {
			// make sure the types are matches
			if ( !TermType.canAssignTo( getVar().getType(),
					getExpression().getType( scope ) ) ) {
				result.fail(String.format(
                        "Invalid assignment type. Expected: '%s', Actual: '%s'",
                        getVar().getType(), getExpression().getType(scope)));
				
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

    /*
	 * (non-Javadoc)
	 *
	 * @see oop.ex7.common.Command#updateScope()
	 */
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
