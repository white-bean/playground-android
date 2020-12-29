package com.doubleslash.playground.chat;


public class MessageDto {
    private String type;
    private String roomId;
    private String sender;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String messageToJson() {
        String messageJson = "{\"type\":\"" + type + "\",\"roomId\":\"" + roomId + "\",\"sender\":\"" + sender + "\",\"message\":\"" + message + "\"}";
        return messageJson;
    }
}