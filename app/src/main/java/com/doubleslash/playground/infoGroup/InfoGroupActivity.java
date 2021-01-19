package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.databinding.ActivityInfoGroupBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Type;

public class InfoGroupActivity extends AppCompatActivity {
    private ActivityInfoGroupBinding binding;

    private RetrofitClient retrofitClient;

    private String teamId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();

//        init();

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter = new memberAdapter();
//        recyclerView.setAdapter(adapter);
//        addDummy();
    }

    private void initUI() {
        retrofitClient = RetrofitClient.getInstance();
        Team_info_responseDTO body = retrofitClient.get_teaminfo(1);

        binding.tvGroupLocation.setText(body.getTeamInfoDTO().getLocation());
        binding.tvGroupName.setText(body.getTeamInfoDTO().getName());
        binding.tvGroupContent.setText(body.getTeamInfoDTO().getContent());
        binding.tvMemberNumber.setText(body.getTeamInfoDTO().getCurrentMemberSize());

        //가입신청버튼눌렀을 때
        binding.btnGroupRegister.setOnClickListener(v -> {
            // 레트로핏 통신으로 리퀘스트 보냄
            retrofitClient.group_request_accept(Aria.GROUP, Type.REQUEST, ClientApp.userEmail, "teamId");
        });
    }
}