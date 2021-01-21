package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total_group_dataDTO {
    @SerializedName("teamId")
    @Expose
    private Long teamId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("currentMemberCount")
    @Expose
    private Integer currentMemberCount;

    @SerializedName("maxMemberCount")
    @Expose
    private Integer maxMemberCount;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("teamImageUrl")
    @Expose
    private String imageUri;

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCurrentMemberCount() {
        return currentMemberCount;
    }

    public void setCurrentMemberCount(Integer currentMemberCount) { this.currentMemberCount = currentMemberCount; }

    public Integer getMaxMemberCount() {
        return maxMemberCount;
    }

    public void setMaxMemberCount(Integer maxMemberCount) {
        this.maxMemberCount = maxMemberCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
