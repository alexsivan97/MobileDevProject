package com.example.mobiledevproject.model;

import java.util.List;

public class Group {

    private List<User> memberList;
    private int groupId;
    private String groupName;
    private int masterId;
    private String description;

    private String type;
    private String rule;

    public Group(GroupCreate groupCreate){
        this.groupName = groupCreate.getGroupName();
        this.type = "default";
        this.description = groupCreate.getDescription();
        this.rule = "";
        this.masterId = -1;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<User> memberList) {
        this.memberList = memberList;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
