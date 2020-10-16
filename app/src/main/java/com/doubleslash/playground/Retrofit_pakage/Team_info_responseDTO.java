package com.doubleslash.playground.Retrofit_pakage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team_info_responseDTO {
    @SerializedName("roomId")
    @Expose
    private String roomId;
    @SerializedName("teamDTO")
    @Expose
    private TeamDTO teamDTO;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public TeamDTO getTeamDTO() {
        return teamDTO;
    }

    public void setTeamDTO(TeamDTO teamDTO) {
        this.teamDTO = teamDTO;
    }
}
