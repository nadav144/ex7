
package oop.ex7.main;

import oop.ex7.common.ValidationResult;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * this is the main executable for the Simplified Java Compiler
 */
public class Sjavac {

	/**
	 * Runs a SJava compiler, outputs 0 if the file is valid, 1 if there
	 * compilation errors and 2 if there were IO errors
	 * 
	 * @param args
	 *            only one variable, the file path to compile.
	 */
    public static void main(String[] args) {
		
        try{
            if (args.length != 1)
                throw new IllegalArgumentException("Execution parameters should contain a single parameter only");
            String fileName = args[0] ;
            File file = new File(fileName);

            if (!file.exists() || !file.canRead())
                throw new IOException("Cannot Read File");
			List< String > lines =
					Files.readAllLines( file.toPath(), Charset.defaultCharset() );

			String Content = Compiler.truncAndFixLines(lines);
			
			ValidationResult res = Compiler.parse(Content);

            if (res.getSuccessful()){
                System.out.println(0);
			}
			else {
                System.out.println(1);
				System.err.println( res.getMessages() );
            }

        // print if needed
		}
		catch ( Exception ex ) {
            if (ex.getClass() == IOException.class){
                System.out.println(2);
			}
			else {
                System.out.println(1);
            }

			System.err.println( ex.getMessage() );

        }
    }
}
