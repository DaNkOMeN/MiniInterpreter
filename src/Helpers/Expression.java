package Helpers;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class Expression {


    public static Object eval(Map<String, String> vars, String code)  {
        try {
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("JavaScript");
            for (String var : vars.keySet()) {
                code = code.replace(var, vars.get(var));
            }
            return se.eval(code);
        } catch (ScriptException ex){
            return null;
        }
    }
}