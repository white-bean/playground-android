package com.doubleslash.playground;

import android.app.Application;

import com.doubleslash.playground.retrofit.dto.ChatRoomDTO;
import com.doubleslash.playground.socket.SocketMananger;
import com.doubleslash.playground.socket.model.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class ClientApp extends Application {
    public static final String API_URL = "http://222.251.129.150";
    public static String userToken;
    public static long userId;
    public static SocketMananger socketMananger;
    public static HashMap<String, Queue<Message>> RoomMsgQueues;
    public static HashMap<String, List<Long>> waitingUsers;

    // 처음에 채팅방 목록 -> 서버에서 받아오고 / ENTER -> 또 서버에서 로딩
    public static HashMap<String, ChatRoomDTO> roomInfos = new HashMap<>();
    // Group : TeamId, PERSON : "member_" + MemberId

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
