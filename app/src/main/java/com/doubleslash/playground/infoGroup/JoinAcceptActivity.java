package com.doubleslash.playground.infoGroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doubleslash.playground.databinding.ActivityJoinAcceptBinding;

public class JoinAcceptActivity extends AppCompatActivity {
    private ActivityJoinAcceptBinding binding;
    private JoinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinAcceptBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initUI() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new JoinAdapter();

        // 어답터에 추가하는 작업 있어야함

        binding.recyclerView.setAdapter(adapter);
    }
}