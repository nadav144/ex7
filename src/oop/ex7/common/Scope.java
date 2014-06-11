package oop.ex7.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadav on 10/06/14.
 */
public class Scope {

    private List<Scope> subScopes;
    private List<VariableDeclaration> vars;

    private Scope parentScope;

    public Scope(){
        subScopes = new ArrayList<Scope>();
        vars = new ArrayList<VariableDeclaration>();
    }

    public MainScope getMainScope(){

        Scope parent = parentScope;
        while (parent.getClass() == Scope.class){
            parent = parent.parentScope;
        }

        if (parent.getClass() == MainScope.class)
            return (MainScope)parent;
        return null;

    }



}
