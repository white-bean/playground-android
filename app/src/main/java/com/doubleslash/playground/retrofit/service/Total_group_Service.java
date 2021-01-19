package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Total_group_Service {
    @GET("/all/teams")
    Call<Total_group_responseDTO> getData();
}
