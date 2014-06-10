package oop.ex7.common;

import java.util.List;

/**
 * Created by Nadav on 10/06/14.
 */
public class Scope {

    List<Scope> subScopes;
    List<Variable> vars;

    Scope parentScope;

    public MainScope getMainScope(){

        // Go to parent until you get the main scope

        return null;

    }



}
