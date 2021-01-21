package com.doubleslash.playground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    //private RetrofitClient retrofitClient;

    // 마지막으로 뒤로 가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로 가기 버튼을 누를 때 표시
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //retrofitClient = RetrofitClient.getInstance();

        initUI();

        ClientApp.socketMananger = new SocketMananger();

        // 테스트용 코드
        ClientApp.waitingUsers = new HashMap<>();
    }// onCreate()..

    private void initUI() {
        //User_info_responseDTO body = retrofitClient.get_userinfo();

        groupListFragment1 = new GroupListFragment1();
        groupListFragment2 = new GroupListFragment2();
        chatRoomFragment = new ChatRoomFragment();
        profileFragment = new ProfileFragment();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setItemIconTintList(null);
        menu=bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener());

//        if (body.getData().getMyteams().size() == 0) {
//             getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment1).commit();
//        } else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
//        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
    }// onCreate()..
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // 기존 뒤로 가기 버튼의 기능을 막기 위해 주석 처리 또는 삭제

        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
        // 2500 milliseconds = 2.5 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            ActivityCompat.finishAffinity(this);
            System.exit(0);
            toast.cancel();
            toast = Toast.makeText(this,"이용해 주셔서 감사합니다.",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
//        User_info_responseDTO body;
//
//        ItemSelectedListener(User_info_responseDTO body) {
//            this.body = body;
//        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.group:
                    menuItem.setIcon(R.drawable.group_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_navigation);
                    menu.findItem(R.id.profile).setIcon(R.drawable.profile);

//                    if (body.getData().getMyteams().size() == 0) {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment1).commit();
//                    } else {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
//                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, groupListFragment2).commit();
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