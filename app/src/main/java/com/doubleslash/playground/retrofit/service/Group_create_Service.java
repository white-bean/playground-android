package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Group_create_Service {
    @Multipart
    @POST("/add/team")
    Call<Group_create_responseDTO> postData(@Part("name") RequestBody name,
                                            @Part("content") RequestBody content,
                                            @Part("startDate") RequestBody startDate,
                                            @Part("endDate") RequestBody endDate,
                                            @Part("maxMemberSize") Integer maxMemberSize,
                                            @Part("category") RequestBody category,
                                            @Part("location") RequestBody location,
                                            @Part MultipartBody.Part teamImageUrl,
                                            @Header("Authorization") String token);


    @Multipart
    @POST("/update/all/{teamid}")
    Call<Group_create_responseDTO> updateData(@Path("teamid") Long id,
                                            @Part("name") RequestBody name,
                                            @Part("content") RequestBody content,
                                            @Part("startDate") RequestBody startDate,
                                            @Part("endDate") RequestBody endDate,
                                            @Part("maxMemberSize") Integer maxMemberSize,
                                            @Part("category") RequestBody category,
                                            @Part("location") RequestBody location,
                                            @Part MultipartBody.Part teamImageUrl,
                                            @Header("Authorization") String token);

    @Multipart
    @POST("/update/info/{teamid}")
    Call<Group_create_responseDTO> updateData2(@Path("teamid") Long id,
                                              @Part("name") RequestBody name,
                                              @Part("content") RequestBody content,
                                              @Part("startDate") RequestBody startDate,
                                              @Part("endDate") RequestBody endDate,
                                              @Part("maxMemberSize") Integer maxMemberSize,
                                              @Part("category") RequestBody category,
                                              @Part("location") RequestBody location,
                                              @Header("Authorization") String token);

}
