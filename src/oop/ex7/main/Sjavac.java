package oop.ex7.main;

import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import oop.ex7.Parser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Stack;

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

            Parser.Parse(Content);


        // Handle Files - Trim, Syntax

        // MainScope = Parser.ParseMethods()

        // Parser.Parse(fileName, MainScope)

        // print if needed
        } catch (Exception ex){
            System.out.println(0);

        }
    }
}