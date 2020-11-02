import java.io.File;

public class littleg {

    public static void main(String[] args) {
        try {
            if( args.length < 0 ){
                System.out.println("littleg file.g");
            }
            Loader loader = new Loader(new File(args[0]));
            Program p = loader.compile();

        } catch(Exception ex) {

        }
    }
}
