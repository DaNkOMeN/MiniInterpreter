package Lexems;

import Helpers.Expression;

import java.util.ArrayList;
import java.util.Map;

public class WhileLexem implements Lexem {

     private String condition;
     private ArrayList<Lexem> body;

    public WhileLexem(String condition, ArrayList<Lexem> body) {
        this.condition = condition;
        this.body = body;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ArrayList<Lexem> getBody() {
        return body;
    }

    public void setBody(ArrayList<Lexem> body) {
        this.body = body;
    }

    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        while ((boolean)Expression.eval(environment, condition)){
            for (Lexem lex : body){
                environment = lex.exec(environment);
            }
        }
        return environment;
    }
}
