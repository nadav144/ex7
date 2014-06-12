
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;
import oop.ex7.common.Variable;

public class VariableLiteralExpression implements Expression {
	
	private String name;
	
	public VariableLiteralExpression( String name ) {
		this.name = name;
	}
	
	@Override
	public boolean isScope() {
		return false;
	}
	
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		ValidationResult result = new ValidationResult();
		int index = scope.getVars().indexOf( getName() );
		
		if ( index < 0 ) {
			result.setSuccessful( false );
			result.append( String.format(
					"Requested variable is not declared in scope. Name: '%s'",
					getName() ) );
		}
		
		Variable var = scope.getVars().get( index );
		if ( !var.isInited() ) {
			result.setSuccessful( false );
			result.append( String.format(
					"Variable is not initialized in scope. Name: '%s'",
					getName() ) );
		}
		return result;
	}
	
	@Override
	public VarType getType( Scope scope ) {
		int index = scope.getVars().indexOf( getName() );
		
		if ( index < 0 ) {
			return null;
		}
		return scope.getVars().get( index ).getType();
	}
	
	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}
	
}
