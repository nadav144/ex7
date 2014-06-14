
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;

public class LiteralExpression implements Expression {
	
	private TermType type;
	
	public LiteralExpression( TermType type ) {
		this.type = type;
	}
	
	@Override
	public boolean isScope() {
		return false;
	}

    @Override
    public void updateScope(Scope scope) {

    }

    @Override
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
	
}
