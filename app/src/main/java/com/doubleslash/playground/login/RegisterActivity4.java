package com.doubleslash.playground.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doubleslash.playground.R;
import com.doubleslash.playground.customwidget.SearchEditText;

public class RegisterActivity4 extends AppCompatActivity {
    private EditText nicknameEdit;
    private EditText birthYearEdit;
    private SearchEditText searchEdit;
    private Button manBtn;
    private Button womanBtn;
    private Button nextBtn;

    private boolean isMan, isWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);

        initUI();
    }

    private void initUI() {
        nicknameEdit = findViewById(R.id.nickname_edit);
        birthYearEdit = findViewById(R.id.birth_year_edit);
        searchEdit = findViewById(R.id.search_edit);
        manBtn = findViewById(R.id.man_btn);
        womanBtn = findViewById(R.id.woman_btn);
        nextBtn = findViewById(R.id.next_btn);

        isMan = false;
        isWoman = false;

        // 남, 여 고르기 버튼 활성화, 비활성화
        manBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWoman) {
                    womanBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    womanBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    isWoman = false;
                }
                manBtn.setBackgroundResource(R.drawable.ic_button);
                manBtn.setTextColor(getResources().getColor(R.color.white));
                isMan = true;

                String text1 = nicknameEdit.getText().toString();
                String text2 = birthYearEdit.getText().toString();
                String text3 = searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setEnabled(true);
                }
            }
        });
        womanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMan) {
                    manBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    manBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    isMan = false;
                }
                womanBtn.setBackgroundResource(R.drawable.ic_button);
                womanBtn.setTextColor(getResources().getColor(R.color.white));
                isWoman = true;

                String text1 = nicknameEdit.getText().toString();
                String text2 = birthYearEdit.getText().toString();
                String text3 = searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setEnabled(true);
                }
            }
        });

        // 모든 EditText에 내용이 있고 남/여 체크가 되어있을 때만 다음 버튼 활성화
        nicknameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = birthYearEdit.getText().toString();
                String text3 = searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setEnabled(true);
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    nextBtn.setEnabled(false);
                }
            }
        });

        birthYearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = nicknameEdit.getText().toString();
                String text3 = searchEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setEnabled(true);
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    nextBtn.setEnabled(false);
                }
            }
        });

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = nicknameEdit.getText().toString();
                String text3 = birthYearEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0 && text3.length() > 0 && (isMan || isWoman)) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setEnabled(true);
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    nextBtn.setEnabled(false);
                }
            }
        });
    }
}