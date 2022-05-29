package com.me.courses_selection.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * id格式判断，包括sid和tid
 */
public class idUtil {


    private static final Pattern id_pattern=Pattern.compile("[1-9][0-9]{4}|[1-9][0-9]{9}");
//    private static final Pattern sid_pattern=Pattern.compile("[1-9][0-9]{9}$");
//    private static final Pattern tid_pattern=Pattern.compile("[1-9][0-9]{4}$");
//    private static final Pattern rid_pattern=Pattern.compile("[9][0-9]{4}");

    public static boolean isId(String id){
        if(StringUtils.isEmpty(id)){
            return false;
        }
        Matcher matcher1=id_pattern.matcher(id);
        return matcher1.matches();
    }
}
