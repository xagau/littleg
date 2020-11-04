import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Loader {
    File file = null;
    public Loader(File file)
    {
        this.file = file;
    }

    public HashMap<String, Clazz> compile()
    {
        try {
            Preprocessor processor = new Preprocessor();
            File pFile = processor.process(file);
            if(pFile == null ){
                System.out.println("Preprocessor ran into an error");
            }
            ArrayList<Particle> list = Parser.parse(pFile);
            HashMap<String, Clazz> map = Parser.parseClasses(list);
           return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}