package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubleslash.playground.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoGroupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private memberAdapter adapter;

    private ImageView group_iV;
    private TextView location_tV, category_tV, subject_tV, period_tV, dday_tV, info_tV, num_member_tV;
    private Button register_btn;
    private FloatingActionButton chat_btn;  //디자이너 님이 아이콘 주시면 레이아웃 바꿔야함~!


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
    private void init(){
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

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가입신청버튼눌렀을 때
            }
        });

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //채팅 아이콘 버튼 눌렀을 때
            }
        });
    }
    private void addDummy(){
        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
    }
}