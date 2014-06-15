
package oop.ex7.main;

import oop.ex7.Exceptions.CommandSyntaxException;
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
 * Represent the main compiler. this class will compile the file given using the factories and the command validation
 * method. 
 */
public class Compiler {
	
	/**
	 * Preprocess text, removing unnecessary lines. (e.g. comments etc.)
	 * 
	 * @param lines
	 *            the lines of the file to compile
	 * @return the filtered content of the file
	 * @throws IllegalArgumentException
	 *             in case of a very obvious syntax error
	 */
	public static String truncAndFixLines( List< String > lines )
			throws IllegalArgumentException {
		
		StringBuilder builder = new StringBuilder();
		Pattern p = Pattern.compile( RegexUtils.LINE_ENDING_PATTERN );
		
		for ( int i = 0 ; i < lines.size() ; i++ ) {
			String line = lines.get( i );
			line = line.trim();
			if ( !line.startsWith( RegexUtils.COMMENT_INDICATOR ) ) {
				
				if ( line.length() > 0 ) {
					Matcher m = p.matcher( line );
					if ( m.matches() )
						builder.append( line + "\r\n" );
					else
						throw new IllegalArgumentException(
								"Lines must end with the specified characters" );
				}
			}
		}
		return builder.toString();
	}
	
	/**
	 * Compiles the file and returns the results
	 * 
	 * @param content
	 *            the file to compile
	 * @return the validation results
	 * @throws Exception
	 *             if any illegal structures appear in the code
	 */
	public static ValidationResult parse( String content ) throws Exception {
		
		// Create the main scope first
		MainScope mainScope = new MainScope();
		ValidationResult preprocessResult = new ValidationResult();
		preprocessResult.append( preProcessMethods( content, mainScope ) );
		
		preprocessResult.append( processGlobalVariables( mainScope, content ) );
		if ( !preprocessResult.getSuccessful() ) {
			return preprocessResult;
		}
		
		// Start parsing line by line
		LineNumberReader reader =
				new LineNumberReader( new StringReader( content ) );
		return parseScope( reader, mainScope );
		
	}
	
	/**
	 * Scan file for method declarations
	 * 
	 * @param content
	 *            the file being compiled
	 * @param mainScope
	 *            the scope the houses the methods
	 */
	private static ValidationResult preProcessMethods( String content,
			MainScope mainScope ) throws CommandSyntaxException{
		ValidationResult result = new ValidationResult();
		// Search for method declarations
		List< MatchResult > results =
				RegexUtils.Match( RegexUtils.METHOD_DECLARATION_PATTERN,
						content );
		
		for ( MatchResult res : results ) {
			try {
				mainScope.addMethod( new MethodDeclaration( res ) );
			}
			// double method declaration
			catch ( IllegalStateException e ) {
				result.fail( e );
				break;
			}
			// bad type in method return type or param types
			catch ( IllegalArgumentException e ) {
				result.fail( e );
				break;
			}
		}
		return result;
	}
	
	/**
	 * Scan file for method declarations
	 * 
	 * @param mainScope
	 *            scope that houses the declarations
	 * @param content
	 *            the file being compiled
	 * @throws Exception
	 */
	private static ValidationResult processGlobalVariables(
			MainScope mainScope, String content ) throws Exception {
		List< MatchResult > results;
		ValidationResult validation = new ValidationResult();
		
		// delete all scoped code and leave only global declarations
		String filtered =
				content.replaceAll( RegexUtils.ENTIRE_METHOD_PATTERN, "" );
		
		results =
				RegexUtils.Match( RegexUtils.VAR_DECLARATION_STATEMENT,
						filtered );
		
		for ( MatchResult res : results ) {
			
			String typestring =
					( res.group( 2 ) != null ) ? res.group( 1 ) + res.group( 2 )
							: res.group( 1 );
			VariableDeclarationCommand cmd =
					new VariableDeclarationCommand( typestring, res.group( 3 ),
							res.group( 4 ) );
			validation.append( cmd.isValid( mainScope ) );
			if ( validation.getSuccessful() ) {
				cmd.updateScope( mainScope );
			}
			else {
				break;
			}
		}
		return validation;
	}
	
	/**
	 * Parses each scope's instructions
	 * 
	 * @param reader
	 *            the file to compile
	 * @param scope
	 *            the current scope we are processing
	 * @return validation results of the scope's compilation
	 * @throws Exception
	 *             if there are any illegal structures in the code
	 */
	private static ValidationResult parseScope( LineNumberReader reader,
			Scope scope ) throws Exception {
		
		ValidationResult returnValue = new ValidationResult();
		
		String line = reader.readLine();
		while ( line != null ) {
			
			// Generate the right command for this line.
			Command command = CommandFactory.CreateCommand( line, scope );
			returnValue.append( command.isValid( scope ) );
			if ( !returnValue.getSuccessful() )
				return returnValue;
			
			if ( command.isScope() ) {
				Scope commandScope = new Scope( scope, command );
				command.updateScope( commandScope );
				returnValue.append( parseScope( reader, commandScope ) );
			}
			else if ( command instanceof EndOFScopeCommand ) {
				return returnValue;
			}
			else {
				// update current scope
				command.updateScope( scope );
			}
			
			if ( !returnValue.getSuccessful() )
				return returnValue;
			
			line = reader.readLine();
			
		}
		
		if ( scope.getClass() != MainScope.class ) {
			returnValue.fail( "Expected }" );
		}
		
		return returnValue;
	}
	
}
