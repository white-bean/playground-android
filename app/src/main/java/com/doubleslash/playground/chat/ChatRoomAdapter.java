package com.doubleslash.playground.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleslash.playground.R;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> implements OnChatRoomItemClickListener {
    ArrayList<ChatRoomItem> items = new ArrayList<ChatRoomItem>();
    OnChatRoomItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.chat_room_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatRoomItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ChatRoomItem item){
        items.add(item);
    }

    public void setItems(ArrayList<ChatRoomItem> items){
        this.items = items;
    }

    public ChatRoomItem getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnChatRoomItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView chatImage;
        RelativeLayout unreadMsgLayout;
        TextView titleText;
        TextView contentText;
        TextView sendTimeText;
        TextView unreadMsgCntText;

        public ViewHolder(View itemView, final OnChatRoomItemClickListener listener){
            super(itemView);

            chatImage = itemView.findViewById(R.id.chat_image);
            unreadMsgLayout = itemView.findViewById(R.id.unread_msg_layout);
            titleText = itemView.findViewById(R.id.chat_title_text);
            contentText = itemView.findViewById(R.id.chat_content_text);
            sendTimeText = itemView.findViewById(R.id.send_time_text);
            unreadMsgCntText = itemView.findViewById(R.id.unread_msg_cnt);

            itemView.setOnClickListener(new View.OnClickListener(){ // 클릭 이벤트
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                    //클릭 시 동작
                }
            });
        }
        public void setItem(ChatRoomItem item){
            titleText.setText(item.getTitle());
            contentText.setText(item.getContent());
            sendTimeText.setText(item.getSendTime());
            unreadMsgCntText.setText(Integer.toString(item.getUnreadMsgCnt()));
        }
    }
}
