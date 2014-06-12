
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

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
	
	public OperationExpression( String lhs, String type, String rhs ) {
		this.lhsString = lhs;
		this.rhsString = rhs;
		this.opString = type;
		
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
	public ValidationResult isValid( String expression, Scope scope ) {
		ValidationResult result = new ValidationResult();
		
		if ( getOpType() == null ) {
			result.setSuccessful( false );
			result.append( String.format( "Invalid Operation type : '%s'",
					getOpString() ) );
		}
		
		result.append( getLhs().isValid( expression, scope ) );
		result.append( getRhs().isValid( expression, scope ) );
		
		if ( result.getsuccessful() ) {
			VarType lType = getLhs().getType( scope );
			VarType rType = getRhs().getType( scope );
			if ( !VarType.INT.equals( lType ) || !VarType.DOUBLE.equals( lType )
					|| !VarType.INT.equals( rType )
					|| !VarType.DOUBLE.equals( rType ) ) {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see oop.ex7.common.Expression#getType(oop.ex7.common.Scope)
	 */
	@Override
	public VarType getType( Scope scope ) {
		VarType lType = getLhs().getType( scope );
		VarType rType = getRhs().getType( scope );
		
		if ( VarType.INT.equals( lType ) && VarType.INT.equals( rType ) ) {
			return VarType.INT;
			
		}
		
		else {
			return VarType.DOUBLE;
		}
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
