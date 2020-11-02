import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Loader {
    File file = null;
    public Loader(File file)
    {
        this.file = file;
    }

    public Program compile()
    {
        try {

            ArrayList arrayList = new ArrayList();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String buffer = null;
            String delim = ",{};";
            while( (buffer = reader.readLine() )!= null ) {
                try {
                    StringTokenizer tokenizer = new StringTokenizer(buffer, delim, true);
                    while( tokenizer.hasMoreTokens() ) {
                        String tok = tokenizer.nextToken();
                        System.out.println(tok);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}