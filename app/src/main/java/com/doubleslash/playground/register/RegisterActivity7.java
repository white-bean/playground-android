package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.CreateGroupActivity;
import com.doubleslash.playground.MainActivity;
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister7Binding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class RegisterActivity7 extends AppCompatActivity {
    ActivityRegister7Binding binding;
    Sign_upDTO sign_upDTO;
    final int MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE =1;
    Uri selectedImageUri;
    ArrayList<Uri> urilist=new ArrayList<>();
    MultipartBody.Part[] selfimage = new MultipartBody.Part[3];
    MultipartBody.Part studentcard;
    public static Context context;
    private final int GET_IMAGE_FOR_STUDENT_CARD = 203;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);
        binding = ActivityRegister7Binding.inflate(getLayoutInflater());
        urilist=getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        sign_upDTO= (Sign_upDTO) getIntent().getSerializableExtra("sign_upDTO");
        setContentView(binding.getRoot());
        context=this;
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

            RetrofitClient my_retrofit=new RetrofitClient();
            studentcard=my_retrofit.prepareFilePart("studentCard", selectedImageUri, context);

            for(int i=0;i<3;i++){
                selfimage[i]=my_retrofit.prepareFilePart("profile", urilist.get(i), context);
            }
            my_retrofit.uploadSign_up(sign_upDTO,studentcard,selfimage);
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
            selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.studentCardBtn);
        }
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        binding.nextBtn.setBackgroundResource(R.drawable.ic_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.white));
        binding.nextBtn.setEnabled(true);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}