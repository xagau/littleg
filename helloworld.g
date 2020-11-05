#########################################################################################
#                  littleg A programming Language
#                 written by Sean Patrick Beecroft
#
#    Copyright (c) 2020 Sean Beecroft,
#    Permission is hereby granted, free of charge, to any person obtaining
#    a copy of this software and associated documentation files (the
#    "Software"), to deal in the Software without restriction, including
#    without limitation the rights to use, copy, modify, merge, publish,
#    distribute, sublicense, and/or sell copies of the Software, and to
#    permit persons to whom the Software is furnished to do so, subject to
#    the following conditions:
#
#    The above copyright notice and this permission notice shall be
#    included in all copies or substantial portions of the Software.
#
#    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
#    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
#    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
#    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
#    LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
#    OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
#    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
#   @author Sean Beecorft (aka xagau) https://www.github.com/xagau
#
##########################################################################################

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

        decimal a = 2.2034909349503490590349509345;

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