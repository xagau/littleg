import java.util.HashMap;

public class ReservedTokens {

    HashMap<String, Token> tokenList = new HashMap<String,Token>();
    public void populate() {
        tokenList.put("++", Token.INCREMENT);
        tokenList.put("--", Token.DECREMENT);
        tokenList.put("=", Token.ASSIGNMENT);
        tokenList.put("==", Token.EQUAL);
        tokenList.put("!=", Token.NOT_EQUAL);
        tokenList.put("{", Token.OPEN_BLOCK);
        tokenList.put("}", Token.CLOSE_BLOCK);
        tokenList.put("<", Token.LT);
        tokenList.put(">", Token.GT);
        tokenList.put("<=", Token.LTE);
        tokenList.put(">=", Token.LTE);
        tokenList.put(".", Token.MEMBER_OF);
        tokenList.put(":", Token.OPTION_OF);
        tokenList.put("?", Token.IF);


    }

}
