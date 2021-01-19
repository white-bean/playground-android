package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_infoDTO {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageUris")
    @Expose
    private String[] imageUris;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("school")
    @Expose
    private String school;

    @SerializedName("introduction")
    @Expose
    private String introduction;

    @SerializedName("myGroups")
    @Expose
    private List<TeamInfoDTO> myGroups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImageUris() {
        return imageUris;
    }

    public void setImageUris(String[] imageUris) { this.imageUris = imageUris; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<TeamInfoDTO> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<TeamInfoDTO> myGroups) {
        this.myGroups = myGroups;
    }
}