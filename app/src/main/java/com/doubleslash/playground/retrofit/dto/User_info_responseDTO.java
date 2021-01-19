package com.doubleslash.playground.retrofit.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User_info_responseDTO {
    @SerializedName("result")
    @Expose
    private Integer result;

    @SerializedName("data")
    @Expose
    private User_infoDTO data = null;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public User_infoDTO getData() {
        return data;
    }

    public void setData(User_infoDTO data) {
        this.data = data;
    }
}