package com.doubleslash.playground.socket;

import android.util.Log;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.ChatRoomDTO;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Message;
import com.doubleslash.playground.socket.model.Type;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SocketMananger {
    private static final Draft DEFAULT_DRAFT = new Draft_6455();
    private WebSocketClient webSocketClient;
    private RetrofitClient retrofitClient;
    private Gson gson = new Gson();

    private final String destination = "ws://222.251.129.150/ws/chat/websocket";

    public SocketMananger() {
        this.connectWebSocket();
    }

    public void connectWebSocket() {
        URI destinationURI;
        retrofitClient = RetrofitClient.getInstance();

        try {
            destinationURI = new URI(destination);
            Log.d("websocket", "URI : " + destinationURI.toString());

            this.webSocketClient = new WebSocketClient(destinationURI, DEFAULT_DRAFT) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d("websocket", "Socket Opened : " + handshakedata);
                    Message msg = new Message(Type.CONNECT, ClientApp.userId, System.currentTimeMillis());
                    webSocketClient.send(msg.toString());
                }

                @Override
                public void onMessage(String message) {
                    Log.d("websocket", "Received Message : " + message);

                    Message msg = gson.fromJson(message, Message.class);
                    // RoomId를 key로 사용함으로써, 해당 키가 존재하면 큐에 집어넣고 아니면 해당 키 추가

                    // 1 대 1 채팅일 경우 roomId = "member_" + memberId
                    if (msg.getAria() == Aria.PERSON) {
                        msg.setTo("member_" + msg.getTo());
                    }

                    // Aria 검증 - GROUP, ENTER
                    // Type 검증 - ENTER, LEFT 채팅방 목록이 바뀌는 시점
                    // roomInfos 갱신
                    if (msg.getType() == Type.ENTER || msg.getType() == Type.START) {
                        List<ChatRoomDTO> chatRooms = retrofitClient.getChatRoomInfos().getData();
                        ClientApp.roomInfos = new HashMap<>();
                        for (ChatRoomDTO room : chatRooms) {
                            ClientApp.roomInfos.put(room.getId(), room);
                        }
                    } else {
                        if (ClientApp.RoomMsgQueues.containsKey(msg.getTo())) {
                            ClientApp.RoomMsgQueues.get(msg.getTo()).add(msg);
                        } else {
                            ClientApp.RoomMsgQueues.put(msg.getTo(), new LinkedList<>());
                            if (msg.getType() == Type.REQUEST) {
                                // 가입 대기 중인 인원에 추가
                                if (ClientApp.waitingUsers.containsKey(msg.getTo())) {
                                    ClientApp.waitingUsers.get(msg.getTo()).add(msg.getFrom());
                                } else {
                                    ClientApp.waitingUsers.put(msg.getTo(), new LinkedList<Long>());
                                    ClientApp.waitingUsers.get(msg.getTo()).add(msg.getFrom());
                                }
                            } else {
                                ClientApp.RoomMsgQueues.get(msg.getTo()).add(msg);
                            }
                        }
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("websocket", "Socket Closed : " + code + " - " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.d("websocket", "Socket Error");
                    ex.printStackTrace();
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        connectSynchronously();
        ClientApp.RoomMsgQueues = new HashMap<>();
    }

    public void connectSynchronously() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                webSocketClient.connect();
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        webSocketClient.send(message.toString());
    }
}
