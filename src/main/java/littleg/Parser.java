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

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Parser {

    static ReservedTokens tokenLookup = new ReservedTokens();
    static ReservedKeywords keywordLookup = new ReservedKeywords();
    static HashMap<String,Clazz> classLookup = new HashMap<String, Clazz>();

    static int index = 0;
    public static HashMap<String, Method> parseMethods(Clazz clazz)
    {
        HashMap<String, Method> methodSet = new HashMap<String, Method>();

        try {
            // TODO
            ArrayList<Particle> list = clazz.getBodySet();


            System.out.println("Analyzing particles looking to extract methods from class:" + clazz.getName());

            for (; index < list.size(); index++) {
                Particle p = list.get(index);
                Particle pp = list.get(index+1);
                //.println(p);
                if (p.isReservedKeyword() && pp.isNamedFunction()) {
                    //System.out.println("Got lead:" + pp.getName());
                    //System.in.read();
                    Method m = new Method();
                    m.setName(pp.getName());
                    if (p.getKeyword().equals(Keyword.DECIMAL)) {
                        //.println("Found Method:" + m.getName());
                        m.setReturnType(PrimitiveType.DECIMAL);
                        m = parseMethod(m, list, clazz);
                        clazz.addMethod(m);
                        methodSet.put(m.getSignature(), m);

                    } else if (p.getKeyword().equals(Keyword.BOOLEAN)) {
                        //System.out.println("Found Method:" + m.getName());
                        m.setReturnType(PrimitiveType.BOOLEAN);
                        m = parseMethod(m, list, clazz);
                        clazz.addMethod(m);
                        methodSet.put(m.getSignature(), m);

                    } else if (p.getKeyword().equals(Keyword.VOID)) {
                        //System.out.println("Found Method:" + m.getName());
                        m.setReturnType(PrimitiveType.VOID);
                        m = parseMethod(m, list, clazz);
                        clazz.addMethod(m);
                        methodSet.put(m.getSignature(), m);

                    } else if (p.getKeyword().equals(Keyword.STRING)) {
                        //System.out.println("Found Method:" + m.getName());
                        m.setReturnType(PrimitiveType.STRING);
                        m = parseMethod(m, list, clazz);
                        clazz.addMethod(m);
                        methodSet.put(m.getSignature(), m);


                    } else if (p.getKeyword().equals(Keyword.OBJECT)) {
                        //System.out.println("Found Method:" + m.getName());
                        m.setReturnType(PrimitiveType.OBJECT);
                        m = parseMethod(m, list, clazz);
                        clazz.addMethod(m);
                        methodSet.put(m.getSignature(), m);

                    }
                }

            }

            return methodSet;

        } catch(IndexOutOfBoundsException ex ) {
            //ex.printStackTrace();
            System.out.println("All methods extracted from " + clazz.getName());
        }
        return methodSet;
    }

    public static void printNormalizedList(ArrayList<Particle> list)
    {
        for(int i = 0; i < list.size(); i++){
            Particle p = list.get(i);
            try {
                boolean interactive = false;
                if (Globals.verbose) {
                    System.out.println("Parser::" + i + ":" + p );
                }
                if (interactive) {
                    System.in.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Method parseMethod(Method m, ArrayList<Particle> list, Clazz clazz)
    {
        Parser.printNormalizedList(list);
        System.out.println("Parse Method:" + m.getName() + " of " + clazz.getName());

        boolean arglistDone = false;
        ArrayList<Particle> argumentList = new ArrayList<Particle>();
        ArrayList<Particle> bodyList = new ArrayList<Particle>();
        ArrayList<Variable> localVariables = new ArrayList<Variable>();
        boolean definedReturnType = false;

        for(int j = index; j < list.size(); j++ ) {
            try {
                Particle pp = list.get(j);

                if (!arglistDone) {
                    if( definedReturnType == false ) {
                        if (pp.isReservedKeyword() && definedReturnType == false) {
                            if (pp.getKeyword().equals(Keyword.BOOLEAN)) {
                                m.setReturnType(PrimitiveType.BOOLEAN);
                                definedReturnType = true;
                            } else if (pp.getKeyword().equals(Keyword.DECIMAL)) {
                                m.setReturnType(PrimitiveType.DECIMAL);
                                definedReturnType = true;
                            } else if (pp.getKeyword().equals(Keyword.STRING)) {
                                m.setReturnType(PrimitiveType.STRING);
                                definedReturnType = true;
                            } else if (pp.getKeyword().equals(Keyword.OBJECT)) {
                                m.setReturnType(PrimitiveType.OBJECT);
                                definedReturnType = true;
                            } else if (pp.getKeyword().equals(Keyword.VOID)) {
                                m.setReturnType(PrimitiveType.VOID);
                                definedReturnType = true;
                            }
                        }
                    } else {
                        if (pp.isReservedToken() && pp.getToken().equals(Token.OPEN_BRACE)) {
                            argumentList.add(pp);
                        } else if (pp.isReservedToken() && pp.getToken().equals(Token.CLOSE_BRACE)) {
                            argumentList.add(pp);
                            arglistDone = true;
                        } else {
                            argumentList.add(pp);
                        }
                    }

                }
                // if args list is done but there is no return type, we can flag this as an error.
                // TODO

                int block = 0;

                if (pp.isReservedToken() && pp.getToken().equals(Token.OPEN_BLOCK)) {
                    block++;
                    bodyList.add(pp);
                }
                else if (pp.isReservedToken() && pp.getToken().equals(Token.CLOSE_BLOCK)) {
                    block--;
                    bodyList.add(pp);
                    if (block == 0) {
                        index = j;
                        break;
                    }
                } else if( pp.isReservedToken() && pp.getToken().equals(Token.END_STATEMENT) ) {
                    bodyList.add(pp);
                } else {
                    try {
                        if (pp.isReservedKeyword() && pp.getKeyword().equals(Keyword.DECIMAL)) {
                            Variable v = new Variable();
                            v.setType(PrimitiveType.DECIMAL);
                            Particle pn = list.get(j + 1);
                            if (pn.isNamedItem() && !pn.isNamedFunction()) {
                                v.setName(pn.getName());
                                localVariables.add(v);
                            }
                        }
                    } catch(Exception ex) { ex.printStackTrace();}

                    try {
                        if (pp.isReservedKeyword() && pp.getKeyword().equals(Keyword.STRING)) {
                            Variable v = new Variable();
                            v.setType(PrimitiveType.STRING);
                            Particle pn = list.get(j + 1);
                            if (pn.isNamedItem() && !pn.isNamedFunction()) {
                                v.setName(pn.getName());
                                localVariables.add(v);
                            }
                        }
                    } catch(Exception ex) { ex.printStackTrace(); }

                    try {
                        if (pp.isReservedKeyword() && pp.getKeyword().equals(Keyword.BOOLEAN)) {
                            Variable v = new Variable();
                            v.setType(PrimitiveType.BOOLEAN);
                            Particle pn = list.get(j + 1);
                            if (pn.isNamedItem() && !pn.isNamedFunction()) {
                                v.setName(pn.getName());
                                localVariables.add(v);
                            }
                        }

                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }

                    try {
                        if (pp.isReservedKeyword() && pp.getKeyword().equals(Keyword.OBJECT)) {
                            Variable v = new Variable();
                            v.setType(PrimitiveType.OBJECT);
                            Particle pn = list.get(j + 1);
                            if (pn.isNamedItem() && !pn.isNamedFunction()) {
                                v.setName(pn.getName());
                                localVariables.add(v);
                            }

                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        if (pp.isNamedItem() && classLookup.get(pp.getName()) != null) {
                            Particle pn = list.get(j + 1);
                            if (pn.isNamedItem()) {
                                Variable v = new Variable();
                                v.setType(PrimitiveType.OBJECT);
                                v.setClazzType(pp.getName());
                                v.setName(pn.getName());
                                v.setValue("");
                                localVariables.add(v);
                            } else {
                                // syntax error ?
                            }
                        }

                    } catch(Exception ex){
                        ex.printStackTrace();

                    }

                    bodyList.add(pp);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        String argumentListStr = "";

        for (int k = 0; k < argumentList.size(); k++) {
            argumentListStr += ((Particle) argumentList.get(k)).getRaw() + " ";
        }
        String sig = m.getReturnType().toString().toLowerCase() + " " + argumentListStr.trim();
        System.out.println("Adding signature for method:" + m.getName() + ":" + sig);
        m.setBody(bodyList);
        m.setArguments(argumentList);
        m.setSignature(sig);
        m.setLocalVariables(localVariables);
        return m;


    }

    public static HashMap<String, Variable> parseVariables(Clazz clazz)
    {
        // TODO
        HashMap<String, Variable> variableSet = new HashMap<String, Variable>();
        ArrayList<Particle> list = clazz.getBodySet();

        int block = 0;

        for(int i = 0; i < list.size(); i++ ) {
            Particle p = list.get(i);
            if( p.isReservedToken() && p.getToken().equals(Token.OPEN_BLOCK)){
                block++;
            }
            if( p.isReservedToken() && p.getToken().equals(Token.CLOSE_BLOCK)){
                block--;
            }
            if( block == 1 ) {
                if (p.isReservedKeyword()) {
                    if (p.getKeyword().equals(Keyword.BOOLEAN)) {
                        Particle pn = list.get(i+1);
                        if( pn.isNamedItem() ){
                            Particle pnn = list.get(i+2);
                            if( (pnn.isReservedToken() && pnn.getToken().equals(Token.ASSIGNMENT)) || (pnn.isReservedToken() && pnn.getToken().equals(Token.END_STATEMENT)) ){
                                Variable v = new Variable();
                                v.setName(pn.getName());
                                v.setValue("false");
                                v.setType(PrimitiveType.BOOLEAN);
                                if( Globals.verbose ) {
                                    System.out.println("Adding " + v + " to " + clazz.getName() + " var set");
                                }
                                variableSet.put(pn.getName(), v);
                                i++;
                                i++;
                            }
                        }
                    } else if (p.getKeyword().equals(Keyword.STRING)) {
                        Particle pn = list.get(i+1);
                        if( pn.isNamedItem() ){
                            Particle pnn = list.get(i+2);
                            if( (pnn.isReservedToken() && pnn.getToken().equals(Token.ASSIGNMENT)) || (pnn.isReservedToken() && pnn.getToken().equals(Token.END_STATEMENT)) ){
                                Variable v = new Variable();
                                v.setName(pn.getName());
                                v.setValue("");
                                v.setType(PrimitiveType.STRING);
                                if( Globals.verbose ) {
                                    System.out.println("Adding " + v + " to " + clazz.getName() + " var set");
                                }
                                variableSet.put(pn.getName(), v);
                                i++;
                                i++;
                            }
                        }
                    }  else if (p.getKeyword().equals(Keyword.DECIMAL)) {
                        Particle pn = list.get(i+1);
                        if( pn.isNamedItem() ){
                            Particle pnn = list.get(i+2);
                            if( (pnn.isReservedToken() && pnn.getToken().equals(Token.ASSIGNMENT)) || (pnn.isReservedToken() && pnn.getToken().equals(Token.END_STATEMENT)) ){
                                Variable v = new Variable();
                                v.setName(pn.getName());
                                v.setValue("0");
                                v.setType(PrimitiveType.DECIMAL);
                                if( Globals.verbose ) {
                                    System.out.println("Adding " + v + " to " + clazz.getName() + " var set");
                                }
                                variableSet.put(pn.getName(), v);
                                i++;
                                i++;
                            }
                        }
                    } else if (p.getKeyword().equals(Keyword.OBJECT)) {
                        Particle pn = list.get(i+1);
                        if( pn.isNamedItem() ){
                            Particle pnn = list.get(i+2);
                            if( (pnn.isReservedToken() && pnn.getToken().equals(Token.ASSIGNMENT)) || (pnn.isReservedToken() && pnn.getToken().equals(Token.END_STATEMENT)) ){
                                Variable v = new Variable();
                                v.setName(pn.getName());
                                v.setValue("{}");
                                v.setType(PrimitiveType.OBJECT);
                                if( Globals.verbose ) {
                                    System.out.println("Adding " + v + " to " + clazz.getName() + " var set");
                                }
                                variableSet.put(pn.getName(), v);
                                i++;
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return variableSet;
    }


    public static HashMap<String, Clazz> parseClasses(ArrayList<Particle> list) {
        HashMap<String, Clazz> map = new HashMap<String, Clazz>();
        try {
            for (int i = 0; i < list.size(); i++) {
                Particle p = list.get(i);
                if( Globals.verbose ) {
                    System.out.println("particle@" + i + ":" + p);
                }
                if (p == null) {
                    System.out.println("First particle is null");
                    System.exit(1);
                }
                boolean expression = false;
                try { expression = p.getKeyword().equals(Keyword.CLASS); } catch(Exception ex) { }
                if (expression) {
                    ArrayList<Particle> subset = new ArrayList<Particle>();
                    subset.add(p);
                    Clazz clazz = new Clazz();
                    Particle holder = list.get(i + 1);
                    String name = holder.getName();
                    holder.setNamedClass(true);
                    subset.add(holder);
                    System.out.println("Adding class:" + name);
                    int block = 0;
                    boolean started = false;
                    for (int j = i+1; j < list.size(); j++) {
                        Particle particle = list.get(j);
                        try {
                            if (particle.getToken().equals(Token.OPEN_BLOCK)) {
                                started = true;
                                block++;
                            } else if (particle.getToken().equals(Token.CLOSE_BLOCK)) {
                                block--;
                            }
                        } catch (Exception ex) {

                        }
                        subset.add(particle);
                        if (started && block == 0) {
                            clazz.setName(name);
                            clazz.setBodySet(subset);
                            HashMap<String, Variable> varMap = parseVariables(clazz);
                            clazz.setVariableSet(varMap);
                            map.put(name, clazz);
                            i = (j++);
                            break;
                        }
                    }
                }
            }
        } catch(Exception ex) { ex.printStackTrace(); }
        return map;
    }

    public static ArrayList<Particle> parseToParticles(String code) {
        ArrayList<Particle> programSet = new ArrayList<Particle>();
        StringTokenizer tokenizer = new StringTokenizer(code, " {}.|&+-*/;,:=<>?!()[]\"", true);
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            if (tok.equals("\"")) {
                while (tokenizer.hasMoreTokens()) {
                    String nxt = tokenizer.nextToken();
                    tok += nxt;
                    if (nxt.equals("\"")) {
                        break;
                    }
                }
            }

            String discardTok = "";
            if (isNumeric(tok)) {
                while (tokenizer.hasMoreTokens()) {
                    String nxt = tokenizer.nextToken();
                    if (nxt.equals(";")) {
                        discardTok = ";";
                        break;
                    }
                    if (nxt.equals(")")) {
                        discardTok = ")";
                        break;
                    }
                    if (nxt.equals(",")) {
                        discardTok = ",";
                        break;
                    }
                    tok += nxt;

                }
            }

            if (!tok.equals(" ")) { // speed up
                Particle p = parseToParticle(tok);
                programSet.add(p);
                if( !discardTok.equals("")){
                    Particle pp = parseToParticle(discardTok);
                    programSet.add(pp);
                }
                try {
                    boolean interactive = false;
                    boolean verbose = false;
                    if (verbose) {
                        System.out.println("::" + p + " " + tok);
                    }
                    if (interactive) {
                        System.in.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return programSet;
    }

    public static ArrayList<Particle> normalize(ArrayList<Particle> list)
    {
        ArrayList<Particle> newList = new ArrayList<Particle>();
        for(int i = 0; i < list.size(); i++ ) {
            Particle p = list.get(i);

            if( Globals.verbose ) {
                System.out.println("normalize:" + i + " " + p);
            }
            try {
                if (p.isReservedToken()) {
                    if (p.getToken().equals(Token.PLUS)) {
                        Particle pp = list.get(i + 1);
                        try {
                            if (pp.getToken().equals(Token.PLUS)) {
                                Particle fp = new Particle("++");
                                fp.setToken(Token.INCREMENT);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.MINUS)) {
                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.MINUS)) {
                                Particle fp = new Particle("--");
                                fp.setToken(Token.DECREMENT);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.GT)) {
                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.ASSIGNMENT)) {
                                Particle fp = new Particle(">=");
                                fp.setToken(Token.GTE);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.LT)) {

                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.ASSIGNMENT)) {
                                Particle fp = new Particle("<=");
                                fp.setToken(Token.LTE);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.AND)) {
                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.AND)) {
                                Particle fp = new Particle("&&");
                                fp.setToken(Token.LOGICAL_AND);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.NOT)) {
                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.ASSIGNMENT)) {
                                Particle fp = new Particle("!=");
                                fp.setToken(Token.NOT_EQUAL);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.ASSIGNMENT)) {
                        try {
                            Particle pp = list.get(i + 1);
                            if (pp.getToken().equals(Token.ASSIGNMENT)) {
                                Particle fp = new Particle("==");
                                fp.setToken(Token.EQUAL);
                                fp.setReservedToken(true);
                                newList.add(fp);
                                i++;
                            } else {
                                newList.add(p);
                            }
                        } catch (Exception ex) {
                            newList.add(p);
                        }
                    } else if (p.getToken().equals(Token.OPEN_BRACE)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.CLOSE_BRACE)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.COMMA)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.OPEN_BLOCK)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.CLOSE_BLOCK)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.OPEN_INDEX)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.CLOSE_INDEX)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.MEMBER_OF)) {
                        newList.add(p);
                    } else if (p.getToken().equals(Token.END_STATEMENT)) {
                        newList.add(p);
                    }
                }
                else if(p.isReservedKeyword() ) {
                    newList.add(p);
                } else if (p.isNamedItem()) {
                    try {
                        Particle pp = list.get(i + 1);
                        if( pp.isReservedToken()) {
                            if (pp.getToken().equals(Token.OPEN_BRACE)) {
                                try {
                                    Particle fp = new Particle(p.getRaw());
                                    fp.setNamedItem(false);
                                    fp.setNamedFunction(true);
                                    fp.setName(p.getName());
                                    newList.add(fp);
                                } catch (Exception ex) {
                                }
                            } else if (pp.getToken().equals(Token.OPEN_BLOCK)) {
                                try {
                                    Particle prevp = list.get(i - 1);
                                    if (prevp.isReservedKeyword() && prevp.getKeyword().equals(Keyword.CLASS)) {
                                        Particle fp = new Particle(p.getRaw());
                                        fp.setNamedItem(true);
                                        fp.setNamedFunction(false);
                                        fp.setNamedClass(true);
                                        fp.setName(p.getName());
                                        newList.add(fp);
                                    }
                                } catch (Exception ex) {
                                }
                            } else {
                                newList.add(p);
                            }
                        } else {
                            newList.add(p);
                        }
                    } catch (Exception ex) {
                        newList.add(p);
                    }

                } else {
                    newList.add(p);
                }

            } catch(Exception ex) {
                newList.add(p);
            }
        }
        return newList;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            BigDecimal bd = new BigDecimal(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Particle parseToParticle(String tok) {
        Particle particle = new Particle(tok);
        Token t = null;
        Keyword k = null;
        if ((t = tokenLookup.get(tok)) != null) {
            particle.setReservedToken(true);
            particle.setToken(t);
            return particle;
        }
        if ((k = keywordLookup.get(tok)) != null) {
            particle.setReservedKeyword(true);
            particle.setKeyword(k);
            return particle;
        }
        if (tok.equals("false")) {
            particle.setType(PrimitiveType.BOOLEAN);
            particle.setLiteralValue(true);
            particle.setBooleanValue(Boolean.FALSE);

            return particle;
        } else if (tok.equals("true")) {
            particle.setType(PrimitiveType.BOOLEAN);
            particle.setLiteralValue(true);
            particle.setBooleanValue(Boolean.TRUE);
            return particle;
        } else if (tok.startsWith("\"") && tok.endsWith("\"")) {
            particle.setType(PrimitiveType.STRING);
            particle.setLiteralValue(true);
            particle.setStringValue(tok);
            return particle;
        } else if (isNumeric(tok)) {
            particle.setType(PrimitiveType.DECIMAL);
            particle.setDecimalValue(new BigDecimal(tok));
            particle.setLiteralValue(true);
            return particle;
        } else if( tok.equals("\n") ) {
            particle.setToken(Token.CRLF);
            particle.setReservedToken(true);
            return particle;
        }
        else {
            particle.setNamedItem(true);
            tok = tok.replaceAll(" ", "");
            particle.setName(tok);
            return particle;
        }
    }


    public static ArrayList<Particle> parse(File pFile) {

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(pFile)));

            StringBuffer buffer = new StringBuffer();
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                buffer.append(buf);
            }

            ArrayList<Particle> list = parseToParticles(buffer.toString());
            ArrayList<Particle> normalizedList = normalize(list); // add double operators.
            return normalizedList;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
