package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister4Binding;

public class RegisterActivity4 extends AppCompatActivity {
    private ActivityRegister4Binding binding;

    private boolean isMan, isWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        isMan = false;
        isWoman = false;

        // 남, 여 고르기 버튼 활성화, 비활성화
        binding.manBtn.setOnClickListener(v -> {
            if (isWoman) {
                binding.womanBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                binding.womanBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                isWoman = false;
            }
            binding.manBtn.setBackgroundResource(R.drawable.ic_sub_black_lined_button);
            binding.manBtn.setTextColor(Color.parseColor("#33353d"));
            isMan = true;

            String text1 = binding.nicknameEdit.getText().toString();
            String text2 = binding.birthYearEdit.getText().toString();
            String text3 = binding.searchEdit.getText().toString();
            if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0) {
                onNextBtn();
            }
        });
        binding.womanBtn.setOnClickListener(v -> {
            if (isMan) {
                binding.manBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                binding.manBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                isMan = false;
            }
            binding.womanBtn.setBackgroundResource(R.drawable.ic_sub_black_lined_button);
            binding.womanBtn.setTextColor(Color.parseColor("#33353d"));
            isWoman = true;

            String text1 = binding.nicknameEdit.getText().toString();
            String text2 = binding.birthYearEdit.getText().toString();
            String text3 = binding.searchEdit.getText().toString();
            if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0) {
                onNextBtn();
            }
        });

        // 모든 EditText에 내용이 있고 남/여 체크가 되어있을 때만 다음 버튼 활성화
        binding.nicknameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.birthYearEdit.getText().toString();
                String text3 = binding.searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        binding.birthYearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.nicknameEdit.getText().toString();
                String text3 = binding.searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        binding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.nicknameEdit.getText().toString();
                String text3 = binding.birthYearEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        binding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity5.class);
            startActivity(intent);
            finish();
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