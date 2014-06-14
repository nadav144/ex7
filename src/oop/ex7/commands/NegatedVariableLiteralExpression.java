
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;

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
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = super.isValid( scope );
		
		if ( result.getsuccessful() ) {
			TermType type = super.getType( scope );
			if ( !TermType.isArithmetic( type ) ) {
				result.setSuccessful( false );
				result.append( String.format(
						"Cannot negate variable of specified type. Type: '%s'",
						type ) );
				
			}
		}
		return result;
	}
	
}
