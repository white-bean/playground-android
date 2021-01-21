package com.doubleslash.playground.retrofit.service;

import com.doubleslash.playground.retrofit.dto.response.Find_group_responseDTO;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Find_group_Service {
    @GET("/team/search")
    Call<Find_group_responseDTO> getData(@Query("name") String searchData);
}
