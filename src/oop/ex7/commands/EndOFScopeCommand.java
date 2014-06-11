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
    public ValidationResult isValid(String expression, Scope scope) {
        return null;
    }
}
