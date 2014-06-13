package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.RegexUtils;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Created by Nadav on 11/06/14.
 */
public class CommandFactory {

    public static final String IF_WHILE_STATEMENT = "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{";
    public static final String VAR_DECLARATION_STATEMENT = "(int|double|String|boolean|char)(\\[\\])?\\s(\\w*);";
    public static final String VAR_DECLARATION_INIT_SATEMENT = "(int|double|String|boolean|char)(\\[\\])?\\s(\\w*)\\s*=\\s*(.*);";


    public static Command CreateCommand(String expresion) throws Exception{

        if (expresion.matches(VAR_DECLARATION_STATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(VAR_DECLARATION_STATEMENT, expresion);
            return new VariableDeclarationCommand(res.group(1) + res.group(2), res.group(3));
        } else if (expresion.matches(VAR_DECLARATION_INIT_SATEMENT)){
            MatchResult res = RegexUtils.MatchSignle(VAR_DECLARATION_INIT_SATEMENT, expresion);
            return new VariableDeclarationCommand(res.group(1) + res.group(2), res.group(3), res.group(4));
        }

        else if (expresion.equals("}")){
            return new EndOFScopeCommand();
        }
        if (expresion.matches(IF_WHILE_STATEMENT)){

        }

        // TEMP CODE
        return new EndOFScopeCommand();
    }
}
