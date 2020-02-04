package br.com.dalla.project.fitcard.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    public static boolean validateAgenciaMask(String agencia){
        if(agencia == null)
            return false;
        Pattern pattern = Pattern.compile("[0-9]{3}-[0-9]");
        Matcher matcher = pattern.matcher(agencia);

        return  matcher.find();
    }

    public static boolean validateContaMask(String conta){
        if(conta == null)
            return false;
        Pattern pattern = Pattern.compile("[0-9]{2}\\.[0-9]{3}-[0-9]");
        Matcher matcher = pattern.matcher(conta);

        return  matcher.find();
    }
}
