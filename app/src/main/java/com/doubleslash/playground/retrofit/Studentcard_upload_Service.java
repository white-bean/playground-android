package com.doubleslash.playground.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Studentcard_upload_Service {
    @Multipart
    @POST("//imageUpload.jsp")
    Call<ResponseBody> uploadFile(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    @POST("//image")
    Call<ResponseBody> uploadFile2(
            @Body RequestBody body
    );
}
