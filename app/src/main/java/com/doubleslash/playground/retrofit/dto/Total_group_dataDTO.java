package com.doubleslash.playground.retrofit.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total_group_dataDTO {

    @SerializedName("teamId")
    @Expose
    private long teamId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("maxMemberCount")
    @Expose
    private Integer maxMemberCount;
    @SerializedName("location")
    @Expose
    private Total_group_locationDTO location;
    @SerializedName("category")
    @Expose
    private String category;

    public long getTeamId() {
        return teamId;
    }

    public void getTeamId(long teamId) {
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

    public Integer getMaxMemberCount() {
        return maxMemberCount;
    }

    public void setMaxMemberCount(Integer maxMemberCount) {
        this.maxMemberCount = maxMemberCount;
    }

    public Total_group_locationDTO getLocation() {
        return location;
    }

    public void setLocation(Total_group_locationDTO location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
