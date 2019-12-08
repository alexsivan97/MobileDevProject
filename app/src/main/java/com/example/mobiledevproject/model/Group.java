package com.example.mobiledevproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Group {

    @Expose(serialize = false)
    private List<User> memberList;
    @Expose(serialize = false)
    private int groupId;

    @Expose
    @SerializedName("name")
    private String groupName;

    @Expose
    @SerializedName("circleMasterId")
    private int masterId;

    @Expose
    @SerializedName("desc")
    private String description;


    @Expose
    private String type;
    @Expose
    private String checkRule;

    @Expose
    private String startAt;
    @Expose
    private String endAt;

    public Group(GroupCreate groupCreate){
        this.groupName = groupCreate.getGroupName();
        this.type = "default";
        this.description = groupCreate.getDescription();
        this.checkRule = "";
        this.masterId = -1;
        this.startAt = "-1";
        this.endAt = "-1";
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

    public String getcheckRule() {
        return checkRule;
    }

    public void setcheckRule(String checkRule) {
        this.checkRule = checkRule;
    }
}
