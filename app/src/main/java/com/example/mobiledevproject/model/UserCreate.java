package com.example.mobiledevproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserCreate {

    @Expose(serialize=false)
    private int userId;


    @Expose
    @SerializedName("username")
    private String userName;


    @Expose
    @SerializedName("password")
    private String password;

    public UserCreate(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public UserCreate(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
