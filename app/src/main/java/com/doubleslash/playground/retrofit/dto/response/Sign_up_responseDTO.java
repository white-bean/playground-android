package com.doubleslash.playground.retrofit.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sign_up_responseDTO {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
