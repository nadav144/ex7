package oop.ex7.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import oop.ex7.common.Expression;
import oop.ex7.common.RegexUtils;


public class ExpressionFactory {
	private static ExpressionFactory instance;
	
	private ExpressionFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static ExpressionFactory instance(){
		if(instance==null){
			instance=new ExpressionFactory();
		}
		return instance;
	}
	
	public Expression create(String expression){
		
		 MatchResult result;
		if ( Pattern.matches( RegexUtils.METHOD_INVOCATION_PATTERN ,  expression) ) {
			result= RegexUtils.MatchSignle( RegexUtils.METHOD_INVOCATION_PATTERN, expression );
			
			return new MethodInvocationExpression(result.group(1),result.group(2));
		}
		if (Pattern.matches( RegexUtils.OPERATION_PATTERN, expression )){
			result= RegexUtils.MatchSignle( RegexUtils.OPERATION_PATTERN, expression );
			
		}
		
		return null;
	}
	
}
