package test;

import org.junit.Assert;
import org.junit.Test;

public class SuiteTest1 {

    public String message = "Saurabh";



    @Test
    public void testJUnitMessage() {

        System.out.println("Junit Message is printing ");
    }

    @Test
    public void testJUnitHiMessage() {
        message = "Hi!" + message;
        System.out.println("Junit Hi Message is printing ");
        Assert.assertEquals(message, message);
        System.out.println("Suite Test 2 is successful " + message);
    }
}