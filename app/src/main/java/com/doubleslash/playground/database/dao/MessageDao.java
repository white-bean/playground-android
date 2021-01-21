package com.doubleslash.playground.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.doubleslash.playground.database.entity.MessageEntity;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Messages")
    List<MessageEntity> getAll();

    @Insert
    void insert(MessageEntity message);

    @Update
    void update(MessageEntity message);

    @Delete
    void delete(MessageEntity message);

    @Query("SELECT * FROM messages WHERE id = :id")
    MessageEntity getItemById(int id);

    @Query("SELECT * FROM Messages WHERE roomId = :roomId AND id < :id ORDER BY id DESC LIMIT 1")
    List<MessageEntity> getLast10Items(String roomId, int id);

    @Query("SELECT * FROM messages WHERE roomId = :roomId")
    List<MessageEntity> getItemByroomId(String roomId);

    @Query("SELECT * FROM Messages WHERE roomId = :roomId ORDER BY id DESC LIMIT 1")
    MessageEntity getLastItemByroomId(String roomId);
}
