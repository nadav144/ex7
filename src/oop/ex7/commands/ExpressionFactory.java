
package oop.ex7.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import oop.ex7.Exceptions.ExpressionSyntaxException;
import oop.ex7.common.*;

/**
 * Expression Factory allows the compiler to create complex expression. each expression has a return type and may
 * consist of inner expression. the facory uses complex regex pattern in order to match the given string expression
 * representation and return a new instance of the right expression object.
 */
public class ExpressionFactory {
	public static final String UNKOWN_EXPRESSION_FORMAT_ERROR = "Unkown expression format: '%s'";

    private static ExpressionFactory instance;

    /**
     * @return the singleton instance of the Expression Factory.
     */
	public static ExpressionFactory instance() {
		if ( instance == null ) {
			instance = new ExpressionFactory();
		}
		return instance;
	}

    /**
     * Creates a new Expression object from string content
     * @param expression strint representation of the expresssion to create
     * @return a new Expression object matches the string content
     * @throws ExpressionSyntaxException exception in case of bad syntax of invalid expression string content
     */
	public Expression create( String expression ) throws ExpressionSyntaxException {
		expression = expression.trim();
		MatchResult result;
        // Array Assignment
		if ( Pattern.matches( RegexUtils.ARRAY_ASSIGNMENT_LITERAL, expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.ARRAY_ASSIGNMENT_LITERAL, expression );
			
			return new ArrayLiteralExpression( result.group( 1 ) );
		}

        // Method Invocation
		if ( Pattern.matches( RegexUtils.METHOD_INVOCATION_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.METHOD_INVOCATION_PATTERN, expression );
			
			return new MethodInvocationExpression( result.group( 1 ),
					result.group( 2 ) );
		}

        // Operation (e.g 5+6)
		if ( Pattern.matches( RegexUtils.OPERATION_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.OPERATION_PATTERN,
							expression );
			
			return new OperationExpression( result.group( 1 ),
					result.group( 2 ), result.group( 3 ) );
			
		}

        // String Literal ""
		if ( Pattern.matches( RegexUtils.STRING_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.STRING_LITERAL_PATTERN,
							expression );
			return new LiteralExpression(
					new TermType( TermType.VarType.STRING ) );
		}

        // Char Literal ''
		if ( Pattern.matches( RegexUtils.CHAR_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.CHAR_LITERAL_PATTERN,
							expression );
			return new LiteralExpression( new TermType( TermType.VarType.CHAR ) );
		}

        // Double or Int Literal 6.7
		if ( Pattern.matches( RegexUtils.DOUBLE_LITERAL_PATTERN, expression ) ) {
            expression = expression.replace(" ", "" );
			result =
					RegexUtils.MatchSignle( RegexUtils.DOUBLE_LITERAL_PATTERN,
							expression );
			try {
                int number = Integer.parseInt( result.group(0).trim() );
				Integer.parseInt( result.group().trim() );

                // Positive numbers are requires for array position assignment.
				return new LiteralExpression( new TermType(
						TermType.VarType.INT ), number >= 0 );
			}
			catch ( Exception ex ) {
                // this must be a double
				return new LiteralExpression( new TermType(
						TermType.VarType.DOUBLE ) );
			}
		}

        // Boolean Literal true/false
		if ( Pattern.matches( RegexUtils.BOOLEAN_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.BOOLEAN_LITERAL_PATTERN,
							expression );
			return new LiteralExpression( new TermType(
					TermType.VarType.BOOLEAN ) );
		}

        // Array Var []
		if ( Pattern.matches( RegexUtils.ARRAY_VARIABLE_NAME_PATTERN,
				expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.ARRAY_VARIABLE_NAME_PATTERN, expression );
			return new ArrayItemLiteralExpression( result.group( 1 ),
					create( result.group( 2 ) ) );
		}

        // Var Name a
		if ( Pattern.matches( RegexUtils.VARIABLE_NAME_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.VARIABLE_NAME_PATTERN,
							expression );
			return new VariableLiteralExpression( result.group( 1 ) );
		}

        // Negated Var (-a)
		if ( Pattern.matches( RegexUtils.NEGATED_VARIABLE_NAME_PATTERN,
				expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.NEGATED_VARIABLE_NAME_PATTERN,
							expression );
			return new NegatedVariableLiteralExpression( result.group( 1 ) );
		}

        // No Syntax of expression found. this must be an error.
		throw new ExpressionSyntaxException(String.format( UNKOWN_EXPRESSION_FORMAT_ERROR, expression ));
	}
		
}
