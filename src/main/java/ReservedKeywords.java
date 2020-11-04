import java.util.HashMap;

public class ReservedKeywords {
    HashMap<String, Keyword> reservedKeywords = new HashMap<String, Keyword>();

    ReservedKeywords() {
        populate();
    }

    public void populate() {
        reservedKeywords.put("if", Keyword.IF);
        reservedKeywords.put("decimal", Keyword.DECIMAL);
        reservedKeywords.put("class", Keyword.CLASS);
        reservedKeywords.put("continue", Keyword.CONTINUE);
        reservedKeywords.put("for", Keyword.FOR);
        reservedKeywords.put("while", Keyword.WHILE);
        reservedKeywords.put("null", Keyword.NULL);
        reservedKeywords.put("case", Keyword.CASE);
        reservedKeywords.put("switch", Keyword.SWITCH);
        reservedKeywords.put("string", Keyword.STRING);
        reservedKeywords.put("boolean", Keyword.BOOLEAN);
        reservedKeywords.put("object", Keyword.OBJECT);
        reservedKeywords.put("return", Keyword.RETURN);
        reservedKeywords.put("break", Keyword.BREAK);
        reservedKeywords.put("null", Keyword.NULL);
        reservedKeywords.put("new", Keyword.NEW);
    }

    public Keyword get(String key)
    {
        return reservedKeywords.get(key);
    }
}