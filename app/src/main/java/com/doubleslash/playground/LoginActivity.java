package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.doubleslash.playground.databinding.ActivityLoginBinding;
import com.doubleslash.playground.register.RegisterActivity1;
import com.doubleslash.playground.retrofit.RetrofitClient;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private RetrofitClient retrofitClient;

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
        retrofitClient = RetrofitClient.getInstance();

        binding.loginBtn.setOnClickListener(v -> {
            int result=0;
            if (binding.emailEdit.getText().toString() != null && binding.passwordEdit.getText().toString() != null) {
                result = retrofitClient.post_login(binding.emailEdit.getText().toString(), binding.passwordEdit.getText().toString());
                System.out.println(result);
                if (result == 1) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("email", binding.emailEdit.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "아이디와 패스워드를 다시 한 번 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
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
}