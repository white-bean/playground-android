package com.doubleslash.playground.GroupList;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleslash.playground.CreateGroupActivity;
import com.doubleslash.playground.FindGroupActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.FragmentGroupList1Binding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.Total_group_responseDTO;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;

public class GroupListFragment1 extends Fragment {
    private FragmentGroupList1Binding binding;

    private RetrofitClient retrofitClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGroupList1Binding.inflate(inflater, container, false);

        retrofitClient = RetrofitClient.getInstance();

        initUI();

        addItems("전체");

        return binding.getRoot();
    }

    private void initUI() {
        // 필터 버튼
        binding.layoutFilter.setOnClickListener(view -> {
            if (binding.layoutFilterCategory.getVisibility() == View.GONE) {
                binding.tvFilter.setTextColor(getResources().getColor(R.color.white));
                binding.imageFilter.setImageResource(R.drawable.ic_filter_white);
                binding.btnFilter.setBackgroundResource(R.drawable.ic_down_white);
                binding.layoutFilter.setBackgroundResource(R.drawable.ic_black_rounded_rectangle);

                binding.layoutFilterCategory.setVisibility(View.VISIBLE);
            } else {
                binding.tvFilter.setTextColor(getResources().getColor(R.color.sub_black));
                binding.imageFilter.setImageResource(R.drawable.ic_filter);
                binding.btnFilter.setBackgroundResource(R.drawable.ic_down_sub_gray);
                binding.layoutFilter.setBackground(null);

                binding.layoutFilterCategory.setVisibility(View.GONE);
            }
        });

        // 필터 항목 선택
        binding.tvAll.setOnClickListener(view -> {
            binding.tvAll.setTextColor(getResources().getColor(R.color.sub_black));
            binding.tvStudy.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvDiet.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvCultural.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvGame.setTextColor(getResources().getColor(R.color.sub_gray));

            addItems("전체");
        });

        binding.tvStudy.setOnClickListener(view -> {
            binding.tvAll.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvStudy.setTextColor(getResources().getColor(R.color.sub_black));
            binding.tvDiet.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvCultural.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvGame.setTextColor(getResources().getColor(R.color.sub_gray));

            addItems("스터디");
        });

        binding.tvDiet.setOnClickListener(view -> {
            binding.tvAll.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvStudy.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvDiet.setTextColor(getResources().getColor(R.color.sub_black));
            binding.tvCultural.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvGame.setTextColor(getResources().getColor(R.color.sub_gray));

            addItems("운동/다이어트");
        });

        binding.tvCultural.setOnClickListener(view -> {
            binding.tvAll.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvStudy.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvDiet.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvCultural.setTextColor(getResources().getColor(R.color.sub_black));
            binding.tvGame.setTextColor(getResources().getColor(R.color.sub_gray));

            addItems("문화생활");
        });

        binding.tvGame.setOnClickListener(view -> {
            binding.tvAll.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvStudy.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvDiet.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvCultural.setTextColor(getResources().getColor(R.color.sub_gray));
            binding.tvGame.setTextColor(getResources().getColor(R.color.sub_black));

            addItems("게임");
        });

        // 소모임 생성 버튼
        binding.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateGroupActivity.class);
            startActivity(intent);
        });

        // 소모임 검색 버튼
        binding.searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FindGroupActivity.class);
            startActivity(intent);
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
            }
        }

        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((holder, view, position) -> {
            Group item = adapter.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);

            // 미완성
            // 소모임 정보 넘겨주기

            startActivity(intent);
        });
    }
}