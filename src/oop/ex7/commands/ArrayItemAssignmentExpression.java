
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.Variable;

/**
 * Created by Nadav on 14/06/14.
 */
public class ArrayItemAssignmentExpression extends AssignmentExpression {
	
	private Expression positionExpression;
	
	/**
	 * @param var
	 * @param expression
	 */
	public ArrayItemAssignmentExpression( Variable var, Expression position,
											Expression expression ) {
		super( var, expression );
		positionExpression = position;
	}
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult res = super.isValid( scope );
		
		if ( !var.isInited() ) {
			res.fail( "Array must be initialized before items can be assigned." );
		}
		
		if ( res.getsuccessful() ) {
			res.append( positionExpression.isValid( scope ) );
			if ( res.getsuccessful() && !var.isArray() ) {
				res.fail( "Array assignment into not array variable is not allowed" );
			}
			if ( res.getsuccessful()
					&& positionExpression.getType( scope ).getType() != TermType.VarType.INT ) {
				res.fail( "Array position assignment must be an int type" );
			}
		}
		
		return res;
	}
}
