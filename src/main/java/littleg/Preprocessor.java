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

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Preprocessor {
    public File process( File file ) {
        try {

            // strip out comments
            // strip out append included files
            String str = file.getAbsolutePath() + ".out";
            File t = new File(str);
            if( t.exists() ) { t.delete(); }
            t = new File(str);
            FileWriter printer = new FileWriter(t);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String buf = null;
            String S = "";
            while (( buf = reader.readLine()) != null ) {
                buf = buf.replaceAll("\t", "");
                int index = -1;
                if( (index = buf.indexOf("#")) != -1 ) {
                    S = buf.substring(0, index) + "\n";
                    printer.append(S);
                } else {
                    S = buf + "\n";
                    printer.append(S);
                }
            }
            printer.flush();
            printer.close();
            return t;

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
