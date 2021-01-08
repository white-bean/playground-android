package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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