package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.CheckChatRoomResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CheckChatRoomService {
    @POST("/check/chatroom")
    Call<CheckChatRoomResponseDTO> getData(@Header("Authorization") String userToken, @Body Long otherId);
}
