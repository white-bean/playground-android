package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_infoDTO {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageUri1")
    @Expose
    private String imageUri1;

    @SerializedName("imageUri2")
    @Expose
    private String imageUri2;

    @SerializedName("imageUri3")
    @Expose
    private String imageUri3;

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

    public String getImageUri1() {
        return imageUri1;
    }

    public void setImageUri1(String imageUri1) {
        this.imageUri1 = imageUri1;
    }

    public String getImageUri2() {
        return imageUri2;
    }

    public void setImageUri2(String imageUri2) {
        this.imageUri2 = imageUri2;
    }

    public String getImageUri3() {
        return imageUri3;
    }

    public void setImageUri3(String imageUri3) {
        this.imageUri3 = imageUri3;
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