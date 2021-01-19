package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateTeamDTO {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("maxMemberSize")
    @Expose
    private Integer maxMemberSize;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public Integer getMaxMemberSize() { return maxMemberSize; }

    public void setMaxMemberSize(Integer maxMemberSize) { this.maxMemberSize = maxMemberSize; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTeamImageUrl() { return teamImageUrl; }

    public void setTeamImageUrl(String teamImageUrl) { this.teamImageUrl = teamImageUrl; }
}
