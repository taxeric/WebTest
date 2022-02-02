package com.kuang.utils;

public class TextUtils {

    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public static boolean isNotNullAndEmpty(String str){
        return !isNullOrEmpty(str);
    }
}
