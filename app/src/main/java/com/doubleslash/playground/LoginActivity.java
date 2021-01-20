package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.doubleslash.playground.databinding.ActivityLoginBinding;
import com.doubleslash.playground.register.RegisterActivity1;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Sign_in_responseDTO;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private RetrofitClient retrofitClient;
    private String user_token,token;
    private String user_email;
    private boolean isCheckOn;
    private int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Check if we have read or write permission
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int writePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE );
        }
        initUI();
    }

    private void initUI() {
        isCheckOn=false;
        SharedPreferences auto = getSharedPreferences("playground", Activity.MODE_PRIVATE);
        user_token=auto.getString("user_token",null);
        user_email=auto.getString("user_email",null);
        retrofitClient = RetrofitClient.getInstance();
        result=0;
        if(user_token!=null){
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w("bjyoo", "Fetching FCM registration token failed");
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                        Log.w("bjyoo", "Fetching FCM registration token success!");
                        System.out.println(user_token);
                        System.out.println(token);
                        result= retrofitClient.post_autologin(user_token, token);
                        if (result == 1) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            ClientApp.userEmail=user_email;
                            intent.putExtra("email", user_email);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Log.e("AutoLoginError : ","토큰값이 다릅니다.");
                        }
                        MyFirebaseMessagingService service = new MyFirebaseMessagingService();
                        service.onNewToken(Objects.requireNonNull(token));
                    });
            // 토큰 생성하기
        }
        binding.checkBtn.setOnClickListener(v -> {
            if (!isCheckOn) {
                onCheck();
            } else {
                offCheck();
            }
        });
        binding.loginBtn.setOnClickListener(v -> {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w("bjyoo", "Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.w("bjyoo", "Fetching FCM registration token success!");

                        if (binding.emailEdit.getText().toString() != null && binding.passwordEdit.getText().toString() != null) {
                            result=0;
                            Sign_in_responseDTO sign_in_responseDTO = retrofitClient.post_login(binding.emailEdit.getText().toString(), binding.passwordEdit.getText().toString(),token);
                            System.out.println(result);
                            if (sign_in_responseDTO.getResult() == 1) {
                                if(isCheckOn) {
                                    SharedPreferences.Editor autoLogin = auto.edit();
                                    autoLogin.putString("user_token", sign_in_responseDTO.getToken());
                                    autoLogin.putString("user_email",binding.emailEdit.getText().toString());
                                    autoLogin.commit();
                                }
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                ClientApp.userEmail = binding.emailEdit.getText().toString();
                                intent.putExtra("email", binding.emailEdit.getText().toString());
                                startActivity(intent);
                                finish();
                                // 토큰 생성하기
                            } else {
                                Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        MyFirebaseMessagingService service = new MyFirebaseMessagingService();
                        service.onNewToken(Objects.requireNonNull(token));
                    });

        });

        binding.findIdPwBtn.setOnClickListener(v -> {
            // 미완성
            // 아이디/비밀번호 찾기
        });

        binding.registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity1.class);
            startActivity(intent);
        });
    }
    private void onCheck() {
        isCheckOn = true;
        binding.checkBtn.setImageResource(R.drawable.ic_check);
    }
    private void offCheck() {
        isCheckOn = false;
        binding.checkBtn.setImageResource(R.drawable.ic_disabled_check);
    }
}