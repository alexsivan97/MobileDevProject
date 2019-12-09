package com.example.mobiledevproject.util;

public class StatusCodeUtil {

    public static boolean isTokenError(int status){
        if(status==403 || status==401){
            System.out.println("token error");
            //  之后应该重新加载一次token
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNormalStatus(int status){
        if(status==1){
            return true;
        }
        return false;
    }
}
