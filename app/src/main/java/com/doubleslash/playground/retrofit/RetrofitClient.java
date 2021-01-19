package com.doubleslash.playground.retrofit;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.doubleslash.playground.ClientApp;
import com.doubleslash.playground.register.RegisterActivity7;
import com.doubleslash.playground.retrofit.dto.response.Chatroom_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.Group_createDTO;
import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;
import com.doubleslash.playground.retrofit.dto.Send_chat_DTO;
import com.doubleslash.playground.retrofit.dto.response.Send_chat_responseDTO;
import com.doubleslash.playground.retrofit.dto.Sign_up_DTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_up_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;
import com.doubleslash.playground.retrofit.service.Chatroom_infoService;
import com.doubleslash.playground.retrofit.service.Group_create_Service;
import com.doubleslash.playground.retrofit.service.Send_chat_Service;
import com.doubleslash.playground.retrofit.service.Sign_up_Service;
import com.doubleslash.playground.retrofit.service.Studentcard_upload_Service;
import com.doubleslash.playground.retrofit.service.Team_info_Service;
import com.doubleslash.playground.retrofit.service.Total_group_Service;
import com.doubleslash.playground.socket.model.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.*;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static Group_create_Service group_create_service;
    private static Sign_up_Service sign_up_service;
    private static Total_group_Service total_group_service;
    private static Team_info_Service team_info_service;
    private static Chatroom_infoService chatroom_infoService;
    private static Studentcard_upload_Service studentcard_upload_service;
    private static Send_chat_Service send_chat_service;

    public static final String API_URL = "http://222.251.129.150/";
    public static int result =- 1;

    public static Total_group_responseDTO total_group_responseDTO = null;
    public static Team_info_responseDTO team_info_responseDTO = null;
    public static Chatroom_info_responseDTO chatroom_infoDTO = null;

    public RetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        group_create_service = retrofit.create(Group_create_Service.class);
        sign_up_service = retrofit.create(Sign_up_Service.class);
        total_group_service = retrofit.create(Total_group_Service.class);
        team_info_service = retrofit.create(Team_info_Service.class);
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


    public void uploadImage(Uri uri){
        Thread thread = new Thread() {
            @Override
            public void run() {
                File file = new File(getRealPathFromURI(uri));
                final RequestBody description = createPartFromString("add text?");
                final MultipartBody.Part body1 = prepareFilePart("image", uri);
                try {
                    ResponseBody body =studentcard_upload_service.uploadFile(description,body1).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(getRealPathFromURI(fileUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public void post_group(final String category, final String location, final String content, final int maxMemberCount, final String name, final String token) {
        final Group_create_responseDTO[] body = new Group_create_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Group_createDTO groupCreateDTO = new Group_createDTO();
                groupCreateDTO.setCategory(category);
                groupCreateDTO.setLocation(location);
                groupCreateDTO.setContent(content);
                groupCreateDTO.setMaxMemberCount(maxMemberCount);
                groupCreateDTO.setName(name);
                groupCreateDTO.setToken(token);
                try {
                    body[0] = group_create_service.postData(groupCreateDTO, ClientApp.userToken).execute().body();
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
                Log.d("Token", body[0].getGroup_infoDTO().getToken() + "");
            } else {
                Log.d("error", "Cannot create group");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int post_login(final String email, final String password){
        final Sign_up_responseDTO[] body = new Sign_up_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Sign_up_DTO sign_up_dto = new Sign_up_DTO();
                sign_up_dto.setEmail(email);
                sign_up_dto.setpassword(password);
                try {
                    body[0] = sign_up_service.sign_in(sign_up_dto).execute().body();
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

    public int post_sign_up(final String email, final String password){
        final Sign_up_responseDTO[] body = new Sign_up_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Sign_up_DTO sign_up_dto = new Sign_up_DTO();
                sign_up_dto.setEmail(email);
                sign_up_dto.setpassword(password);
                try {
                    body[0] = sign_up_service.sign_up(sign_up_dto).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            System.out.println("222" +result);
            Log.d("data.getUserId()", body[0].getMessage() + "");
            Log.d("data.getId()", body[0].getResult() + "");
            Log.e("postData end3", "======================================");
            result = body[0].getResult();
            System.out.println("111" + result);
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

    public void send_chat(final Type type, final String from, final String to, final String text, final long sendTime){
        final Send_chat_responseDTO[] body = new Send_chat_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Send_chat_DTO send_chat_dto = new Send_chat_DTO();
                send_chat_dto.setType(type);
                send_chat_dto.setFrom(from);
                send_chat_dto.setTo(to);
                send_chat_dto.setText(text);
                send_chat_dto.setSendTime(sendTime);
                try {
                    body[0] = send_chat_service.postData(send_chat_dto, ClientApp.userToken).execute().body();
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
}