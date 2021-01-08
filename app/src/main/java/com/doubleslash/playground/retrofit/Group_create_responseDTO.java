package com.doubleslash.playground.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group_create_responseDTO {
    @SerializedName("chatRoomId")
    @Expose
    private String chatRoomId;
    @SerializedName("team")
    @Expose
    private Group_infoDTO group_infoDTO;

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Group_infoDTO getGroup_infoDTO() {
        return group_infoDTO;
    }

    public void setGroup_infoDTO(Group_infoDTO group_infoDTO) {
        this.group_infoDTO = group_infoDTO;
    }
}
