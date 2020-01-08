package Helpers;

import Lexems.*;

import java.util.Scanner;

public class LexemsFactory  {

    public static Lexem createLexem(String lexem, Scanner scanner){
        while (lexem.indexOf(" ") == 0) {
            lexem = lexem.substring(1);
        }
        String lexemName = lexem.substring(0, lexem.indexOf(" "));

        switch (lexemName) {
            case "Let":
                return new LetLexem(lexem.substring(lexemName.length()).trim());
            case "Print":
                return new PrintLexem(lexem.substring(lexemName.length()).trim());
            case "If" :
                return new IfLexem(lexem.substring(lexemName.length()).trim(), scanner);
            case "Input":
                return new InputLexem(lexem.substring(lexemName.length()).trim());
            case "While" :
                return new WhileLexem(lexem.substring(lexemName.length()).trim(), scanner);
            default: return null;
        }
    }
}
