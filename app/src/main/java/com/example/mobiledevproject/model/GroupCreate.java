package com.example.mobiledevproject.model;

import java.io.Serializable;

public class GroupCreate implements Serializable {

    private String groupName;
    private int groupId;
    private String description;
    private User master;
    private String startAt;
    private String endAt;

    public GroupCreate(){

    }

    public GroupCreate(String groupName, String description, User founder){
        this.groupName = groupName;
        this.description = description;
        this.master = founder;
    }

    public GroupCreate(String groupName, String description){
        this.groupName = groupName;
        this.description = description;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User founder) {
        this.master = founder;
    }


}
