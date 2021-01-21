package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomDTO {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;
    @SerializedName("membersInfo")
    @Expose
    private List<MemberDTO> membersInfo;

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getTeamImageUrl() { return teamImageUrl; }

    public void setTeamImageUrl(String teamImageUrl) { this.teamImageUrl = teamImageUrl; }

    public List<MemberDTO> getMembersInfo() {
        return membersInfo;
    }

    public void setMembersInfo(List<MemberDTO> membersInfo) {
        this.membersInfo = membersInfo;
    }
}
