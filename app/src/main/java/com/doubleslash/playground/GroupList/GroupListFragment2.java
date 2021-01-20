package com.doubleslash.playground.GroupList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.databinding.FragmentGroupList2Binding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.doubleslash.playground.profile.MyGroup;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;

import java.util.Objects;

public class GroupListFragment2 extends Fragment {
    private FragmentGroupList2Binding binding;

    private RetrofitClient retrofitClient;
    private GroupAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroupList2Binding.inflate(inflater, container, false);

        retrofitClient = RetrofitClient.getInstance();

        initUI();

        return binding.getRoot();
    }

    private void initUI() {
        User_info_responseDTO body = retrofitClient.get_userinfo();

        // 사용자 이름 & 사진
        binding.tvUserName.setText(body.getData().getName() + "님");

        MultiTransformation multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));

        Glide.with(Objects.requireNonNull(getActivity()))
                .asBitmap()
                .load(body.getData().getImages().get(0))
                .apply(RequestOptions.bitmapTransform(multiOption))
                .into(binding.imageUser);

        // 1. 가입한 소모임 목록
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        binding.rvUserGroup.setLayoutManager(layoutManager);

        MyGroupAdapter2 myGroupAdapter2 = new MyGroupAdapter2(getContext());

        for (int i = 0; i < body.getData().getMyteams().size(); i++) {
            myGroupAdapter2.addItem(new MyGroup(
                    body.getData().getMyteams().get(i).getName(),
                    body.getData().getMyteams().get(i).getCategory(),
                    body.getData().getMyteams().get(i).getLocation(),
                    body.getData().getImages().get(i),
                    body.getData().getMyteams().get(i).getCurrentMemberSize(),
                    body.getData().getMyteams().get(i).getMaximumMemberSize()));
        }

        binding.rvUserGroup.setAdapter(myGroupAdapter2);

        myGroupAdapter2.setOnItemClickListener((holder, view, position) -> {
            MyGroup item = myGroupAdapter2.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);

            // 미완성
            // 소모임 정보 넘겨주기

            startActivity(intent);
        });

        // 2. 예정된 모임 일정
        // 미완성

        // 3. 소모임 추천 목록
        addItems("스터디");

        // 알림 버튼
        binding.btnAlarms.setOnClickListener(v -> {
            // 미완성
        });

        // 설정 버튼
        binding.btnSettings.setOnClickListener(v -> {
            // 미완성
        });
    }

    private void addItems(String category){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GroupAdapter adapter = new GroupAdapter(getContext());

        Total_group_responseDTO body = retrofitClient.get_grouplist();

        for (int i = 0; i < body.getData().size(); i++) {
            if (category.equals("전체") || body.getData().get(i).getCategory().equals(category)) {
                adapter.addItem(new Group(
                        body.getData().get(i).getLocation(),
                        body.getData().get(i).getCategory(),
                        body.getData().get(i).getCurrentMemberCount(),
                        body.getData().get(i).getMaxMemberCount(),
                        body.getData().get(i).getName(),
                        body.getData().get(i).getContent(),
                        body.getData().get(i).getImageUri()));
                Log.d("group", body.getData().get(i).getLocation());
            }
        }

        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((holder, view, position) -> {
            Group item = adapter.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);
            intent.putExtra("teamId", body.getData().get(position).getTeamId());
            // 미완성 (소모임 정보 넘겨주기)

            startActivity(intent);
        });
    }
}