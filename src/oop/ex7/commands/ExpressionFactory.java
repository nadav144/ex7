
package oop.ex7.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import oop.ex7.common.Expression;
import oop.ex7.common.RegexUtils;
import oop.ex7.common.VarType;

public class ExpressionFactory {
	
	private static ExpressionFactory instance;
	
	private ExpressionFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static ExpressionFactory instance() {
		if ( instance == null ) {
			instance = new ExpressionFactory();
		}
		return instance;
	}
	
	public Expression create( String expression ) throws Exception {
		
		MatchResult result;
		if ( Pattern.matches( RegexUtils.METHOD_INVOCATION_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.METHOD_INVOCATION_PATTERN, expression );
			
			return new MethodInvocationExpression( result.group( 1 ),
					result.group( 2 ) );
		}
		if ( Pattern.matches( RegexUtils.OPERATION_PATTERN, expression ) ) {
			result =RegexUtils.MatchSignle( RegexUtils.OPERATION_PATTERN,
							expression );

			return new OperationExpression( result.group( 1 ),
					result.group( 2 ), result.group( 3 ) );
			
		}
		if ( Pattern.matches( RegexUtils.STRING_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.STRING_LITERAL_PATTERN,
							expression );
			return new LiteralExpression( VarType.STRING );
		}
		if ( Pattern.matches( RegexUtils.CHAR_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.CHAR_LITERAL_PATTERN,
							expression );
			return new LiteralExpression( VarType.CHAR );
		}
		if ( Pattern.matches( RegexUtils.DOUBLE_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.DOUBLE_LITERAL_PATTERN,
							expression );
            try{
                Integer.parseInt(result.group().trim());
                return new LiteralExpression(VarType.INT);
            }
            catch (Exception ex){
                return new LiteralExpression( VarType.DOUBLE );
            }
		}
		if ( Pattern.matches( RegexUtils.BOOLEAN_LITERAL_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.BOOLEAN_LITERAL_PATTERN,
							expression );
			return new LiteralExpression( VarType.BOOLEAN );
		}
		if ( Pattern.matches( RegexUtils.VARIABLE_NAME_PATTERN, expression ) ) {
			result =
					RegexUtils.MatchSignle( RegexUtils.VARIABLE_NAME_PATTERN,
							expression );
			return new VariableLiteralExpression( result.group( 1 ) );
		}
		if ( Pattern.matches( RegexUtils.NEGATED_VARIABLE_NAME_PATTERN,
				expression ) ) {
			result =
					RegexUtils.MatchSignle(
							RegexUtils.NEGATED_VARIABLE_NAME_PATTERN,
							expression );
			return new NegatedVariableLiteralExpression( result.group( 1 ) );
		}
		
		throw new Exception();
	}

	
}
