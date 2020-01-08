package Lexems;

import Helpers.Expression;
import Helpers.LexemsFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IfLexem implements Lexem {

    private String condition;
    private ArrayList<Lexem> thenBody ;
    private ArrayList<Lexem> elseBody ;



    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        Map<String, String> localEnvironment = new HashMap<String, String>(environment);
        if (expressionToBoolean(Expression.eval(environment, condition))){
            for (Lexem lex : thenBody){
               localEnvironment = lex.exec(localEnvironment);
            }
        } else {
            for (Lexem lex : elseBody){
                localEnvironment = lex.exec(localEnvironment);
            }
        }
        for (Map.Entry<String, String> mainEnv : environment.entrySet()){
            mainEnv.setValue(localEnvironment.get(mainEnv.getKey()));
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
        if (ex instanceof Integer){
            if ((Integer)ex <0 ){
                return false;
            } else {
                return true;
            }
        }
        if (ex instanceof String){
            return true;
        }
        else return (boolean)ex;
    }

    public IfLexem() {
    }

    public IfLexem(String condition, ArrayList<Lexem> thenBody, ArrayList<Lexem> elseBody) {
        this.condition = condition;
        this.thenBody = thenBody;
        this.elseBody = elseBody;
    }


    public IfLexem(String condition, Scanner scanner) {
        thenBody = new ArrayList<>();
        elseBody = new ArrayList<>();
        this.condition = condition;
        String currentLexem;

        boolean hasElse = false;
        int currentIf = 0;
        while ((!((currentLexem = getNormalLexemNext(scanner)).equals( "endIf")) || currentIf != 0)) {
            if (!currentLexem.isEmpty()) {
                if (currentLexem.equals("endIf")) {
                    currentIf--;
                }
                if (currentLexem.equals("elseIf")) {
                    hasElse = true;
                } else {
                    if (getNormalLexem(currentLexem).equals("If")) currentIf++;
                    if (hasElse) {
                        elseBody.add(LexemsFactory.createLexem(currentLexem, scanner));
                    } else {
                        thenBody.add(LexemsFactory.createLexem(currentLexem, scanner));
                    }
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
