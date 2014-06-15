
package oop.ex7.commands;

import oop.ex7.Exceptions.ExpressionSyntaxException;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;

public class OperationExpression implements Expression {
	
	private enum OperationType {
		PLUS, MINUS, MULT, DIV;
		
		public static OperationType parse( String type ) {
			if ( type == null || type.length() != 1 ) {
				return null;
			}
			
			switch ( type.charAt( 0 ) ) {
				case '+':
					return PLUS;
				case '-':
					return MINUS;
				case '*':
					return MULT;
				case '/':
					return DIV;
				default:
					return null;
			}
		}
	}
	
	private String lhsString;
	private String rhsString;
	private String opString;
	private OperationType opType;
	private Expression lhs;
	private Expression rhs;
	
	public OperationExpression( String lhs, String type, String rhs )
			throws ExpressionSyntaxException {
		this.lhsString = lhs.trim();
		this.rhsString = rhs.trim();
		this.opString = type.trim();
		
		this.lhs = ExpressionFactory.instance().create( lhs );
		this.rhs = ExpressionFactory.instance().create( rhs );
		this.opType = OperationType.parse( type );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.CommandValidator#isValid(java.lang.String,
	 * oop.ex7.common.Scope)
	 */
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		if ( getOpType() == null ) {
			result.setSuccessful( false );
			result.append( String.format( "Invalid Operation type : '%s'",
					getOpString() ) );
		}
		
		result.append( getLhs().isValid( scope ) );
		result.append( getRhs().isValid( scope ) );
		
		if ( result.getSuccessful() ) {
			TermType lType = getLhs().getType( scope );
			TermType rType = getRhs().getType( scope );
			if ( !TermType.isArithmetic(lType)
					|| !TermType.isArithmetic(rType) || lType.isArray()
					|| rType.isArray() ) {
				result.setSuccessful( false );
				result.append( String.format(
						"Invalid operand types. Left: '%s', Right: '%s'",
						getLhsString(), getRhsString() ) );
			}
		}
		
		return result;
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
	
	@Override
	public void updateScope( Scope scope ) {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.Expression#getType(oop.ex7.common.Scope)
	 */
	@Override
	public TermType getType( Scope scope ) {
		TermType lType = getLhs().getType( scope );
		TermType rType = getRhs().getType( scope );
		
		return TermType.getCommon( new TermType[] { lType, rType } );
		// if ( VarType.INT.equals( lType ) && VarType.INT.equals( rType ) ) {
		// return VarType.INT;
		//
		// }
		//
		// else {
		// return VarType.DOUBLE;
		// }
		// TODO: remove comment
	}
	
	/**
	 * @return the lhsString
	 */
	private String getLhsString() {
		return lhsString;
	}
	
	/**
	 * @return the rhsString
	 */
	private String getRhsString() {
		return rhsString;
	}
	
	/**
	 * @return the opString
	 */
	private String getOpString() {
		return opString;
	}
	
	/**
	 * @return the opType
	 */
	private OperationType getOpType() {
		return opType;
	}
	
	/**
	 * @return the lhs
	 */
	private Expression getLhs() {
		return lhs;
	}
	
	/**
	 * @return the rhs
	 */
	private Expression getRhs() {
		return rhs;
	}
	
}
