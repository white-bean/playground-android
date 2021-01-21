package com.doubleslash.playground.database.repository;

import android.app.Application;

import com.doubleslash.playground.database.AppDatabase;
import com.doubleslash.playground.database.dao.ChatRoomDao;
import com.doubleslash.playground.database.entity.ChatRoomEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatRoomRepository {
    private ChatRoomDao chatRoomDao;
    private List<ChatRoomEntity> chatRooms;

    public ChatRoomRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        chatRoomDao = db.chatRoomDao();
    }

    public List<ChatRoomEntity> getAllChatRooms() {
        chatRooms = chatRoomDao.getAll();
        return chatRooms;
    }

    public List<ChatRoomEntity> getChatRoomsByName(String name) {
        chatRooms = chatRoomDao.getItemsByName(name);
        return chatRooms;
    }

    public List<ChatRoomEntity> serachChatRoomsByName(String name) {
        chatRooms = chatRoomDao.searchItemsByName(name);
        return chatRooms;
    }

    public void insert(final ChatRoomEntity chatRoom) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                chatRoomDao.insert(chatRoom);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(String id, String name, String roomImage) {
        Runnable runnable = () -> {
            ChatRoomEntity chatRoom = chatRoomDao.getItemById(id);
            chatRoom.setName(name);
            chatRoom.setRoomImage(roomImage);
        };
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }

    public void delete(String id) {
        Runnable runnable = () -> chatRoomDao.delete(chatRoomDao.getItemById(id));
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }
}
