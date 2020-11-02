import java.util.ArrayList;

public class Method {
    private String signature;
    private ArrayList arguments = new ArrayList();
    private ArrayList<Statement> body = new ArrayList<Statement>();
    Variable returnValue = null;

    public Variable invoke(ArrayList arguments)
    {
        return null;
    }

    public Variable invoke()
    {
        return invoke(null);
    }


}
