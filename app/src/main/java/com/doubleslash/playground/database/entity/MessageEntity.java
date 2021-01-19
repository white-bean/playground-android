package com.doubleslash.playground.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Messages")
public class MessageEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "type")
    private int type;
    @ColumnInfo(name = "from")
    private String from;
    @ColumnInfo(name = "roomId")
    private String roomId;
    @ColumnInfo(name = "text")
    private String text;
    @ColumnInfo(name = "sendTime")
    private long sendTime;

    public MessageEntity(int type, String from, String roomId, String text, long sendTime) {
        this.type = type;
        this.from = from;
        this.roomId = roomId;
        this.text = text;
        this.sendTime = sendTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
