package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_infoDTO {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("images")
    @Expose
    private List<String> images;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("university")
    @Expose
    private String university;

    @SerializedName("introduction")
    @Expose
    private String introduction;

    @SerializedName("myteams")
    @Expose
    private List<MyTeamDTO> myteams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) { this.images = images; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<MyTeamDTO> getMyteams() {
        return myteams;
    }

    public void setMyGroups(List<MyTeamDTO> myteams) {
        this.myteams = myteams;
    }
}