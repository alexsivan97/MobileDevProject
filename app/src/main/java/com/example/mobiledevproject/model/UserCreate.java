package com.example.mobiledevproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserCreate implements Serializable {

    @Expose(serialize=false)
    private int userId;

    @Expose
    @SerializedName("username")
    private String userName;

    @Expose
    @SerializedName("password")
    private String password;

    public UserCreate(){

    }

    public UserCreate(User user){
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.userId = user.getUserId();
    }

    public UserCreate(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public UserCreate(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCreate{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
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
