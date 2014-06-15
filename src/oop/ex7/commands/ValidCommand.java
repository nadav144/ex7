package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.Scope;
import oop.ex7.common.ValidationResult;

/**
 * A command that is always valid.
 * Used as a stub to replace pre-processed commands to avoid double processing
 * 
 * @author Jirsch
 *
 */
public class ValidCommand implements Command {
	
	@Override
	public ValidationResult isValid( Scope scope ) {
		return new ValidationResult();
	}
	
	@Override
	public boolean isScope() {
		return false;
	}
	
	@Override
	public void updateScope( Scope scope ) {		
	}
	
}
