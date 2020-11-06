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
 * @author Sean Beecorft (aka xagau) https://www.github.com/xagau
 *
 */

import java.util.HashMap;

public class ReservedKeywords {
    HashMap<String, Keyword> reservedKeywords = new HashMap<String, Keyword>();

    ReservedKeywords() {
        populate();
    }

    public void populate() {
        reservedKeywords.put("if", Keyword.IF);
        reservedKeywords.put("decimal", Keyword.DECIMAL);
        reservedKeywords.put("void", Keyword.VOID);
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
        reservedKeywords.put("else", Keyword.ELSE);
    }

    public Keyword get(String key)
    {
        return reservedKeywords.get(key);
    }
}