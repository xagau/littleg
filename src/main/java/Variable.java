import java.math.BigDecimal;

public class Variable {
    private String name;
    private VisibilityModifier visibility = VisibilityModifier.PRIVATE;
    private PrimitiveType type = PrimitiveType.UNDEFINED;

    BigDecimal decimal = new BigDecimal(0);
    Boolean bool = false;
    String string = "";
    Object object = null;
    Void nothing = null;

    public void setValue(Object o)
    {
        switch(type){
            case DECIMAL: {
                if( o instanceof BigDecimal ){
                    decimal = (BigDecimal)o;
                }
                break;

            }
            case STRING: {
                if( o instanceof String ){
                    string = (String)o;
                }
                break;
            }
            case OBJECT: {
                if( o instanceof Object ) {
                    object = (Object) o;
                }
                break;

            }
            case BOOLEAN: {
                if( o instanceof Boolean ) {
                    bool = (Boolean) o;
                }
                break;
            }
            case VOID: {
                if( o instanceof Void ) {
                    nothing = (Void) o;
                }
                break;
            }
        }
    }


    public Object getValue()
    {
        switch(type){
            case DECIMAL: {
                return decimal;
            }
            case STRING: {
                return string;
            }
            case OBJECT: {
                return object;
            }
            case VOID: {
                return nothing;
            }
            case BOOLEAN: {
                return bool;
            }
        }
        return null;
    }

    public VisibilityModifier getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityModifier visibility) {
        this.visibility = visibility;
    }
}
