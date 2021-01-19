package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.Send_chat_DTO;
import com.doubleslash.playground.retrofit.dto.response.Send_chat_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Send_chat_Service {
    @POST("/chat/message")
    Call<Send_chat_responseDTO> postData(@Body Send_chat_DTO sendChatDto, @Header("Authorization") String token);
}
