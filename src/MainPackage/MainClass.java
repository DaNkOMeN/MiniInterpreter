package MainPackage;

import Lexems.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {

    private static Map<String, String> environment = new HashMap<>();
    private static ArrayList<Lexem> arrLexem;


    public static void main(String[] args) throws FileNotFoundException{


        File textOfProgram = new File("src/resources/test.qqq");
        Scanner scanner = new Scanner(textOfProgram);
        arrLexem = new ArrayList<>();
        arrLexem.add(new LetLexem("n 1024"));
        //arrLexem.add(new LetLexem("step 2"));
        arrLexem.add(new InputLex("step"));
        arrLexem.add(new LetLexem("pos 0"));
        arrLexem.add(new LetLexem("len 2"));


        ArrayList<Lexem> forWhile = new ArrayList<>();
        forWhile.add(new LetLexem("pos (step+pos)%len"));
        forWhile.add(new LetLexem("len len+1"));

        arrLexem.add(new WhileLexem("len<=n",forWhile));
        arrLexem.add(new PrintLexem("pos+1"));
        for (Lexem lexem : arrLexem) {
            environment = eval(lexem, environment);
        }


    }

    public static Map<String, String> eval(Lexem lexem, Map<String, String> environment){
        return lexem.exec(environment);
    }
}
