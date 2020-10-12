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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        check_btn = findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //중복확인하기
            }
        });

        info_edit = findViewById(R.id.info_edit);  //소모임 소개 입력받기

        text_num_tV = findViewById(R.id.text_num_tV);   // 소개에서 입력받은 글자 수 실시간 출력하기

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
        Intent intent = new Intent();
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 101){ // 갤러리 열 때
            if(resultCode == RESULT_OK){
                Uri fileUri = data.getData();   //선택한 사진

                ContentResolver resolver = getContentResolver();

                try{
                    InputStream instream = resolver.openInputStream(fileUri);
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    register_pic_btn.setVisibility(View.INVISIBLE);
                    register_pic_iV.setVisibility(View.VISIBLE);
                    register_pic_iV.setImageBitmap(imgBitmap);
                    instream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}