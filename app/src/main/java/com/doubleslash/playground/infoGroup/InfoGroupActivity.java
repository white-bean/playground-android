package com.doubleslash.playground.infoGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.R;
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
        // 방장이 아닐 경우
        binding.btnSetting.setVisibility(View.GONE);
        binding.btnAcceptPage.setVisibility(View.GONE);

        // GroupListFragment로부터 teamId를 넘겨받음
        Intent intent = getIntent();
        teamId = intent.getLongExtra("teamId", -1);

        retrofitClient = RetrofitClient.getInstance();
        Team_info_responseDTO body = retrofitClient.get_teaminfo(teamId);

        Glide.with(this)
                .load(ClientApp.API_URL + body.getTeamInfoDTO().getTeamImageUrl())
                .into(binding.imageGroup);
        binding.tvGroupLocation.setText(body.getTeamInfoDTO().getLocation());
        binding.tvGroupName.setText(body.getTeamInfoDTO().getName());
        binding.tvGroupContent.setText(body.getTeamInfoDTO().getContent());
        binding.tvMemberNumber.setText(body.getTeamInfoDTO().getCurrentMemberSize());
        binding.tvMemberNumber2.setText(body.getTeamInfoDTO().getCurrentMemberSize());

        String ddayDate = body.getTeamInfoDTO().getStartDate() + "~" + body.getTeamInfoDTO().getEndDate();
        binding.tvGroupDdayDate.setText(ddayDate);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.rvGroupMembers.setLayoutManager(layoutManager);

        MemberAdapter adapter = new MemberAdapter();
        for (int i = 0; i < body.getTeamInfoDTO().getTeamMembers().size(); i++) {
            adapter.addItem(new Member(
                    body.getTeamInfoDTO().getTeamMembers().get(i).getTeamImageUrl(),
                    body.getTeamInfoDTO().getTeamMembers().get(i).getName()));
        }

        switch (body.getTeamInfoDTO().getCategory()) {
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

        //가입신청버튼눌렀을 때
        binding.btnGroupRegister.setOnClickListener(v -> {
            // 레트로핏 통신으로 리퀘스트 보냄
            retrofitClient.group_request_accept(Aria.GROUP, Type.REQUEST, ClientApp.userEmail, String.valueOf(teamId));
        });
    }
}