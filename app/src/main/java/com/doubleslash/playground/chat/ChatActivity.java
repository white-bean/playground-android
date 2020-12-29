package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.doubleslash.playground.R;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;

import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import nbouma.com.wstompclient.implementation.StompClient;
import nbouma.com.wstompclient.model.Frame;

public class ChatActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private static ChatAdapter adapter=new ChatAdapter();
    public static StompClient stompClient;
    private EditText messageBox;
    private TextView sendBtn;
    private static String my_id="";
    public static Queue<String> message=new LinkedList<>();
    private static final String TAG = "stomp app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUI();
    }

    private void initUI() {

        recyclerView = findViewById(R.id.recyclerView);
        messageBox= findViewById(R.id.content_edit);
        sendBtn = findViewById(R.id.send_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Intent intent=getIntent();
        final String roomid=intent.getStringExtra("roomId");
        my_id="chan";
        System.out.println(roomid);
        //Intent intent=getIntent();
        //String email=intent.getStringExtra("email");
        //String sender=email.split("@")[0];
        connectStomp();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            break;
        }
        subscribeStomp(my_id,roomid);
        addDummyData(adapter);
        recyclerView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전송 누르면 할 일
                if(messageBox.getText().toString()!=null)
                    sendMessage("chan",roomid);

            }
        });
    }

    private void addDummyData(ChatAdapter adapter) {
        adapter.addItem(new ChatItem(null,"유저 1이 입장하셨습니다.", null, ChatType.ViewType.CENTER_CONTENT));
        adapter.addItem(new ChatItem(null,"유저 2가 입장하셨습니다.", null, ChatType.ViewType.CENTER_CONTENT));
        adapter.addItem(new ChatItem("유저 1","상대가 보낸 메세지", "00:00", ChatType.ViewType.LEFT_CONTENT));
        adapter.addItem(new ChatItem(null,"상대가 보낸 메세지", "00:00", ChatType.ViewType.RIGHT_CONTENT));
    }

    private void connectStomp() {
        stompClient = new StompClient("ws://222.251.129.150/ws/chat/websocket") { //example "ws://localhost:8080/message-server"
            @Override
            protected void onStompError(String errorMessage) {
                Log.d(TAG, "error : " + errorMessage);
            }

            @Override
            protected void onConnection(boolean connected) {
                Log.d(TAG, "connected : " + String.valueOf(connected));
            }

            @Override
            protected void onDisconnection(String reason) {
                Log.d(TAG, "disconnected : " + reason);
            }


            @Override
            protected void onStompMessage(String frame) throws JSONException {
                JSONObject jsonObject=new JSONObject(frame);
                String sender=jsonObject.get("sender").toString();
                String message=jsonObject.get("message").toString();
                String type=jsonObject.get("type").toString();
                Log.d("Message : ",message);
                if(message!=null) {
                    if (sender.equals(my_id)) {
                        if(type.equals("ENTER")){
                            adapter.addItem(new ChatItem(sender, message, Long.toString(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                        }else{
                            adapter.addItem(new ChatItem(sender, message, Long.toString(System.currentTimeMillis()), ChatType.ViewType.RIGHT_CONTENT));
                        }
                    }
                    else if(!sender.equals(my_id)) {
                        if (type.equals("ENTER")) {
                            adapter.addItem(new ChatItem(sender, message, Long.toString(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                        } else {
                            adapter.addItem(new ChatItem(sender, message, Long.toString(System.currentTimeMillis()), ChatType.ViewType.LEFT_CONTENT));
                        }
                    }
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setAdapter(adapter);
                }


            }
        };
    }

    private void subscribeStomp(String sender,String roomId) {
        MessageDto messageDto = new MessageDto();
        messageDto.setRoomId(roomId);
        messageDto.setSender(sender);
        System.out.println(messageDto.toString());
        this.stompClient.subscribe("/ws/chat/websocket",messageDto.messageToJson());
        System.out.println("start subscribe");
    }


    private void sendMessage(String sender,String roomId) {
        String message = messageBox.getText().toString();
        MessageDto messageDto = new MessageDto();
        messageDto.setRoomId(roomId);
        messageDto.setSender(sender);
        messageDto.setMessage(message);
        this.stompClient.sendMessage("/ws/chat/websocket", messageDto.messageToJson());
    }
}