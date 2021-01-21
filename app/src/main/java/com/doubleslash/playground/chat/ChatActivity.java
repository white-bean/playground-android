package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.doubleslash.playground.retrofit.dto.MemberDTO;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Message;
import com.doubleslash.playground.socket.model.Type;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import static android.view.View.GONE;

public class ChatActivity extends AppCompatActivity{
    private ActivityChatBinding binding;
    private RetrofitClient retrofitClient;
    private ChatAdapter adapter;
    private ArrayList<ChatItem> chats = new ArrayList<ChatItem>();
    private MutableLiveData<ArrayList<ChatItem>> chatsLiveData = new MutableLiveData<ArrayList<ChatItem>>();
    private List<MessageEntity> databaseMsgs;

    private MessageRepository messageRepository;
    
    private String roomId;
    private String roomType;

    private boolean menuOn;

    private UpdateThread updateThread;

    private HashMap<Long, MemberDTO> memberInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        adapter = new ChatAdapter(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!binding.recyclerView.canScrollVertically(-1)) {

                } else if (!binding.recyclerView.canScrollVertically(1)) {

                }
            }
        });

        // 채팅 목록에서 인텐트로 roomId, roomType 받아옴
        Intent intent = getIntent();
        roomId = intent.getStringExtra("roomId");
        roomType = intent.getStringExtra("roomType");



        // 멤버들 정보 초기화 (해싱으로 빠르게 불러오기 위함)
        memberInfos = new HashMap<>();
        List<MemberDTO> memberList = ClientApp.roomInfos.get(roomId).getMembersInfo();
        for (MemberDTO member : memberList) {
            memberInfos.put(member.getId(), member);
            Gson gson = new Gson();
            Log.d("members", gson.toJson(member));
        }




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
                databaseMsgs = messageRepository.getMessagesByRoomId(roomId);
                for (MessageEntity msg : databaseMsgs) {
                    if (msg.getType() == ChatType.ViewType.CENTER_CONTENT) {
                        chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                "",
                                msg.getText(),
                                dateConvert(msg.getSendTime()),
                                ChatType.ViewType.CENTER_CONTENT));
                    } else {
                        if (ClientApp.userId == msg.getFrom()) {
                            chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                    "",
                                    msg.getText(),
                                    dateConvert(msg.getSendTime()),
                                    ChatType.ViewType.RIGHT_CONTENT));
                        } else {
                            chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                    memberInfos.get(msg.getFrom()).getImageUrl(),
                                    msg.getText(),
                                    dateConvert(msg.getSendTime()),
                                    ChatType.ViewType.LEFT_CONTENT));
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
        if (roomType.equals("GROUP")) {
            retrofitClient.send_chat(Aria.GROUP, Type.SEND, ClientApp.userId, roomId, msg, System.currentTimeMillis());
        } else {
            retrofitClient.send_chat(Aria.PERSON, Type.SEND, ClientApp.userId, roomId, msg, System.currentTimeMillis());
        }
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

            // SEND, ENTER, LEFT만 받자
            while (true) {
                if (ClientApp.RoomMsgQueues.containsKey(roomId)) {
                    Queue<Message> unloadedMsgs = ClientApp.RoomMsgQueues.get(roomId);

                    if (unloadedMsgs.size() > 0) {
                        while (!unloadedMsgs.isEmpty()) {
                            Message msg = unloadedMsgs.poll();
                            Log.d("Message", msg.toString());
                            if (msg.getType() == Type.ENTER) {
                                MessageEntity message = new MessageEntity(ChatType.ViewType.CENTER_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                messageRepository.insert(message);
                                chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                        "",
                                        msg.getText(),
                                        dateConvert(msg.getSendTime()),
                                        ChatType.ViewType.CENTER_CONTENT));
                            } else if (msg.getType() == Type.SEND) {
                                if (ClientApp.userId == msg.getFrom()) {
                                    MessageEntity message = new MessageEntity(ChatType.ViewType.RIGHT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                    messageRepository.insert(message);
                                    chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                            "",
                                            msg.getText(),
                                            dateConvert(msg.getSendTime()),
                                            ChatType.ViewType.RIGHT_CONTENT));
                                    // 사용자가 메세지를 보냈을 경우 채팅방의 맨 마지막으로 스크롤링
                                    handler.post(() -> {
                                        binding.recyclerView.scrollToPosition(chats.size() - 1);
                                    });
                                } else {
                                    MessageEntity message = new MessageEntity(ChatType.ViewType.LEFT_CONTENT, msg.getFrom(), msg.getTo(), msg.getText(), msg.getSendTime());
                                    messageRepository.insert(message);
                                    chats.add(new ChatItem(memberInfos.get(msg.getFrom()).getNickname(),
                                            memberInfos.get(msg.getFrom()).getImageUrl(),
                                            msg.getText(),
                                            dateConvert(msg.getSendTime()),
                                            ChatType.ViewType.LEFT_CONTENT));
                                }
                            }
                            chatsLiveData.postValue(chats);
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