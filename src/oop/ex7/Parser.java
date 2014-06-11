package oop.ex7;

import oop.ex7.common.MainScope;
import oop.ex7.common.MethodDecleration;
import oop.ex7.common.RegexUtils;
import oop.ex7.common.Scope;

import java.io.LineNumberReader;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadav on 10/06/14.
 */
public class Parser {

    public ValidationResult Parse(String filename, Scope scope){

        /*
        // GetFile Content.
        // For each line -
        //          Match the pattern and create "Scope/Assignment/.... - CommandType

                    if i got scope, read to parse again

                    ELSE

                    Create new validator for that line
                    validate current line - recursive
                    (CommandValidator.Validate(Command, Scope))
                    update scope information

                    if End of Scope
                    exit function iteration - return to parent scope.




        */

        return null;


    }

    public static String TruncAndFixLines(List<String> lines) throws Exception{

        StringBuilder builder = new StringBuilder();
        Pattern p = Pattern.compile(".*[{|}|;]");

        for (int i=0;i<lines.size(); i++){
            String line = lines.get(i);
            if (!line.startsWith("//")){
                line = line.trim();
                line = line.replace("\t", "");
                if (line.length() > 0){
                    Matcher m = p.matcher(line);
                    if (m.matches())
                        builder.append(line + "\r\n");
                    else
                        throw new Exception("Syntax Error");
                }
            }
        }
        return builder.toString();
    }


    public static void Parse(String content) throws Exception{

        // Create the main scope first
        MainScope mainScope = new MainScope();

        // Search for method declerations

        //TODO: Add the regex to config file
        List<MatchResult> results = RegexUtils.Match("^\\s*(\\w*)\\s*(\\w*)\\s*\\((.*)\\)\\s*\\{", content);

        String test = "";
        for (MatchResult res : results){

            mainScope.addMethod(new MethodDecleration(res));
        }



    }


}
