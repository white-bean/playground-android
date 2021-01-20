package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.database.entity.MessageEntity;
import com.doubleslash.playground.database.repository.MessageRepository;
import com.doubleslash.playground.databinding.ActivityChatBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Message;
import com.doubleslash.playground.socket.model.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import static android.view.View.GONE;

public class ChatActivity extends AppCompatActivity{
    private ActivityChatBinding binding;
    private RetrofitClient retrofitClient;
    private ChatAdapter adapter = new ChatAdapter();
    private ArrayList<ChatItem> chats = new ArrayList<ChatItem>();
    private MutableLiveData<ArrayList<ChatItem>> chatsLiveData = new MutableLiveData<ArrayList<ChatItem>>();
    private List<MessageEntity> databaseMsgs;

    private MessageRepository messageRepository;
    private String teamId;

    private boolean menuOn;

    private UpdateThread updateThread;

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

        // 채팅 목록에서 인텐트로 teamId 받아옴
        Intent intent = getIntent();
        teamId = intent.getStringExtra("teamId");

        // 플러스 버튼으로 메뉴 열고 닫기
        binding.menuLayout.setVisibility(GONE);
        menuOn = true;
        binding.plusButton.setOnClickListener(v -> {
            if (menuOn) {
                binding.menuLayout.setVisibility(View.VISIBLE);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int bottomPadding = Math.round(140 * dm.density);
                binding.recyclerView.setPadding(0, 0, 0, bottomPadding);
                binding.recyclerView.scrollToPosition(chats.size() - 1);
                menuOn = false;
            } else {
                binding.menuLayout.setVisibility(View.GONE);
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int bottomPadding = Math.round(60 * dm.density);
                binding.recyclerView.setPadding(0, 0, 0, bottomPadding);
                menuOn = true;
            }
        });

        // 내부 데이터베이스에 읽는 채팅 내용을 읽어와서 추가
        messageRepository = new MessageRepository(getApplication());
        Thread thread = new Thread() {
            @Override
            public void run() {
                databaseMsgs = messageRepository.getMessagesByRoomId(teamId);
                for (MessageEntity msg : databaseMsgs) {
                    if (msg.getType() == ChatType.ViewType.CENTER_CONTENT) {
                        chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.CENTER_CONTENT));
                    } else {
                        if (ClientApp.userEmail.equals(msg.getFrom())) {
                            chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.RIGHT_CONTENT));
                        } else {
                            chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.LEFT_CONTENT));
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
        binding.recyclerView.scrollToPosition(chats.size()-1);

        binding.recyclerView.setAdapter(adapter);
        // LiveData를 통한 채팅사항 항시 반영 (이 때, 채팅방이 Observe하고 있는 대상은 데이터베이스의 내용)
        chatsLiveData.observe(this, adapter::setItems);

        // 메세지 큐에 쌓여 있던 메세지 데이터베이스에 추가
        updateThread = new UpdateThread();
        updateThread.start();

        binding.sendBtn.setOnClickListener(v -> {
            // 전송 누르면 할 일
            if(binding.contentEdit.getText().toString() != null) {
                sendMessage(binding.contentEdit.getText().toString());
                binding.contentEdit.setText("");
            }
        });
    }

    private void sendMessage(String msg) {
        retrofitClient = RetrofitClient.getInstance();
        retrofitClient.send_chat(Aria.GROUP, Type.SEND, ClientApp.userEmail, teamId, msg, System.currentTimeMillis());
    }

    // System.currentTimeMillis를 몇시:몇분 am/pm 형태의 문자열로 반환
    private String dateConvert(long currentMiliis) {
        return new SimpleDateFormat("hh:mm a").format(new Date(currentMiliis));
    }

    // 메시지를 계속 업데이트해주는 쓰레드
    class UpdateThread extends Thread {
        @Override
        public void run() {
            final Handler handler = new Handler(Looper.getMainLooper());

            while (true) {
                if (ClientApp.RoomMsgQueues.containsKey(teamId)) {
                    Queue<Message> unloadedMsgs = ClientApp.RoomMsgQueues.get(teamId);

                    if (unloadedMsgs.size() > 0) {
                        while (!unloadedMsgs.isEmpty()) {
                            Message msg = unloadedMsgs.poll();
                            Log.d("Message", msg.toString());
                            if (msg.getType() == Type.ENTER) {
                                MessageEntity message = new MessageEntity(ChatType.ViewType.CENTER_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                messageRepository.insert(message);
                                chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.CENTER_CONTENT));
                            } else {
                                if (ClientApp.userEmail.equals(msg.getFrom())) {
                                    MessageEntity message = new MessageEntity(ChatType.ViewType.RIGHT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                    messageRepository.insert(message);
                                    chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.RIGHT_CONTENT));
                                } else {
                                    MessageEntity message = new MessageEntity(ChatType.ViewType.LEFT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                    messageRepository.insert(message);
                                    chats.add(new ChatItem(msg.getFrom(), msg.getText(), dateConvert(msg.getSendTime()), ChatType.ViewType.LEFT_CONTENT));
                                }
                            }
                            chatsLiveData.postValue(chats);
                            handler.post(() -> {
                                binding.recyclerView.scrollToPosition(chats.size() - 1);
                            });
                        }
                    }
                }
            }
        }
    }

    // 액티비티가 꺼질 때 쓰레드도 같이 종료
    @Override
    public void onDestroy() {
        super.onDestroy();
        updateThread.interrupt();
    }
}