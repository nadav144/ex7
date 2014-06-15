package oop.ex7.main;

import oop.ex7.Parser;
import oop.ex7.ValidationResult;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * this is the main executable for the Simplified Java Compiler
 */
public class Sjavac {

    /**
     * Execte the compiler. result will be printed.
     * @param args string args containg the SJAVA's path file to check
     */
    public static void main(String[] args) {
        try{
            if (args.length != 1)
                throw new IllegalArgumentException("Execution parameters should contain a single parameter only");
            String fileName = args[0] ;
            File file = new File(fileName);

            if (!file.exists() || !file.canRead())
                throw new IOException("Cannot Read File");
            List<String> lines = Files.readAllLines(file.toPath(), Charset.defaultCharset());

            String Content = Parser.TruncAndFixLines(lines);

            ValidationResult res = Parser.Parse(Content);
            if (res.getsuccessful()){
                System.out.println(0);
            } else {
                System.out.println(1);
                System.out.println(res.getMessages());
            }

        // print if needed
        } catch (Exception ex){
            if (ex.getClass() == IOException.class){
                System.out.println(2);
            } else {
                System.out.println(1);
            }

            System.out.println(ex.getMessage());


        }
    }
}
