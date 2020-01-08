package Lexems;

import java.util.Map;
import java.util.Scanner;

public class InputLexem implements Lexem {

    private String body;

    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        System.out.println("Введите значение переменной " + body);
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        environment.put(body, value);
        return environment;
    }

    public InputLexem(String body) {
        this.body = body;
    }

    public InputLexem() {
    }
}
