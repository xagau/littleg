package littleg;

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

public class Variable {
    private String name;
    private String clazzType;
    private VisibilityModifier visibility = VisibilityModifier.PRIVATE;
    private PrimitiveType type = PrimitiveType.UNDEFINED;

    private boolean literalValue = false;

    private BigDecimal decimal = new BigDecimal(0);
    private Boolean bool = false;
    private String string = "";
    private Object object = null;
    private Void nothing = null;

    public void setValue(String val)
    {
        switch(getType()){
            case DECIMAL: {
                setDecimal(new BigDecimal(val));
                break;
            }
            case STRING: {
                setString(val);
                break;
            }
            case OBJECT: {
                setObject("{}");
                break;
            }
            case BOOLEAN: {
                setBool((Boolean.parseBoolean(val)));
                break;
            }
        }
    }


    public String getValue()
    {
        switch(getType()){
            case DECIMAL: {
                return getDecimal().toString();
            }
            case STRING: {
                return getString();
            }
            case OBJECT: {
                return getObject().toString();
            }
            case BOOLEAN: {
                return getBool().toString();
            }
        }
        return null;
    }

    public VisibilityModifier getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityModifier visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrimitiveType getType() {
        return type;
    }

    public void setType(PrimitiveType type) {
        this.type = type;
    }

    public String toString(){
        if( clazzType != null ){
            return type.toString() + " " + clazzType + " class instance named " + getName() + ":" + getValue();
        }
        return type.toString() + " " + getName() + ":" + getValue();
    }

    public String getClazzType() {
        return clazzType;
    }

    public void setClazzType(String clazzType) {
        this.clazzType = clazzType;
    }

    public boolean isLiteralValue() {
        return literalValue;
    }

    public void setLiteralValue(boolean literalValue) {
        this.literalValue = literalValue;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Void getNothing() {
        return nothing;
    }

    public void setNothing(Void nothing) {
        this.nothing = nothing;
    }
}
