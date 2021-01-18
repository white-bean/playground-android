package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityInfoGroupBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.Team_info_responseDTO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InfoGroupActivity extends AppCompatActivity {
    private ActivityInfoGroupBinding binding;

    private RetrofitClient retrofitClient;
    private RecyclerView recyclerView;
    private memberAdapter adapter;
    private ImageView group_iV;
    private TextView location_tV, category_tV, subject_tV, period_tV, dday_tV, info_tV, num_member_tV;
    private Button register_btn;
    private FloatingActionButton chat_btn;  //디자이너 님이 아이콘 주시면 레이아웃 바꿔야함~!

    private String roomId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        init();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new memberAdapter();
//        recyclerView.setAdapter(adapter);
//        addDummy();
    }

    private void init() {
        retrofitClient = RetrofitClient.getInstance();
        Team_info_responseDTO body = retrofitClient.get_teaminfo(1);

        roomId = body.getRoomId();
        Log.d("room Id : ", roomId);
        //가입신청버튼눌렀을 때
        register_btn.setOnClickListener(v -> {
            //그룹가입
        });

        chat_btn.setOnClickListener(v -> {
            //채팅 아이콘 버튼 눌렀을 때

        });
    }

    private void addDummy() {
        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.profile_img, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
        adapter.addItem(new member(R.drawable.pro_min, "홍길동"));
    }
}