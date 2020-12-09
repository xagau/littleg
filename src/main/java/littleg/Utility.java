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

public class Utility {

    public static BigDecimal getDefaultValueForBigDecimal()
    {
        return new BigDecimal("0");
    }

    public static String getDefaultValueForString()
    {
        return "";
    }

    public static Object getDefaultValueForObject()
    {
        return new Object();
    }

    public static Boolean getDefaultValueForBoolean()
    {
        return Boolean.FALSE;
    }



    public static String parseValueForPrimitiveType(PrimitiveType type, String val)
    {
        switch(type){
            case OBJECT:
                return "{}";
            case STRING:
                return val;
            case DECIMAL:
                return val;
        }
        return null;
    }

    public static String getDefaultValueForPrimitiveType(PrimitiveType type)
    {
        switch(type){
            case OBJECT:
                return "{}";
            case STRING:
                return "";
            case DECIMAL:
                return "0";
            case BOOLEAN:
                return "false";
        }
        return null;
    }


}
