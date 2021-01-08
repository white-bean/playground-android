package com.doubleslash.playground.GroupList;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.doubleslash.playground.CreateGroupActivity;
import com.doubleslash.playground.FindGroupActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.Total_group_responseDTO;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;


public class GroupListFragment extends Fragment {
    private RetrofitClient retrofitClient;
    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    private Button add_btn, search_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group_list, container, false);

        retrofitClient = RetrofitClient.getInstance();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GroupAdapter();
        addItems(); // 리사이클러뷰에 들어갈 객체들
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((holder, view, position) -> {
            Group item = adapter.getItem(position);

            Intent intent = new Intent(getActivity(), InfoGroupActivity.class);
            startActivity(intent);
        });
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        add_btn = rootView.findViewById(R.id.add_btn);      //그룹추가버튼
        search_btn = rootView.findViewById(R.id.search_btn);//그룹찾기버튼

        add_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateGroupActivity.class);
            startActivity(intent);
        });

        search_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FindGroupActivity.class);
            startActivity(intent);
        });
    }

    private void addItems(){
        Total_group_responseDTO body = retrofitClient.get_grouplist();
        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new Group(body.getData().get(i).getLocation().getCity() + " " + body.getData().get(i).getLocation().getStreet(), body.getData().get(i).getCategory(), "1", body.getData().get(i).getMaxMemberCount().toString(), body.getData().get(i).getName(),
                    body.getData().get(i).getContent(), R.drawable.img_join));
        }
    }
        /*
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "자소서 스터디",
                "자기소개서 스터디 하실 분 구합니다:)", R.drawable.img_join));
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "C++ 스터디",
                "열심히 코딩 공부하실 분들 오세여", R.drawable.img_join));
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "자소서 스터디",
                "자소서 서로 첨삭해주는 스터디", R.drawable.group_vio));
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "자소서 스터디",
                "자소서 서로 첨삭해주는 스터디", R.drawable.img_join));
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "자소서 스터디",
                "자소서 서로 첨삭해주는 스터디", R.drawable.group_vio));
        adapter.addItem(new Group("서울 송파", "스터디", "1", "4", "자소서 스터디",
                "자소서 서로 첨삭해주는 스터디", R.drawable.group_vio));
    }*/
}