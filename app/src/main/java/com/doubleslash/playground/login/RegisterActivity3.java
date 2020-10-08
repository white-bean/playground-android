package com.doubleslash.playground.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doubleslash.playground.R;

public class RegisterActivity3 extends AppCompatActivity {
    private EditText emailEdit;
    private Button requestNumberBtn;
    private RelativeLayout numberLayout;
    private EditText numberEdit;
    private TextView okBtn;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        initUI();
    }

    private void initUI() {
        emailEdit = findViewById(R.id.email_edit);
        requestNumberBtn = findViewById(R.id.request_number_btn);
        numberLayout = findViewById(R.id.number_layout);
        numberEdit = findViewById(R.id.number_edit);
        okBtn = findViewById(R.id.ok_btn);
        nextBtn = findViewById(R.id.next_btn);

        requestNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberLayout.setVisibility(View.VISIBLE);
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nextBtn.setBackgroundResource(R.drawable.ic_button);
                nextBtn.setTextColor(getResources().getColor(R.color.white));
                nextBtn.setClickable(true);
            }
        });
    }
}