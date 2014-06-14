
package oop.ex7.commands;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

public class ArrayLiteralExpression implements Expression {
	
	private List< Expression > expressions;
	
	public ArrayLiteralExpression( String params ) throws Exception {
		String[] items = params.split( "," );
		
		expressions = new LinkedList< Expression >();
		
		if ( items.length == 1 && !items[0].trim().equals( "" ) ) {
			Expression exp = ExpressionFactory.instance().create( items[0] );
			expressions.add( exp );
		}
		else {
			for ( String item : items ) {
				Expression exp = ExpressionFactory.instance().create( item );
				expressions.add( exp );
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.commands.AssignmentExpression#isValid(oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( Scope scope ) {
		Set< VarType > t = new HashSet< VarType >();
		
		ValidationResult result = new ValidationResult();
		
		for ( Expression term : getExpressions() ) {
			
			result.append( term.isValid( scope ) );
			if ( result.getsuccessful() ) {
				t.add( term.getType( scope ) );
			}
		}
		
		boolean canAssign = true;
		for ( VarType i : t ) {
			canAssign = true;
			for ( VarType j : t ) {
				canAssign = canAssign && VarType.canAssignTo( i, j );
			}
			if ( canAssign ) {
				break;
			}
		}
		if ( !canAssign ) {
			result.setSuccessful( false );
			result.append( "Array initializaer has mismatching types" );
		}
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * oop.ex7.commands.AssignmentExpression#updateScope(oop.ex7.common.Scope)
	 */
	@Override
	public void updateScope( Scope scope ) {
		// TODO Auto-generated method stub
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.Command#isScope()
	 */
	@Override
	public boolean isScope() {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.Expression#getType(oop.ex7.common.Scope)
	 */
	@Override
	public VarType getType( Scope scope ) {
		Set< VarType > t = new HashSet< VarType >();
		
		for ( Expression term : getExpressions() ) {
			t.add( term.getType( scope ) );
			
		}
		
		boolean canAssign = true;
		for ( VarType i : t ) {
			canAssign = true;
			for ( VarType j : t ) {
				canAssign = canAssign && VarType.canAssignTo( i, j );
			}
			if ( canAssign ) {
				return i;
			}
		}
		
		return null;
	}
	
	/**
	 * @return the expressions
	 */
	private List< Expression > getExpressions() {
		return expressions;
	}
	
}
