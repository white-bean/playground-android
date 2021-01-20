package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Editgroup_Service {
    @POST("/add/team")
    Call<Group_create_responseDTO> getData();
}
