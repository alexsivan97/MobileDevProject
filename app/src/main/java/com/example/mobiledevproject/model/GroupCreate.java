package com.example.mobiledevproject.model;

import java.io.Serializable;

public class GroupCreate implements Serializable {

    private String groupName;
    private int groupId;
    private String description;
    private User founder;

    public GroupCreate(String groupName, String description, User founder){
        this.groupName = groupName;
        this.description = description;
        this.founder = founder;
    }

    public GroupCreate(String groupName, String description){
        this.groupName = groupName;
        this.description = description;
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

    public User getFounder() {
        return founder;
    }

    public void setFounder(User founder) {
        this.founder = founder;
    }


}
