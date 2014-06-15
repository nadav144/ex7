package oop.ex7.commands;

import oop.ex7.common.*;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Created by Nadav on 11/06/14.
 */
public class CommandFactory {

    public static final String IF_WHILE_STATEMENT = "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{";
    public static final String VAR_DECLARATION_STATEMENT = "(int|double|String|boolean|char)\\s*(\\[\\s*\\])?\\s*(\\w*)\\s*;";
    public static final String VAR_DECLARATION_INIT_SATEMENT = "(int|double|String|boolean|char)\\s*(\\[\\s*\\])?\\s*(\\w*)\\s*=(.*);";
    public static final String VAR_ASSIGNMENT_STATEMENT = "\\s*(\\w*)\\s*(\\[(.*)\\])?\\s*=(.*);";
    public static final String METHOD_RETURN_STATEMENT = "return\\s*(.*);";


    public static Command CreateCommand(String expresion, Scope scope) throws Exception{

        if (expresion.matches(VAR_DECLARATION_STATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(VAR_DECLARATION_STATEMENT, expresion);
            String typestring = (res.group(2) != null) ? res.group(1) + res.group(2) : res.group(1);
            return new VariableDeclarationCommand(typestring, res.group(3));

        } else if (expresion.matches(VAR_DECLARATION_INIT_SATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(VAR_DECLARATION_INIT_SATEMENT, expresion);
            String typestring = (res.group(2) != null) ? res.group(1) + res.group(2) : res.group(1);
            return new VariableDeclarationCommand(typestring, res.group(3), res.group(4));
        } else if (expresion.matches(VAR_ASSIGNMENT_STATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(VAR_ASSIGNMENT_STATEMENT, expresion);
            // TODO: OOOPS... what to do now?
            String paramName = res.group(1);
            Expression arrayAssignmentExpression = ExpressionFactory.instance().create(res.group(3));
            Expression RHSexpression = ExpressionFactory.instance().create(res.group(4));
            Variable var = scope.getVar(paramName);
            if (arrayAssignmentExpression == null){
                return new AssignmentExpression(var, RHSexpression);
            } else {
                return new ArrayItemAssignmentExpression(var, arrayAssignmentExpression, RHSexpression);
            }

        }
        if (expresion.matches(IF_WHILE_STATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(IF_WHILE_STATEMENT, expresion);
            return new IfWhileCommand(ExpressionFactory.instance().create(res.group(1)));

        }
        else if (expresion.matches(RegexUtils.METHOD_DECLARATION_PATTERN)){
            MatchResult res = RegexUtils.MatchSignle(RegexUtils.METHOD_DECLARATION_PATTERN, expresion);
            // Get the name and return the command from the main scope
            String methodName = res.group(2);
            return scope.getMainScope().getMethod(methodName);

        } else if (expresion.matches(METHOD_RETURN_STATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(METHOD_RETURN_STATEMENT, expresion);
            return new ReturnCommand(res.group(1));
        }

        else if (expresion.equals("}")){
            return new EndOFScopeCommand();
        }


        // if nothing matches - throw exception
        throw new Exception(expresion + " cannot be reconzied.");
    }
}
