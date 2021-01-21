package com.doubleslash.playground.infoGroup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.databinding.ActivityJoinAcceptBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.socket.model.Type;

import java.util.ArrayList;
import java.util.List;

public class JoinAcceptActivity extends AppCompatActivity {
    private ActivityJoinAcceptBinding binding;

    private RetrofitClient retrofitClient;

    List<Long> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinAcceptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        retrofitClient = RetrofitClient.getInstance();

        Intent intent = getIntent();
        String teamId = intent.getStringExtra("teamId");

        binding.rvWaitingUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        JoinAdapter joinAdapter = new JoinAdapter(this);

        users = ClientApp.waitingUsers.get(teamId);
        if (users != null) {
            if (users.size() > 0) {
                if (users.size() == 1) {
                    joinAdapter.addItem(new Join(Long.toString(users.get(0)), "서울 광진구", "서울대학교"));
                } else if (users.size() == 2) {
                    joinAdapter.addItem(new Join(Long.toString(users.get(0)), "서울 광진구", "서울대학교"));
                    joinAdapter.addItem(new Join(Long.toString(users.get(1)), "경기 가평군", "중앙대학교"));
                } else if (users.size() == 3) {
                    joinAdapter.addItem(new Join(Long.toString(users.get(0)), "서울 광진구", "서울대학교"));
                    joinAdapter.addItem(new Join(Long.toString(users.get(1)), "경기 가평군", "중앙대학교"));
                    joinAdapter.addItem(new Join(Long.toString(users.get(2)), "안산 상록구", "아주대학교"));
                }

//                for (Long user : users) {
//                    joinAdapter.addItem(new Join(Long.toString(user), "서울 광진구", "대학교"));
//                }

//                // 가입 대기 인원 체크
//                binding.tvWaitingUsersNumber.setText(users.size());
//
//                binding.tvNoMember.setVisibility(View.GONE);
//                binding.imageNoMember.setVisibility(View.GONE);
            }

            joinAdapter.setOnItemClickListener((holder, view, position) -> {
                retrofitClient.group_request_accept(Aria.GROUP, Type.ACCEPT, Long.parseLong(joinAdapter.getItem(position).getUserName()), teamId, System.currentTimeMillis());

                ClientApp.waitingUsers.get(teamId).remove(position);
                ClientApp.waitingUsers.remove(joinAdapter.getItem(position).getUserName());
                joinAdapter.removeItem(position);

                // 가입 대기 인원 체크
//                binding.tvWaitingUsersNumber.setText(joinAdapter.getItemCount());
//
//                if (joinAdapter.getItemCount() > 0) {
//                    binding.tvNoMember.setVisibility(View.GONE);
//                    binding.imageNoMember.setVisibility(View.GONE);
//                } else {
//                    binding.tvNoMember.setVisibility(View.VISIBLE);
//                    binding.imageNoMember.setVisibility(View.VISIBLE);
//                }
            });

            binding.rvWaitingUsers.setAdapter(joinAdapter);
        }
    }
}