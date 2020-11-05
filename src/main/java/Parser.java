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
                if (p.keyword.equals(Keyword.CLASS)) {
                    ArrayList<Particle> subset = new ArrayList<Particle>();
                    subset.add(p);
                    Clazz clazz = new Clazz();
                    Particle tmp = list.get(i + 1);
                    String name = tmp.name;
                    int block = 0;
                    boolean started = false;
                    for (int j = i; j < list.size(); j++) {
                        Particle particle = list.get(j);
                        try {
                            if (particle.token.equals(Token.OPEN_BLOCK)) {
                                started = true;
                                block++;
                            } else if (particle.token.equals(Token.CLOSE_BLOCK)) {
                                block--;
                            }
                        } catch (Exception ex) {
                        }
                        System.out.println("Block Count:" + block);
                        System.out.println(particle);

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
        int pos = 0;
        while (tokenizer.hasMoreTokens()) {
            String tok = tokenizer.nextToken();
            pos++;
            if (tok.equals("\"")) {
                while (tokenizer.hasMoreTokens()) {
                    String nxt = tokenizer.nextToken();
                    tok += nxt;
                    if (nxt.equals("\"")) {
                        break;
                    }
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
        programSet = normalize(programSet);
        return programSet;
    }

    public static ArrayList<Particle> normalize(ArrayList<Particle> list)
    {
        ArrayList<Particle> newList = new ArrayList<Particle>();
        for(int i = 0; i < list.size(); i++ ) {
            Particle p = list.get(i);
            try {
                if( p.reservedToken ) {
                    if (p.token.equals(Token.PLUS)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.PLUS)) {
                            Particle fp = new Particle();
                            fp.token = Token.INCREMENT;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        } else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.MINUS)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.MINUS)) {
                            Particle fp = new Particle();
                            fp.token = Token.DECREMENT;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.GT)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.ASSIGNMENT)) {
                            Particle fp = new Particle();
                            fp.token = Token.GTE;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.LT)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.ASSIGNMENT)) {
                            Particle fp = new Particle();
                            fp.token = Token.LTE;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.AND)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.AND)) {
                            Particle fp = new Particle();
                            fp.token = Token.LOGICAL_AND;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.NOT)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.ASSIGNMENT)) {
                            Particle fp = new Particle();
                            fp.token = Token.NOT_EQUAL;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else if (p.token.equals(Token.ASSIGNMENT)) {
                        Particle pp = list.get(i + 1);
                        if (pp.token.equals(Token.ASSIGNMENT)) {
                            Particle fp = new Particle();
                            fp.token = Token.EQUAL;
                            fp.reservedToken = true;
                            newList.add(pp);
                            i++;
                        }  else {
                            newList.add(p);
                        }
                    }
                    else {
                        newList.add(p);
                    }
                }
                else {
                    newList.add(p);
                }
            } catch( Exception ex) { }
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
        Particle particle = new Particle();
        Token t = null;
        Keyword k = null;
        if ((t = tokenLookup.get(tok)) != null) {
            particle.reservedToken = true;
            particle.token = t;
            return particle;
        }
        if ((k = keywordLookup.get(tok)) != null) {
            particle.reservedKeyword = true;
            particle.keyword = k;
            return particle;
        }
        if (tok.equals("false")) {
            particle.literalValue = true;
            particle.booleanValue = Boolean.FALSE;
            return particle;
        } else if (tok.equals("true")) {
            particle.literalValue = true;
            particle.booleanValue = Boolean.TRUE;
            return particle;
        } else if (tok.startsWith("\"") && tok.endsWith("\"")) {
            particle.literalValue = true;
            particle.stringValue = tok;
            return particle;
        } else if (isNumeric(tok)) {
            particle.decimalValue = new BigDecimal(tok);
            particle.literalValue = true;
            return particle;
        } else {
            particle.namedItem = true;
            particle.name = tok;
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
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
