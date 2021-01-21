package com.doubleslash.playground.infoGroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.databinding.ActivityJoinAcceptBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;

import java.util.List;

public class JoinAcceptActivity extends AppCompatActivity {
    private ActivityJoinAcceptBinding binding;
    private RetrofitClient retrofitClient;
    private JoinAdapter adapter;

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
        String category = intent.getStringExtra("category");
        String teamId = intent.getStringExtra("teamId");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new JoinAdapter(category, teamId);

        // 어답터에 추가하는 작업 있어야함
        List<Long> users = ClientApp.waitingUsers.get(teamId);
        for (Long user : users) {
            adapter.addItem(new Join(Long.toString(user), "서울 광진구", "대학교"));
        }
        binding.recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() > 0) {
            binding.tvNoMember.setVisibility(View.GONE);
            binding.imageNoMember.setVisibility(View.GONE);
        } else {
            binding.tvNoMember.setVisibility(View.VISIBLE);
            binding.imageNoMember.setVisibility(View.VISIBLE);
        }
    }
}