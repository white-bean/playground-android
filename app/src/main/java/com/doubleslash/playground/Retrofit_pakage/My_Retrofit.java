package com.doubleslash.playground.Retrofit_pakage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.*;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class My_Retrofit {
    public static final String API_URL = "http://222.251.129.150/";


    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    @SuppressLint("StaticFieldLeak")
    public void post_group(){
        final Group_create_Service group_create_service=retrofit.create(Group_create_Service.class);
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... voids) {
                Group_createDTO groupCreateDTO=new Group_createDTO();
                groupCreateDTO.setCategory("123");
                groupCreateDTO.setCity("2222");
                groupCreateDTO.setContent("333");
                groupCreateDTO.setMaxMemberCount(3334);
                groupCreateDTO.setName("23");
                groupCreateDTO.setStreet("23");
                groupCreateDTO.setToken("123321");
                try {
                    Group_create_responseDTO body =group_create_service.postData(groupCreateDTO).execute().body();
                    if (body.getChatRoomId()!=null) {
                        Log.d("RoomId : ", body.getChatRoomId() + "");
                        Log.d("setCategory(", body.getGroup_infoDTO().getCategory() + "");
                        Log.d("setCity", body.getGroup_infoDTO().getCity() + "");
                        Log.d("content : ", body.getGroup_infoDTO().getContent() + "");
                        Log.d("tMaxMemberCount", body.getGroup_infoDTO().getMaxMemberCount() + "");
                        Log.d("setName", body.getGroup_infoDTO().getName() + "");
                        Log.d("setStreet", body.getGroup_infoDTO().getStreet() + "");
                        Log.d(".setToken", body.getGroup_infoDTO().getToken() + "");
                    }else{
                        Log.d("setCategory(", body.getGroup_infoDTO().getCategory() + "");
                        Log.d("setCity", body.getGroup_infoDTO().getCity() + "");
                        Log.d("detDesscription", body.getGroup_infoDTO().getContent() + "");
                        Log.d("tMaxMemberCount", body.getGroup_infoDTO().getMaxMemberCount() + "");
                        Log.d("setName", body.getGroup_infoDTO().getName() + "");
                        Log.d("setStreet", body.getGroup_infoDTO().getStreet() + "");
                        Log.d(".setToken", body.getGroup_infoDTO().getToken() + "");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public int post_login(final String email, final String password){
        final Sign_up_Service sign_up_service =retrofit.create(Sign_up_Service.class);
        final int[] result = {0};
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... voids){
                Sign_up_DTO sign_up_dto =new Sign_up_DTO();
                sign_up_dto.setEmail(email);
                sign_up_dto.setpassword(password);
                try {
                    Sigh_up_responseDTO body= sign_up_service.sign_in(sign_up_dto).execute().body();
                    Log.d("data.getUserId()", body.getMessage() + "");
                    Log.d("data.getId()", body.getResult() + "");
                    Log.e("postData end3", "======================================");
                    result[0] =body.getResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return result[0];
    }

    @SuppressLint("StaticFieldLeak")
    public void post_sign_up(){
        final Sign_up_Service sign_up_service =retrofit.create(Sign_up_Service.class);
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... voids){
                Sign_up_DTO sign_up_dto =new Sign_up_DTO();
                sign_up_dto.setEmail("gichan@google.com");
                sign_up_dto.setpassword("gichanpassword");
                try {
                    Sigh_up_responseDTO body= sign_up_service.sign_up(sign_up_dto).execute().body();
                    Log.d("data.getUserId()", body.getMessage() + "");
                    Log.d("data.getId()", body.getResult() + "");
                    Log.e("postData end3", "======================================");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();

    }


    @SuppressLint("StaticFieldLeak")
    public void get_grouplist(){
        final Total_group_Service total_group_service =retrofit.create(Total_group_Service.class);
        new AsyncTask<Void,Void,String>() {
            @Override
            protected String doInBackground(Void... voids){
                try {
                    Total_group_responseDTO body= total_group_service.getData().execute().body();
                    Log.d("data.getUserId()", body.getResult() + "");
                    //Log.d("data.getId()", body.getData() + "");
                    Log.e("postData end3", "======================================");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();

    }

}