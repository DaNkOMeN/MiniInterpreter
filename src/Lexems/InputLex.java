package Lexems;

import java.util.Map;
import java.util.Scanner;

public class InputLex implements Lexem {

    private String body;

    @Override
    public Map<String, String> exec(Map<String, String> environment) {
        System.out.println("Введите значение переменной " + body);
        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        environment.put(body, value);
        return environment;
    }

    public InputLex(String body) {
        this.body = body;
    }
}
