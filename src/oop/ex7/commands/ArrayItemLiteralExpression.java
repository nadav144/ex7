
package oop.ex7.commands;

import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;
import oop.ex7.common.Variable;


/**
 * Represents assignment to array variable
 */
public class ArrayItemLiteralExpression extends VariableLiteralExpression {
	
	private Expression position;

    /**
     * Initialize a new instance of ArrayItemLiteralExpression
     * @param name name of the variable parameter
     * @param position position to assign
     */
	public ArrayItemLiteralExpression( String name, Expression position ) {
		super( name );
		this.position = position;
	}
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		result.append( super.isValid( scope ) );
		result.append( position.isValid( scope ) );
		Variable var = scope.getVar( getName() );
		
		if ( result.getSuccessful() ) {
			if ( !var.isArray() ) {
				result.fail( "Array assignment into not array variable is not allowed" );
			}
			
			if ( !position.getType( scope ).equals(
					new TermType( TermType.VarType.INT ) ) ) {
				result.fail( "Array position assignment must be an int type" );
			}
			
		}
		return result;
	}
	
	@Override
	public TermType getType( Scope scope ) {
		return new TermType( scope.getVar( getName() ).getType().getType() );
	}
	
}
