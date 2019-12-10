package com.example.mobiledevproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GroupCreate implements Serializable {

    @SerializedName("name")
    private String groupName;
    private String type = "";
    private String startAt;
    private String endAt;

    @SerializedName("desc")
    private String description;
    private String checkRule = "";

    @SerializedName("circleMasterId")
    private int masterId;

    @Override
    public String toString() {
        return "GroupCreate{" +
                "groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", masterId=" + masterId +
                ", startAt='" + startAt + '\'' +
                ", endAt='" + endAt + '\'' +
                '}';
    }

    public GroupCreate(){

    }

    public GroupCreate(String groupName, String description){
        this.groupName = groupName;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheckRule() {
        return checkRule;
    }

    public void setCheckRule(String checkRule) {
        this.checkRule = checkRule;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
