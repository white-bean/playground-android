package com.doubleslash.playground.infoGroup;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doubleslash.playground.databinding.ActivityJoinAcceptBinding;
import com.doubleslash.playground.retrofit.RetrofitClient;

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

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new JoinAdapter(category);

        // 어답터에 추가하는 작업 있어야함

        binding.recyclerView.setAdapter(adapter);
    }
}