public class SyntaxChecker {

    public static boolean isPrimitiveType(String str)
    {
        try {
            str = str.toUpperCase();
            PrimitiveType t = PrimitiveType.valueOf(str);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static boolean isBalanced(String raw)
    {
        int block = 0;

        int len = raw.length();
        for(int i =0; i < len; i++ ){
            if( raw.charAt(i) == '{') {
                block++;
            }
            if ( raw.charAt(i) == '}') {
                block--;
            }
        }
        return block == 0;

    }

    public static boolean isVariable(String str)
    {
        str = str.trim();

        //System.out.println("Breakdown:" + str);
        String[] split = str.split(" ");
        if( split.length < 1) {
            return false;
        }
        if( !isBalanced(str) ) {
            return false;
        }
        if( isPrimitiveType(split[0]) ) {
            String varName = split[1];
            System.out.println("Variable:" + split[0] + " " + varName);
            return true;
        }
        return false;
    }


    public static boolean isClazzBalanced(Clazz clazz){
        String raw = clazz.getBody();
        int block = 0;
        int statements = 0;
        boolean checkBalanced = isBalanced(raw);
        if( block != 0 ) {
            //System.out.println("class:" + clazz.name + " has unbalanced brackets");
            //System.out.println("class:" + clazz.name + " has " + statements + " statements");
            return false;
        } else {
            //System.out.println("class:" + clazz.name + " has balanced brackets");
            //System.out.println("class:" + clazz.name + " has " + statements + " statements");
            return true;
        }
    }

}
