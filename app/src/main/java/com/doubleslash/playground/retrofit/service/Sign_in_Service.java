package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.Sign_inDTO;
import com.doubleslash.playground.retrofit.dto.response.AutoLoginResponseDTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_in_responseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Sign_in_Service {
    @POST("/member/login")
    Call<Sign_in_responseDTO> sign_in(@Body Sign_inDTO sign_in_dto);

    @POST("/member/login/again")
    Call<AutoLoginResponseDTO> auto_sign_in(@Header("Authorization") String userToken,
                                       @Body String fcmToken);
}
