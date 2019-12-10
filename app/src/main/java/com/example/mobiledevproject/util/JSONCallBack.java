package com.example.mobiledevproject.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class JSONCallBack implements Callback {
    private Handler uiTransferHandler;  //进行消息的转发，操作UI线程。
    private HandlerResultListener resultListener;
    private Gson gson;
    private static final String TAG = "JSONCallBack";

    public JSONCallBack(HandlerResultListener resultListener){
        this.gson = new Gson();
        this.resultListener = resultListener;
        this.uiTransferHandler = new Handler(Looper.getMainLooper());
    }

    public void onStart(){
        resultListener.onStart();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        uiTransferHandler.post(new Runnable() {
            @Override
            public void run() {
                resultListener.onFailure(new OkHttpException(-1, e.getMessage()));
                resultListener.onFinish();
            }
        });
    }

    @Override
    public void onResponse(final Call call, final Response response) throws IOException {

        final String result = response.body().string();
        Log.i(TAG, "onResponse: "+result);
        uiTransferHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResult(result);
            }
        });
    }

    private void handleResult(Object result){
        if(null==result||result.toString().trim().equals("")){
            resultListener.onFailure(new OkHttpException(-1, "请求结果为空"));
            return;
        }

        try{
            JSONObject jsonObject = new JSONObject(result.toString());
            if(jsonObject.has("status") && jsonObject.has("data")){
                if(jsonObject.getInt("status")==1){
                    resultListener.onSuccess(jsonObject);
                } else {
                    resultListener.onFailure(new OkHttpException(jsonObject.getInt("status"), "状态码错误"));
                }
            } else {
                resultListener.onFailure(new OkHttpException(-2, "返回数据格式错误"));
            }
        } catch (Exception e){
            resultListener.onFailure(new OkHttpException(-3, e.getMessage()));
        } finally {
            resultListener.onFinish();
        }
    }
}
