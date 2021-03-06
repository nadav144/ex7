
package oop.ex7.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Static class with simple regex operation used by the compiler.
 *  */
public class RegexUtils {

    /**
     * Match many results by regex pattern
     * @param pattern pattern to match by
     * @param content content to match to
     * @return list of MatchResults for that pattern and content
     */
	public static List< MatchResult > Match( String pattern, String content ) {
		List< MatchResult > returnValue = new ArrayList< MatchResult >();
		Pattern regex = Pattern.compile( pattern, Pattern.MULTILINE );
		
		Matcher matcher = regex.matcher( content );
		while ( matcher.find() ) {
			returnValue.add( matcher.toMatchResult() );
		}
		
		return returnValue;
		
	}

    /**
     * Match many results by regex pattern
     * @param pattern pattern to match by
     * @param content content to match to
     * @return list of MatchResults for that pattern and content
     */
	public static List< MatchResult > Match( String pattern, String[] content ) {
		List< MatchResult > returnValue = new ArrayList< MatchResult >();
		Pattern regex = Pattern.compile( pattern, Pattern.MULTILINE );
		
		for ( String string : content ) {
			Matcher matcher = regex.matcher( string );
			if ( matcher.find() ) {
				returnValue.add( matcher.toMatchResult() );
			}
		}
		
		return returnValue;
	}

    /**
     * Match a single results by regex pattern
     * @param pattern pattern to match by
     * @param content content to match to
     * @return a single, first MatchResult for that pattern and content
     */
	public static MatchResult MatchSignle( String pattern, String content ) {
		Pattern regex = Pattern.compile( pattern, Pattern.MULTILINE );
		Matcher matcher = regex.matcher( content );
		if ( matcher.find() ) {
			return matcher.toMatchResult();
		}
		return null;
		
	}

    //**********************
    // Regex Patterns
    //**********************


    public static final String END_OF_SCOPE_PATTERN = "}";

	/**
	 * group 1 - type, group 2 - name
	 */
	public static final String PARAM_PATTERN =
			"^\\s*(\\w*)\\s*(\\[\\])?\\s*(\\w*)\\s*$";

    public static final String METHOD_NAME_PATTERN = "\\s*([a-zA-Z]\\w*)\\s*";

    public static final String VARIABLE_NAME_PATTERN =
			"\\s*(_?[a-zA-Z]\\w*)\\s*";

    public static final String ARRAY_VARIABLE_NAME_PATTERN =
			"\\s*(_?[a-zA-Z]\\w*)\\s*\\[(.+)\\]\\s*";

    public static final String NEGATED_VARIABLE_NAME_PATTERN =
			"\\s*\\-\\s*(_?[a-zA-Z]\\w*)\\s*";
	/**
	 * group 1 - name group 2 - params
	 */
	public static final String METHOD_INVOCATION_PATTERN =
			"^\\s*([a-zA-Z]\\w*)\\s*\\((.*)\\)\\s*;?";
	/**
	 * group 1 - lhs, group 2 - operator, group 3 - rhs
	 */
	public static final String OPERATION_PATTERN =
			"^([-,+,*]*[^-,+,*,]+)([\\+\\*\\-/])(.+)\\s*$";
	/**
	 * group 1 - string
	 */
	public static final String STRING_LITERAL_PATTERN =
			"^\\s*(\"[^\"]*\")\\s*$";
	/**
	 * group 1 - char
	 */
	public static final String CHAR_LITERAL_PATTERN = "^\\s*('[^']')\\s*$";
	/**
	 * group 1 - double
	 */
	public static final String DOUBLE_LITERAL_PATTERN =
			"^\\s*(-?\\s*\\d+\\.?\\d*)\\s*$";
	/**
	 * group 1 - int value
	 */
	public static final String INT_LITERAL_PATTERN = "^\\s*(-?\\s*\\d+)\\s*$";
	/**
	 * group 1 - bool
	 */
	public static final String BOOLEAN_LITERAL_PATTERN =
			"^\\s*(true|false)\\s*$";
	
	public static final String METHOD_DECLARATION_PATTERN =
			"^\\s*(\\w+)(\\[\\])?\\s+(\\w+)\\s*\\((.*)\\)\\s*\\{$";
	
	public static final String ARRAY_ASSIGNMENT_LITERAL =
			"^\\s*\\{\\s*(.*)\\s*\\}\\s*$";

    public static final String COMMENT_INDICATOR = "//";

    public static final String LINE_ENDING_PATTERN = ".*[{|}|;]$";

    public static final String ENTIRE_METHOD_PATTERN =
			"\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{(.|\\n|\\r)*(return)(.|\\n|\\r)*\\}";

    public static final String IF_WHILE_STATEMENT =
            "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{";

    public static final String VAR_DECLARATION_STATEMENT =
            "(int|double|String|boolean|char)\\s*(\\[\\s*\\])?\\s*(\\w*)\\s*(?:=(.*))?\\s*;";

    public static final String VAR_ASSIGNMENT_STATEMENT =
            "\\s*(\\w*)\\s*(\\[(.*)\\])?\\s*=(.*);";

    public static final String METHOD_RETURN_STATEMENT = "return\\s*(.*);";
}
