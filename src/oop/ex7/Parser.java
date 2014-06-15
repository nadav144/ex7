
package oop.ex7;

import oop.ex7.commands.CommandFactory;
import oop.ex7.commands.EndOFScopeCommand;
import oop.ex7.commands.MethodDeclaration;
import oop.ex7.commands.VariableDeclarationCommand;
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

	public static String TruncAndFixLines( List< String > lines )
			throws Exception {

        StringBuilder builder = new StringBuilder();
        Pattern p = Pattern.compile(LINE_ENDING_PATTERN);

        for (int i=0;i<lines.size(); i++){
            String line = lines.get(i);
            line = line.trim();
            if (!line.startsWith(COMMENT_INDICATOR)){

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
		List< MatchResult > results =
				RegexUtils.Match( RegexUtils.METHOD_DECLARATION_PATTERN,
						content );

        for (MatchResult res : results){

            mainScope.addMethod(new MethodDeclaration(res));
        }
		//
		String filtered = content.replaceAll( "\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{(.|\\n|\\r)*(return)(.|\\n|\\r)*\\}", "" );
		
		ValidationResult globalResult =
				processGlobalVariables( mainScope, filtered );
		if ( !globalResult.getsuccessful() ) {
			return globalResult;
		}

        // Start parsing line by line

		LineNumberReader reader =
				new LineNumberReader( new StringReader( content ) );
        return ParseScope(reader, mainScope);

	}
	
	/**
	 * @param mainScope
	 * @param filtered
	 * @throws Exception
	 */
	private static ValidationResult processGlobalVariables(
			MainScope mainScope, String filtered ) throws Exception {
		List< MatchResult > results;
		ValidationResult validation = new ValidationResult();
		results =
				RegexUtils.Match( CommandFactory.VAR_DECLARATION_STATEMENT,
						filtered );

		for ( MatchResult res : results ) {
			String typestring =
					( res.group( 2 ) != null ) ? res.group( 1 ) + res.group( 2 )
							: res.group( 1 );
			VariableDeclarationCommand cmd =
					new VariableDeclarationCommand( typestring, res.group( 3 ) );
			validation.append( cmd.isValid( mainScope ) );
			if ( validation.getsuccessful() ) {
				cmd.updateScope( mainScope );
			}
			else {
				return validation;
			}

    }

		filtered =
				filtered.replaceAll( CommandFactory.VAR_DECLARATION_STATEMENT,
						"" );
		
		results =
				RegexUtils.Match( CommandFactory.VAR_DECLARATION_INIT_SATEMENT,
						filtered );

		for ( MatchResult res : results ) {
			
			String typestring =
					( res.group( 2 ) != null ) ? res.group( 1 ) + res.group( 2 )
							: res.group( 1 );
			VariableDeclarationCommand cmd =
					new VariableDeclarationCommand( typestring, res.group( 3 ),
							res.group( 4 ) );
			validation.append( cmd.isValid( mainScope ) );
			if ( validation.getsuccessful() ) {
				cmd.updateScope( mainScope );
			}
			else {
				return validation;
			}
		}
		return validation;
	}
	
	private static ValidationResult ParseScope( LineNumberReader reader,
			Scope scope ) throws Exception {

        ValidationResult returnValue = new ValidationResult();

        String line = reader.readLine();
        while (line != null){

            // Generate the right command for this line.
            Command command = CommandFactory.CreateCommand(line, scope);
            returnValue.append(command.isValid(scope));
            if (!returnValue.getsuccessful())
                return returnValue;

            if (command.isScope()){
                Scope commandScope = new Scope(scope, command);
                command.updateScope(commandScope);
                returnValue.append(ParseScope(reader, commandScope));
			}
			else if ( command.getClass() == EndOFScopeCommand.class ) {
                return returnValue;
			}
			else {
                // update current scope
                command.updateScope(scope);
            }

            if (!returnValue.getsuccessful())
                return returnValue;

            line = reader.readLine();

        }

        if (scope.getClass() != MainScope.class){
            returnValue.setSuccessful(false);
            returnValue.append("Expected }");
        }

        return returnValue;
    }

}
