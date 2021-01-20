package com.doubleslash.playground;

import android.app.Application;

import com.doubleslash.playground.socket.SocketMananger;
import com.doubleslash.playground.socket.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class ClientApp extends Application {
    public static final String API_URL = "http://222.251.129.150";
    public static String userToken;
    public static String userEmail;
    public static SocketMananger socketMananger;
    public static HashMap<String, Queue<Message>> RoomMsgQueues;
    public static HashMap<String, List<String>> waitingUsers;
    public static List<String> rooms = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
