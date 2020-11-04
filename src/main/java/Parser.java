import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {

    public static ArrayList parseToParticles(String code)
    {
        ArrayList programSet = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(code, " |&+-*/;,:=<>?()[]\"", true);
        while(tokenizer.hasMoreTokens()){
            String tok = tokenizer.nextToken();
            if( tok.equals("\"")){
                while(tokenizer.hasMoreTokens() ){
                    String nxt = tokenizer.nextToken();
                    tok += nxt;
                    if( nxt.equals("\"")){
                        break;
                    }
                }
            }
            if( !tok.equals(" ")) { // speed up
                //System.out.println(tok);
                Particle p = parseToParticle(tok);

                System.out.println(p.toString());

                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return programSet;
    }

    static ReservedTokens tokenLookup =new ReservedTokens();
    static ReservedKeywords keywordLookup =new ReservedKeywords();

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

    public static Particle parseToParticle(String tok)
    {
        Particle particle = new Particle();
        Token t = null;
        Keyword k = null;
        if( (t = tokenLookup.get(tok)) != null ){
            particle.reservedToken = true;
            particle.token = t;
            return particle;
        }
        if( (k = keywordLookup.get(tok)) != null ){
            particle.reservedKeyword = true;
            particle.keyword = k;
            return particle;
        }
        if( tok.equals("false")){
            particle.literalValue = true;
            particle.booleanValue = Boolean.FALSE;
            return particle;
        } else if( tok.equals(true) ) {
            particle.literalValue = true;
            particle.booleanValue = Boolean.TRUE;
            return particle;
        } else if( tok.startsWith("\"") && tok.endsWith("\"")){
            particle.literalValue = true;
            particle.stringValue = tok;
            return particle;
        } else if ( isNumeric(tok) ){
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
            while( (buf = reader.readLine()) != null) {
                buffer.append(buf);
            }

            ArrayList<Particle> list = parseToParticles(buffer.toString());
            return list;

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
