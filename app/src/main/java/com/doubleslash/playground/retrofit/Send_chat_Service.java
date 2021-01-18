package com.doubleslash.playground.retrofit;

import com.doubleslash.playground.retrofit.dto.Send_chat_DTO;
import com.doubleslash.playground.retrofit.dto.Send_chat_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Send_chat_Service {
    @POST("/add/team")      // 맞나?
    Call<Send_chat_responseDTO> postData(@Body Send_chat_DTO sendChatDto);
}
