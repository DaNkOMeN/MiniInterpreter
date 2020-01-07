package Lexems;

import Helpers.Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IfLexem implements Lexem {

    private String condition;
    private ArrayList<Lexem> thenBody;
    private ArrayList<Lexem> elseBody;

    @Override
    public Map<String, String> exec(Map<String, String> environment) {

        if ((boolean)Expression.eval(environment, condition)){
            for (Lexem lex : thenBody){
               environment = lex.exec(environment);
            }
        } else {
            for (Lexem lex : elseBody){
                environment = lex.exec(environment);
            }
        }
        return environment;
    }

    public IfLexem(String condition, ArrayList<Lexem> thenBody, ArrayList<Lexem> elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ArrayList<Lexem> getThenBody() {
        return thenBody;
    }

    public void setThenBody(ArrayList<Lexem> thenBody) {
        this.thenBody = thenBody;
    }

    public ArrayList<Lexem> getElseBody() {
        return elseBody;
    }

    public void setElseBody(ArrayList<Lexem> elseBody) {
        this.elseBody = elseBody;
    }
}
