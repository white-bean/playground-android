package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleslash.playground.R;
import com.doubleslash.playground.Retrofit_pakage.My_Retrofit;
import com.doubleslash.playground.Retrofit_pakage.Team_info_responseDTO;
import com.doubleslash.playground.Retrofit_pakage.Total_group_responseDTO;
import com.doubleslash.playground.chat.ChatRoomItem;
import com.doubleslash.playground.chat.MessageDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nbouma.com.wstompclient.implementation.StompClient;
import nbouma.com.wstompclient.model.Frame;

public class InfoGroupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private memberAdapter adapter;
    public static StompClient stompClient;
    private ImageView group_iV;
    private TextView location_tV, category_tV, subject_tV, period_tV, dday_tV, info_tV, num_member_tV;
    private Button register_btn;
    private FloatingActionButton chat_btn;  //디자이너 님이 아이콘 주시면 레이아웃 바꿔야함~!
    private String roomId = "";
    private static final String TAG = "stomp app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_group);

        init();

        recyclerView = findViewById(R.id.member_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new memberAdapter();
        recyclerView.setAdapter(adapter);
        addDummy();


    }

    private void init() {
        group_iV = findViewById(R.id.group_iV);
        location_tV = findViewById(R.id.location_tV);
        category_tV = findViewById(R.id.category_tV);
        subject_tV = findViewById(R.id.subject_tV);
        period_tV = findViewById(R.id.period_tV);
        dday_tV = findViewById(R.id.dday_tV);
        info_tV = findViewById(R.id.info_tV);
        num_member_tV = findViewById(R.id.num_member_tV);
        register_btn = findViewById(R.id.register_btn);
        chat_btn = findViewById(R.id.chat_btn);
        My_Retrofit my_retrofit = new My_Retrofit();
        Team_info_responseDTO body = my_retrofit.get_teaminfo();
        while (body == null) {
            body = my_retrofit.team_info_responseDTO;
        }
        System.out.println("112");
        roomId = body.getRoomId();
        Log.d("room Id : ", roomId);
        //가입신청버튼눌렀을 때
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //그룹가입

                //소켓통신
                connectStomp();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscribeStomp("chan",roomId);
            }
        });

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //채팅 아이콘 버튼 눌렀을 때

            }
        });
    }

    private void addDummy() {

        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
    }


    private static void connectStomp() {
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
            protected void onStompMessage(String frame) {

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


}