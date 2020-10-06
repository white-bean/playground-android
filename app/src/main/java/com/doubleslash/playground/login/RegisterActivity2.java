package com.doubleslash.playground.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doubleslash.playground.R;

public class RegisterActivity2 extends AppCompatActivity {
    private ImageButton checkAllBtn;

    private ImageButton check1Btn;
    private ImageButton check2Btn;
    private ImageButton check3Btn;
    private ImageButton check4Btn;

    private TextView view1Btn;
    private TextView view2Btn;
    private TextView view3Btn;
    private TextView view4Btn;

    private boolean isCheck1On, isCheck2On, isCheck3On, isCheck4On;

    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        initUI();
    }

    private void initUI() {
        isCheck1On = false;
        isCheck2On = false;
        isCheck3On = false;
        isCheck4On = false;

        checkAllBtn = findViewById(R.id.check_all_btn);
        check1Btn = findViewById(R.id.check1_btn);
        check2Btn = findViewById(R.id.check2_btn);
        check3Btn = findViewById(R.id.check3_btn);
        check4Btn = findViewById(R.id.check4_btn);

        view1Btn = findViewById(R.id.view1_btn);
        view2Btn = findViewById(R.id.view2_btn);
        view3Btn = findViewById(R.id.view3_btn);
        view4Btn = findViewById(R.id.view4_btn);

        nextBtn = findViewById(R.id.next_btn);

        checkAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1Btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check2));
                check2Btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check2));
                check3Btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check2));
                check4Btn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check2));
                checkAllBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_check));

                nextBtn.setBackgroundResource(R.drawable.ic_button);
                nextBtn.setTextColor(getResources().getColor(R.color.white));
                nextBtn.setClickable(true);
            }
        });
    }
}