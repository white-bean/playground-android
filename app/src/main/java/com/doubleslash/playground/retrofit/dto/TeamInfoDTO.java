package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamInfoDTO {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("adminInfo")
    @Expose
    private AdminInfoDTO adminInfo;
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
    @SerializedName("teamMembers")
    @Expose
    private List<MemberDTO> teamMembers;
    @SerializedName("currentMemberSize")
    @Expose
    private Integer currentMemberSize;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;

    @SerializedName("admin")
    @Expose
    private Boolean admin;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public AdminInfoDTO getAdminInfo() {
        return adminInfo;
    }

    public void setAdminInfo(AdminInfoDTO adminInfo) {
        this.adminInfo = adminInfo;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public List<MemberDTO> getTeamMembers() { return teamMembers; }

    public void setTeamMembers(List<MemberDTO> teamMembers) { this.teamMembers = teamMembers; }

    public int getCurrentMemberSize() { return currentMemberSize; }

    public void setCurrentMemberSize(Integer currentMemberSize) { this.currentMemberSize = currentMemberSize; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getTeamImageUrl() { return teamImageUrl; }

    public void setTeamImageUrl(String teamImageUrl) { this.teamImageUrl = teamImageUrl; }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
