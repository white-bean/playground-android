package com.doubleslash.playground.retrofit.dto.response;


import com.doubleslash.playground.retrofit.dto.TeamInfoDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team_info_responseDTO {
    @SerializedName("roomId")
    @Expose
    private String roomId;
    @SerializedName("teamDTO")
    @Expose
    private TeamInfoDTO teamInfoDTO;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public TeamInfoDTO getTeamInfoDTO() {
        return teamInfoDTO;
    }

    public void setTeamInfoDTO(TeamInfoDTO teamInfoDTO) {
        this.teamInfoDTO = teamInfoDTO;
    }
}
