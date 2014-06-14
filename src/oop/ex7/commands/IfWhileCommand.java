package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.VarType;

import java.util.regex.MatchResult;

/**
 * Created by Nadav on 11/06/14.
 */
public class IfWhileCommand implements Command {

    private Expression booleanExpresion;

    public IfWhileCommand(Expression expression){
        booleanExpresion = expression;
    }

    @Override
    public boolean isScope() {
        return true;
    }

    @Override
    public void updateScope(Scope scope) {

    }

    @Override
    public ValidationResult isValid(Scope scope) {
        ValidationResult res = new ValidationResult();
        res.append(booleanExpresion.isValid(scope));
        if (res.getsuccessful() && booleanExpresion.getType(scope) != VarType.BOOLEAN)
            res.fail("IF or While statement expression is not a valid boolean expression");

        return res;
    }
}