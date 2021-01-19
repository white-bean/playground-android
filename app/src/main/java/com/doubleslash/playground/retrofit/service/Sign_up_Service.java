package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.Sign_up_DTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_up_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Sign_up_Service {
    @POST("/member/join")
    //Call<Sign_up_DTO> postData(@Field("email") String email,
        //                       @Field("password") String password);
    Call<Sign_up_responseDTO> sign_up(@Body Sign_up_DTO sign_up_dto);


    @POST("/member/login")
    //Call<Sign_up_DTO> postData(@Field("email") String email,
      //                         @Field("password") String password);
    Call<Sign_up_responseDTO> sign_in(@Body Sign_up_DTO sign_up_dto);
}
