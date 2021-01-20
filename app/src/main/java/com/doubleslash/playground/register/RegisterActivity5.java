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
import android.text.Editable;
import android.text.TextWatcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister5Binding;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class RegisterActivity5 extends AppCompatActivity {
    ActivityRegister5Binding binding;
    Sign_upDTO sign_upDTO;
    RetrofitClient retrofitClient;
    Uri[] uri;
    MultipartBody.Part[] selfimage = new MultipartBody.Part[3];
    final int MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE =1;
    private final int GET_IMAGE_FOR_PICTURE1 = 300;
    private final int GET_IMAGE_FOR_PICTURE2 = 301;
    private final int GET_IMAGE_FOR_PICTURE3 = 302;
    public static Context context;
    public int result=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitClient=new RetrofitClient();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);
        context=this;
        uri=new Uri[3];
        sign_upDTO=(Sign_upDTO)getIntent().getSerializableExtra("sign_upDTO");
        initIUI();
    }

    private void initIUI() {

        binding.picture1Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE1);
            result+=1;
            if(result>=3){
                onNextBtn();
            }
        });

        binding.picture2Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE2);
            result+=1;
            if(result>=3){
                onNextBtn();
            }
        });

        binding.picture3Btn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, GET_IMAGE_FOR_PICTURE3);
            result+=1;
            if(result>=3){
                onNextBtn();
            }
        });


        binding.nextBtn.setOnClickListener(v -> {
            ArrayList<Uri> urilist=new ArrayList<Uri>();
            urilist.add(uri[0]);
            urilist.add(uri[1]);
            urilist.add(uri[2]);
            Intent intent = new Intent(getApplicationContext(), RegisterActivity6.class);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,urilist);
            intent.putExtra("sign_upDTO",sign_upDTO);
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
                    uri[0]=selectedImageUri;
                    //sign_upDTO.setselfimage1(retrofitClient.prepareFilePart("selfimage1",selectedImageUri,context));
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.picture1Btn);
                    break;
                case GET_IMAGE_FOR_PICTURE2:
                    selectedImageUri = data.getData();
                    uri[1]=selectedImageUri;
                    //sign_upDTO.setselfimage2(retrofitClient.prepareFilePart("selfimage2",selectedImageUri,context));
                    Glide.with(getApplicationContext()).asBitmap().load(selectedImageUri).apply(RequestOptions.bitmapTransform(multiOption)).into(binding.picture2Btn);
                    break;
                case GET_IMAGE_FOR_PICTURE3:
                    selectedImageUri = data.getData();
                    uri[2]=selectedImageUri;
                    //sign_upDTO.setselfimage3(retrofitClient.prepareFilePart("selfimage3",selectedImageUri,context));
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
    private void requestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE);
                // MY_PERMISSIONS_REQUEST_READ_EXT_STORAGE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
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