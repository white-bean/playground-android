package com.doubleslash.playground.retrofit.dto.response;


import com.doubleslash.playground.retrofit.dto.TeamInfoDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team_info_responseDTO {
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("data")
    @Expose
    private TeamInfoDTO data;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public TeamInfoDTO getData() {
        return data;
    }

    public void setData(TeamInfoDTO data) {
        this.data = data;
    }
}
