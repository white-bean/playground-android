package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateGroupActivity extends AppCompatActivity {
    private Button register_pic_btn;
    private EditText GroupName_edit;
    private Button check_btn;
    private EditText info_edit;
    private TextView text_num_tV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        initUI();
    }
    private void initUI() {
        register_pic_btn = findViewById(R.id.register_pic_btn);
        register_pic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //대표사진 등록하기
            }
        });

        GroupName_edit = findViewById(R.id.GroupName_edit); //소모임 이름 입력받기

        check_btn = findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //중복확인하기
            }
        });

        info_edit = findViewById(R.id.info_edit);  //소모임 소개 입력받기

        text_num_tV = findViewById(R.id.text_num_tV);   // 소개에서 입력받은 글자 수 실시간 출력하기
    }
}