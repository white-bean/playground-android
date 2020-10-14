package com.doubleslash.playground.chat;

import android.view.View;

public interface OnChatRoomItemClickListener {
    public void onItemClick(ChatRoomAdapter.ViewHolder holder, View view, int position);
}
