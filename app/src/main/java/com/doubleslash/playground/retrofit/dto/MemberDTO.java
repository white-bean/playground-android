package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberDTO {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("nickname")
    @Expose
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
