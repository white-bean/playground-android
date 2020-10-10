package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            }
        });
        check1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck1On) {
                    onCheck1();
                    if (isAllChecked()) onNextBtn();
                } else {
                    offCheck1();
                    offNextBtn();
                }
            }
        });
        check2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck2On) {
                    onCheck2();
                    if (isAllChecked()) onNextBtn();
                } else {
                    offCheck2();
                    offNextBtn();
                }
            }
        });
        check3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck3On) {
                    onCheck3();
                    if (isAllChecked()) onNextBtn();
                } else {
                    offCheck3();
                    offNextBtn();
                }
            }
        });
        check4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck4On) {
                    onCheck4();
                    if (isAllChecked()) onNextBtn();
                } else {
                    offCheck4();
                    offNextBtn();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isAllChecked() {
        return isCheck1On && isCheck2On && isCheck3On && isCheck4On;
    }

    // 체크 활성화
    private void onCheck1() {
        isCheck1On = true;
        check1Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck2() {
        isCheck2On = true;
        check2Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck3() {
        isCheck3On = true;
        check3Btn.setImageResource(R.drawable.ic_check2);
    }

    private void onCheck4() {
        isCheck4On = true;
        check4Btn.setImageResource(R.drawable.ic_check2);
    }

    // 체크 비활성화
    private void offCheck1() {
        isCheck1On = false;
        check1Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck2() {
        isCheck2On = false;
        check2Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck3() {
        isCheck3On = false;
        check3Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void offCheck4() {
        isCheck4On = false;
        check4Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    // 다음 버튼 활성화
    private void onNextBtn() {
        checkAllBtn.setImageResource(R.drawable.ic_check);
        nextBtn.setBackgroundResource(R.drawable.ic_button);
        nextBtn.setTextColor(getResources().getColor(R.color.white));
        nextBtn.setEnabled(true);
    }

    // 다음 버튼 비활성화
    private void offNextBtn() {
        checkAllBtn.setImageResource(R.drawable.ic_disabled_check);
        nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        nextBtn.setEnabled(false);
    }

}