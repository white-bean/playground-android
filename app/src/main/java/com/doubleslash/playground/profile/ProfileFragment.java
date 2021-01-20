package com.doubleslash.playground.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.databinding.FragmentProfileBinding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    private RetrofitClient retrofitClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        retrofitClient = RetrofitClient.getInstance();

        User_info_responseDTO body = retrofitClient.get_userinfo();

        // 사용자 정보
        Glide.with(getContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(0))
                .into(binding.imageUser01);

        Glide.with(getContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(1))
                .into(binding.imageUser02);

        Glide.with(getContext())
                .load(ClientApp.API_URL + body.getData().getImages().get(2))
                .into(binding.imageUser03);

        binding.tvUserName.setText(body.getData().getName());
        binding.tvUserLocation.setText(body.getData().getLocation());
        binding.tvUserSchool.setText(body.getData().getUniversity());
        binding.tvUserContent.setText(body.getData().getIntroduction());

        // 가입한 소모임 목록 가져오기
        binding.rvUserGroup.setLayoutManager(new LinearLayoutManager(getContext()));

        MyGroupAdapter myGroupAdapter = new MyGroupAdapter(getContext());

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

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);
            intent.putExtra("teamId", body.getData().getMyteams().get(position).getId());

            startActivity(intent);
        });

        binding.logout.setOnClickListener(v -> {
            //로그아웃 클릭했을 때
        });

        return binding.getRoot();
    }
}