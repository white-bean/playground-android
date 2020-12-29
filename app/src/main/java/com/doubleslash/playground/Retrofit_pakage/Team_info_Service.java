package com.doubleslash.playground.Retrofit_pakage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Team_info_Service {
    @GET("/team/{teamId}")
    Call<Team_info_responseDTO> getData(@Path("teamId") Long id);
}
