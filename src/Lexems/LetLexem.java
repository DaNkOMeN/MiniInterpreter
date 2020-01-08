package Lexems;

import Helpers.Expression;

import java.util.Map;

public class LetLexem implements Lexem {

    private String body;

    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        String var = body.substring(0,body.indexOf(" "));
        String value = Expression.eval(environment, body.substring(var.length()).trim()).toString();

        environment.put(var, value);
        return environment;
    }

    public LetLexem(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public LetLexem() {
    }

    public void setBody(String body) {
        this.body = body;
    }
}
