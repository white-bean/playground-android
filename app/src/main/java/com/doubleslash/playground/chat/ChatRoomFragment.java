package com.doubleslash.playground.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.doubleslash.playground.databinding.FragmentChatListBinding;
import com.doubleslash.playground.retrofit.Chatroom_info_responseDTO;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.Total_group_responseDTO;

public class ChatRoomFragment extends Fragment {
    private FragmentChatListBinding binding;
    private RetrofitClient retrofitClient;
    private ChatRoomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initUI();

        return view;
    }

    private void initUI() {
        retrofitClient = RetrofitClient.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        //Bundle bundle=getArguments();
        //final String email=bundle.getString("email");
        adapter = new ChatRoomAdapter();
        Chatroom_info_responseDTO body = retrofitClient.get_Chatroominfo();
        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new ChatRoomItem(body.getData().get(i).getName(), body.getData().get(i).getName(), "123", 5));
        }
        adapter.setOnItemClickListener((holder, view1, position) -> {
            // 눌렀을 때
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("roomId", body.getData().get(position).getRoomId());
            Log.d("roomid : ",body.getData().get(position).getRoomId());
            //.putExtra("email",email);
            startActivity(intent);
        });


        /*
        Total_group_responseDTO totalGroupBody = retrofitClient.get_grouplist();
        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new ChatRoomItem(totalGroupBody.getData().get(i).getCategory(), totalGroupBody.getData().get(i).getContent(), body.getData().get(i).getName(), totalGroupBody.getData().get(i).getMaxMemberCount()));
        }
        */

        binding.recyclerView.setAdapter(adapter);
    }
}
