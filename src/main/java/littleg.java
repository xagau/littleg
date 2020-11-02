import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class littleg {

    public static void main(String[] args) {
        try {
            if( args.length < 0 ){
                System.out.println("littleg file.g");
            }
            Loader loader = new Loader(new File(args[0]));
            HashMap<String, Clazz> list = loader.compile();
            Clazz main = list.get("Main");
            // encapsulate
            Method m = main.methodSet.get("main");
            m.invoke();

        } catch(Exception ex) {

        }
    }
}
