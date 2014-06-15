package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.MainScope;
import oop.ex7.common.Scope;
import oop.ex7.common.ValidationResult;

/**
 * Represents an end of scope Command.
 * this command will make sure the command is closed right, and remove the scope from the stack
 */
public class EndOFScopeCommand implements Command {

    @Override
    public boolean isScope() {
        return false;
    }

    @Override
    public void updateScope(Scope scope) {

    }

    @Override
    public ValidationResult isValid(Scope scope) {
        ValidationResult res = new ValidationResult();
        // Validate the scope returned the necessary values
        boolean shouldReturn = false;
        if (scope.getParentScope() instanceof MainScope){
            shouldReturn = true;
        }
        if (shouldReturn && !scope.isReturnedValue()){
            res.fail("Scope should return " + scope.getReturnType().toString());
        }

        return res;

    }
}
