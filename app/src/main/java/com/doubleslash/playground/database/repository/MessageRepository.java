package com.doubleslash.playground.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.doubleslash.playground.database.AppDatabase;
import com.doubleslash.playground.database.dao.MessageDao;
import com.doubleslash.playground.database.entity.MessageEntity;
import com.doubleslash.playground.socket.model.Type;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageRepository {
    private MessageDao messageDao;
    private List<MessageEntity> messages;

    public MessageRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        messageDao = db.messageDao();
    }

    public List<MessageEntity> getAllMessages() {
        messages = messageDao.getAll();
        return messages;
    }

    public List<MessageEntity> getMessagesByRoomId(String roomId) {
        messages = messageDao.getItemByroomId(roomId);
        return messages;
    }

    public void insert(final MessageEntity message) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                messageDao.insert(message);
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, int type, String from, String to, String text, String sendTime) {
        Runnable runnable = () -> {
            MessageEntity message = messageDao.getItemById(id);
            message.setType(type);
            message.setFrom(from);
            message.setRoomId(to);
            message.setText(text);
            message.setSendTime(sendTime);
            messageDao.update(message);
        };
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }

    public void delete(int id) {
        Runnable runnable = () -> messageDao.delete(messageDao.getItemById(id));
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }
}
