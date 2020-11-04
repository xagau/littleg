import java.util.ArrayList;
import java.util.HashMap;

public class Clazz {
    private HashMap<String, Method> methodSet = new HashMap<String, Method>();
    private HashMap<String, Variable> variableSet = new HashMap<String, Variable>();
    private String body = "";
    private String name = "";
    private ArrayList<Particle> bodySet = new ArrayList<Particle>();

    public HashMap<String, Method> getMethodSet() {
        return methodSet;
    }

    public void setMethodSet(HashMap<String, Method> methodSet) {
        this.methodSet = methodSet;
    }

    public HashMap<String, Variable> getVariableSet() {
        return variableSet;
    }

    public void setVariableSet(HashMap<String, Variable> variableSet) {
        this.variableSet = variableSet;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Particle> getBodySet() {
        return bodySet;
    }

    public void setBodySet(ArrayList<Particle> bodySet) {
        this.bodySet = bodySet;
    }
}
