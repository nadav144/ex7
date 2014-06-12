
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Scope;
import oop.ex7.common.Variable;

/**
 * Created by Nadav on 10/06/14.
 */
public class VariableDeclarationCommand implements Command {
	
	private Variable var;
	private AssignmentExpression assign;
	
	public VariableDeclarationCommand( String type, String name ) {
		this( type, name, null );
	}
	
	public VariableDeclarationCommand( String type, String name, String rhs ) {
		var = new Variable( type, name );
		if ( rhs != null ) {
			assign =
					new AssignmentExpression( var,
							ExpressionFactory.instance().create( rhs ) );
		}
		
	}
	
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		result.append( getVar().isValid( expression, scope ) );
		if ( getAssign() != null ) {
			result.append( getAssign().isValid( expression, scope ) );
			var.setInited( true );
		}
		
		if ( result.getsuccessful() ) {
			scope.getVars().add( getVar() );
		}
		
		return result;
	}
	
	/**
	 * @return the var
	 */
	private Variable getVar() {
		return var;
	}
	
	/**
	 * @return the assign
	 */
	private AssignmentExpression getAssign() {
		return assign;
	}
	
	@Override
	public boolean isScope() {
		return false;
	}
}
