package com.doubleslash.playground.retrofit.dto.response;

import com.doubleslash.playground.retrofit.dto.ChatRoomDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatRoomInfoResponseDTO {
    @SerializedName("result")
    @Expose
    private Integer result;

    @SerializedName("data")
    @Expose
    private List<ChatRoomDTO> data = null;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<ChatRoomDTO> getData() {
        return data;
    }

    public void setData(List<ChatRoomDTO> data) {
        this.data = data;
    }
}
