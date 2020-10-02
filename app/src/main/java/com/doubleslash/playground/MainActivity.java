package com.doubleslash.playground;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setItemIconTintList(null);
        menu=bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigation.setSelectedItemId(R.id.home);  //선택된 아이템 지정
    }// onCreate()..

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.group:
                    menuItem.setIcon(R.drawable.group_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_navigation);
                    menu.findItem(R.id.profile).setIcon(R.drawable.profile);
                    break;

                case R.id.chat:
                    menuItem.setIcon(R.drawable.chat_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.group).setIcon(R.drawable.group);
                    menu.findItem(R.id.profile).setIcon(R.drawable.profile);
                    break;

                case R.id.profile:
                    menuItem.setIcon(R.drawable.profile_vio);    // 선택한 이미지 변경
                    menu.findItem(R.id.chat).setIcon(R.drawable.chat_navigation);
                    menu.findItem(R.id.group).setIcon(R.drawable.group);
                    break;
            }// switch()..
            return true;
        }
    }
}