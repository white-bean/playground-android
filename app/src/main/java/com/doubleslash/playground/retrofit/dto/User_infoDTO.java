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
    private List<ImageDTO> imageUris;

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
    private List<TeamDTO> myGroups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImageDTO> getImageUris() {
        return imageUris;
    }

    public void setImageUris(List<ImageDTO> imageUris) {
        this.imageUris = imageUris;
    }

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

    public List<TeamDTO> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<TeamDTO> myGroups) {
        this.myGroups = myGroups;
    }
}