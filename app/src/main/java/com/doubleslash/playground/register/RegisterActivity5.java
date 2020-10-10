package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.doubleslash.playground.R;

public class RegisterActivity5 extends AppCompatActivity {
    private ImageView picture1Btn;
    private ImageView picture2Btn;
    private ImageView picture3Btn;
    private EditText introEdit;
    private Button nextBtn;

    private final int GET_IMAGE_FOR_PICUTRE1 = 200;
    private final int GET_IMAGE_FOR_PICUTRE2 = 201;
    private final int GET_IMAGE_FOR_PICUTRE3 = 202;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);

        initIUI();
    }

    private void initIUI() {
        picture1Btn = findViewById(R.id.picture1_btn);
        picture2Btn = findViewById(R.id.picture2_btn);
        picture3Btn = findViewById(R.id.picture3_btn);
        introEdit = findViewById(R.id.intro_edit);
        nextBtn = findViewById(R.id.next_btn);

        picture1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FOR_PICUTRE1);
            }
        });
        picture2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FOR_PICUTRE2);
            }
        });
        picture3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FOR_PICUTRE3);
            }
        });

        introEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 10) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity6.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri;

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            switch(requestCode) {
                case GET_IMAGE_FOR_PICUTRE1:
                    selectedImageUri = data.getData();
                    picture1Btn.setImageURI(selectedImageUri);
                    break;
                case GET_IMAGE_FOR_PICUTRE2:
                    selectedImageUri = data.getData();
                    picture2Btn.setImageURI(selectedImageUri);
                    break;
                case GET_IMAGE_FOR_PICUTRE3:
                    selectedImageUri = data.getData();
                    picture3Btn.setImageURI(selectedImageUri);
                    break;
            }
        }
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        nextBtn.setBackgroundResource(R.drawable.ic_button);
        nextBtn.setTextColor(getResources().getColor(R.color.white));
        nextBtn.setEnabled(true);
    }

    // 다음 버튼 비활성화
    private void offNextBtn() {
        nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        nextBtn.setEnabled(false);
    }
}