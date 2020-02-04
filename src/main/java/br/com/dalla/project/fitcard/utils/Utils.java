package br.com.dalla.project.fitcard.utils;


import br.com.dalla.project.fitcard.erros.tipos.BaseException;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isNullOrEmpty(String string){
        if(string == null || string == "")
            return true;
        return false;
    }

}
