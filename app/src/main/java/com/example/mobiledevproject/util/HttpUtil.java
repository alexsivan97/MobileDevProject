package com.example.mobiledevproject.util;

import com.example.mobiledevproject.config.WebConfig;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static void getOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);

    }

    public static void postOkHttpRequest(String address, String jsonInfo, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType
                .parse("application/json; charset=utf-8"), jsonInfo);
        Request request = new Request.Builder().url(address)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void postRequestWithToken(String address, String jsonInfo, String token, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType
                .parse("application/json; charset=utf-8"), jsonInfo);
        Request request = new Request.Builder().url(address)
                .addHeader(WebConfig.TOKEN_KEY, WebConfig.TOKEN_VALUE_PRE+token)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
