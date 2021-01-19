package com.doubleslash.playground.retrofit.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sign_inDTO {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fcmToken")
    @Expose
    private String fcm_Token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getFcm_Token() {
        return fcm_Token;
    }

    public void setFcm_Token(String fcm_Token) {
        this.fcm_Token = fcm_Token;
    }
}