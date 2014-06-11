package oop.ex2.scanner;

import java.util.regex.Pattern;

/**
 * A configuration class containing all different constants used
 * 
 * @author kobi_atiya 301143244 kobi atiya
 */
public class Config {

	// Comments and empty line constants - ignored
	public static final String SINGLE_COMMENT = "//";
	public static final String COMMENT_SECTION_START = "/*";
	public static final String COMMENT_SECTION_END = "*/";
	public static final String EMPTY_LINE = "(?m)^\\s*$[\n\r]{1,}";

	// Block start and end
	public static final String PARENTHESES_START = "{";
	public static final String PARENTHESES_END = "}";

	// Method declaration
	public static final String METHOD_DECLERATION = "^\\s*(\\w*)\\s*(\\w*)\\s*\\((.*)\\)\\s*\\{";
	public static final String METHOD_NAME = "^(?!_)[A-Za-z_]\\w*";

	// Variable declaration regex
	public static final String VARIABLE_VALUE = "([-{0,1}\\w.*]*|\\\".*\\\"|'.*');\\s*";
	public static final String VAR_WITH_INIT_NOT_FINAL = "^\\s*(\\w*)\\s*(\\w*)\\s*=\\s*";
	public static final String VAR_WITN_INIT_FINAL = "^\\s*(\\w*)\\s*(\\w*)\\s*(\\w*)\\s*=\\s*";
	public static final String VAR_WITHOUT_INIT = "^\\s*(\\w*)\\s*(\\w*)\\s*;";

	// Variable assignment regex
	public static final String VAR_ASSIGNMENT = "^\\s*(\\w*)\\s*=\\s*";
	public static final String VALUES_METHOD_ASSIGNMENT = "(.*);";
	public static final String METHOD_CALL = "^\\s*(\\w*)\\((.*)\\);\\s*";

	// Return lines
	public static final String RETURN_LINE = "^\\s*return\\s*(.*);";

	// An if/while line
	public static final String IF_WHILE_STATEMENT = "^\\s*(?:if|while)\\s*\\((.*)\\)\\s*\\{";

	public static final String VAR_NAME = "^[A-Za-z]\\w*|^[_]\\w+";
	public static final String BRACKETS = "(\\w*)\\s*\\((.*)\\)";
	public static final String INLINE_COMMENT = "/\\*.*\\*/";
	public static final String SPACES_CHARS = "\\s+";
	
	//Default values for variables
	public static final String STRING_DEFAULT_VALUE = "\"\"";
	public static final String BOOLEAN_DEFAULT_VALUE = "0";
	public static final String CHAR_DEFAULT_VALUE = "''";

	// Regex patterns
	public static Pattern VAR_NO_INIT_PATTERN = Pattern.compile(Config.VAR_WITHOUT_INIT);
	public static Pattern VAR_INIT_NOT_FINAL_PATTERN = Pattern.compile(Config.VAR_WITH_INIT_NOT_FINAL+Config.VARIABLE_VALUE);
	public static Pattern VAR_INIT_FINAL_PATTERN = Pattern.compile(Config.VAR_WITN_INIT_FINAL+Config.VARIABLE_VALUE);
	public static Pattern METHOD_DECLERATION_PATTERN = Pattern.compile(Config.METHOD_DECLERATION);
	public static Pattern METHOD_NAME_PATTERN = Pattern.compile(Config.METHOD_NAME);
	public static Pattern VARIABLE_NAME_PATTERN = Pattern.compile(Config.VAR_NAME);
	public static Pattern VAR_ASSIGNMENT_PATTERN = Pattern.compile(VAR_ASSIGNMENT+Config.VARIABLE_VALUE);
	public static Pattern IF_WHILE_PATTERN = Pattern.compile(IF_WHILE_STATEMENT);
	public static Pattern BRACKETS_PATTERN = Pattern.compile(BRACKETS);
	public static Pattern RETURN_PATTERN = Pattern.compile(RETURN_LINE);
	public static Pattern EMPTY_LINE_PATTERN = Pattern.compile(EMPTY_LINE);
}
