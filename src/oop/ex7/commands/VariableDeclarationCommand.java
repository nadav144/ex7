package oop.ex7.commands;

import oop.ex7.ValidationResult;
import oop.ex7.common.AssignmentExpression;
import oop.ex7.common.Command;
import oop.ex7.common.CommandValidator;
import oop.ex7.common.Expression;
import oop.ex7.common.Scope;
import oop.ex7.common.Variable;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * Created by Nadav on 10/06/14.
 */
public class VariableDeclarationCommand implements Command {

	private Variable var;
	private AssignmentExpression assign;
	
    public VariableDeclarationCommand(String type, String name){
    	this(type,name,null);
    }
    
    public VariableDeclarationCommand(String type, String name,String assignment){
    	var=new Variable( type, name );
    	if (assignment==null){
    		var.setInited(false);
    	}
    	else{
    		
    		assign=new AssignmentExpression(var,new Expression(assignment));
    	}
    	
    }


    @Override
    public ValidationResult isValid(String expression, Scope scope) {
    	ValidationResult result=new ValidationResult();
    	
    	result.append( getVar().isValid( expression, scope ) );
    	if (getAssign()!=null){
    	result.append( getAssign().isValid( expression, scope ) );
    	}
    	
    	if (result.getsuccessful()){
    		scope.getVars().add( getVar() );
    	}
    	
    	return result;
    }

    
	/**
	 * @return the var
	 */
	private Variable getVar() {
		return var;
	}

	
	/**
	 * @return the assign
	 */
	private AssignmentExpression getAssign() {
		return assign;
	}

	@Override
    public boolean isScope() {
        return false;
    }
}
