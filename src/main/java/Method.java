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
 * @author Sean Beecorft (aka xagau) https://www.github.com/xagau
 *
 */

import java.util.ArrayList;

public class Method {
    private VisibilityModifier visibility = null;
    private String signature;
    private PrimitiveType returnType = null;
    private ArrayList<Statement> arguments = new ArrayList<Statement>();
    private ArrayList<Statement> body = new ArrayList<Statement>();
    private Variable returnValue = null;
    public Variable invoke(ArrayList arguments)
    {
        return null;
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

    public ArrayList<Statement> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<Statement> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<Statement> getBody() {
        return body;
    }

    public void setBody(ArrayList<Statement> body) {
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
}
