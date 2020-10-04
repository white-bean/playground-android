package com.doubleslash.playground.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.doubleslash.playground.R;

public class RegiserActivity1 extends AppCompatActivity {
    EditText univEdit;
    EditText yearEdit;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser1);

        initUI();
    }

    private void initUI() {
        univEdit = findViewById(R.id.univ_edit);
        yearEdit = findViewById(R.id.year_edit);
        nextBtn = findViewById(R.id.next_btn);

        univEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = yearEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setClickable(true);
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    nextBtn.setClickable(false);
                }
            }
        });

        yearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = univEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    nextBtn.setBackgroundResource(R.drawable.ic_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.white));
                    nextBtn.setClickable(true);
                }
                else {
                    nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
                    nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
                    nextBtn.setClickable(false);
                }
            }
        });
    }
}