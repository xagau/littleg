import java.math.BigDecimal;

public class Particle {
    public boolean reservedKeyword = false;
    public boolean namedItem = false;
    public boolean literalValue = false;
    public boolean reservedToken = false;
    public boolean namedFunction = false;

    public String raw = "";

    public Keyword keyword = null;
    public Token token = null;
    public String name = null;
    public String stringValue = null;
    public BigDecimal decimalValue = null;
    public Boolean booleanValue = null;

    public Particle(String raw){
        this.raw = raw;
    }

    public String toString()
    {
        String str = "";
        if( reservedKeyword ){
            str += "Is a keyword:" + keyword.toString();
        }
        if( reservedToken ){
            str += "Is a token:" + token.toString();
        }
        if( literalValue ){
            str += "Is a literalValue:" + literalValue + "[";
            if( stringValue != null ){
                str += "string:" + stringValue;
            }
            if( booleanValue != null ){
                str += "boolean:" + booleanValue.toString();
            }
            if( decimalValue != null ){
                str += "decimal:" + decimalValue.toString();
            }
            str += "]";
        }
        if( namedItem ){
            str += "Is a namedItem:" + namedItem + " [" + name + "]";
        }
        if( namedFunction ){
            str += "Is a namedFunction:" + namedFunction + " [" + name + "]";
        }
        return str;
    }
}
