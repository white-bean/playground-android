package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.MainActivity;
import com.doubleslash.playground.R;

public class RegisterActivity7 extends AppCompatActivity {
    ImageView studentCardBtn;
    Button nextBtn;

    private final int GET_IMAGE_FOR_STUDENT_CARD = 203;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register7);

        initUI();
    }

    private void initUI() {
        studentCardBtn = findViewById(R.id.student_card_btn);
        nextBtn = findViewById(R.id.next_btn);

        studentCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FOR_STUDENT_CARD);
                onNextBtn();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null && requestCode == GET_IMAGE_FOR_STUDENT_CARD) {
            MultiTransformation multiOption = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));
            Uri selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(studentCardBtn);
        }
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        nextBtn.setBackgroundResource(R.drawable.ic_button);
        nextBtn.setTextColor(getResources().getColor(R.color.white));
        nextBtn.setEnabled(true);
    }
}