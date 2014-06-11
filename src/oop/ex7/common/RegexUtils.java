package oop.ex7.common;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nadav on 10/06/14.
 */
public class RegexUtils {

    public static List<MatchResult> Match(String pattern, String content){
        List<MatchResult> returnValue = new ArrayList<MatchResult>();
        Pattern regex =  Pattern.compile(pattern, Pattern.MULTILINE);

        Matcher matcher = regex.matcher(content);
        while (matcher.find()){
            returnValue.add(matcher.toMatchResult());
        }

        return returnValue;

    }
}
