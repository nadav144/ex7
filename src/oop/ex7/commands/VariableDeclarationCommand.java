
package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.Scope;
import oop.ex7.common.ValidationResult;
import oop.ex7.common.Variable;

/**
 * Represents a Variable Decleration command
 * */
public class VariableDeclarationCommand implements Command {
	
	private Variable var;
	private AssignmentExpression assign;

    /**
     * Initialize a new instance of VariableDeclarationCommand
     * @param type type of variable
     * @param name name of variable to declare
     * @throws Exception if type is invalid
     */
	public VariableDeclarationCommand( String type, String name )
			throws Exception {
		this( type, name, null );
	}

    /**
     * Initialize a new instance of VariableDeclarationCommand
     * @param type type of variable
     * @param name name of variable to declare
     * @param rhs expression to assign to the variable
     * @throws Exception if type is invalid
     */
	public VariableDeclarationCommand( String type, String name, String rhs )
			throws Exception {
		var = new Variable( type, name );
		if ( rhs != null ) {
			assign =
					new AssignmentExpression( var,
							ExpressionFactory.instance().create( rhs ) );
		}
		
	}
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		result.append( getVar().isValid( scope ) );
		if ( getAssign() != null ) {
			result.append( getAssign().isValid( scope ) );
		}
		if ( scope.getVars().containsKey( getVar().getName() ) ) {
			result.fail( "Cannot declare more than one variable of the same name in the same scope" );
		}
		return result;
	}
	
	/**
	 * @return the var
	 */
	public Variable getVar() {
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
	
	@Override
	public void updateScope( Scope scope ) {
		if ( getAssign() != null ) {
			var.setInited( true );
		}
		scope.getVars().put( getVar().getName(), getVar() );
	}
}
