package Lexems;

import Helpers.Expression;

import java.util.Map;

public class PrintLexem implements Lexem {

    private String body;

    public PrintLexem(String body) {
        this.body = body;
    }

    public PrintLexem() {
    }

    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        if (Expression.eval(environment, this.body) != null){
            System.out.println(Expression.eval(environment, this.body));
        } else {
            System.out.println(body);
        }
        return environment;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBody(){
        return this.body;
    }
}
