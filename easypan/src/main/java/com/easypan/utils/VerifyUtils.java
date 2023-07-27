package com.easypan.utils;

import com.easypan.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
    public static boolean verify(String regex, String value) {
        if(StringTools.isEmpty(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean verify(VerifyRegexEnum regexEnum, String value) {
        return verify(regexEnum.getRegex(), value);
    }
}
