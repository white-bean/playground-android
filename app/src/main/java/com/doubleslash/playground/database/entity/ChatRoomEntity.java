package com.doubleslash.playground.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ChatRooms")
public class ChatRoomEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")    // Group일 때 teamId, PERSON일 때 "member" +  memberId
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "roomImage")
    private String roomImage;

    public ChatRoomEntity(String id, String name, String roomImage) {
        this.id = id;
        this.name = name;
        this.roomImage = roomImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }
}
