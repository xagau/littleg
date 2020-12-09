package littleg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Evaluator {

    public static Variable evaluate(Variable lo, Token op, Variable ro) throws UnexpectedTokenException, IllegalExpressionException {
        if( lo == null || op == null || ro == null ){
            throw new IllegalExpressionException("variables or operands are null");
        }
        Variable v = new Variable();
        Token tok = op;
        switch(tok){
            case GT:
                if( !lo.getType().equals(PrimitiveType.DECIMAL) ){
                    throw new UnexpectedTokenException(" > not applicable to type " + lo.getType());
                }
                else if( !ro.getType().equals(PrimitiveType.DECIMAL) ){
                    throw new UnexpectedTokenException(" > not applicable to type " + ro.getType());
                }
                if( new BigDecimal(lo.getValue()).max(new BigDecimal(ro.getValue())).equals(new BigDecimal(lo.getValue())))  {
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue("true");
                    return v;
                } else {
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue("false");
                    return v;
                }
            case LT:
                if( !lo.getType().equals(PrimitiveType.DECIMAL) ){
                    throw new UnexpectedTokenException("< not applicable to type " + lo.getType());
                }
                if( !ro.getType().equals(PrimitiveType.DECIMAL) ){
                    throw new UnexpectedTokenException("< not applicable to type " + ro.getType());
                }
                if( new BigDecimal(lo.getValue()).max(new BigDecimal(ro.getValue())).equals(new BigDecimal(lo.getValue())))  {
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue("false");
                    return v;
                } else {
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue("true");
                    return v;
                }
            case LOGICAL_AND:
                if( !ro.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("&& only applicable to type boolean");
                }
                if( !lo.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("&& only applicable to type boolean");
                }
                v.setType(PrimitiveType.BOOLEAN);
                v.setBool((lo.getBool() && ro.getBool()));

                break;
            case LOGICAL_OR:
                if( !ro.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("|| only applicable to type boolean");
                }
                if( !lo.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("|| only applicable to type boolean");
                }
                v.setType(PrimitiveType.BOOLEAN);
                v.setBool((lo.getBool() || ro.getBool()));

                break;
            case AND:
                break;
            case OR:
                break;
            case GTE:
                if( ro.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException(">= not applicable to type boolean");
                }
                break;
            case LTE:
                if( ro.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("<= not applicable to type boolean");
                }
                break;
            case PLUS:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( lo.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.getDecimal().add(ro.getDecimal());
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.setDecimal(nv);
                        return v;
                    }
                }

                break;
            case MINUS:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( lo.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.getDecimal().subtract(ro.getDecimal());
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.setDecimal(nv);
                        return v;
                    }
                }
                break;
            case MULTIPLY:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( ro.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.getDecimal().multiply(ro.getDecimal());
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.setDecimal(nv);
                        return v;
                    }
                }
                break;
            case DIVIDE:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( ro.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.getDecimal().divide(ro.getDecimal());
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.setDecimal(nv);
                        return v;
                    }
                }
                break;
            case EQUAL:
                try {
                    if( lo.getType().equals(PrimitiveType.BOOLEAN) && ro.getType().equals(PrimitiveType.BOOLEAN)){
                        if( ro.getValue().equals(lo.getValue())){
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("true");
                            return v;
                        } else {
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("false");
                            return v;
                        }
                    }
                    else if( lo.getType().equals(PrimitiveType.DECIMAL) && ro.getType().equals(PrimitiveType.DECIMAL)){
                        if( new BigDecimal(lo.getValue()).equals(new BigDecimal(ro.getValue())))  {
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("true");
                            return v;
                        } else {
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("false");
                            return v;
                        }
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case NOT_EQUAL:
                try {
                    if (lo.getType().equals(PrimitiveType.DECIMAL) && ro.getType().equals(PrimitiveType.DECIMAL)) {
                        if (new BigDecimal(lo.getValue()).equals(new BigDecimal(ro.getValue()))) {
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("false");
                            return v;
                        } else {
                            v.setName("result");
                            v.setType(PrimitiveType.BOOLEAN);
                            v.setValue("true");
                            return v;
                        }
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case ASSIGNMENT:
                System.out.println("Assignment:" + lo + "=>" + ro);
                if( lo.getType().equals(PrimitiveType.DECIMAL )){
                    v.setName("result");
                    v.setType(PrimitiveType.DECIMAL);
                    v.setDecimal(ro.getDecimal());
                    return v;
                }
                else if( lo.getType().equals(PrimitiveType.BOOLEAN )){
                    v.setName("result");
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue(ro.getBool().toString());
                    return v;
                }
                else if( lo.getType().equals(PrimitiveType.STRING )){
                    v.setName("result");
                    v.setType(PrimitiveType.STRING);
                    v.setValue(ro.getValue());
                    return v;
                }
                else if( lo.getType().equals(PrimitiveType.OBJECT )){
                    v.setName("result");
                    v.setType(PrimitiveType.OBJECT);
                    v.setObject(ro.getObject());
                    return v;
                }
                break;
        }
        return v;
    }

    public static Variable evaluate(Variable lo, Token op) throws UnexpectedTokenException, IllegalExpressionException {
        if( lo == null || op == null ){
            throw new IllegalExpressionException("variables or operands are null");
        }
        Variable v = new Variable();
        Token tok = op;
        switch(tok){
            case INCREMENT:
                if (lo.getType().equals(PrimitiveType.DECIMAL) ) {
                    v.setName("result");
                    v.setType(PrimitiveType.DECIMAL);
                    v.setDecimal(lo.getDecimal().add(new BigDecimal(1)));
                    return v;
                }
                break;
            case DECREMENT:
                if (lo.getType().equals(PrimitiveType.DECIMAL) ) {
                    v.setName("result");
                    v.setType(PrimitiveType.DECIMAL);
                    v.setDecimal(lo.getDecimal().subtract(new BigDecimal(1)));
                    return v;
                }
                break;
        }
        return v;
    }

    public static boolean isComparisionOperand(Token tok)
    {
        switch(tok){
            case GT:
                return true;
            case LT:
                return true;
            case LOGICAL_AND:
                return true;
            case LOGICAL_OR:
                return true;
            case AND:
                return true;
            case OR:
                return true;
            case GTE:
                return true;
            case LTE:
                return true;
            case PLUS:
                return true;
            case MINUS:
                return true;
            case MULTIPLY:
                return true;
            case DIVIDE:
                return true;
            case EQUAL:
                return true;
            case NOT_EQUAL:
                return true;
            case ASSIGNMENT:
                return true;
        }
        return false;
    }

    public static boolean isUniaryOperand(Token tok)
    {
        if( tok == null ){
            return false;
        }
        switch(tok){
            case INCREMENT:
                return true;
            case DECREMENT:
                return true;
        }
        return false;
    }

    public static Variable evaluate(ArrayList<Particle> list, HashMap<String, Variable> varList, HashMap<String, Clazz> clazzList) throws UnexpectedTokenException, IllegalExpressionException {

        Variable lo = null;
        Variable ro = null;

        Token operand = null;


        for(int i = 0; i < list.size(); i++ ){
            Particle p = list.get(i);

            if( operand == null ) {
                if (p.isNamedItem()) {
                    lo = (Variable) varList.get(p.getName());
                } else if ( p.isLiteralValue() ){
                    //System.out.println("Reached literal check");
                    PrimitiveType type = p.getType();
                    if( type.equals(PrimitiveType.DECIMAL)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.setDecimal(p.getDecimalValue());
                        System.out.println("lo: decimal:" + lo.getDecimal());
                    }
                    else if( type.equals(PrimitiveType.STRING)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.setString(p.getStringValue());
                    }
                    else if( type.equals(PrimitiveType.BOOLEAN)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.setBool(p.getBooleanValue());
                        System.out.println("lo:" + lo.getBool());
                    }
                    else if( type.equals(PrimitiveType.OBJECT)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.setObject(p.getObjectValue());
                    }
                }
            }
            boolean c = false;
            boolean u = false;
            if (p.isReservedToken() ) {
                if( lo == null ){
                    throw new UnexpectedTokenException("Unexpected operator / token @ " + i);
                }
                Token tok = p.getToken();

                c = isComparisionOperand(tok);
                if( c ){
                    operand = tok;
                }
                u = isUniaryOperand(tok);
                if( u ){
                    operand = tok;
                }
            }


            if( u == false ) {
                if (lo != null && operand != null && ro == null) {
                    if (p.isNamedItem()) {
                        ro = varList.get(p.getName());
                    } else if (p.isLiteralValue()) {
                        PrimitiveType type = p.getType();
                        if (type.equals(PrimitiveType.DECIMAL)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.setDecimal(p.getDecimalValue());
                            System.out.println("ro: decimal:" + ro.getDecimal());
                        } else if (type.equals(PrimitiveType.STRING)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.setString(p.getStringValue());
                        } else if (type.equals(PrimitiveType.BOOLEAN)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.setBool(p.getBooleanValue());

                        } else if (type.equals(PrimitiveType.OBJECT)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.setObject(p.getObjectValue());
                        }
                    }
                }
            }

            /*
            if( p.isReservedToken() && p.getToken().equals(Token.END_STATEMENT)){
                boolean b = false;
                try {
                    if (list.get(i + 1) != null) {
                        b = true;
                    }
                } catch(Exception ex) { }

                if( b ) {
                    String msg = "Found unexpected token after end of statement `;' @ index " + i;
                    throw new UnexpectedParticleException(msg);
                }

            }
            */

            if( u ){
                System.out.println("eval(" + lo + " " + operand + " " + ro + ")");
                Variable ret = evaluate(lo, operand);
                System.out.println("eval result:" + ret.getValue());
                return ret;
            }

            if( lo != null && operand != null && ro != null ){
                System.out.println("eval(" + lo + " " + operand + " " + ro + ")");
                Variable ret = evaluate(lo, operand, ro);
                System.out.println("eval result:" + ret.getValue());
                return ret;
            }
        }
        return null;
    }




    public static void main(String[] args)
    {

    }

}
