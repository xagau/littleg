import java.math.BigDecimal;

public class Utility {

    public static BigDecimal getDefaultValueForBigDecimal()
    {
        return new BigDecimal("0");
    }

    public static String getDefaultValueForString()
    {
        return "";
    }

    public static Object getDefaultValueForObject()
    {
        return new Object();
    }

    public static Boolean getDefaultValueForBoolean()
    {
        return Boolean.FALSE;
    }



    public static String parseValueForPrimitiveType(PrimitiveType type, String val)
    {
        switch(type){
            case OBJECT:
                return "{}";
            case STRING:
                return val;
            case DECIMAL:
                return val;
        }
        return null;
    }

    public static String getDefaultValueForPrimitiveType(PrimitiveType type)
    {
        switch(type){
            case OBJECT:
                return "{}";
            case STRING:
                return "";
            case DECIMAL:
                return "0";
            case BOOLEAN:
                return "false";
        }
        return null;
    }


}
