package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.CommandValidator;
import oop.ex7.common.Scope;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * Created by Nadav on 10/06/14.
 */
public class VariableDeclarationCommand implements Command {

    public VariableDeclarationCommand(MatchResult matcher){

    }


    @Override
    public ValidationResult isValid(String expression, Scope scope) {
        return null;
    }

    @Override
    public boolean isScope() {
        return false;
    }
}
