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
import com.doubleslash.playground.retrofit.dto.CreateTeamDTO;
import com.doubleslash.playground.retrofit.dto.Send_chat_DTO;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;
import com.doubleslash.playground.retrofit.dto.Sign_inDTO;
import com.doubleslash.playground.retrofit.dto.response.AutoLoginResponseDTO;
import com.doubleslash.playground.retrofit.dto.response.ChatRoomInfoResponseDTO;
import com.doubleslash.playground.retrofit.dto.response.Find_group_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Group_create_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Other_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Send_chat_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_in_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Sign_up_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Team_info_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;
import com.doubleslash.playground.retrofit.service.Chatroom_infoService;
import com.doubleslash.playground.retrofit.service.Find_group_Service;
import com.doubleslash.playground.retrofit.service.Group_create_Service;
import com.doubleslash.playground.retrofit.service.Other_info_Service;
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
    private static Other_info_Service other_info_service;
    private static Chatroom_infoService chatroom_infoService;
    private static Studentcard_upload_Service studentcard_upload_service;
    private static Send_chat_Service send_chat_service;
    private static Find_group_Service find_group_service;

    public static int result = -1;

    public static Total_group_responseDTO total_group_responseDTO = null;
    public static Team_info_responseDTO team_info_responseDTO = null;
    public static User_info_responseDTO user_info_responseDTO = null;
    public static Other_info_responseDTO other_info_responseDTO = null;
    public static ChatRoomInfoResponseDTO chatroom_infoDTO = null;
    public static Sign_up_responseDTO sign_up_responseDTO = null;
    public static Find_group_responseDTO find_group_responseDTO = null;

    public RetrofitClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ClientApp.API_URL + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        group_create_service = retrofit.create(Group_create_Service.class);
        sign_in_service = retrofit.create(Sign_in_Service.class);
        total_group_service = retrofit.create(Total_group_Service.class);
        team_info_service = retrofit.create(Team_info_Service.class);
        user_info_service = retrofit.create(User_info_Service.class);
        other_info_service = retrofit.create(Other_info_Service.class);
        chatroom_infoService = retrofit.create(Chatroom_infoService.class);
        studentcard_upload_service = retrofit.create(Studentcard_upload_Service.class);
        send_chat_service = retrofit.create(Send_chat_Service.class);
        find_group_service = retrofit.create(Find_group_Service.class);
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

    // 그룹 생성 (CreateGroupActivity)
    public void post_group(CreateTeamDTO createTeamDTO, MultipartBody.Part teamImageUrl) {
        final Group_create_responseDTO[] body = new Group_create_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    body[0] = group_create_service.postData(createPartFromString(createTeamDTO.getName()),
                                                    createPartFromString(createTeamDTO.getContent()),
                                                    createPartFromString(createTeamDTO.getStartDate()),
                                                    createPartFromString(createTeamDTO.getEndDate()),
                                                    createTeamDTO.getMaxMemberSize(),
                                                    createPartFromString(createTeamDTO.getCategory()),
                                                    createPartFromString(createTeamDTO.getLocation()),
                                                    teamImageUrl,
                                                    "TOKEN " + ClientApp.userToken).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            if (body[0].getResult() == 1) {
                Log.d("notice", "Create group success " + body[0].getMessage());
            } else {
                Log.d("error", "Cannot create group failed " + body[0].getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //그룹 업데이트
    public void update_group(CreateTeamDTO createTeamDTO, MultipartBody.Part teamImageUrl,Long teamid) {
        final Group_create_responseDTO[] body = new Group_create_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if(teamImageUrl!=null) {
                        body[0] = group_create_service.updateData(teamid, createPartFromString(createTeamDTO.getName()),
                                createPartFromString(createTeamDTO.getContent()),
                                createPartFromString(createTeamDTO.getStartDate()),
                                createPartFromString(createTeamDTO.getEndDate()),
                                createTeamDTO.getMaxMemberSize(),
                                createPartFromString(createTeamDTO.getCategory()),
                                createPartFromString(createTeamDTO.getLocation()),
                                teamImageUrl,
                                "TOKEN " + ClientApp.userToken).execute().body();
                    }else{
                        body[0] = group_create_service.updateData2(teamid, createPartFromString(createTeamDTO.getName()),
                                createPartFromString(createTeamDTO.getContent()),
                                createPartFromString(createTeamDTO.getStartDate()),
                                createPartFromString(createTeamDTO.getEndDate()),
                                createTeamDTO.getMaxMemberSize(),
                                createPartFromString(createTeamDTO.getCategory()),
                                createPartFromString(createTeamDTO.getLocation()),
                                "TOKEN " + ClientApp.userToken).execute().body();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            if (body[0].getResult() == 1) {
                Log.d("notice", "Create group success " + body[0].getMessage());
            } else {
                Log.d("error", "Cannot create group failed " + body[0].getMessage());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 자동 로그인 (LoginActivity)
    public int post_autologin(final String user_token, final String fcm_token){
        final AutoLoginResponseDTO[] body = new AutoLoginResponseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("");
                    System.out.println(fcm_token);
                    body[0] = sign_in_service.auto_sign_in("TOKEN " +user_token,fcm_token).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            // 로그인할 때 유저 토큰 받아옴
            ClientApp.userToken = user_token;
            ClientApp.userId = body[0].getResult();
            if (ClientApp.userId != 0) {
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

    // 로그인 (LoginActivity)
    public Sign_in_responseDTO post_login(final String email, final String password, final String fcmToken){
        final Sign_in_responseDTO[] body = new Sign_in_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Sign_inDTO sign_in_dto = new Sign_inDTO();
                sign_in_dto.setEmail(email);
                sign_in_dto.setpassword(password);
                sign_in_dto.setFcm_Token(fcmToken);
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
            ClientApp.userId = body[0].getResult();
            Log.d("userId", body[0].getResult() + "");
            if (ClientApp.userId != 0) {
                Log.d("notice", "Login success");
            } else {
                Log.d("error", "Login failed");
            }
            return body[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 소모임 정보 가져오기 (GroupListFragment)
    public Total_group_responseDTO get_grouplist(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    total_group_responseDTO = total_group_service.getData().execute().body();
                    if (total_group_responseDTO.getResult() == 1) {
                        Log.d("notice", "group list fetch success");
                    } else {
                        Log.e("error", "group list fetch failed");
                    }
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

    // 소모임 검색 시 소모임 정보 가져오기 (FindGroupActivity)
    public Find_group_responseDTO get_findgrouplist(String searchData){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    find_group_responseDTO = find_group_service.getData(searchData).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            System.out.println("아무거나");
            System.out.println(find_group_responseDTO.getResult());
            return find_group_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 상세보기 (InfoGroupActivity)
    public Team_info_responseDTO get_teaminfo(long id){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.d("USER_TOKEN", ClientApp.userToken + "");
                    team_info_responseDTO = team_info_service.getData("TOKEN " + ClientApp.userToken, id).execute().body();
                    if (team_info_responseDTO.getResult() == 1) {
                        Log.d("notice", "team info fetch success");
                    } else {
                        Log.d("error", "team info fetch failed");
                    }
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

    // 프로필 정보 보기 (ProfileFragment)
    public User_info_responseDTO get_userinfo(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    user_info_responseDTO = user_info_service.getData("TOKEN " + ClientApp.userToken).execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            if (user_info_responseDTO.getResult() == 1) {
                Log.d("notice", "user info fetch success");
            } else {
                Log.d("error", "user info fetch failed");
            }
            return user_info_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 다른 사람 프로필 정보 보기 (ProfileOtherActivity)
    public Other_info_responseDTO get_otherinfo(long id){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    other_info_responseDTO = other_info_service.getData(id).execute().body();
                    if (other_info_responseDTO.getResult() == 1) {
                        Log.d("notice", "other info fetch success");
                    } else {
                        Log.d("error", "other info fetch failed");
                    }
                } catch (IOException e) {
                    Log.d("error", "other info fetch failed");
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            thread.join();
            return other_info_responseDTO;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 채팅방 정보 가져오기 (ChatRoomFragment)
    public ChatRoomInfoResponseDTO getChatRoomInfos() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    chatroom_infoDTO = chatroom_infoService.getData("TOKEN " + ClientApp.userToken).execute().body();
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

    // 채팅 메시지 보내기 (ChatActivity)
    public void send_chat(final Aria aria, final Type type, final long from, final String to, final String text, final long sendTime){
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
            if(body[0].getResult() == 1) {
                Log.d("notice", "send message success");
            } else {
                Log.d("error", "send message failed");
            }
        } catch (InterruptedException e) {
            Log.d("error", "send message failed");
            e.printStackTrace();
        }
    }

    // 그룹 참여 요청, 수락 (AcceptActivity, InfoGroupActivity)
    public void group_request_accept(final Aria aria, final Type type, final long from, final String to, final long sendTime) {
        final Send_chat_responseDTO[] body = new Send_chat_responseDTO[1];
        Thread thread = new Thread() {
            @Override
            public void run() {
                Send_chat_DTO send_chat_dto = new Send_chat_DTO();
                send_chat_dto.setAria(aria);
                send_chat_dto.setType(type);
                send_chat_dto.setFrom(from);
                send_chat_dto.setTo(to);
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
            if(body[0].getResult() == 1){
                Log.d("notice", "send message success");
            } else {
                Log.d("error", "send message failed");
            }
        } catch (InterruptedException e) {
            Log.d("error", "send message failed");
            e.printStackTrace();
        }
    }

    // 리퀘스트 "Aria" : "GROUP", "Type" : "REQUEST", "from" : "가입신청 유저", "to" : "teamId", "sendTime" : "sendTime"
    // 어셉트 "Aria" : "GROUP", "TYPE" :"ACCEPT", "from" : "가입신청 유저", "to" : "teamId", "sendTime" : "sendTime"
}