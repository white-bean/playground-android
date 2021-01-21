package com.doubleslash.playground.retrofit.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckChatRoomResponseDTO {
    @SerializedName("result")
    @Expose
    private Long result;
    @SerializedName("message")
    @Expose
    private String message;

    public Long getResult() {
        return result;
    }

    public void setResult(Long result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
