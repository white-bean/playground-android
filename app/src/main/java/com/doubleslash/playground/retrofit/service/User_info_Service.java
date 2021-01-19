package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface User_info_Service {
    @POST("/myinfo")
    Call<User_info_responseDTO> getData(@Header("Authorization") String token);
}