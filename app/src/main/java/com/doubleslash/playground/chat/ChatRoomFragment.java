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
import com.doubleslash.playground.retrofit.dto.response.Chatroom_info_responseDTO;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.socket.model.Message;

import java.util.HashMap;
import java.util.Queue;

public class ChatRoomFragment extends Fragment {
    private FragmentChatListBinding binding;
    private RetrofitClient retrofitClient;
    private ChatRoomAdapter adapter;

    public static HashMap<String, Queue<Message>> RoomMsgs;     // Key는 RoomID, Value는 Message 큐
    private Queue<Message> messageQueue;

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

        adapter = new ChatRoomAdapter();
        Chatroom_info_responseDTO body = retrofitClient.get_Chatroominfo();
        for (int i = 0; i < body.getData().size(); i++) {
            Log.d("Room info", body.getData().get(i).getRoomId());
            adapter.addItem(new ChatRoomItem(body.getData().get(i).getName(), body.getData().get(i).getName(), "123", 5));
        }
        adapter.setOnItemClickListener((holder, view1, position) -> {
            // 눌렀을 때
            Intent intent = new Intent(getContext(), ChatActivity.class);
            intent.putExtra("roomId", body.getData().get(position).getRoomId());
            Log.d("roomId : ", body.getData().get(position).getRoomId());
            startActivity(intent);
        });

        binding.recyclerView.setAdapter(adapter);
    }
}
