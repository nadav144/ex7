package oop.ex7.commands;

import oop.ex7.common.Command;
import oop.ex7.common.RegexUtils;

/**
 * Created by Nadav on 11/06/14.
 */
public class CommandFactory {

    public static Command CreateCommand(String expresion){

        // TEMP CODE
        return new VariableDeclarationCommand(RegexUtils.Match("", "").get(0));
    }
}
