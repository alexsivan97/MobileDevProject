package com.example.mobiledevproject.model;

import android.graphics.Bitmap;


import java.io.Serializable;
import java.util.List;

public class MessageBean implements Serializable {

    private String userId;
    private String content;
    private List<String> localImages;
    private List<Bitmap> onlineImages;
    private String time;

    public MessageBean(String userId, String content, List<String> localImages, List<Bitmap> onlineImages, String time) {
        this.userId = userId;
        this.content = content;
        this.localImages = localImages;
        this.onlineImages = onlineImages;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public List<String> getLocalImages() {
        return localImages;
    }

    public void setLocalImages(List<String> localImages) {
        this.localImages = localImages;
    }

    public List<Bitmap> getOnlineImages() {
        return onlineImages;
    }

    public void setOnlineImages(List<Bitmap> onlineImages) {
        this.onlineImages = onlineImages;
    }
}
