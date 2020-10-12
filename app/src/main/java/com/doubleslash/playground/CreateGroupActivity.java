package com.doubleslash.playground;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

public class CreateGroupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button register_pic_btn;
    private EditText GroupName_edit;
    private Button check_btn, search_btn;//search_btn은 돋보기 버튼 -> 나중에 구현해야함
    private EditText info_edit;
    private TextView text_num_tV;
    private Spinner member_spinner, category_spinner, sub_category_spinner;
    private ImageView register_pic_iV;


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
                openGallery();
            }
        });

        GroupName_edit = findViewById(R.id.GroupName_edit); //소모임 이름 입력받기
        GroupName_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        check_btn = findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //중복확인하기
            }
        });

        info_edit = findViewById(R.id.info_edit);  //소모임 소개 입력받기
        text_num_tV = findViewById(R.id.text_num_tV);   // 소개에서 입력받은 글자 수 실시간 출력하기
        info_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findViewById(R.id.info_edit).setBackground(getResources().getDrawable(R.drawable.focus_box));
                String input = info_edit.getText().toString();
                text_num_tV.setText(input.length()+"/300");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        member_spinner = findViewById(R.id.member_spinner);
        ArrayAdapter memberAdapter = ArrayAdapter.createFromResource(this, R.array.member, android.R.layout.simple_spinner_dropdown_item);
        memberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        member_spinner.setAdapter(memberAdapter);
        member_spinner.setOnItemSelectedListener(this);

        category_spinner = findViewById(R.id.category_spinner);
        ArrayAdapter cateAdapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(cateAdapter);
        category_spinner.setOnItemSelectedListener(this);

        sub_category_spinner = findViewById(R.id.sub_category_spinner);
        ArrayAdapter subAdapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_dropdown_item);
        // 나중에 팀원들과 상의해서 세부 카테고리에 뭐가 들어갈지 정해야함, array도 만들어야함, 지금은 임시로 category 리스트로 넣었음
        subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_category_spinner.setAdapter(subAdapter);
        sub_category_spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.member_spinner:
                Toast.makeText(CreateGroupActivity.this,"선택된 아이템 : "+member_spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                break;
            case R.id.category_spinner:
                Toast.makeText(CreateGroupActivity.this,"선택된 아이템 : "+category_spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                break;
            case R.id.sub_category_spinner:
                Toast.makeText(CreateGroupActivity.this,"선택된 아이템 : "+sub_category_spinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                break;
        }//Toast는 그저 확인용
    }//이 오버라이드 메소드에서 position은 몇번째 값이 클릭됐는지 알 수 있음
    //getItemAtPosition(position)를 통해서 해당 값을 받아올수있음

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Uri selectedImageUri;

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            switch(requestCode) {
                case 101:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).into(register_pic_iV);
                    break;
            }
        }
    }
}