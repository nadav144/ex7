package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;

/**
 * Created by Nadav on 13/06/14.
 */
public class ReturnCommand implements Command {

    Expression expression;

    public ReturnCommand(String returnExpresion) throws Exception{
        expression = ExpressionFactory.instance().create(returnExpresion);
    }
    @Override
    public ValidationResult isValid(Scope scope) {
        ValidationResult res = new ValidationResult();
        res.append(expression.isValid(scope));
        if (res.getsuccessful() && !(scope.getReturnType().canReturn(expression.getType(scope))) ){
            res.fail("The return for the method is not of the right type");
        }

        return res;
    }

    @Override
    public boolean isScope() {
        return false;
    }

    @Override
    public void updateScope(Scope scope) {
        scope.setReturnedValue(true);

    }
}
