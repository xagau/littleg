import java.io.*;

public class Preprocessor {
    public File process( File file ) {
        try {

            // strip out comments
            // strip out append included files
            String str = file.getAbsolutePath() + ".out";
            File t = new File(str);
            if( t.exists() ) { t.delete(); }
            t = new File(str);
            FileWriter printer = new FileWriter(t);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String buf = null;
            String S = "";
            while (( buf = reader.readLine()) != null ) {
                buf = buf.replaceAll("\t", "");
                int index = -1;
                if( (index = buf.indexOf("#")) != -1 ) {
                    S = buf.substring(0, index) + "\n";
                    printer.append(S);
                } else {
                    S = buf + "\n";
                    printer.append(S);
                }
            }
            printer.flush();
            printer.close();
            return t;

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
