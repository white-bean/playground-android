package com.doubleslash.playground;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleslash.playground.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.imageEditProfile.setOnClickListener(view -> {
            // 사진 설정
        });

        // 사용자 위치 가져오기
        String user_location = "";

        binding.tvUserLocation.setText(user_location);

        // 사용자 대학 가져오기
        String user_school = "";

        binding.tvUserSchool.setText(user_school);

        // 가입한 소모임 목록 가져오기


        return binding.getRoot();
    }
}