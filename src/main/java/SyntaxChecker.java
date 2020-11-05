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

public class SyntaxChecker {

    public static boolean isPrimitiveType(String str)
    {
        try {
            str = str.toUpperCase();
            PrimitiveType t = PrimitiveType.valueOf(str);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static boolean isBalanced(String raw)
    {
        int block = 0;

        int len = raw.length();
        for(int i =0; i < len; i++ ){
            if( raw.charAt(i) == '{') {
                block++;
            }
            if ( raw.charAt(i) == '}') {
                block--;
            }
        }
        return block == 0;

    }

    public static boolean isVariable(String str)
    {
        str = str.trim();

        //System.out.println("Breakdown:" + str);
        String[] split = str.split(" ");
        if( split.length < 1) {
            return false;
        }
        if( !isBalanced(str) ) {
            return false;
        }
        if( isPrimitiveType(split[0]) ) {
            String varName = split[1];
            System.out.println("Variable:" + split[0] + " " + varName);
            return true;
        }
        return false;
    }


    public static boolean isClazzBalanced(Clazz clazz){
        String raw = clazz.getBody();
        int block = 0;
        int statements = 0;
        boolean checkBalanced = isBalanced(raw);
        if( block != 0 ) {
            //System.out.println("class:" + clazz.name + " has unbalanced brackets");
            //System.out.println("class:" + clazz.name + " has " + statements + " statements");
            return false;
        } else {
            //System.out.println("class:" + clazz.name + " has balanced brackets");
            //System.out.println("class:" + clazz.name + " has " + statements + " statements");
            return true;
        }
    }

}
