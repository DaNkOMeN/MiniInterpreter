package Lexems;

import Helpers.Expression;
import Helpers.LexemsFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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

    public WhileLexem(String condition, Scanner scanner) {
        this.condition = condition;
        body = new ArrayList<>();
        int currentWhile = 0;
        String currentLexem;
        while (!((currentLexem = getNormalLexemNext(scanner)).equals( "endWhile"))) {
            if (!currentLexem.isEmpty()) {
                if (currentLexem.equals("endWhile")) {
                    currentWhile--;
                } else {
                    if (getNormalLexem(currentLexem).equals("While")) currentWhile++;
                    body.add(LexemsFactory.createLexem(currentLexem, scanner));
                }
            }
        }

    }


    public String getNormalLexemNext(Scanner scanner){
        String currentLexem = scanner.nextLine();
        while (currentLexem.indexOf(" ") == 0) {
            currentLexem = currentLexem.substring(1);
        }
        return currentLexem;
    }

    public String getNormalLexem(String string){
        while (string.indexOf(" ") == 0) {
            string = string.substring(1);
        }
        return string;
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
        while (expressionToBoolean(Expression.eval(environment, condition))){
            for (Lexem lex : body){
                environment = lex.exec(environment);
            }
        }
        return environment;
    }

    public boolean expressionToBoolean(Object ex) {
        if (ex instanceof Double) {
            if ((Double) ex < 0) {
                return false;
            } else {
                return true;
            }
        }
        if (ex instanceof Integer) {
            if ((Integer) ex < 0) {
                return false;
            } else {
                return true;
            }
        }
        if (ex instanceof String){
            return true;
        }
        else return (boolean) ex;
    }
}
