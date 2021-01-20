package com.doubleslash.playground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.doubleslash.playground.GroupList.GroupListFragment2;
import com.doubleslash.playground.chat.ChatRoomFragment;
import com.doubleslash.playground.databinding.ActivityMainBinding;
import com.doubleslash.playground.profile.ProfileFragment;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.User_info_responseDTO;
import com.doubleslash.playground.socket.SocketMananger;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.doubleslash.playground.GroupList.GroupListFragment1;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    BottomNavigationView bottomNavigation;

    GroupListFragment1 groupListFragment1;
    GroupListFragment2 groupListFragment2;
    ChatRoomFragment chatRoomFragment;
    ProfileFragment profileFragment;

    Menu menu;

    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        retrofitClient = RetrofitClient.getInstance();

        groupListFragment1 = new GroupListFragment1();
        groupListFragment2 = new GroupListFragment2();
        setContentView(R.layout.activity_main);

        initUI();

        ClientApp.socketMananger = new SocketMananger();

        // 테스트용 코드
        ClientApp.waitingUsers = new HashMap<>();
    }// onCreate()..

    private void initUI() {
        User_info_responseDTO body = retrofitClient.get_userinfo();

        groupListFragment1 = new GroupListFragment1();
        groupListFragment2 = new GroupListFragment2();
        chatRoomFragment = new ChatRoomFragment();
        profileFragment = new ProfileFragment();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setItemIconTintList(null);
        menu=bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener(body));

        if (body.getData().getMyteams().size() == 0) {
             getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment1).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
        }
    }// onCreate()..

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        User_info_responseDTO body;

        ItemSelectedListener(User_info_responseDTO body) {
            this.body = body;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.group:
                    menuItem.setIcon(R.drawable.group_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_navigation);
                    menu.findItem(R.id.profile).setIcon(R.drawable.profile);

                    if (body.getData().getMyteams().size() == 0) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment1).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
                    }
                    break;

                case R.id.chat:
                    menuItem.setIcon(R.drawable.chat_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.group).setIcon(R.drawable.group);
                    menu.findItem(R.id.profile).setIcon(R.drawable.profile);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, chatRoomFragment).commit();

                    Intent intent=getIntent();
                    String email=intent.getStringExtra("email");
                    System.out.println(email);
                    Bundle bundle=new Bundle();
                    bundle.putString("email",email);
                    //chatRoomFragment.setArguments(bundle);
                    break;

                case R.id.profile:
                    menuItem.setIcon(R.drawable.profile_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_navigation);
                    menu.findItem(R.id.group).setIcon(R.drawable.group);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                    break;
            }// switch()..
            return true;
        }// onNavigationItemSelected()..
    }// ItemSelectedListener..
}//MainActivity..