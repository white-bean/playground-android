package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Find_group_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface Find_group_Service {
    @GET("/team/search")
    Call<Find_group_responseDTO> getData(String searchData);
}
