package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface User_info_Service {
    @GET("/user/{userId}")
    Call<User_info_responseDTO> getData(@Path("userId") Long id);
}