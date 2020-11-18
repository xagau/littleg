/** Copyright (c) 2020 Sean Beecroft,
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Sean Beecroft (aka xagau) https://www.github.com/xagau
 *
 */

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
