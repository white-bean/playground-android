package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTeamDTO {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("currentMemberSize")
    @Expose
    private Integer currentMemberSize;

    @SerializedName("maximumMemberSize")
    @Expose
    private Integer maximumMemberSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentMemberSize() {
        return currentMemberSize;
    }

    public void setCurrentMemberSize(Integer currentMemberSize) {
        this.currentMemberSize = currentMemberSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaximumMemberSize() {
        return maximumMemberSize;
    }

    public void setMaximumMemberSize(Integer maximumMemberSize) {
        this.maximumMemberSize = maximumMemberSize;
    }
}
