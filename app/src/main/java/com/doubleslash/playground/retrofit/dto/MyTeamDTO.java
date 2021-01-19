package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTeamDTO {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("teamImageUrl")
    @Expose
    private String teamImageUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("currentMemberSize")
    @Expose
    private Integer currentMemberSize;
}
