
package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.TermType;
import oop.ex7.common.ValidationResult;
import oop.ex7.common.TermType.VarType;

/**
 * Created by Nadav on 11/06/14.
 */
public class IfWhileCommand implements Command {
	
	private Expression booleanExpresion;
	
	public IfWhileCommand( Expression expression ) {
		booleanExpresion = expression;
	}
	
	@Override
	public boolean isScope() {
		return true;
	}
	
	@Override
	public void updateScope( Scope scope ) {
		
	}
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		ValidationResult res = new ValidationResult();
		res.append( booleanExpresion.isValid( scope ) );
		TermType booleanType = new TermType( VarType.BOOLEAN );
		if ( res.getsuccessful()
				&& !booleanExpresion.getType( scope ).equals( booleanType ) )
			res.fail( "IF or While statement expression is not a valid boolean expression" );
		
		return res;
	}
}
