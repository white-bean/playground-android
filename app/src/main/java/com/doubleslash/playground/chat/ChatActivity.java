package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.database.entity.MessageEntity;
import com.doubleslash.playground.database.repository.MessageRepository;
import com.doubleslash.playground.databinding.ActivityChatBinding;
import com.doubleslash.playground.socket.model.Message;
import com.doubleslash.playground.socket.model.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class ChatActivity extends AppCompatActivity{
    private ActivityChatBinding binding;
    private ChatAdapter adapter = new ChatAdapter();
    private ArrayList<ChatItem> chats = new ArrayList<ChatItem>();
    private MutableLiveData<ArrayList<ChatItem>> chatsLiveData = new MutableLiveData<ArrayList<ChatItem>>();
    private List<MessageEntity> databaseMsgs;

    private static MessageRepository messageRepository;
    private String roomId;

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

        Intent intent = getIntent();
        roomId = intent.getStringExtra("roomId");

        String userId = ClientApp.user_token;

        // 내부 데이터베이스에 읽는 채팅 내용을 읽어와서 추가
        messageRepository = new MessageRepository(getApplication());
        Thread thread = new Thread() {
            @Override
            public void run() {
                databaseMsgs = messageRepository.getMessagesByRoomId(roomId);
                for (MessageEntity msg : databaseMsgs) {
                    if (msg.getType() == ChatType.ViewType.CENTER_CONTENT) {
                        chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                    } else {
                        if (userId.equals(msg.getFrom())) {
                            chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.RIGHT_CONTENT));
                        } else {
                            chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.LEFT_CONTENT));
                        }
                    }
                    chatsLiveData.postValue(chats);
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        binding.recyclerView.setAdapter(adapter);
        // LiveData를 통한 채팅사항 항시 반영 (이 때, 채팅방이 Observe하고 있는 대상은 데이터베이스의 내용)
        chatsLiveData.observe(this, adapter::setItems);

        // 메세지 큐에 쌓여 있던 메세지 데이터베이스에 추가
        // 선택지가 2개 있음
        // 1. ChatItem을 기반으로 한 리사이클러뷰로 데이터베이스로부터 불러오고 데이터베이스의 내용을 다시 ChatItem으로 만들어 어댑터에 추가
        // 2. 그냥 데이터베이스를 기반으로한 리사이클러뷰
        if (ClientApp.RoomMsgQueues.containsKey(roomId)) {
            Queue<Message> unloadedMsgs = ClientApp.RoomMsgQueues.get(roomId);

            while (!unloadedMsgs.isEmpty()) {
                Message msg = unloadedMsgs.poll();
                Log.d("Message", msg.toString());
                if (msg.getType() == Type.ENTER) {
                    MessageEntity message = new MessageEntity(ChatType.ViewType.CENTER_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), dateConvert(System.currentTimeMillis()));
                    messageRepository.insert(message);
                    chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.CENTER_CONTENT));
                } else {
                    if (userId.equals(msg.getFrom())) {
                        MessageEntity message = new MessageEntity(ChatType.ViewType.RIGHT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), dateConvert(System.currentTimeMillis()));
                        messageRepository.insert(message);
                        chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.RIGHT_CONTENT));
                    } else {
                        MessageEntity message = new MessageEntity(ChatType.ViewType.LEFT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), dateConvert(System.currentTimeMillis()));
                        messageRepository.insert(message);
                        chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(System.currentTimeMillis()), ChatType.ViewType.LEFT_CONTENT));
                    }
                }
                chatsLiveData.postValue(chats);
            }
        }

        binding.sendBtn.setOnClickListener(v -> {
            // 전송 누르면 할 일
            if(binding.contentEdit.getText().toString() != null) {
                sendMessage(binding.contentEdit.getText().toString());
                binding.contentEdit.setText("");
            }
        });
    }

    private void sendMessage(String msg) {
        Message message = new Message(Type.SEND, ClientApp.user_token, roomId, msg);

        // 지원씨가 하셔야 할 일 : retrofit으로 message객체 바꾸기
        // Message의 경우 toString()하면 Json형태의 문자열이 반환
        ClientApp.socketMananger.sendMessage(message);
    }

    // 현재 시간을 몇시:몇분 am/pm 형태의 문자열로 반환
    private String dateConvert(long currentMiliis) {
        return new SimpleDateFormat("hh:mm a").format(new Date(currentMiliis));
    }
}