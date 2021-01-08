package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister3Binding;
import com.doubleslash.playground.retrofit.RetrofitClient;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegisterActivity3 extends AppCompatActivity {
    ActivityRegister3Binding binding;
    private RetrofitClient retrofitClient;

    String user_email;
    String verification;
    String user_password;

    int time, min, sec; // 타이머를 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        retrofitClient = RetrofitClient.getInstance();

        // 이메일 인증
        binding.emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user_email = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.requestNumberBtn.setOnClickListener(v -> {
            if (isValidEmail(user_email)) {
                timerStart();
            } else {
                Toast.makeText(getApplicationContext(), "올바른 이메일 주소가 아닙니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 인증번호 입력
        binding.numberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                verification = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.okBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(verification)) {
                binding.passwordLayout.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "인증번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 비밀번호 입력
        binding.passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user_password = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.passwordOkBtn.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(user_password)){
                onNextBtn();
            } else {
                Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.nextBtn.setOnClickListener(v -> {
            if(binding.emailEdit.getText().toString() != null && binding.passwordEdit.getText().toString() != null){
                System.out.println(binding.emailEdit.getText().toString() + ",,,," + binding.passwordEdit.getText().toString());

                int result= retrofitClient.post_sign_up(binding.emailEdit.getText().toString(), binding.passwordEdit.getText().toString());

                if (result == 1){
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity4.class);
                    startActivity(intent);
                }
            }
        });
    }

    // 인증 번호 요청 후 타이머 구현
    private void timerStart() {
        binding.numberLayout.setVisibility(View.VISIBLE);
        binding.requestNumberBtn.setBackgroundResource(R.drawable.ic_disabled_blue_lined_button);
        binding.requestNumberBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        binding.requestNumberBtn.setEnabled(false);

        final Handler handler = new Handler();
        Runnable addRunnable = () -> {
            time = 120;

            while (time > 0) {
                try {
                    min = time / 60;
                    sec = time % 60;
                    handler.post(() -> {
                        if (sec < 10) {
                            binding.requestNumberBtn.setText(min + ":0" + sec);
                        } else {
                            binding.requestNumberBtn.setText(min + ":" + sec);
                        }
                    });
                    Thread.sleep(1000);
                    time--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(addRunnable);
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        binding.nextBtn.setBackgroundResource(R.drawable.ic_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.white));
        binding.nextBtn.setEnabled(true);
    }

    // 이메일 형식 확인
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}