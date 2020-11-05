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

import java.math.BigDecimal;

public class Particle {
    public boolean reservedKeyword = false;
    public boolean namedItem = false;
    public boolean literalValue = false;
    public boolean reservedToken = false;
    public boolean namedFunction = false;

    public String raw = "";

    public Keyword keyword = null;
    public Token token = null;
    public String name = null;
    public String stringValue = null;
    public BigDecimal decimalValue = null;
    public Boolean booleanValue = null;

    public Particle(String raw){
        this.raw = raw;
    }

    public String toString()
    {
        String str = "";
        if( reservedKeyword ){
            str += "Is a keyword:" + keyword.toString();
        }
        if( reservedToken ){
            str += "Is a token:" + token.toString();
        }
        if( literalValue ){
            str += "Is a literalValue:" + literalValue + "[";
            if( stringValue != null ){
                str += "string:" + stringValue;
            }
            if( booleanValue != null ){
                str += "boolean:" + booleanValue.toString();
            }
            if( decimalValue != null ){
                str += "decimal:" + decimalValue.toString();
            }
            str += "]";
        }
        if( namedItem ){
            str += "Is a namedItem:" + namedItem + " [" + name + "]";
        }
        if( namedFunction ){
            str += "Is a namedFunction:" + namedFunction + " [" + name + "]";
        }
        return str;
    }
}
