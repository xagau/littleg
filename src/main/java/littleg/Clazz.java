package littleg;
/** Copyright (c) 2020 Sean Beecroft,
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * @author Sean Beecroft (aka xagau) https://www.github.com/xagau
 *
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Clazz {
    private HashMap<String, Method> methodSet = new HashMap<String, Method>();
    private HashMap<String, Variable> variableSet = new HashMap<String, Variable>();
    private String body = "";
    private String name = "";
    private ArrayList<Particle> bodySet = new ArrayList<Particle>();

    public void addMethod(Method m){
        methodSet.put(m.getSignature(), m);
    }

    public Method getMethod(String signature)
    {
        return methodSet.get(signature);
    }

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
