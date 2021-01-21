package com.doubleslash.playground.socket.model;

import com.google.gson.Gson;

public class Message {
    private Aria aria;
    private Type type;
    private long from;
    private String to;
    private String text;
    // System.currentTimeMillis 형식으로 보냄
    private long sendTime;

    public Message(Type type, long from, long sendTime) {
        this.type = type;
        this.from = from;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, long from, long sendTime) {
        this.aria = aria;
        this.type = type;
        this.from = from;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, long from, String to, long sendTime) {
        this.aria = aria;
        this.type = type;
        this.from = from;
        this.to = to;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, long from, String to, String text, long sendTime) {
        this.aria = aria;
        this.type = type;
        this.from = from;
        this.to = to;
        this.text = text;
        this.sendTime = sendTime;
    }

    public Aria getAria() { return aria; }

    public Type getType() {
        return type;
    }

    public long getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setAria(Aria aria) { this.aria = aria; }

    public void setType(Type type) { this.type = type; }

    public void setFrom(long from) { this.from = from; }

    public void setTo(String to) { this.to = to; }

    public void setText(String text) { this.text = text; }

    public void setSendTime(long sendTime) { this.sendTime = sendTime; }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
