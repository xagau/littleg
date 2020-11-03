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

    public void setValue(String val)
    {
        switch(getType()){
            case DECIMAL: {
                decimal = new BigDecimal(val);
                break;

            }
            case STRING: {
                string = val;
                break;
            }
            case OBJECT: {
                object = "{}";
                break;

            }
            case BOOLEAN: {
                bool = (Boolean.parseBoolean(val));
                break;
            }
        }
    }


    public String getValue()
    {
        switch(getType()){
            case DECIMAL: {
                return decimal.toString();
            }
            case STRING: {
                return string;
            }
            case OBJECT: {
                return object.toString();
            }
            case BOOLEAN: {
                return bool.toString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrimitiveType getType() {
        return type;
    }

    public void setType(PrimitiveType type) {
        this.type = type;
    }
}
