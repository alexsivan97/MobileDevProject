package com.example.mobiledevproject.util;

import org.json.JSONObject;

public interface HandlerResultListener {

    public void onStart();

    public void onSuccess(JSONObject responseObj);

    public void onFailure(Object obj);

    public void onFinish();

}
