##########################
# This is a comment
##########################

class Main {

    decimal Z = 3.21;
    string test = "this is a test";
    boolean bb = false;

    string functionTestA()
    {
        if(!bb) {
            println("bb is false");
        }

        if(bb != true ) {
            println("bb is false");
        }

        if(bb == false ) {
            println("bb is false ");
        } else {
            println("bb is true ");
        }

    }

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

class Three {
    decimal x = 1;
    decimal y = 2;
    decimal z = 3;
    string testTwo = "This is a test " + x + " some more string.";

    string gotcha( arguments ) {

        if( x == x ) {
            return "True";
        }
        if( x != x ) {
            return "True";
        }

        x++;
        y++;
        z--;

        return testTwo;
    }
}

class Four {
    void main() {
        decimal x = 0;
        x++;
        if( x == 1 ) {
            return;
        }
    }
}