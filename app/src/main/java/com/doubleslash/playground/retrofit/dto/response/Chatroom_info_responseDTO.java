package com.doubleslash.playground.retrofit.dto.response;


import java.util.List;

import com.doubleslash.playground.retrofit.dto.Chatroom_infoDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Chatroom_info_responseDTO {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("data")
    @Expose
    private List<Chatroom_infoDTO> data = null;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public List<Chatroom_infoDTO> getData() {
        return data;
    }

    public void setData(List<Chatroom_infoDTO> data) {
        this.data = data;
    }
}
