
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
		ValidationResult result = new ValidationResult();// = super.isValid(
															// scope );
		
		result.append( getVar().isValid( scope ) );
		result.append( getExpression().isValid( scope ) );
		
		if ( result.getsuccessful() ) {
			
			if ( !TermType.VarType.canAssignTo( getVar().getType().getType(),
					getExpression().getType( scope ).getType() ) ) {
				result.fail( String.format(
						"Invalid assignment type. Expected: '%s', Actual: '%s'",
						getVar().getType().getType(), getExpression().getType(
								scope ).getType() ) );
				
			}
		}
//		
//		if ( !var.isInited() ) {
//			result.fail( "Array must be initialized before items can be assigned." );
//		}
		
		if ( result.getsuccessful() ) {
			result.append( positionExpression.isValid( scope ) );
			if ( result.getsuccessful() && !var.isArray() ) {
				result.fail( "Array assignment into not array variable is not allowed" );
			}
			if ( result.getsuccessful()
					&& positionExpression.getType( scope ).getType() != TermType.VarType.INT ) {
				result.fail( "Array position assignment must be an int type" );
			}
		}
		
		return result;
	}
}
