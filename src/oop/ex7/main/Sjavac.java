package oop.ex7.main;

import oop.ex7.Parser;
import oop.ex7.ValidationResult;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Sjavac {

    public static void main(String[] args) {
	// write your code here

        try{
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
            System.out.println(1);
            System.out.println(ex.getMessage());


        }
    }
}
