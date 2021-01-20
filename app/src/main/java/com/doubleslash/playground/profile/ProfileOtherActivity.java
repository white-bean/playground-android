package com.doubleslash.playground.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.databinding.ActivityProfileOtherBinding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Other_info_responseDTO;

public class ProfileOtherActivity extends AppCompatActivity {
    ActivityProfileOtherBinding binding;

    private RetrofitClient retrofitClient;

    private long memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileOtherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }
    private void initUI(){
        Intent intent = getIntent();
        memberId = intent.getLongExtra("memberId", -1);

        retrofitClient = RetrofitClient.getInstance();

        Other_info_responseDTO body = retrofitClient.get_otherinfo(memberId);

        // 사용자 정보
        Glide.with(getApplicationContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(0))
                .into(binding.imageUser01);

        Glide.with(getApplicationContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(1))
                .into(binding.imageUser02);

        Glide.with(getApplicationContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(2))
                .into(binding.imageUser03);

        binding.tvUserName.setText(body.getData().getName());
        binding.tvUserLocation.setText(body.getData().getLocation());
        binding.tvUserSchool.setText(body.getData().getUniversity());
        binding.tvUserContent.setText(body.getData().getIntroduction());

        // 가입한 소모임 목록 가져오기
        binding.rvUserGroup.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MyGroupAdapter myGroupAdapter = new MyGroupAdapter(getApplicationContext());

        for (int i = 0; i < body.getData().getMyteams().size(); i++) {
            myGroupAdapter.addItem(new MyGroup(
                    body.getData().getMyteams().get(i).getName(),
                    null,
                    body.getData().getMyteams().get(i).getLocation(),
                    body.getData().getMyteams().get(i).getTeamImageUrl(),
                    body.getData().getMyteams().get(i).getCurrentMemberSize(),
                    null));
        }

        if (myGroupAdapter.getItemCount() > 0) {
            binding.layoutCaution.setVisibility(View.GONE);
        } else {
            binding.layoutCaution.setVisibility(View.VISIBLE);
        }

        binding.rvUserGroup.setAdapter(myGroupAdapter);

        myGroupAdapter.setOnItemClickListener((holder, view, position) -> {
            MyGroup item = myGroupAdapter.getItem(position);

            Intent intent1 = new Intent(this, InfoGroupActivity.class);
            intent1.putExtra("teamId", body.getData().getMyteams().get(position).getId());

            startActivity(intent1);
        });

        binding.fabChat.setOnClickListener(v -> {
            // 1:1 채팅으로 이동
        });
    }
}