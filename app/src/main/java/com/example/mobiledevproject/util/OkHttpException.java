package com.example.mobiledevproject.util;

public class OkHttpException extends Exception {

    private int msgCode;

    private Object msg;

    public OkHttpException(int msgCode, Object msg){
        this.msg = msg;
        this.msgCode = msgCode;
    }

    public int getMsgCode(){
        return msgCode;
    }

    public Object getMsg(){
        return msg;
    }

}
