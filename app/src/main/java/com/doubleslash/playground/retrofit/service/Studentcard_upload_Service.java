package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Sign_up_responseDTO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Studentcard_upload_Service {
    /*
    @Multipart
    @POST("//imageUpload.jsp")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);
     */

    @Multipart
    @POST("/member/join")
    Call<Sign_up_responseDTO> uploadFile(
            @Part("university") RequestBody university,
            @Part("studentNumber") RequestBody studentNumber,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("nickname") RequestBody nickname,
            @Part("sex") RequestBody sex,
            @Part("age") RequestBody age,
            @Part("location") RequestBody location,
            @Part("hobby") RequestBody hobby,
            @Part MultipartBody.Part studentCard,
            @Part MultipartBody.Part[] profile);
}
