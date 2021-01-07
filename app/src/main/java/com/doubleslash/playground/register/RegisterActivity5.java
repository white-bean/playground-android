package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister5Binding;

public class RegisterActivity5 extends AppCompatActivity {
    ActivityRegister5Binding binding;

    private final int GET_IMAGE_FOR_PICTURE1 = 300;
    private final int GET_IMAGE_FOR_PICTURE2 = 301;
    private final int GET_IMAGE_FOR_PICTURE3 = 302;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initIUI();
    }

    private void initIUI() {
        binding.picture1Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE1);
        });

        binding.picture2Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE2);
        });

        binding.picture3Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE3);
        });

        binding.introEdit.addTextChangedListener(new TextWatcher() {
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
                } else {
                    // 테스트용으로 버튼 무조건 활성화
                    // offNextBtn();
                    onNextBtn();
                }
            }
        });

        binding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity6.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri;
        MultiTransformation multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            switch(requestCode) {
                case GET_IMAGE_FOR_PICTURE1:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.picture1Btn);
                    break;
                case GET_IMAGE_FOR_PICTURE2:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.picture2Btn);
                    break;
                case GET_IMAGE_FOR_PICTURE3:
                    selectedImageUri = data.getData();
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.picture3Btn);
                    break;
            }
        }
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        binding.nextBtn.setBackgroundResource(R.drawable.ic_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.white));
        binding.nextBtn.setEnabled(true);
    }

    // 다음 버튼 비활성화
    private void offNextBtn() {
        binding.nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        binding.nextBtn.setEnabled(false);
    }

}