package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister6Binding;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

import java.util.ArrayList;

public class RegisterActivity6 extends AppCompatActivity {
    ActivityRegister6Binding binding;
    Uri[] uri=new Uri[3];
    Sign_upDTO sign_upDTO;
    private boolean isStudyOn, isDietOn, isCulturalOn, isGameOn;
    ArrayList<Uri> urilist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},1);
        sign_upDTO=(Sign_upDTO)getIntent().getSerializableExtra("sign_upDTO");
        urilist=getIntent().getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        initUI();
    }

    private void initUI() {
        isStudyOn = false;
        isDietOn = false;
        isCulturalOn = false;
        isGameOn = false;

        binding.studyBtn.setOnClickListener(v -> {
            if (!isStudyOn) {
                binding.studyBtn.setBackgroundResource(R.drawable.ic_button_study);
                binding.tvStudy1.setTextColor(getResources().getColor(R.color.white));
                binding.tvStudy2.setTextColor(getResources().getColor(R.color.white));
                isStudyOn = true;
            } else {
                binding.studyBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                binding.tvStudy1.setTextColor(Color.parseColor("#3b3e4a"));
                binding.tvStudy2.setTextColor(Color.parseColor("#3b3e4a"));
                isStudyOn = false;
            }
            if (anyChecked()) onNextBtn();
            else offNextBtn();
        });

        binding.dietBtn.setOnClickListener(v -> {
            if (!isDietOn) {
                binding.dietBtn.setBackgroundResource(R.drawable.ic_button_diet);
                binding.tvDiet1.setTextColor(getResources().getColor(R.color.white));
                binding.tvDiet2.setTextColor(getResources().getColor(R.color.white));
                isDietOn = true;
            } else {
                binding.dietBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                binding.tvDiet1.setTextColor(Color.parseColor("#3b3e4a"));
                binding.tvDiet2.setTextColor(Color.parseColor("#3b3e4a"));
                isDietOn = false;
            }
            if (anyChecked()) onNextBtn();
            else offNextBtn();
        });

        binding.culturalBtn.setOnClickListener(v -> {
            if (!isCulturalOn) {
                binding.culturalBtn.setBackgroundResource(R.drawable.ic_button_cultural);
                binding.tvCultural1.setTextColor(getResources().getColor(R.color.white));
                binding.tvCultural2.setTextColor(getResources().getColor(R.color.white));
                isCulturalOn = true;
            } else {
                binding.culturalBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                binding.tvCultural1.setTextColor(Color.parseColor("#3b3e4a"));
                binding.tvCultural2.setTextColor(Color.parseColor("#3b3e4a"));
                isCulturalOn = false;
            }
            if (anyChecked()) onNextBtn();
            else offNextBtn();
        });

        binding.gameBtn.setOnClickListener(v -> {
            if (!isGameOn) {
                binding.gameBtn.setBackgroundResource(R.drawable.ic_button_game);
                binding.tvGame1.setTextColor(getResources().getColor(R.color.white));
                binding.tvGame2.setTextColor(getResources().getColor(R.color.white));
                isGameOn = true;
            } else {
                binding.gameBtn.setBackgroundResource(R.drawable.ic_rounded_rectangle);
                binding.tvGame1.setTextColor(Color.parseColor("#3b3e4a"));
                binding.tvGame2.setTextColor(Color.parseColor("#3b3e4a"));
                isGameOn = false;
            }
            if (anyChecked()) onNextBtn();
            else offNextBtn();
        });

        binding.nextBtn.setOnClickListener(v -> {
            String hobby="";
            if(isStudyOn)  hobby+="스터디,";
            if(isDietOn) hobby += "운동/다이어트,";
            if(isCulturalOn) hobby += "문화생활,";
            if(isGameOn) hobby += "게임";
            sign_upDTO.setHobby(hobby);
            Intent intent = new Intent(getApplicationContext(), RegisterActivity7.class);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,urilist);
            intent.putExtra("sign_upDTO",sign_upDTO);
            startActivity(intent);
            finish();
        });
    }

    private boolean anyChecked() {
        return isStudyOn || isDietOn || isCulturalOn || isGameOn;
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