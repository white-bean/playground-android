package com.doubleslash.playground.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.doubleslash.playground.database.entity.ChatRoomEntity;

import java.util.List;

@Dao
public interface ChatRoomDao {
    @Query("SELECT * FROM ChatRooms")
    List<ChatRoomEntity> getAll();

    @Insert
    void insert(ChatRoomEntity chatRoom);

    @Update
    void update(ChatRoomEntity chatRoom);

    @Delete
    void delete(ChatRoomEntity chatRoom);

    @Query("SELECT * FROM ChatRooms WHERE id = :id")
    ChatRoomEntity getItemById(String id);

    @Query("SELECT * FROM ChatRooms WHERE name = :name")
    List<ChatRoomEntity> getItemsByName(String name);

    @Query("SELECT * FROM ChatRooms WHERE name LIKE :name")
    List<ChatRoomEntity> searchItemsByName(String name);
}
