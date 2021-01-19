package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Team_info_Service {
    @GET("/team/{teamId}")
    Call<Team_info_responseDTO> getData(@Path("teamId") Long id);
}
