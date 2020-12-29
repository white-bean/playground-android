package com.doubleslash.playground.Retrofit_pakage;

import com.google.gson.JsonArray;
import com.google.gson.annotations.JsonAdapter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Group_create_Service {
    @POST("/add/team")
        //Call<Sign_up_DTO> postData(@Field("email") String email,
        //                           @Field("password") String password);
    Call<Group_create_responseDTO> postData(@Body Group_createDTO groupcreate);

}
