package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Find_group_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Find_group_Service {
    @POST("/team/search")
    Call<Find_group_responseDTO> getData(@Body String searchData);
}
