/**
 * 
 */
package oop.ex7.common;

import oop.ex7.ValidationResult;


/**
 * @author Jirsch
 *
 */
public class AssignmentExpression implements Command {
	private Variable lhs;
	private Expression rhs;
	/**
	 * 
	 */
	public AssignmentExpression(Variable var,Expression expression) {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String, oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see oop.ex7.common.Command#isScope()
	 */
	@Override
	public boolean isScope() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
