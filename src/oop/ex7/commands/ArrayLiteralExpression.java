
package oop.ex7.commands;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import oop.ex7.Exceptions.CommandSyntaxException;
import oop.ex7.Exceptions.ExpressionSyntaxException;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;

/**
 * Represent an array variable Assignment of one or more expressions as values
 */
public class ArrayLiteralExpression implements Expression {
	
	private List< Expression > expressions;

    /**
     * Initialize a new instance of ArrayLiteralExpression
     * @param params params to assign
     * @throws Exception in case of bad parameters
     */
	public ArrayLiteralExpression( String params ) throws ExpressionSyntaxException{
        // this is invalid syntax, we must fail it.
        if (params.endsWith(","))
            throw new ExpressionSyntaxException("invalid array assignment");

		String[] items = params.split( "," );
		expressions = new LinkedList< Expression >();
		
		if ( items.length == 1 ) {
			if ( !items[0].trim().equals( "" ) ) {
				Expression exp = ExpressionFactory.instance().create( items[0] );
				expressions.add( exp );
			}
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
		Set< TermType > types = new HashSet< TermType >();
		
		ValidationResult result = new ValidationResult();
		
		for ( Expression term : getExpressions() ) {
			
			result.append( term.isValid( scope ) );
			if ( result.getSuccessful() ) {
				types.add( term.getType( scope ) );
			}
		}
		if ( types.size() != 0 ) {
			TermType common =
					TermType.getCommon( types.toArray( new TermType[0] ) );
			if ( common == null ) {
				result.setSuccessful( false );
				result.append( "Array initializaer has mismatching types" );
			}
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
	public TermType getType( Scope scope ) {
		Set< TermType > types = new HashSet< TermType >();
		
		for ( Expression term : getExpressions() ) {
			types.add( term.getType( scope ) );
		}
		TermType.VarType varType;
		if ( types.size() == 0 ) {
			varType = TermType.VarType.ANY;
		}
		else {
			varType =
					TermType.getCommon( types.toArray( new TermType[0] ) ).getType();
		}
		return new TermType( varType, true );
	}
	
	/**
	 * @return the expressions of that assignment
	 */
	private List< Expression > getExpressions() {
		return expressions;
	}
	
}
