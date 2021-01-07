package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister1Binding;

public class RegisterActivity1 extends AppCompatActivity {
    ActivityRegister1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        binding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity2.class);
            startActivity(intent);
            finish();
        });

        // 두 EditText에 모두 내용이 있을 때만 다음 버튼 활성화
        binding.univEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.yearEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        binding.yearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.univEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });
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