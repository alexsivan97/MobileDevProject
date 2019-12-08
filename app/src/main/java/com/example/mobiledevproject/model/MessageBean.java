package com.example.mobiledevproject.model;

import android.graphics.Bitmap;


import java.io.Serializable;
import java.util.List;

public class MessageBean implements Serializable {

    private String userId;
    private String content;
    private List<String> localImagePath;
    private List<String> onlineImagePath;
    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLocalImagePath() {
        return localImagePath;
    }

    public void setLocalImagePath(List<String> localImagePath) {
        this.localImagePath = localImagePath;
    }

    public List<String> getOnlineImagePath() {
        return onlineImagePath;
    }

    public void setOnlineImagePath(List<String> onlineImagePath) {
        this.onlineImagePath = onlineImagePath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MessageBean(String userId, String content, List<String> localImagePath, List<String> onlineImagePath, String time) {
        this.userId = userId;
        this.content = content;
        this.localImagePath = localImagePath;
        this.onlineImagePath = onlineImagePath;
        this.time = time;
    }
}
