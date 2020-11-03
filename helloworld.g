##########################
# This is a comment
##########################

class Main {

    decimal Z = 3.21;
    string test = "this is a test";
    boolean bb = false;

    decimal functionTest(decimal A, decimal B)
    {
        return A + B + Z; # add A + B
    }

    boolean b2 = false;

    boolean main( arguments ) {

        decimal a = 2.2;

        for(decimal d = 0; d < 10; d++ ) {
            println(d);
        }

        Two two = new Two();
        println(two.getHelloWorld());
        println(functionTest(a,2.5));

        return true;
    }
}

class Two {
    boolean b1 = false;
    boolean b2 = false;

    string getHelloWorld(){
        return "Hello World";
    }
}