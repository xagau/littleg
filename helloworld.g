##########################
# This is a comment
##########################

class Main {

    decimal Z = 3.21;


    decimal functionTest(decimal A, decimal B)
    {
        return A + B + Z; # add A + B
    }

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

    string getHelloWorld(){
        return "Hello World";
    }
}