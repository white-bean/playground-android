package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.doubleslash.playground.Retrofit_pakage.My_Retrofit;
import com.doubleslash.playground.databinding.ActivityLoginBinding;
import com.doubleslash.playground.register.RegisterActivity1;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        final My_Retrofit my_retrofit=new My_Retrofit();

        binding.loginBtn.setOnClickListener(v -> {
            int result=0;

            if(binding.emailEdit.getText().toString() != null && binding.passwordEdit.getText().toString() != null) {
                    result = my_retrofit.post_login(binding.emailEdit.getText().toString(), binding.passwordEdit.getText().toString());
                    result = my_retrofit.result1;

                    while(result==-1) {result = my_retrofit.result1;}
                    System.out.println(result);

                    if (result == 1) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("email", binding.emailEdit.getText().toString());
                        startActivity(intent);
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