package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.doubleslash.playground.R;

public class RegisterActivity6 extends AppCompatActivity {
    private Button studyBtn;
    private Button dietBtn;
    private Button culturalBtn;
    private Button gameBtn;
    private Button nextBtn;

    private boolean isStudyOn, isDietOn, isCulturalOn, isGameOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register6);

        initUI();
    }

    private void initUI() {
        isStudyOn = false;
        isDietOn = false;
        isCulturalOn = false;
        isGameOn = false;

        studyBtn = findViewById(R.id.study_btn);
        dietBtn = findViewById(R.id.diet_btn);
        culturalBtn = findViewById(R.id.cultural_btn);
        gameBtn = findViewById(R.id.game_btn);
        nextBtn = findViewById(R.id.next_btn);

        studyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isStudyOn) {
                    studyBtn.setBackgroundResource(R.drawable.ic_button);
                    studyBtn.setTextColor(getResources().getColor(R.color.white));
                    isStudyOn = true;
                } else {
                    studyBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                    studyBtn.setTextColor(getResources().getColor(R.color.sub_black));
                    isStudyOn = false;
                }
                if (anyChecked()) onNextBtn();
                else offNextBtn();
            }
        });
        dietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDietOn) {
                    dietBtn.setBackgroundResource(R.drawable.ic_button);
                    dietBtn.setTextColor(getResources().getColor(R.color.white));
                    isDietOn = true;
                } else {
                    dietBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                    dietBtn.setTextColor(getResources().getColor(R.color.sub_black));
                    isDietOn = false;
                }
                if (anyChecked()) onNextBtn();
                else offNextBtn();
            }
        });
        culturalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCulturalOn) {
                    culturalBtn.setBackgroundResource(R.drawable.ic_button);
                    culturalBtn.setTextColor(getResources().getColor(R.color.white));
                    isCulturalOn = true;
                } else {
                    culturalBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                    culturalBtn.setTextColor(getResources().getColor(R.color.sub_black));
                    isCulturalOn = false;
                }
                if (anyChecked()) onNextBtn();
                else offNextBtn();
            }
        });
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGameOn) {
                    gameBtn.setBackgroundResource(R.drawable.ic_button);
                    gameBtn.setTextColor(getResources().getColor(R.color.white));
                    isGameOn = true;
                } else {
                    gameBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                    gameBtn.setTextColor(getResources().getColor(R.color.sub_black));
                    isGameOn = false;
                }
                if (anyChecked()) onNextBtn();
                else offNextBtn();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity7.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean anyChecked() {
        return isStudyOn || isDietOn || isCulturalOn || isGameOn;
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        nextBtn.setBackgroundResource(R.drawable.ic_button);
        nextBtn.setTextColor(getResources().getColor(R.color.white));
        nextBtn.setEnabled(true);
    }

    // 다음 버튼 비활성화
    private void offNextBtn() {
        nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        nextBtn.setEnabled(false);
    }
}