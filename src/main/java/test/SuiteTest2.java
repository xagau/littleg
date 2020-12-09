package test;

import org.junit.Assert;
import org.junit.Test;

public class SuiteTest2 {


    @Test
    public void createAndSetName() {

        String expected = "Y";
        String actual = "Y";

        Assert.assertEquals(expected, actual);
        System.out.println("Suite Test 1 is successful " + actual);
    }

}