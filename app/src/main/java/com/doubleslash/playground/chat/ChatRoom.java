package com.doubleslash.playground.chat;

public class ChatRoom {
    String title;   // 제목
    String content; // 마지막에 친 내용
    String sendTime;    // 마지막에 보낸 시각
    int unreadMsgCnt;   // 안 읽은 메세지 갯수

    public ChatRoom(String title, String content, String sendTime, int unreadMsgCnt) {
        this.title = title;
        this.content = content;
        this.sendTime = sendTime;
        this.unreadMsgCnt = unreadMsgCnt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getUnreadMsgCnt() {
        return unreadMsgCnt;
    }

    public void setUnreadMsgCnt(int unreadMsgCnt) {
        this.unreadMsgCnt = unreadMsgCnt;
    }
}
