
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

public class NegatedVariableLiteralExpression extends VariableLiteralExpression {
	
	public NegatedVariableLiteralExpression( String name ) {
		super( name );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.commands.VariableLiteralExpression#isValid(java.lang.String,
	 * oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( String expression, Scope scope ) {
		ValidationResult result = super.isValid( expression, scope );
		
		if ( result.getsuccessful() ) {
			VarType type = super.getType( scope );
			if ( !VarType.INT.equals( type ) && !VarType.DOUBLE.equals( type ) ) {
				result.setSuccessful( false );
				result.append( String.format(
						"Cannot negate variable of specified type. Type: '%s'",
						type ) );
				
			}
		}
		return result;
	}
	
}
