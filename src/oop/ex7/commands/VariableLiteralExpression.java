
package oop.ex7.commands;

import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;
import oop.ex7.common.Variable;

/**
 * Represent a literal variable expression used to get the variable content
 */
public class VariableLiteralExpression implements Expression {
	
	private String name;

    /**
     * Initialize a new instance of VariableLiteralExpression
     * @param name name of the variable
     */
	public VariableLiteralExpression( String name ) {
		this.name = name;
	}
	
	@Override
	public boolean isScope() {
		return false;
	}

    @Override
    public void updateScope(Scope scope) {

    }

    @Override
	public ValidationResult isValid(Scope scope ) {
		ValidationResult result = new ValidationResult();
		Variable var = scope.getVar(getName());
		// make sure the variable exists in the scope
		if ( var == null) {
			result.setSuccessful( false );
			result.append( String.format(
					"Requested variable is not declared in scope. Name: '%s'",
					getName() ) );
		} else if ( !var.isInited() ) {
			result.setSuccessful( false );
			result.append( String.format(
					"Variable is not initialized in scope. Name: '%s'",
					getName() ) );
		}
		return result;
	}

	@Override
	public TermType getType( Scope scope ) {
        Variable var = scope.getVar(getName());
        if (var == null)
            return null;
		return var.getType();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
}
