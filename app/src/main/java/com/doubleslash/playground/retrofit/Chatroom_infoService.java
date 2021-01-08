package com.doubleslash.playground.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Chatroom_infoService {
    @GET("/chat/rooms")
    Call<Chatroom_info_responseDTO> getData();
}
