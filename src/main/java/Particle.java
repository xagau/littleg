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

import java.math.BigDecimal;

public class Particle {
    private boolean reservedKeyword = false;
    private boolean namedItem = false;
    private boolean literalValue = false;
    private boolean reservedToken = false;
    private boolean namedFunction = false;

    private String raw = "";

    private Keyword keyword = null;
    private Token token = null;
    private String name = null;
    private String stringValue = null;
    private BigDecimal decimalValue = null;
    private Boolean booleanValue = null;

    public Particle(String raw){
        this.setRaw(raw);
    }

    public String toString()
    {
        String str = "";
        if(isReservedKeyword()){
            str += "Is a keyword:" + getKeyword().toString();
        }
        if(isReservedToken()){
            str += "Is a token:" + getToken().toString();
        }
        if(isLiteralValue()){
            str += "Is a literalValue:" + isLiteralValue() + "[";
            if( getStringValue() != null ){
                str += "string:" + getStringValue();
            }
            if( getBooleanValue() != null ){
                str += "boolean:" + getBooleanValue().toString();
            }
            if( getDecimalValue() != null ){
                str += "decimal:" + getDecimalValue().toString();
            }
            str += "]";
        }
        if(isNamedItem()){
            str += "Is a namedItem:" + isNamedItem() + " [" + getName() + "]";
        }
        if(isNamedFunction()){
            str += "Is a namedFunction:" + isNamedFunction() + " [" + getName() + "]";
        }
        return str;
    }

    public boolean isReservedKeyword() {
        return reservedKeyword;
    }

    public void setReservedKeyword(boolean reservedKeyword) {
        this.reservedKeyword = reservedKeyword;
    }

    public boolean isNamedItem() {
        return namedItem;
    }

    public void setNamedItem(boolean namedItem) {
        this.namedItem = namedItem;
    }

    public boolean isLiteralValue() {
        return literalValue;
    }

    public void setLiteralValue(boolean literalValue) {
        this.literalValue = literalValue;
    }

    public boolean isReservedToken() {
        return reservedToken;
    }

    public void setReservedToken(boolean reservedToken) {
        this.reservedToken = reservedToken;
    }

    public boolean isNamedFunction() {
        return namedFunction;
    }

    public void setNamedFunction(boolean namedFunction) {
        this.namedFunction = namedFunction;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(BigDecimal decimalValue) {
        this.decimalValue = decimalValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
