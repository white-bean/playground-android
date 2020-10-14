package com.doubleslash.playground.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.MainActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.customwidget.SearchEditText;

public class ChatRoomFragment extends Fragment {
    SearchEditText searchEdit;
    private RecyclerView recyclerView;
    private ChatRoomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat_list, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(View view) {
        searchEdit = view.findViewById(R.id.search_edit);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ChatRoomAdapter();
        adapter.setOnItemClickListener(new OnChatRoomItemClickListener() {
            @Override
            public void onItemClick(ChatRoomAdapter.ViewHolder holder, View view, int position) {
                // 눌렀을 때
                Intent intent = new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
        addDummyData(adapter);
        recyclerView.setAdapter(adapter);
    }

    private void addDummyData(ChatRoomAdapter adapter) {
        adapter.addItem(new ChatRoomItem("테니스 마니아", "오늘 6시에 뭐하세요?", "00:00", 3));
        adapter.addItem(new ChatRoomItem("UI/UX 스터디", "다음주 스터디 준비사항 안내해드립니...", "00:00", 3));
    }

}
