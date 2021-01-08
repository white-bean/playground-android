package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
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
import com.doubleslash.playground.databinding.ActivityChatBinding;
import com.doubleslash.playground.databinding.ActivityLoginBinding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;

import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import nbouma.com.wstompclient.implementation.StompClient;
import nbouma.com.wstompclient.model.Frame;

public class ChatActivity extends AppCompatActivity{
    private ActivityChatBinding binding;
    private static ChatAdapter adapter = new ChatAdapter();
    private ArrayList<ChatItem> chats = new ArrayList<ChatItem>();
    private MutableLiveData<ArrayList<ChatItem>> chatsLiveData = new MutableLiveData<ArrayList<ChatItem>>();

    public static StompClient stompClient;
    private static String my_id = "";
    private static final String TAG = "stomp app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        Intent intent=getIntent();
        final String roomid=intent.getStringExtra("roomId");
        my_id = "chan";
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

        subscribeStomp(my_id,roomid);
        binding.recyclerView.setAdapter(adapter);
        // LiveData를 통한 채팅사항 항시 반영
        chatsLiveData.observe(this, adapter::setItems);

        binding.sendBtn.setOnClickListener(v -> {
            // 전송 누르면 할 일
            if(binding.contentEdit.getText().toString() != null)
                sendMessage("chan", roomid);
        });
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
                JSONObject jsonObject = new JSONObject(frame);
                String sender = jsonObject.get("sender").toString();
                String message = jsonObject.get("message").toString();
                String type = jsonObject.get("type").toString();
                Log.d("Message : ",message);

                if (sender.equals(my_id)) {
                    if(type.equals("ENTER")){
                        chats.add(new ChatItem(sender, message, dateConvert(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                        binding.contentEdit.setText("");
                        chatsLiveData.postValue(chats);
                    } else {
                        chats.add(new ChatItem(sender, message, dateConvert(System.currentTimeMillis()), ChatType.ViewType.RIGHT_CONTENT));
                        binding.contentEdit.setText("");
                        chatsLiveData.postValue(chats);
                    }
                } else {
                    if (type.equals("ENTER")) {
                        chats.add(new ChatItem(sender, message, dateConvert(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                        binding.contentEdit.setText("");
                        chatsLiveData.postValue(chats);
                    } else {
                        chats.add(new ChatItem(sender, message, dateConvert(System.currentTimeMillis()), ChatType.ViewType.LEFT_CONTENT));
                        binding.contentEdit.setText("");
                        chatsLiveData.postValue(chats);
                    }
                }
            }
        };
    }

    private void subscribeStomp(String sender, String roomId) {
        MessageDto messageDto = new MessageDto();
        messageDto.setRoomId(roomId);
        messageDto.setSender(sender);
        System.out.println(messageDto.toString());
        this.stompClient.subscribe("/ws/chat/websocket",messageDto.messageToJson());
        System.out.println("start subscribe");
    }


    private void sendMessage(String sender, String roomId) {
        String message = binding.contentEdit.getText().toString();
        MessageDto messageDto = new MessageDto();
        messageDto.setRoomId(roomId);
        messageDto.setSender(sender);
        messageDto.setMessage(message);
        this.stompClient.sendMessage("/ws/chat/websocket", messageDto.messageToJson());
    }

    private String dateConvert(long currentMiliis) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        String str = timeFormat.format(new Date(currentMiliis));

        return str;
    }
}