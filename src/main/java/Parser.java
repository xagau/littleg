import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {

    public static HashMap<String,Clazz> parseClazzes(File pFile) {

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(pFile)));

            StringBuffer buffer = new StringBuffer();
            String buf = null;
            while( (buf = reader.readLine()) != null) {
                buffer.append(buf);
            }

            String split[] = buffer.toString().split("class");

            int count = 1;
            for(int i = 0; i < split.length; i++ ) {
                Clazz clazz = parseClazz(split[i]);

                System.out.println("class body:" + clazz.body);
                System.out.println("class name:" +clazz.name);
            }



        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Clazz parseClazz(String code) {
        Clazz clazz = new Clazz();
        clazz.body = code;
        clazz.name = code.split("\\{")[0];
        clazz.name.trim();
        clazz.body.trim();

        clazz.methodSet = parseMethodSet(clazz);
        clazz.variableSet = parseVariableSet(clazz);

        return clazz;
    }

    public static HashMap<String, Method> parseMethodSet(Clazz clazz) {

        String raw = clazz.body;
        int block = 0;
        int statements = 0;
        boolean checkBalanced = false;
        int len = raw.length();
        for(int i =0; i < len; i++ ){
            if( raw.charAt(i) == '{') {
                block++;
            }
            if ( raw.charAt(i) == '}') {
                block--;
            }
            if ( raw.charAt(i) == ';') {
                statements++;
            }
        }
        if( block != 0 ) {
            System.out.println("class:" + clazz.name + " has unbalanced paransynthsis");
            System.out.println("class:" + clazz.name + " has " + statements + " statements");
        } else {
            System.out.println("class:" + clazz.name + " has balanced paransynthsis");
            System.out.println("class:" + clazz.name + " has " + statements + " statements");
        }

        return null;
    }

    public static HashMap<String, Variable> parseVariableSet(Clazz clazz)
    {
        return null;
    }


    public static Statement parseStatment(String code)
    {
        return null;
    }

    public static Expression parseExpression(String code)
    {
        return null;
    }

}
