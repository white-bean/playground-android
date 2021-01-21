package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.ChatRoomInfoResponseDTO;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Chatroom_infoService {
    @POST("/update/info/chatrooms")
    Call<ChatRoomInfoResponseDTO> getData(@Header("Authorization") String userToken);
}
