package oop.ex7;


import oop.ex7.commands.EndOFScopeCommand;
import oop.ex7.common.*;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadav on 10/06/14.
 */
public class Parser {

    private static final String COMMENT_INDICATOR = "//";
	private static final String LINE_ENDING_PATTERN = ".*[{|}|;]$";


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
        Pattern p = Pattern.compile(LINE_ENDING_PATTERN);

        for (int i=0;i<lines.size(); i++){
            String line = lines.get(i);
            if (!line.startsWith(COMMENT_INDICATOR)){
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


    public static ValidationResult Parse(String content) throws Exception{

        // Create the main scope first
        MainScope mainScope = new MainScope();

        // Search for method declarations

        //TODO: Add the regex to config file
        List<MatchResult> results = RegexUtils.Match("^\\s*(\\w*)\\s*(\\w*)\\s*\\((.*)\\)\\s*\\{", content);

        String test = "";
        for (MatchResult res : results){

            mainScope.addMethod(new MethodDeclaration(res));
        }

        // Start parsing line by line

        LineNumberReader reader = new LineNumberReader(new StringReader(content));
        return ParseScope(reader, mainScope);



    }


    private static ValidationResult ParseScope(LineNumberReader reader, Scope scope ) throws Exception{

        ValidationResult returnValue = new ValidationResult();

        String line = reader.readLine();
        while (line != null){

            // Generate the right command for this line.
            Command command = null;//CommandFactory.CreateCommand(line);
            //returnValue.append(command.isValid(scope))
            //  return False
            // if this is a new scope, parse the scope

            if (command.isScope()){
                Scope commandScope = new Scope(scope);
                returnValue.append(ParseScope(reader, scope));
            } else if (command.getClass() == EndOFScopeCommand.class){
                return returnValue;
            }


            line = reader.readLine();
        }

        if (scope.getClass() != MainScope.class){
            returnValue.setSuccessful(false);
            returnValue.append("Expected }");
        }


        return null;
    }

}
