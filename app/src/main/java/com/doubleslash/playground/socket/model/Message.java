package com.doubleslash.playground.socket.model;

import com.google.gson.Gson;

public class Message {
    private Aria aria;
    private Type type;
    private String from;
    private String to;
    private String text;
    // System.currentTimeMillis 형식으로 보냄
    private long sendTime;

    public Message(Type type, String from, long sendTime) {
        this.type = type;
        this.from = from;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, String from, long sendTime) {
        this.aria = aria;
        this.type = type;
        this.from = from;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, String from, String to, long sendTime) {
        this.aria = aria;
        this.type = type;
        this.from = from;
        this.to = to;
        this.sendTime = sendTime;
    }

    public Message(Aria aria, Type type, String from, String to, String text, long sendTime) {
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

    public String getFrom() {
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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
