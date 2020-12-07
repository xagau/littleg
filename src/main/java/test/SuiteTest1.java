package src.main.test

public class SuiteTest1 {

    public String message = "Saurabh";

    JUnitMessage junitMessage = new JUnitMessage(message);

    @Test(expected = ArithmeticException.class)
    public void testJUnitMessage() {

        System.out.println("Junit Message is printing ");
        junitMessage.printMessage();

    }

    @Test
    public void testJUnitHiMessage() {
        message = "Hi!" + message;
        System.out.println("Junit Hi Message is printing ");
        assertEquals(message, junitMessage.printHiMessage());
        System.out.println("Suite Test 2 is successful " + message);
    }
}