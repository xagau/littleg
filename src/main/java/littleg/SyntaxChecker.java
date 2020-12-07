import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

public class SyntaxChecker {

    public static ArrayList<Exception> analyze(ArrayList<Particle> list)
    {
        ArrayList<Exception> exceptions = new ArrayList<Exception>();
        Particle p = list.get(0);
        if( p.isReservedToken()) {
            exceptions.add(new UnexpectedTokenException("Unexpected token " + p.getRaw() + " @ position " + 0 + " expected class"));
            // later, try to collect as many as possible before returning.
            return exceptions;
        }
        p = list.get(0);
        if( p.isReservedKeyword()) {
            Keyword k = p.getKeyword();
            if( !k.equals(Keyword.CLASS)) { // later modify for imports/includes.
                exceptions.add(new UnexpectedTokenException("Unexpected keyword " + p.getRaw() + " @ position " + 0 + " expected class"));
                // later, try to collect as many as possible before returning.
                return exceptions;
            }
        }
        try {
            try {
                boolean b = isProgramBalanced(list);
            } catch(Exception ex) {
                exceptions.add(ex);
                return exceptions;
            }
            HashMap<String, Clazz> map = Parser.parseClasses(list);
            Set set = map.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                String s = (String)iterator.next();
                Clazz c = (Clazz)map.get(s);
                if( !isClazzBalanced(c)){
                    exceptions.add(new ClazzUnbalancedException(c.getName() + " is unbalanced"));
                }
                HashMap<String, Method> hm = c.getMethodSet();
                Set mset = hm.keySet();
                Iterator itr = mset.iterator();
                while( itr.hasNext()){
                    Method m = (Method)itr.next();
                    if( !isMethodBalanced(m)){

                    }
                }
            }
        } catch(Exception ex) {
            exceptions.add(ex);
        }
        return exceptions;
    }

    public static boolean isMethodBalanced(Method m) throws MethodUnbalancedException {

        ArrayList<Particle> list = m.getBody();

        int block = 0;
        for(int i =0; i < list.size(); i++ ){
            Particle p = list.get(i);
            if( p.isReservedToken() && p.getToken().equals(Token.OPEN_BLOCK)){
                block++;
            }
            if( p.isReservedToken() && p.getToken().equals(Token.OPEN_BLOCK)){
                block--;
            }
        }
        if( block < 0 ){
            throw new MethodUnbalancedException("Method " + m.getName() + " has too many closing blocks");
        }
        if( block < 0 ){
            throw new MethodUnbalancedException("Method " + m.getName() + " does not have enough closing blocks");
        }
        if( block == 0 ) {
            return true;
        }
        return false;
    }

    public static boolean isProgramBalanced(ArrayList<Particle> list) throws ProgramUnbalancedException {

        int block = 0;
        for(int i =0; i < list.size(); i++ ){
            Particle p = list.get(i);
            if( p.isReservedToken() && p.getToken().equals(Token.OPEN_BLOCK)){
                block++;
            }
            if( p.isReservedToken() && p.getToken().equals(Token.OPEN_BLOCK)){
                block--;
            }
        }
        if( block < 0 ){
            throw new ProgramUnbalancedException("Program has too many closing blocks");
        }
        if( block < 0 ){
            throw new ProgramUnbalancedException("Program does not have enough closing blocks");
        }
        if( block == 0 ) {
            return true;
        }
        return false;
    }

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
