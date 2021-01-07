package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.MainActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister7Binding;

public class RegisterActivity7 extends AppCompatActivity {
    ActivityRegister7Binding binding;

    private final int GET_IMAGE_FOR_STUDENT_CARD = 203;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        binding.studentCardBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_STUDENT_CARD);
            onNextBtn();
        });

        binding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null && requestCode == GET_IMAGE_FOR_STUDENT_CARD) {
            MultiTransformation multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));
            Uri selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.studentCardBtn);
        }
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        binding.nextBtn.setBackgroundResource(R.drawable.ic_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.white));
        binding.nextBtn.setEnabled(true);
    }
}