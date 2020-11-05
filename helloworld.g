##########################
# This is a comment
##########################

class Main {

    decimal Z = 3.210490590390590349509382387648723658764526735446752376452387654876253; # test big decimal
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

        return "Nothing important";

    }

    decimal functionTest(decimal A, decimal B)
    {
        return A + B + Z; # add A + B + Z
    }

    # test ability to declare variables in class body.
    boolean b2 = false;

    boolean main( ) {

        decimal a = 2.2;

        for(decimal d = 0; d < 10; d++ ) {
            # replace with suitable logging facility
            println(d);
        }

        Two two = new Two();
        println(two.getHelloWorld()); # test instance invocation
        println(functionTest(a,2.5)); # test function stack management

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

    string gotcha( ) {

        if( x == x ) {
            return "True"; # Test EQUALITY
        }
        if( x != x ) {
            return "True"; # Test NOT_EQUAL
        }

        x++;
        y++;
        z--;

        return testTwo; # test returning class member variable from within function scope.
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