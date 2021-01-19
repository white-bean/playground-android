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

        // 미완성 (userId 어떻게?)
        long userId = 1;

        User_info_responseDTO body = retrofitClient.get_userinfo(userId);

        /*
        // 사용자 정보
        Glide.with(getContext())
                .load(body.getData().getImageUri1())
                .into(binding.imageUser01);

        Glide.with(getContext())
                .load(body.getData().getImageUri2())
                .into(binding.imageUser02);

        Glide.with(getContext())
                .load(body.getData().getImageUri3())
                .into(binding.imageUser03);

        binding.tvUserName.setText(body.getData().getName());
        binding.tvUserLocation.setText(body.getData().getLocation());
        binding.tvUserSchool.setText(body.getData().getSchool());
        binding.tvUserContent.setText(body.getData().getIntroduction());

        // 가입한 소모임 목록 가져오기
        binding.rvUserGroup.setLayoutManager(new LinearLayoutManager(getContext()));

        MyGroupAdapter myGroupAdapter = new MyGroupAdapter(getContext());

        for (int i = 0; i < body.getData().getMyGroups().size(); i++) {
            myGroupAdapter.addItem(new MyGroup(
                    body.getData().getMyGroups().get(i).getLocation(),
                    body.getData().getMyGroups().get(i).getCategory(),
                    body.getData().getMyGroups().get(i).getCurrentMemberSize(),
                    body.getData().getMyGroups().get(i).getMaxMemberSize(),
                    body.getData().getMyGroups().get(i).getName(),
                    body.getData().getMyGroups().get(i).getContent(),
                    body.getData().getMyGroups().get(i).getTeamImageUrl()));
        }

        binding.rvUserGroup.setAdapter(myGroupAdapter);

        myGroupAdapter.setOnItemClickListener((holder, view, position) -> {
            MyGroup item = myGroupAdapter.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);

            // 미완성
            // 소모임 정보 넘겨주기

            startActivity(intent);
        });
         */

        return binding.getRoot();
    }
}