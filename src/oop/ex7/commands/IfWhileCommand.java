package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Scope;

import java.util.regex.MatchResult;

/**
 * Created by Nadav on 11/06/14.
 */
public class IfWhileCommand implements Command {

    private String booleanExpresion;

    public IfWhileCommand(MatchResult matchResult){
        booleanExpresion = matchResult.group(1);
    }

    @Override
    public boolean isScope() {
        return true;
    }

    @Override
    public ValidationResult isValid(String expression, Scope scope) {
        return null;
    }
}
