package com.doubleslash.playground.socket.model;

public class Message {
    private Type type;
    private String from;
    private String to;
    private String text;

    public Message(Type type, String from, String to) {
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public Message(Type type, String from, String to, String text) {
        this.type = type;
        this.from = from;
        this.to = to;
        this.text = text;
    }

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

    @Override
    public String toString() {
        if (text == null) return "{\"type\":\"" + type + "\",\"from\":\"" + from + "\",\"to\":\"" + to + "\"}";
        return "{\"type\":\"" + type + "\",\"from\":\"" + from + "\",\"to\":\"" + to + "\",\"text\":\"" + text + "\"}";
    }
}
