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

import java.io.File;
import java.util.HashMap;

public class littleg {

    public static void main(String[] args) {
        try {
            if( args != null ) {
                // tmp for building.
                args = new String[]{"helloworld.g"};
            }
            if (args.length < 0) {
                System.out.println("littleg file.g");
            }
            Loader loader = new Loader(new File(args[0]));
            HashMap<String, Clazz> list = loader.compile();
            String target = "Four";
            Clazz main = list.get(target);

            if( main == null ){
                System.out.println("Target class:" + target + " could not be found");
                return;
            }

            for(int i = 0; i < main.getBodySet().size(); i++ ){
                System.out.println(main.getBodySet().get(i));
            }
            // encapsulate
            //Method m = main.getMethodSet().get("main");
            //m.invoke();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
