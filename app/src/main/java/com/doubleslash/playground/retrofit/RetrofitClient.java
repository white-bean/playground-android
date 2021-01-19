package com.doubleslash.playground.retrofit;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.register.RegisterActivity7;
import com.doubleslash.playground.retrofit.dto.Group_createDTO;
import com.doubleslash.playground.retrofit.dto.Send_chat_DTO;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;
import com.doubleslash.playground.retrofit.dto.Sign_inDTO;
import com.doubleslash.playground.retrofit.dto.TeamDTO;
import com.doubleslash.playground.retrofit.dto.response.Chatroom_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Send_chat_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_in_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_up_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;
import com.doubleslash.playground.retrofit.service.Chatroom_infoService;
import com.doubleslash.playground.retrofit.service.Group_create_Service;
import com.doubleslash.playground.retrofit.service.Send_chat_Service;
import com.doubleslash.playground.retrofit.service.Sign_in_Service;
import com.doubleslash.playground.retrofit.service.Studentcard_upload_Service;
import com.doubleslash.playground.retrofit.service.Team_info_Service;
import com.doubleslash.playground.retrofit.service.Total_group_Service;
import com.doubleslash.playground.socket.model.Aria;
import com.doubleslash.playground.retrofit.service.User_info_Service;
import com.doubleslash.playground.socket.model.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.*;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static Group_create_Service group_create_service;
    private static Sign_in_Service sign_in_service;
    private static Total_group_Service total_group_service;
    private static Team_info_Service team_info_service;
    private static User_info_Service user_info_service;
    private static Chatroom_infoService chatroom_infoService;
    private static Studentcard_upload_Service studentcard_upload_service;
    private static Send_chat_Service send_chat_service;

    public static final String API_URL = "http://222.251.129.150/";
    public static int result = -1;

    public static Total_group_responseDTO total_group_responseDTO = null;
    public static Team_info_responseDTO team_info_responseDTO = null;
    public static User_info_responseDTO user_info_responseDTO = null;
    public static Chatroom_info_responseDTO chatroom_infoDTO = null;
    public static Sign_up_responseDTO sign_up_responseDTO;

    public RetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        group_create_service = retrofit.create(Group_create_Service.class);
        sign_in_service = retrofit.create(Sign_in_Service.class);
        total_group_service = retrofit.create(Total_group_Service.class);
        team_info_service = retrofit.create(Team_info_Service.class);
        user_info_service = retrofit.create(User_info_Service.class);
        chatroom_infoService = retrofit.create(Chatroom_infoService.class);
        studentcard_upload_service = retrofit.create(Studentcard_upload_Service.class);
        send_chat_service = retrofit.create(Send_chat_Service.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(RegisterActivity7.context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public void uploadSign_up(Sign_upDTO sign_upDTO, MultipartBody.Part studentcard, MultipartBody.Part[] selfimage){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sign_up_responseDTO = studentcard_upload_service.uploadFile(createPartFromString(sign_upDTO.getSchoolname()),createPartFromString(sign_upDTO.getSchoolnum()),createPartFromString(sign_upDTO.getEmail()),createPartFromString(sign_upDTO.getPassword()),createPartFromString(sign_upDTO.getName()),createPartFromString(sign_upDTO.getSex()),
                            createPartFromString(sign_upDTO.getAge()),createPartFromString(sign_upDTO.getRegion()),createPartFromString(sign_upDTO.getHobby()),studentcard,selfimage).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private String getRealPathFromURI(Uri contentUri,Context context) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse("text/plain"), descriptionString);
    }

    @NonNull
    public MultipartBody.Part prepareFilePart(String partName, Uri fileUri, Context context) {
        File file = new File(getRealPathFromURI(fileUri,context));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public void post_group(final String category, final String location, final String content, final int maxMemberCount, final String name, final String startDate, final String endDate, final String groupimage) {
        final Group_create_responseDTO[] body = new Group_create_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                TeamDTO teamDTO = new TeamDTO();
                teamDTO.setCategory(category);
                teamDTO.setLocation(location);
                teamDTO.setContent(content);
                teamDTO.setMaxMemberCount(maxMemberCount);
                teamDTO.setName(name);
                teamDTO.setStartDate(startDate);
                teamDTO.setEndDate(endDate);
                teamDTO.setImageUri(groupimage);
                try {
                    body[0] = group_create_service.postData(teamDTO, ClientApp.userToken).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            if (body[0].getChatRoomId() != null) {
                Log.d("notice", "Create group");
                Log.d("RoomId : ", body[0].getChatRoomId());
                Log.d("Category(", body[0].getGroup_infoDTO().getCategory());
                Log.d("Location", body[0].getGroup_infoDTO().getLocation());
                Log.d("content : ", body[0].getGroup_infoDTO().getContent());
                Log.d("MaxMemberCount", body[0].getGroup_infoDTO().getMaxMemberCount().toString());
                Log.d("Name", body[0].getGroup_infoDTO().getName() + "");
            } else {
                Log.d("error", "Cannot create group");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int post_login(final String email, final String password){
        final Sign_in_responseDTO[] body = new Sign_in_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Sign_inDTO sign_in_dto = new Sign_inDTO();
                sign_in_dto.setEmail(email);
                sign_in_dto.setpassword(password);
                try {
                    body[0] = sign_in_service.sign_in(sign_in_dto).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            // 로그인할 때 유저 토큰 받아옴
            ClientApp.userToken = body[0].getToken();
            result = body[0].getResult();
            if (result == 1) {
                Log.d("notice", "Login success");
            } else {
                Log.d("error", "Login failed");
            }
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Total_group_responseDTO get_grouplist(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    total_group_responseDTO = total_group_service.getData().execute().body();
                    Log.e("notice", "group list fetch success");
                } catch (IOException e) {
                    Log.e("error", "group list fetch failed");
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            return total_group_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 수정해야 됨
    public Team_info_responseDTO get_teaminfo(long id){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    team_info_responseDTO = team_info_service.getData(id).execute().body();
                    Log.d("notice", "team info fetch success");
                } catch (IOException e) {
                    Log.d("error", "team info fetch failed");
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            return team_info_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User_info_responseDTO get_userinfo(long id){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    user_info_responseDTO = user_info_service.getData(id).execute().body();
                    Log.d("data.getUserId()", user_info_responseDTO.getResult() + "");
                    //Log.d("data.getId()", body.getData() + "");
                    Log.e("postData end3", "======================================");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            return user_info_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 수정해야됨
    public Chatroom_info_responseDTO get_Chatroominfo(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    chatroom_infoDTO = chatroom_infoService.getData().execute().body();
                    Log.d("notice", "chatroom info fetch success");
                } catch (IOException e) {
                    Log.d("error", "chatroom info fetch success");
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            return chatroom_infoDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void send_chat(final Aria aria, final Type type, final String from, final String to, final String text, final long sendTime){
        final Send_chat_responseDTO[] body = new Send_chat_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Send_chat_DTO send_chat_dto = new Send_chat_DTO();
                send_chat_dto.setAria(aria);
                send_chat_dto.setType(type);
                send_chat_dto.setFrom(from);
                send_chat_dto.setTo(to);
                send_chat_dto.setText(text);
                send_chat_dto.setSendTime(sendTime);
                try {
                    body[0] = send_chat_service.postData(send_chat_dto, "TOKEN " + ClientApp.userToken).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            if(body[0].getResult() != null){
                Log.d("notice", "send message success");
            }
        } catch (InterruptedException e) {
            Log.d("error", "send message failed");
            e.printStackTrace();
        }
    }

    public void group_request_accept(final Aria aria, final Type type, final String from, final String to) {
        final Send_chat_responseDTO[] body = new Send_chat_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Send_chat_DTO send_chat_dto = new Send_chat_DTO();
                send_chat_dto.setAria(aria);
                send_chat_dto.setType(type);
                send_chat_dto.setFrom(from);
                send_chat_dto.setTo(to);
                try {
                    body[0] = send_chat_service.postData(send_chat_dto, "TOKEN " + ClientApp.userToken).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            if(body[0].getResult() != null){
                Log.d("notice", "send message success");
            }
        } catch (InterruptedException e) {
            Log.d("error", "send message failed");
            e.printStackTrace();
        }
    }

    // 리퀘스트 "Aria" : "GROUP", "Type" : "REQUEST", "from" : "가입신청 유저", "to" : "teamId"
    // 어셉트 "Aria" : "GROUP", "TYPE" :"ACCEPT", "from" : "가입신청 유저", "to" : "teamId"
}