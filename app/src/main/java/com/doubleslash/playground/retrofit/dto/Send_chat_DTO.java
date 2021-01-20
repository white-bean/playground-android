package com.doubleslash.playground.retrofit.dto;

import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Send_chat_DTO {
    @SerializedName("aria")
    @Expose
    private Aria aria;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("sendTime")
    @Expose
    private long sendTime;

    public Aria getAria() { return aria; }

    public void setAria(Aria aria) { this.aria = aria; }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }
}
