package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.Command;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;

/**
 * Created by Nadav on 13/06/14.
 */
public class ReturnCommand implements Command {

    private Expression expression;
    private boolean isVoid;

    public ReturnCommand(String returnExpresion) throws Exception{
        if (!returnExpresion.equals(""))
            expression = ExpressionFactory.instance().create(returnExpresion);
        else
            isVoid = true;
    }
    @Override
    public ValidationResult isValid(Scope scope) throws Exception{
        ValidationResult res = new ValidationResult();
        if (!isVoid){
            res.append(expression.isValid(scope));
            if (res.getsuccessful() && !(scope.getReturnType().canReturn(expression.getType(scope))) ){
                res.fail("The return for the method is not of the right type");
            }
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
