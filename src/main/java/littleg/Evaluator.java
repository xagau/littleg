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
                v.bool = (lo.bool && ro.bool);

                break;
            case LOGICAL_OR:
                if( !ro.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("|| only applicable to type boolean");
                }
                if( !lo.getType().equals(PrimitiveType.BOOLEAN) ){
                    throw new UnexpectedTokenException("|| only applicable to type boolean");
                }
                v.setType(PrimitiveType.BOOLEAN);
                v.bool = (lo.bool || ro.bool);

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
                        BigDecimal nv = lo.decimal.add(ro.decimal);
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.decimal = nv;
                        return v;
                    }
                }

                break;
            case MINUS:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( lo.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.decimal.subtract(ro.decimal);
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.decimal = nv;
                        return v;
                    }
                }
                break;
            case MULTIPLY:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( ro.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.decimal.multiply(ro.decimal);
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.decimal = nv;
                        return v;
                    }
                }
                break;
            case DIVIDE:
                if( lo.getType().equals(PrimitiveType.DECIMAL) ){
                    if( ro.getType().equals(PrimitiveType.DECIMAL)){
                        BigDecimal nv = lo.decimal.divide(ro.decimal);
                        v.setName("result");
                        v.setType(PrimitiveType.DECIMAL);
                        v.decimal = nv;
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
                    v.decimal = ro.decimal;
                    return v;
                }
                else if( lo.getType().equals(PrimitiveType.BOOLEAN )){
                    v.setName("result");
                    v.setType(PrimitiveType.BOOLEAN);
                    v.setValue(ro.bool.toString());
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
                    v.object = ro.object;
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
                    v.decimal = lo.decimal.add(new BigDecimal(1));
                    return v;
                }
                break;
            case DECREMENT:
                if (lo.getType().equals(PrimitiveType.DECIMAL) ) {
                    v.setName("result");
                    v.setType(PrimitiveType.DECIMAL);
                    v.decimal = lo.decimal.subtract(new BigDecimal(1));
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
                        lo.decimal = p.getDecimalValue();
                        System.out.println("lo: decimal:" + lo.decimal);
                    }
                    else if( type.equals(PrimitiveType.STRING)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.string = p.getStringValue();
                    }
                    else if( type.equals(PrimitiveType.BOOLEAN)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.bool = p.getBooleanValue();
                        System.out.println("lo:" + lo.bool);
                    }
                    else if( type.equals(PrimitiveType.OBJECT)) {
                        lo = new Variable();
                        lo.setType(type);
                        lo.object = p.getObjectValue();
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
                            ro.decimal = p.getDecimalValue();
                            System.out.println("ro: decimal:" + ro.decimal);
                        } else if (type.equals(PrimitiveType.STRING)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.string = p.getStringValue();
                        } else if (type.equals(PrimitiveType.BOOLEAN)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.bool = p.getBooleanValue();

                        } else if (type.equals(PrimitiveType.OBJECT)) {
                            ro = new Variable();
                            ro.setType(type);
                            ro.object = p.getObjectValue();
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
        boolean t0 = testDecimalOnePlusOne();
        System.out.println("testDecimalOnePlusOne:" + ((t0)?"passed":"failed"));

        boolean t1 = testDecimalEqual();
        System.out.println("testDecimalEqual:" + ((t1)?"passed":"failed"));
        boolean t2 = testDecimalNotEqual();
        System.out.println("testDecimalEqual:" + ((!t2)?"passed":"failed"));
        boolean t3 = testDecimalGTEdgeOne();
        System.out.println("testDecimalGTEdgeOne:" + ((t3)?"passed":"failed"));
        boolean t4= testDecimalGTEdgeTwo();
        System.out.println("testDecimalGTEdgeTwo:" + ((!t4)?"passed":"failed"));
        boolean t5= testDecimalNotEqualOperandOne();
        System.out.println("testDecimalNotEqualOperandOne:" + ((t5)?"passed":"failed"));
        boolean t6= testDecimalNotEqualOperandTwo();
        System.out.println("testDecimalNotEqualOperandTwo:" + ((!t6)?"passed":"failed"));

        boolean t7 = testDecimalLTEdgeOne();
        System.out.println("testDecimalLTEdgeOne:" + ((!t7)?"passed":"failed"));
        boolean t8= testDecimalLTEdgeTwo();
        System.out.println("testDecimalLTEdgeTwo:" + ((t8)?"passed":"failed"));

        boolean t9 = testDecimalTwoMinusOne();
        System.out.println("testDecimalTwoMinusOne:" + ((t9)?"passed":"failed"));

        boolean t10 = testDecimalOnePlusOneLiteral();
        System.out.println("testDecimalOnePlusOneLiteral:" + ((t10)?"passed":"failed"));

        boolean t11 = testDecimalOneMinusOneLiteral();
        System.out.println("testDecimalOneMinusOneLiteral:" + ((t11)?"passed":"failed"));

        boolean t12 = testDecimal5X5();
        System.out.println("testDecimal5X5:" + ((t12)?"passed":"failed"));

        boolean t13 = testDecimal10D2();
        System.out.println("testDecimal10D2:" + ((t13)?"passed":"failed"));

        boolean t14 = testDecimal5X5S();
        System.out.println("testDecimal5X5S:" + ((t14)?"passed":"failed"));

        boolean t15 = testBooleanLogicalOr();
        System.out.println(" testBooleanLogicalOr:" + ((t15)?"passed":"failed"));

        boolean t16 = testBooleanLogicalAnd();
        System.out.println(" testBooleanLogicalAnd:" + ((t16)?"passed":"failed"));

        boolean t16b = testBooleanLogicalAndLiteral();
        System.out.println(" testBooleanLogicalAndLiteral:" + ((t16b)?"passed":"failed"));


        boolean t17 = testBooleanLogicalOrNot();
        System.out.println(" testBooleanLogicalOrNot:" + ((t17)?"passed":"failed"));

        boolean t18 = testBooleanLogicalAndNot();
        System.out.println(" testBooleanLogicalAndNot:" + ((t18)?"passed":"failed"));

        boolean t19 = testBooleanLogicalOrLiteral();
        System.out.println(" testBooleanLogicalOrLiteral:" + ((t19)?"passed":"failed"));

        boolean t20 = testBooleanTrueAndFalse();
        System.out.println(" testBooleanLogicalOrLiteral:" + ((t20)?"passed":"failed"));

        boolean t21 = testIncrement();
        System.out.println(" testIncrement:" + ((t21)?"passed":"failed"));

        boolean t22 = testDecrement();
        System.out.println(" testDecrement:" + ((t22)?"passed":"failed"));

        boolean t23 = testAssignment();
        System.out.println(" testAssignment:" + ((t23)?"passed":"failed"));

        boolean t24 = testAssignmentString();
        System.out.println(" testAssignmentString:" + ((t24)?"passed":"failed"));

        boolean t25 = testAssignmentBoolean();
        System.out.println(" testAssignmentBoolean:" + ((t24)?"passed":"failed"));

    }

}
