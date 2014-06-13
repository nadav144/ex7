package oop.ex7.common;

import oop.ex7.commands.MethodDeclaration;

import java.util.HashMap;

/**
 * Created by Nadav on 10/06/14.
 */
public class MainScope extends Scope {

    private HashMap<String, MethodDeclaration> methodDeclarations;

    public MainScope(){
        super();
        methodDeclarations = new HashMap<String, MethodDeclaration>();
    }

    public void addMethod(MethodDeclaration declaration) throws Exception{
        if (methodDeclarations.containsKey(declaration.getName())){
            throw new Exception("Method defined twice");
        }
        methodDeclarations.put(declaration.getName(), declaration);
    }
    
    public MethodDeclaration getMethod(String name){
    	return methodDeclarations.get( name );
    }





}
