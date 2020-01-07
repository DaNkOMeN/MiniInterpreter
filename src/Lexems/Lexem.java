package Lexems;

import java.util.Map;

public interface Lexem {
    public Map<String, String> exec(Map<String, String> environment);
}
