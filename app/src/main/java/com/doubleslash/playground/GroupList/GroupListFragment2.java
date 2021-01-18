package com.doubleslash.playground.GroupList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.FragmentGroupList2Binding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.Total_group_responseDTO;

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
        // 사용자 이름 & 사진
        String user_name = ""; // 서버에서 사용자 이름 가져오기
        binding.tvUserName.setText(user_name + "님");

        Uri image_user = null; // 서버에서 사용자 사진 가져오기
        MultiTransformation multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));

        Glide.with(Objects.requireNonNull(getActivity()))
                .asBitmap()
                .load(image_user)
                .apply(RequestOptions.bitmapTransform(multiOption))
                .into(binding.imageUser);

        // 1. 가입한 소모임 목록
        // 미완성 (서버에서 가져오기)

        // 2. 예정된 모임 일정
        // 미완성 (서버에서 가져오기)

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

        GroupAdapter adapter = new GroupAdapter();

        Total_group_responseDTO body = retrofitClient.get_grouplist();

        for (int i = 0; i < body.getData().size(); i++) {
            if (category.equals("전체") || body.getData().get(i).getCategory().equals(category)) {
                adapter.addItem(new Group(
                        body.getData().get(i).getLocation().getCity() + " " + body.getData().get(i).getLocation().getStreet(),
                        body.getData().get(i).getCategory(),
                        "1", // 미완성 (소모임 현재 인원수)
                        body.getData().get(i).getMaxMemberCount().toString(),
                        body.getData().get(i).getName(),
                        body.getData().get(i).getContent(),
                        R.drawable.img_join));
            }
        }

        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((holder, view, position) -> {
            Group item = adapter.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);

            // 미완성 (소모임 정보 넘겨주기)

            startActivity(intent);
        });
    }
}