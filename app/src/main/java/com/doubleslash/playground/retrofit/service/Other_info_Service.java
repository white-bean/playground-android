package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Other_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Other_info_Service {
    @GET("/member/{memberId}")
    Call<Other_info_responseDTO> getData(@Path("memberId") Long id);
}
