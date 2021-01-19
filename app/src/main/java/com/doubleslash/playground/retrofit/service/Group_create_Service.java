package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.Group_createDTO;
import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Group_create_Service {

//    Call<Sign_up_DTO> postData(@Field("email") String email,
//                               @Field("password") String password);
    @POST("/add/team")
    Call<Group_create_responseDTO> postData(@Body Group_createDTO groupcreate, @Header("Authorization") String token);
}
