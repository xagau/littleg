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

public class Method {
    private VisibilityModifier visibility = null;
    private String signature;
    private String name;
    private int endIndex = 0;
    private PrimitiveType returnType = null;
    private ArrayList<Particle> arguments = new ArrayList<Particle>();
    private ArrayList<Particle> body = new ArrayList<Particle>();
    private ArrayList<Variable> localVariables = new ArrayList<Variable>();
    private Variable returnValue = null;
    public Variable invoke(ArrayList<Particle> arguments)
    {
        for (int i = 0; i < this.arguments.size(); i++) {
                System.out.println("Defined Arguments :" + (Particle) this.arguments.get(i));
        }

        if( arguments == null ){
            System.out.println("There are no arguments passed to this method");
        } else {
            for (int i = 0; i < arguments.size(); i++) {
                System.out.println("Passed Arguments :" + (Particle) arguments.get(i));
            }
        }

        for(int i = 0; i < localVariables.size(); i++){
            System.out.println("Local Variables:" + (Variable)localVariables.get(i));
        }

        Variable returnVariable = new Variable();
        returnVariable.setType(getReturnType());
        returnVariable.setValue("");

        // evaluate statement
        for(int ptr = 0; ptr < body.size(); ptr++){

            Particle p = body.get( ptr);
            System.out.println(name + ":" + ptr + ":" + p);

        }

        return returnVariable;
    }


    public Variable invoke()
    {

        return invoke(null);
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public PrimitiveType getReturnType() {
        return returnType;
    }

    public void setReturnType(PrimitiveType returnType) {
        this.returnType = returnType;
    }

    public ArrayList<Particle> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<Particle> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<Particle> getBody() {
        return body;
    }

    public void setBody(ArrayList<Particle> body) {
        this.body = body;
    }

    public Variable getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Variable returnValue) {
        this.returnValue = returnValue;
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

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public ArrayList<Variable> getLocalVariables() {
        return localVariables;
    }

    public void setLocalVariables(ArrayList<Variable> localVariables) {
        this.localVariables = localVariables;
    }
}
