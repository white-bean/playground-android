package com.doubleslash.playground.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.MainActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.Retrofit_pakage.Chatroom_info_responseDTO;
import com.doubleslash.playground.Retrofit_pakage.My_Retrofit;
import com.doubleslash.playground.Retrofit_pakage.Total_group_responseDTO;
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
        final My_Retrofit my_retrofit = new My_Retrofit();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //Bundle bundle=getArguments();
        //final String email=bundle.getString("email");
        adapter = new ChatRoomAdapter();
        Chatroom_info_responseDTO body = my_retrofit.get_Chatroominfo();
        while (body == null) {
            body = my_retrofit.chatroom_infoDTO;
        }
        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new ChatRoomItem(body.getData().get(i).getName(), body.getData().get(i).getName(), "123", 5));
        }
        final Chatroom_info_responseDTO finalBody = body;
        adapter.setOnItemClickListener(new OnChatRoomItemClickListener() {
            @Override
            public void onItemClick(ChatRoomAdapter.ViewHolder holder, View view, int position) {
                // 눌렀을 때
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("roomId", finalBody.getData().get(position).getRoomId());
                Log.d("roomid : ",finalBody.getData().get(position).getRoomId());
                //.putExtra("email",email);
                startActivity(intent);
            }
        });
        //addDummyData(adapter);

                /*
        Total_group_responseDTO body=my_retrofit.get_grouplist();
        while(body==null) {body=my_retrofit.total_group_responseDTO;}
        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new ChatRoomItem(body.getData().get(i).getCategory(), body.getData().get(i).getContent(), body.getData().get(i).getName(), body.getData().get(i).getMaxMemberCount()));

        }
        */

        recyclerView.setAdapter(adapter);
    }

    private void addDummyData(ChatRoomAdapter adapter) {
        adapter.addItem(new ChatRoomItem("테니스 마니아", "오늘 6시에 뭐하세요?", "00:00", 3));
        adapter.addItem(new ChatRoomItem("UI/UX 스터디", "다음주 스터디 준비사항 안내해드립니...", "00:00", 3));
    }
}
