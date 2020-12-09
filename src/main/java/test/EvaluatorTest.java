package test;



import littleg.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.fail;

public class EvaluatorTest {
    //////////////////////////////////////////////////////////////////////////////////////////////
    // MOVE TO TEST UNIT
    @Test
    public void testDecimalOnePlusOne(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("1");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("+"));
            Particle p = Parser.parseToParticle("1");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Variable value = new Variable();
        try {
            value = Evaluator.evaluate(expression, varList, clazzList);
            //System.out.println("plus:" + value.getDecimal());
            if( value.getType().equals(PrimitiveType.DECIMAL)) {
                if( value.getDecimal().equals(new BigDecimal("2"))){
                }
                else {
                    fail("1 + 1 == 2");
                }
            }
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testDecimalOnePlusOneLiteral(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("1");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("1"));
            expression.add(Parser.parseToParticle("+"));
            Particle p = Parser.parseToParticle("1");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Variable value = new Variable();
        try {
            value = Evaluator.evaluate(expression, varList, clazzList);
            if( value.getType().equals(PrimitiveType.DECIMAL)) {
                if( value.getDecimal().equals(new BigDecimal("2"))){

                }
                else {
                    fail("Should be 2");
                }
            }
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
    }
    @Test
    public void testDecimalTwoMinusOne(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("2");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            Particle pp = Parser.parseToParticle("-");
            expression.add(pp);
            Particle p = Parser.parseToParticle("1");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail("should not throw exception");
            ex.printStackTrace();
        }
        try {
            Variable value = Evaluator.evaluate(expression, varList, clazzList);
            if( value.getType().equals(PrimitiveType.DECIMAL)) {
                if( value.getDecimal().equals(new BigDecimal("1"))){
                }
                else {
                    fail("should be 1");
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            fail("should not throw exception");
        }


    }
    @Test
    public void testDecimalOneMinusOneLiteral(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("1");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("1"));
            expression.add(Parser.parseToParticle("-"));
            Particle p = Parser.parseToParticle("1");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable value = new Variable();
        try {
            value = Evaluator.evaluate(expression, varList, clazzList);
            if( value.getType().equals(PrimitiveType.DECIMAL)) {
                if( value.getDecimal().equals(new BigDecimal("0"))){
                }
                else {
                    fail("Should be 0");
                }
            }
        } catch(Exception ex) {
            fail("Should not throw exception");
            ex.printStackTrace();
        }
    }

    @Test
    public void testDecimalEqual(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("10");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("=="));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail("should be equal");}

    }

    @Test
    public void testDecimalGTEdgeOne(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("11");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle(">"));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail("should be greater");}

    }

    @Test
    public void testDecimalGTEdgeTwo(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("9");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle(">"));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { fail("should be less"); } else { }


    }

    @Test
    public void testDecimalLTEdgeOne(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("11");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("<"));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { fail("should be less"); } else { }

    }

    @Test
    public void testDecimalLTEdgeTwo(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("9");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("<"));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) {  } else { fail("should be less / false "); }

    }

    @Test
    public void testDecimalNotEqual(){
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("6");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("=="));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch(Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { fail("6 != 10"); }

    }

    @Test
    public void testDecimalNotEqualOperandOne() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("6");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("!="));
            Particle p = Parser.parseToParticle("10");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) {} else { fail(" 6!=10 should be true");}

    }

    public void testDecimal5X5() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("5");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("*"));
            Particle p = Parser.parseToParticle("5");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v.getDecimal().equals(new BigDecimal("25"))) {
            } else {
                fail("should be 25");
            }
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Test
    public void testDecimal5X5S() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("5");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("*"));
            Particle p = Parser.parseToParticle("d");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v != null ) {
                if (v.getDecimal().equals(new BigDecimal("25"))) {
                } else {
                    fail("should be 25");
                }
            } else {
                fail( "v is null");
                System.out.println("v is null");
            }
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testDecimal10D2() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("10");
        d.setName("d");

        Variable s = new Variable();
        s.setType(PrimitiveType.DECIMAL);
        s.setValue("2");
        s.setName("s");

        varList.put("d", d);
        varList.put("s", s);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("/"));
            Particle p = Parser.parseToParticle("s");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v != null ) {
                if (v.getDecimal().equals(new BigDecimal("5"))) {
                } else {
                    fail( "10/5 = 2");
                }
            } else {
                System.out.println("v is null");
                fail( "v is null");
            }
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Test
    public void testDecimalNotEqualOperandTwo() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("6");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("!="));
            Particle p = Parser.parseToParticle("6");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) {

        } else {
            fail( "6!=6 == false");
        }

    }

    @Test
    public void testBooleanLogicalOr() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("false");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("||"));
            Particle p = Parser.parseToParticle("true");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail( "false || true == true"); }

    }

    @Test
    public void testBooleanLogicalOrLiteral() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("false");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("false"));
            expression.add(Parser.parseToParticle("||"));
            Particle p = Parser.parseToParticle("true");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail( "false || true == true"); }
    }

    @Test
    public void testBooleanLogicalAndLiteral() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("false");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("true"));
            expression.add(Parser.parseToParticle("&&"));
            Particle p = Parser.parseToParticle("true");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail( "true && true == true"); }

    }

    @Test
    public void testBooleanLogicalOrNot() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("false");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("||"));
            Particle p = Parser.parseToParticle("false");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { fail( "false || false == false"); } else {  }

    }

    @Test
    public void testBooleanLogicalAndNot() {
        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("false");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("&&"));
            Particle p = Parser.parseToParticle("false");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { fail( "false && false == false"); } else {  }
    }

    @Test
    public void testBooleanLogicalAnd() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("true");
        d.setName("d");
        Variable s = new Variable();
        s.setType(PrimitiveType.BOOLEAN);
        s.setValue("true");
        s.setName("s");
        varList.put("d", d);
        varList.put("s", s);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("&&"));
            Particle p = Parser.parseToParticle("s");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail( "true && true == true"); }

    }

    @Test
    public void testBooleanTrueAndFalse() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("true");
        d.setName("d");
        Variable s = new Variable();
        s.setType(PrimitiveType.BOOLEAN);
        s.setValue("false");
        s.setName("s");
        varList.put("d", d);
        varList.put("s", s);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("&&"));
            Particle p = Parser.parseToParticle("s");
            expression.add(p);
            expression.add(Parser.parseToParticle(";"));

        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable trueOrFalse = new Variable();
        try {
            trueOrFalse = Evaluator.evaluate(expression, varList, clazzList);
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }

        if( trueOrFalse.getBool() ) { } else { fail( "true && false == false"); }

    }

    @Test
    public void testIncrement() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("1");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("++"));
            expression.add(Parser.parseToParticle(";"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v.getDecimal().equals(new BigDecimal(2))){

            } else {
                fail("if d = 1, then d++ = 2");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testDecrement() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("10");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("--"));
            expression.add(Parser.parseToParticle(";"));
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v.getDecimal().equals(new BigDecimal(9))){
            } else {
                fail("if d = 10 then d-- == 9");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testAssignment() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.DECIMAL);
        d.setValue("10");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("="));
            expression.add(Parser.parseToParticle("11"));
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            if( v.getDecimal().equals(new BigDecimal(11))){
            } else {
                fail("if d = 10 then d = 11, d == 11");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testAssignmentString() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.STRING);
        d.setValue("Happy");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("="));
            expression.add(Parser.parseToParticle("Hello littleg"));
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            //System.out.println(v);
            if( v.getString().equals("Hello littleg")){

            } else {
                fail( "d not reassigned to said string");
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Test
    public void testAssignmentBoolean() {

        ArrayList<Particle> expression = new ArrayList<Particle>();
        HashMap<String, Variable> varList = new HashMap<String, Variable>();
        HashMap<String, Clazz> clazzList = new HashMap<String, Clazz>();

        Variable d = new Variable();
        d.setType(PrimitiveType.BOOLEAN);
        d.setValue("true");
        d.setName("d");
        varList.put("d", d);

        try {
            expression.add(Parser.parseToParticle("d"));
            expression.add(Parser.parseToParticle("="));
            expression.add(Parser.parseToParticle("false"));
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
        Variable v = new Variable();
        try {
            v = Evaluator.evaluate(expression, varList, clazzList);
            //System.out.println(v);
            //System.out.println("::" + v.getBool());

            if( v.getBool() == false){
            } else {
                fail( "if d assigned to false, then d == false");
            }
        } catch (Exception ex) {
            fail( ex.getMessage());
            ex.printStackTrace();
        }
    }
}
