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

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Loader {
    File file = null;
    public Loader(File file)
    {
        this.file = file;
    }

    public void printNormalizedList(ArrayList<Particle> list)
    {
        for(int i = 0; i < list.size(); i++){
            Particle p = list.get(i);
            try {
                boolean interactive = false;
                boolean verbose =true;
                if (verbose) {
                    System.out.println("::" + i + ":" + p );
                }
                if (interactive) {
                    System.in.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public HashMap<String, Clazz> compile()
    {
        try {
            System.out.println("Run preprocessor");
            Preprocessor processor = new Preprocessor();
            File pFile = processor.process(file);
            if(pFile == null ){
                System.out.println("Preprocessor ran into an error");
            }
            System.out.println("Parse particles from file");
            ArrayList<Particle> list = Parser.parse(pFile);

            printNormalizedList(list);

            System.out.println("Parse classes from file particle list");
            HashMap<String, Clazz> map = Parser.parseClasses(list);
            System.out.println("file particle list contained:" + map.size() + " classes");

            Set set = map.keySet();
            Iterator iterator = set.iterator();
            while( iterator.hasNext() ){
                Clazz clazz = map.get((String)iterator.next());
                // parse out method definitions within class. For method calls that belong to external classes, leave an undefined/defined reference.
                HashMap<String, Method> methodSet = Parser.parseMethods(clazz);
                clazz.setMethodSet(methodSet);

                // Parse out global scope variables to class.
                HashMap<String, Variable> variableSet = Parser.parseVariables(clazz);
                clazz.setVariableSet(variableSet);

            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}