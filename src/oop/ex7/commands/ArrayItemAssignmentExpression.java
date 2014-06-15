package oop.ex7.commands;

import oop.ex7.common.*;

/**
 * Represents an assignment to array item command
 */
public class ArrayItemAssignmentExpression extends AssignmentExpression {
	
	private Expression positionExpression;
	
	/**
     * Initialize a new instance of ArrayItemAssignmentExpression
	 * @param var array variable to assign to
	 * @param expression expression to assign
	 */
	public ArrayItemAssignmentExpression( Variable var, Expression position,
											Expression expression ) {
		super( var, expression );
		positionExpression = position;
	}

    /*
	 * (non-Javadoc)
	 *
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String,
	 * oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();// = super.isValid(
															// scope );
		
		result.append( getVar().isValid( scope ) );
		result.append( getExpression().isValid( scope ) );
		
		if ( result.getSuccessful() ) {
			
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
		
		if ( result.getSuccessful() ) {
			result.append( positionExpression.isValid( scope ) );
			if ( result.getSuccessful() && !var.isArray() ) {
				result.fail( "Array assignment into not array variable is not allowed" );
			}
			if ( result.getSuccessful()){
					if (positionExpression.getType( scope ).getType() != TermType.VarType.INT )
				        result.fail( "Array position assignment must be an int type" );
                    if (result.getSuccessful() && positionExpression.getClass() == LiteralExpression.class){
                        if (((LiteralExpression)positionExpression).isPositive() == PositiveEnum.NEGATIVE){
                            result.fail("Array item position must be a positive number");
                        }
                    }
			}
		}
		
		return result;
	}
}
