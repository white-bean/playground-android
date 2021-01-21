package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.EditGroupActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.chat.ChatActivity;
import com.doubleslash.playground.databinding.ActivityInfoGroupBinding;
import com.doubleslash.playground.profile.ProfileOtherActivity;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;
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

        retrofitClient = RetrofitClient.getInstance();

        initUI();
    }

    private void initUI() {
        // 이전 화면으로부터 teamId를 넘겨받음
        Intent intent = getIntent();
        teamId = intent.getLongExtra("teamId", -1);

        Team_info_responseDTO body = retrofitClient.get_teaminfo(teamId);

        // 소모임 기본 정보
        Glide.with(this)
                .load(ClientApp.API_URL + body.getData().getTeamImageUrl())
                .into(binding.imageGroup);
        binding.tvGroupLocation.setText(body.getData().getLocation());
        binding.tvGroupName.setText(body.getData().getName());
        binding.tvGroupContent.setText(body.getData().getContent());
        binding.tvMemberNumber.setText(Integer.toString(body.getData().getCurrentMemberSize()));
        binding.tvMemberNumber2.setText(Integer.toString(body.getData().getCurrentMemberSize()));

        String ddayDate = body.getData().getStartDate() + "~" + body.getData().getEndDate();
        binding.tvGroupDdayDate.setText(ddayDate);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvGroupMembers.setLayoutManager(layoutManager);

        MemberAdapter memberAdapter = new MemberAdapter(body.getData().getAdminInfo().getId());
        for (int i = 0; i < body.getData().getTeamMembers().size(); i++) {
            memberAdapter.addItem(new Member(
                    body.getData().getTeamMembers().get(i).getId(),
                    body.getData().getTeamMembers().get(i).getImageUrl(),
                    body.getData().getTeamMembers().get(i).getNickname()));
        }

        binding.rvGroupMembers.setAdapter(memberAdapter);

        memberAdapter.setOnItemClickListener((holder, view, position) -> {
            Intent intent2 = new Intent(this, ProfileOtherActivity.class);
            intent2.putExtra("memberId", body.getData().getTeamMembers().get(position).getId());

            startActivity(intent2);
        });

        switch (body.getData().getCategory()) {
            case "스터디":
                binding.imageGroupCategory.setImageResource(R.drawable.writing_hand);
                binding.imageGroupCategory.setBackgroundResource(R.drawable.ic_button_study);
                break;
            case "운동/다이어트":
                binding.imageGroupCategory.setImageResource(R.drawable.diet);
                binding.imageGroupCategory.setBackgroundResource(R.drawable.ic_button_diet);
                break;
            case "문화생활":
                binding.imageGroupCategory.setImageResource(R.drawable.draw);
                binding.imageGroupCategory.setBackgroundResource(R.drawable.ic_button_cultural);
                break;
            case "게임":
                binding.imageGroupCategory.setImageResource(R.drawable.game);
                binding.imageGroupCategory.setBackgroundResource(R.drawable.ic_button_game);
                break;
            default:
                binding.imageGroupCategory.setImageResource(R.drawable.ic_camera);
                binding.imageGroupCategory.setBackgroundResource(R.drawable.ic_button_study);
                break;
        }

        // 모임 일정 리스트
        if (false) {
            // 모임 일정이 있는 경우
            binding.tvNoMeeting.setVisibility(View.GONE);
        }

        // 가입 신청 버튼 눌렀을 때
        binding.btnGroupRegister.setOnClickListener(v -> {
            // 레트로핏 통신으로 리퀘스트 보냄
            retrofitClient.group_request_accept(Aria.GROUP, Type.REQUEST, ClientApp.userId, String.valueOf(teamId), System.currentTimeMillis());
        });

        // 채팅방 입장 버튼 눌렀을 때
        binding.btnGroupChatroom.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), ChatActivity.class);
            intent1.putExtra("teamId", teamId);

            startActivity(intent1);

            finish();
        });

        // (방장용) 가입 대기 버튼 눌렀을 때
        binding.btnAcceptPage.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, JoinAcceptActivity.class);
            intent3.putExtra("teamId", Long.toString(teamId));

            startActivity(intent3);
        });

        // (방장용) 소모임 설정 버튼 눌렀을 때
        binding.btnSetting.setOnClickListener(v->{
            Intent intent1 = new Intent(getApplicationContext(), EditGroupActivity.class);

            System.out.println(body.getData().getName());

            Bundle bundle = new Bundle();

            bundle.putString("name",body.getData().getName());
            bundle.putString("startdate",body.getData().getStartDate());
            bundle.putString("enddate",body.getData().getEndDate());
            bundle.putString("url",body.getData().getTeamImageUrl());
            bundle.putString("content",body.getData().getContent());
            bundle.putString("location",body.getData().getLocation());
            bundle.putLong("teamId",teamId);
            
            intent1.putExtras(bundle);

            startActivity(intent1);

            finish();
        });

        User_info_responseDTO body_userinfo = retrofitClient.get_userinfo();

        // 소모임 방장인 경우
        if (body.getData().getAdmin() == Boolean.TRUE) {
            binding.btnSetting.setVisibility(View.VISIBLE);
            binding.btnAcceptPage.setVisibility(View.VISIBLE);
        }

        // 소모임에 가입되어있는 경우
        for (int i = 0; i < body_userinfo.getData().getMyteams().size(); i++) {
            if (body_userinfo.getData().getMyteams().get(i).getId() == teamId) {
                binding.layoutCaution.setVisibility(View.GONE);

                binding.tvGroupDday.setText("소모임 채팅");
                binding.tvGroupDdayDate.setText(body.getData().getTeamMembers().size() + "명의 소모임원이 온라인입니다!");

                binding.btnGroupRegister.setVisibility(View.GONE);
                binding.btnGroupChatroom.setVisibility(View.VISIBLE);
            }
        }
    }
}