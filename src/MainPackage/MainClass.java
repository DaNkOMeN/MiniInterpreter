package MainPackage;

import Helpers.LexemsFactory;
import Lexems.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    private static Map<String, String> environment = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

//testWhileIf.qqq
        File textOfProgram = new File("src/resources/testWhileIf.qqq");
        Scanner scanner = new Scanner(textOfProgram);
        Lexem lexem;
        while ((lexem = getNextLexem(scanner)) != null) {
            eval(lexem, environment);
        }

    }

    public static Map<String, String> eval(Lexem lexem, Map<String, String> environment) {
        return lexem.exec(environment);
    }

    public static Lexem getNextLexem(Scanner scanner) {
        if (scanner.hasNext()){
            String currentLexem = scanner.nextLine();
            if (!currentLexem.isEmpty()) {
                return LexemsFactory.createLexem(currentLexem, scanner);
            } else {
                if (currentLexem.isEmpty()) {
                    if (scanner.hasNext()) {
                        return getNextLexem(scanner);
                    } else {
                        return null;
                    }
                } else {
                    return LexemsFactory.createLexem(currentLexem, scanner);
                }
            }

        } else return null;
    }
}
