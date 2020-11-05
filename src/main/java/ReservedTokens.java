import java.util.HashMap;

public class ReservedTokens {

   HashMap<String, Token> tokenList = new HashMap<String,Token>();
    ReservedTokens() {
        populate();
    }
    public void populate() {
        tokenList.put("*", Token.MULTIPLY);
        tokenList.put("/", Token.DIVIDE);
        tokenList.put("-", Token.MINUS);
        tokenList.put("+", Token.PLUS);
        tokenList.put("=", Token.ASSIGNMENT);
        tokenList.put("!", Token.NOT);
        tokenList.put("(", Token.OPEN_BRACE);
        tokenList.put(")", Token.CLOSE_BRACE);
        tokenList.put("{", Token.OPEN_BLOCK);
        tokenList.put("}", Token.CLOSE_BLOCK);
        tokenList.put("[", Token.OPEN_INDEX);
        tokenList.put("]", Token.CLOSE_INDEX);
        tokenList.put("<", Token.LT);
        tokenList.put(">", Token.GT);
        tokenList.put(",", Token.COMMA);
        tokenList.put("&", Token.AND);
        tokenList.put("|", Token.OR);
        tokenList.put(".", Token.MEMBER_OF);
        tokenList.put(":", Token.OPTION_OF);
        tokenList.put(";", Token.END_STATEMENT);
        // Double token
        tokenList.put("!=", Token.NOT_EQUAL);
        tokenList.put("<=", Token.LTE);
        tokenList.put("&&", Token.LOGICAL_AND);
        tokenList.put("||", Token.LOGICAL_OR);
        tokenList.put(">=", Token.LTE);
        tokenList.put("++", Token.INCREMENT);
        tokenList.put("--", Token.DECREMENT);
        tokenList.put("==", Token.EQUAL);
    }

    public Token get(String token)
    {
        return tokenList.get(token);
    }

}
