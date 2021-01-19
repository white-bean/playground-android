package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamInfoDTO {
    @SerializedName("id")
    @Expose
    private Long id;
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
//    @SerializedName("id")
//    @Expose
//    private List<ResponseFindTeamMemberDto> teamMembers;
    @SerializedName("currentMemberSize")
    @Expose
    private int currentMemberSize;
    @SerializedName("maxMemberSize")
    @Expose
    private int maxMemberSize;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public int getCurrentMemberSize() { return currentMemberSize; }

    public void setCurrentMemberSize(int currentMemberSize) { this.currentMemberSize = currentMemberSize; }

    public int getMaxMemberSize() { return maxMemberSize; }

    public void setMaxMemberSize(int maxMemberSize) { this.maxMemberSize = maxMemberSize; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTeamImageUrl() { return teamImageUrl; }

    public void setTeamImageUrl(String teamImageUrl) { this.teamImageUrl = teamImageUrl; }
}
