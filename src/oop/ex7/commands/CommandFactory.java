package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.RegexUtils;

import java.util.regex.Pattern;

/**
 * Created by Nadav on 11/06/14.
 */
public class CommandFactory {

    public static final String IF_WHILE_STATEMENT = "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{";


    public static Command CreateCommand(String expresion){

        if (expresion.equals("}")){
            return new EndOFScopeCommand();
        }
        if (expresion.matches(IF_WHILE_STATEMENT)){

        }

        // TEMP CODE
        return new EndOFScopeCommand();
    }
}
