
package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

public class LiteralExpression implements Expression {
	
	private VarType type;
	
	public LiteralExpression( VarType type ) {
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
	public VarType getType( Scope scope ) {
		return getType();
	}
	
	/**
	 * @return the type
	 */
	private VarType getType() {
		return type;
	}
	
}
