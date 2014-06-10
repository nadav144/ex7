package oop.ex7.common;

import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by Nadav on 10/06/14.
 */
public class MainScope extends Scope {

    private HashMap<String, MethodDecleration> methodDeclerations;

    public MainScope(){
        super();
        methodDeclerations = new HashMap<String, MethodDecleration>();
    }

    public void addMethod(MethodDecleration decleration) throws Exception{
        if (methodDeclerations.containsKey(decleration.getName())){
            throw new Exception("Method defined twice");
        }
        methodDeclerations.put(decleration.getName(), decleration);
    }





}
