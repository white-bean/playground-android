package com.doubleslash.playground.Retrofit_pakage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Chatroom_infoService {
    @GET("/chat/rooms")
    Call<Chatroom_info_responseDTO> getData();
}
