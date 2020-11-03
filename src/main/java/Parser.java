import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

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

            HashMap<String, Clazz> map = new HashMap<String, Clazz>();
            for(int i = 0; i < split.length; i++ ) {
                String part = split[i];
                Clazz clazz = parseClazz(part);
                map.put(clazz.name, clazz);
            }

            return map;

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

        try {
            clazz.methodSet = parseMethodSet(clazz);
        } catch(Exception ex) { ex.printStackTrace(); }
        try {
            clazz.variableSet = parseVariableSet(clazz);
        } catch(Exception ex) { ex.printStackTrace(); }

        return clazz;
    }

    public static HashMap<String, Method> parseMethodSet(Clazz clazz) {

        HashMap<String, Method> methodSet = new HashMap<String, Method>();

        boolean isBalanced = SyntaxChecker.isClazzBalanced(clazz);
        if( !isBalanced ){
            return null;
        }
        return methodSet;
    }


    public static HashMap<String, Variable> parseVariableSet(Clazz clazz)
    {
        if( clazz.name.equals("")) { return new HashMap<String, Variable>(); }
        //System.out.println("Parse Variable Set:[" + clazz.name + "]");
        String raw = clazz.body;
        raw = raw.replaceAll(clazz.name, "");
        int index = raw.indexOf("{");
        raw = raw.substring(index+1);
        index = raw.lastIndexOf("}");
        try { raw = raw.substring(0, index); } catch(Exception ex) { }
        String[] split = raw.split(";");
        HashMap<String, Variable> varSet = new HashMap<String, Variable>();
        for(int i = 0; i < split.length; i++ ){
            boolean isVar = SyntaxChecker.isVariable(split[i]);
            if( isVar ){
                Variable v = parseVariable(split[i]);
                varSet.put(v.getName(), v);
            }
        }

        System.out.println(clazz.name + " has " + varSet.size() + " global variables ");

        return varSet;
    }

    public static Variable parseVariable(String str)
    {
        String[] splits = str.split(" ");
        Variable var = new Variable();
        String varName = splits[1];
        var.setVisibility(VisibilityModifier.PRIVATE);
        var.setName(varName);


        PrimitiveType type = PrimitiveType.UNDEFINED;
        try {
            type = PrimitiveType.valueOf(splits[0].toUpperCase());
        } catch(Exception ex) {  }
        var.setType(type);
        // parse value
        // ...
        try {
            if( splits[2].equals("=")){
                String val = splits[3];
                var.setValue(Utility.parseValueForPrimitiveType(type, val));
                // Do we allow complex expressions? Scope here.
            }
        } catch(Exception ex) {
            var.setValue(Utility.getDefaultValueForPrimitiveType(type));
        }

        return var;
    }


    public static Statement parseStatement(String code)
    {
        return null;
    }

    public static Expression parseExpression(String code)
    {
        return null;
    }

}
