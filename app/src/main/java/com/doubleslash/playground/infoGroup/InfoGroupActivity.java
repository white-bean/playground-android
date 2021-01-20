package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private long teamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        // GroupListFragment로부터 teamId를 넘겨받음
        Intent intent = getIntent();
        teamId = intent.getLongExtra("teamId", -1);

        retrofitClient = RetrofitClient.getInstance();
        Team_info_responseDTO body = retrofitClient.get_teaminfo(teamId);

        binding.tvGroupLocation.setText(body.getTeamInfoDTO().getLocation());
        binding.tvGroupName.setText(body.getTeamInfoDTO().getName());
        binding.tvGroupContent.setText(body.getTeamInfoDTO().getContent());
        binding.tvMemberNumber.setText(body.getTeamInfoDTO().getCurrentMemberSize());

        //가입신청버튼눌렀을 때
        binding.btnGroupRegister.setOnClickListener(v -> {
            // 레트로핏 통신으로 리퀘스트 보냄
            retrofitClient.group_request_accept(Aria.GROUP, Type.REQUEST, ClientApp.userEmail, String.valueOf(teamId));
        });
    }
}