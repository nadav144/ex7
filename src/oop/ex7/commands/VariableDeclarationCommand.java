package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.CommandValidator;
import oop.ex7.common.Scope;

import java.util.regex.Matcher;

/**
 * Created by Nadav on 10/06/14.
 */
public class VariableDeclarationCommand implements CommandValidator {

    public VariableDeclarationCommand(Matcher matcher){

    }


    @Override
    public ValidationResult isValid(String expression, Scope scope) {
        return null;
    }
}
