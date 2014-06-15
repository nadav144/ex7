
package oop.ex7.commands;

import oop.ex7.common.Expression;
import oop.ex7.common.PositiveEnum;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;

/**
 * Represnt a literal expression for assignments, position etc.
 */
public class LiteralExpression implements Expression {

    private PositiveEnum isPositive;
	private TermType type;

    /**
     * Initailize a new instance of LiteralExpression with no positive information
     * @param type Type of the expression (affect as return type)
     */
    public LiteralExpression( TermType type ) {
		this.type = type;
        isPositive = PositiveEnum.UNKNOWN;
	}

    /**
     * Intizlie a new instance of LiteralExpression
     * @param type Type of the expression (affect as return type)
     * @param isPositive Positive if the expression has a positive literal value.
     */
    public LiteralExpression( TermType type, boolean isPositive){
        this(type);
        if (isPositive)
            this.isPositive = PositiveEnum.POSITIVE;
        else
            this.isPositive = PositiveEnum.NEGATIVE;
    }
	
	public ValidationResult isValid(Scope scope ) {
		return new ValidationResult();
	}
	
	@Override
	public TermType getType( Scope scope ) {
		return getType();
	}
	
	/**
	 * @return the type
	 */
	private TermType getType() {
		return type;
	}


    /**
     * @return the positive state of this expression
     */
    public PositiveEnum isPositive() {
        return isPositive;
    }
}
