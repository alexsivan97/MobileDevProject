package com.example.mobiledevproject;

import android.app.Application;

import com.example.mobiledevproject.model.User;

public class MyApp extends Application {

    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
