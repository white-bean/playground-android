package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister2Binding;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

public class RegisterActivity2 extends AppCompatActivity {
    ActivityRegister2Binding binding;
    String schoolname;
    String schoolnum;
    Sign_upDTO sign_upDTO;
    private boolean isCheck1On, isCheck2On, isCheck3On, isCheck4On;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sign_upDTO=(Sign_upDTO)getIntent().getSerializableExtra("sign_upDTO");
        initUI();
    }

    private void initUI() {
        isCheck1On = false;
        isCheck2On = false;
        isCheck3On = false;
        isCheck4On = false;

        binding.checkAllBtn.setOnClickListener(v -> {
            if (!isAllChecked()) {
                onCheck1();
                onCheck2();
                onCheck3();
                onCheck4();
                onNextBtn();
            } else {
                offCheck1();
                offCheck2();
                offCheck3();
                offCheck4();
                offNextBtn();
            }
        });
        binding.check1Btn.setOnClickListener(v -> {
            if (!isCheck1On) {
                onCheck1();
                if (isAllChecked()) onNextBtn();
            } else {
                offCheck1();
                offNextBtn();
            }
        });
        binding.check2Btn.setOnClickListener(v -> {
            if (!isCheck2On) {
                onCheck2();
                if (isAllChecked()) onNextBtn();
            } else {
                offCheck2();
                offNextBtn();
            }
        });
        binding.check3Btn.setOnClickListener(v -> {
            if (!isCheck3On) {
                onCheck3();
                if (isAllChecked()) onNextBtn();
            } else {
                offCheck3();
                offNextBtn();
            }
        });
        binding.check4Btn.setOnClickListener(v -> {
            if (!isCheck4On) {
                onCheck4();
                if (isAllChecked()) onNextBtn();
            } else {
                offCheck4();
                offNextBtn();
            }
        });

        binding.nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity3.class);
            intent.putExtra("sign_upDTO",sign_upDTO);
            startActivity(intent);
            finish();
        });
    }

    private boolean isAllChecked() {
        return isCheck1On && isCheck2On && isCheck3On && isCheck4On;
    }

    // 체크 활성화
    private void onCheck1() {
        isCheck1On = true;
        binding.check1Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck2() {
        isCheck2On = true;
        binding.check2Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck3() {
        isCheck3On = true;
        binding.check3Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck4() {
        isCheck4On = true;
        binding.check4Btn.setImageResource(R.drawable.ic_check2);
    }

    // 체크 비활성화
    private void offCheck1() {
        isCheck1On = false;
        binding.check1Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck2() {
        isCheck2On = false;
        binding.check2Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck3() {
        isCheck3On = false;
        binding.check3Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck4() {
        isCheck4On = false;
        binding.check4Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        binding.checkAllBtn.setImageResource(R.drawable.ic_check);
        binding.nextBtn.setBackgroundResource(R.drawable.ic_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.white));
        binding.nextBtn.setEnabled(true);
    }

    // 다음 버튼 비활성화
    private void offNextBtn() {
        binding.checkAllBtn.setImageResource(R.drawable.ic_disabled_check);
        binding.nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        binding.nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        binding.nextBtn.setEnabled(false);
    }

}