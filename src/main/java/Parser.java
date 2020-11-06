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

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Parser {

    static ReservedTokens tokenLookup = new ReservedTokens();
    static ReservedKeywords keywordLookup = new ReservedKeywords();

    public static HashMap<String, Clazz> parseClasses(ArrayList<Particle> list) {
        HashMap<String, Clazz> map = new HashMap<String, Clazz>();
        try {
            for (int i = 0; i < list.size(); i++) {
                Particle p = list.get(i);
                if (p == null) {
                    System.out.println("First particle is null");
                    System.exit(1);
                }
                if (p.getKeyword().equals(Keyword.CLASS)) {
                    ArrayList<Particle> subset = new ArrayList<Particle>();
                    subset.add(p);
                    Clazz clazz = new Clazz();
                    Particle holder = list.get(i + 1);
                    String name = holder.getName();
                    System.out.println("Adding class:" + name);
                    int block = 0;
                    boolean started = false;
                    for (int j = i; j < list.size(); j++) {
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
                            clazz.setBodySet(subset);
                            map.put(name, clazz);
                            i = j++;
                            break;
                        }
                    }
                }
            }
        } catch(Exception ex) {}
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
            if (isNumeric(tok)) {
                while (tokenizer.hasMoreTokens()) {
                    String nxt = tokenizer.nextToken();
                    if (nxt.equals(";")) {
                        break;
                    }
                    if (nxt.equals(")")) {
                        break;
                    }
                    if (nxt.equals(",")) {
                        break;
                    }
                    tok += nxt;

                }
            }

            if (!tok.equals(" ")) { // speed up
                Particle p = parseToParticle(tok);
                programSet.add(p);
                try {
                    boolean interactive = false;
                    boolean verbose = false;
                    if (verbose) {
                        System.out.println(p);
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
                    }
                } else if (p.isNamedItem()) {
                    try {
                        Particle pp = list.get(i + 1);

                        if (pp.getToken().equals(Token.OPEN_BRACE)) {
                            Particle fp = new Particle(p.getRaw());
                            System.out.println("Prospective Function:" + p.getRaw());
                            fp.setNamedItem(false);
                            fp.setNamedFunction(true);
                            newList.add(fp);
                        } else {
                            newList.add(p);
                        }
                    } catch (Exception ex) {
                        newList.add(p);
                    }

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
            particle.setLiteralValue(true);
            particle.setBooleanValue(Boolean.FALSE);
            return particle;
        } else if (tok.equals("true")) {
            particle.setLiteralValue(true);
            particle.setBooleanValue(Boolean.TRUE);
            return particle;
        } else if (tok.startsWith("\"") && tok.endsWith("\"")) {
            particle.setLiteralValue(true);
            particle.setStringValue(tok);
            return particle;
        } else if (isNumeric(tok)) {
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
