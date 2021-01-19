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
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("currentMemberSize")
    @Expose
    private Integer currentMemberSize;
    @SerializedName("location")
    @Expose
    private String location;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTeamImageUrl() { return teamImageUrl; }

    public void setTeamImageUrl(String teamImageUrl) { this.teamImageUrl = teamImageUrl; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getCurrentMemberSize() { return currentMemberSize; }

    public void setCurrentMemberSize(Integer currentMemberSize) { this.currentMemberSize = currentMemberSize; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
