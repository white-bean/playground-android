package com.doubleslash.playground.chat;

public class ChatRoomItem {
    private String roomId;  // 방 id
    private String title;   // 제목
    private String imageUrl;    // 채팅방 이미지
    private String content; // 마지막에 친 내용
    private String sendTime;    // 마지막에 보낸 시각
    int unreadMsgCnt;   // 안 읽은 메세지 갯수

    public ChatRoomItem(String roomId, String title, String imageUrl, String content, String sendTime, int unreadMsgCnt) {
        this.roomId = roomId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
        this.sendTime = sendTime;
        this.unreadMsgCnt = unreadMsgCnt;
    }

    public String getRoomId() { return roomId; }

    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

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
