package com.doubleslash.playground.retrofit;

import com.doubleslash.playground.retrofit.dto.User_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface User_info_Service {
    @GET("/user/{userId}")
    Call<User_info_responseDTO> getData(@Path("userId") Long id);
}