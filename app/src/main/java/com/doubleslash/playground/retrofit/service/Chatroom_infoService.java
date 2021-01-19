package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Chatroom_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Chatroom_infoService {
    @GET("/chat/rooms")
    Call<Chatroom_info_responseDTO> getData();
}
