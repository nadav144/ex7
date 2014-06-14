package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Scope;

/**
 * Created by Nadav on 11/06/14.
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
        boolean shouldReturn = false;
        if (scope.getReturnType() != null){
            shouldReturn = true;
        }
        if (shouldReturn != scope.isReturnedValue()){
            res.fail("Scope should return " + scope.getReturnType().toString());
        }

        return res;

    }
}
