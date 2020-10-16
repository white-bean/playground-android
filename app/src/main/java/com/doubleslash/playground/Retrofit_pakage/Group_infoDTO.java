package com.doubleslash.playground.Retrofit_pakage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group_infoDTO {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("maxMemberCount")
    @Expose
    private Integer maxMemberCount;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxMemberCount() {
        return maxMemberCount;
    }

    public void setMaxMemberCount(Integer maxMemberCount) {
        this.maxMemberCount = maxMemberCount;
    }

}
