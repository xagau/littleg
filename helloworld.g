

class Main {

    decimal functionTest(decimal A, decimal B)
    {
        return A + B;
    }

    decimal main( string args[]) {

        decimal a = 2.2;

        for(decimal d = 0; d < 10; d++ ) {
            println(d);
        }
        println("hello world");
        println(functionTest(a,2.5));

        return 1;
    }
}