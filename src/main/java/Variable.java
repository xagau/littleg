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

public class Variable {
    private String name;
    private VisibilityModifier visibility = VisibilityModifier.PRIVATE;
    private PrimitiveType type = PrimitiveType.UNDEFINED;

    BigDecimal decimal = new BigDecimal(0);
    Boolean bool = false;
    String string = "";
    Object object = null;
    Void nothing = null;

    public void setValue(String val)
    {
        switch(getType()){
            case DECIMAL: {
                decimal = new BigDecimal(val);
                break;

            }
            case STRING: {
                string = val;
                break;
            }
            case OBJECT: {
                object = "{}";
                break;

            }
            case BOOLEAN: {
                bool = (Boolean.parseBoolean(val));
                break;
            }
        }
    }


    public String getValue()
    {
        switch(getType()){
            case DECIMAL: {
                return decimal.toString();
            }
            case STRING: {
                return string;
            }
            case OBJECT: {
                return object.toString();
            }
            case BOOLEAN: {
                return bool.toString();
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
}
