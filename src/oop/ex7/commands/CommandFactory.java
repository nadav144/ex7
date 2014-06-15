
package oop.ex7.commands;

import oop.ex7.Exceptions.CommandSyntaxException;
import oop.ex7.common.*;

import java.io.SyncFailedException;
import java.util.regex.MatchResult;

/**
 * Command factory creates command object from string expression using regex pattern to match the string content.
 * this factory is the main factory of the complier, and uses the expression factory for expressions.
 * the factory will return a new Command object
 */
public class CommandFactory {

    /**
     * Generate the right Command object for the given string content
     * @param commandstr command string representation
     * @param scope scope the command should be crated in. this uses by some of the command for initialization
     * @return a new object that implements Command
     * @throws Exception if the command is not a valid command in SJava
     */
	public static Command CreateCommand( String commandstr, Scope scope )
			throws Exception {
		// Var declaration statement command
        if ( commandstr.matches(RegexUtils.VAR_DECLARATION_STATEMENT) ) {
			if ( scope instanceof MainScope ) {
				return new ValidCommand();
			}
			
			MatchResult res =
					RegexUtils.MatchSignle( RegexUtils.VAR_DECLARATION_STATEMENT,
							commandstr );
			String typestring =
					( res.group( 2 ) != null ) ? res.group( 1 ) + res.group( 2 )
							: res.group( 1 );
			return new VariableDeclarationCommand( typestring, res.group( 3 ),
					res.group( 4 ) );
		}

        // Var assignment statement command
		else if ( commandstr.matches( RegexUtils.VAR_ASSIGNMENT_STATEMENT ) ) {
			MatchResult res =
					RegexUtils.MatchSignle( RegexUtils.VAR_ASSIGNMENT_STATEMENT,
							commandstr );
			String paramName = res.group( 1 );
			Expression arrayAssignmentExpression =
					res.group( 3 ) != null
							? ExpressionFactory.instance().create(
									res.group( 3 ) ) : null;
			Expression RHSexpression =
					ExpressionFactory.instance().create( res.group( 4 ) );
			Variable var = scope.getVar( paramName );
			if ( arrayAssignmentExpression == null ) {
				return new AssignmentExpression( var, RHSexpression );
			}
			else {
				return new ArrayItemAssignmentExpression( var,
						arrayAssignmentExpression, RHSexpression );
			}
			
		}
        // IF WHILE Statement commands
		else if ( commandstr.matches( RegexUtils.IF_WHILE_STATEMENT ) ) {
			MatchResult res =
					RegexUtils.MatchSignle( RegexUtils.IF_WHILE_STATEMENT, commandstr );
			return new IfWhileCommand( ExpressionFactory.instance().create(
					res.group( 1 ) ) );
			
		}
        // Method declaration commands
		else if ( commandstr.matches( RegexUtils.METHOD_DECLARATION_PATTERN ) ) {
			MatchResult res =
					RegexUtils.MatchSignle(
							RegexUtils.METHOD_DECLARATION_PATTERN, commandstr );
			// Get the name and return the command from the main scope
			String methodName = res.group( 3 );
			return scope.getMainScope().getMethod( methodName );
			
		}
        // Method invoke command
		else if ( commandstr.matches( RegexUtils.METHOD_INVOCATION_PATTERN ) ) {
            Expression expression = ExpressionFactory.instance().create( commandstr );
            try{
			    return (Command) expression;
            } catch (Exception ex){
                throw new CommandSyntaxException("Cannot create the method invocation command");
            }
		}
        // Return (value) command
		else if ( commandstr.matches(RegexUtils.METHOD_RETURN_STATEMENT) ) {
			MatchResult res =
					RegexUtils.MatchSignle( RegexUtils.METHOD_RETURN_STATEMENT, commandstr );
			return new ReturnCommand( res.group( 1 ) );
		}
		// End of scope command
		else if ( commandstr.equals( "}" ) ) {
			return new EndOFScopeCommand();
		}
		
		// if nothing matches - throw exception
		throw new SyncFailedException( commandstr + " cannot be recognized." );
	}
}
