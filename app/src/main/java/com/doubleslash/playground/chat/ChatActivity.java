package com.doubleslash.playground.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.doubleslash.playground.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText contentEdit;
    private TextView sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initUI();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        contentEdit = findViewById(R.id.content_edit);
        sendBtn = findViewById(R.id.send_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ChatAdapter();
        addDummyData(adapter);
        recyclerView.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전송 누르면 할 일
            }
        });
    }

    private void addDummyData(ChatAdapter adapter) {
        adapter.addItem(new ChatItem(null,"유저 1이 입장하셨습니다.", null, ChatType.ViewType.CENTER_CONTENT));
        adapter.addItem(new ChatItem(null,"유저 2가 입장하셨습니다.", null, ChatType.ViewType.CENTER_CONTENT));
        adapter.addItem(new ChatItem("유저 1","상대가 보낸 메세지", "00:00", ChatType.ViewType.LEFT_CONTENT));
        adapter.addItem(new ChatItem(null,"상대가 보낸 메세지", "00:00", ChatType.ViewType.RIGHT_CONTENT));
    }
}