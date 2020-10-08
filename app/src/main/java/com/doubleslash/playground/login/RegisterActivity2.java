package com.doubleslash.playground.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
                    OnCheck1();
                    OnCheck2();
                    OnCheck3();
                    OnCheck4();
                    OnNextBtn();
                } else {
                    OffCheck1();
                    OffCheck2();
                    OffCheck3();
                    OffCheck4();
                    OffNextBtn();
                }
            }
        });
        check1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck1On) {
                    OnCheck1();
                    if (isAllChecked()) OnNextBtn();
                } else {
                    OffCheck1();
                    OffNextBtn();
                }
            }
        });
        check2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck2On) {
                    OnCheck2();
                    if (isAllChecked()) OnNextBtn();
                } else {
                    OffCheck2();
                    OffNextBtn();
                }
            }
        });
        check3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck3On) {
                    OnCheck3();
                    if (isAllChecked()) OnNextBtn();
                } else {
                    OffCheck3();
                    OffNextBtn();
                }
            }
        });
        check4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isCheck4On) {
                    OnCheck4();
                    if (isAllChecked()) OnNextBtn();
                } else {
                    OffCheck4();
                    OffNextBtn();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity3.class);
                startActivity(intent);
            }
        });
    }

    private boolean isAllChecked() {
        if (isCheck1On && isCheck2On && isCheck3On && isCheck4On) {
            return true;
        } else {
            return false;
        }
    }

    private void OnCheck1() {
        isCheck1On = true;
        check1Btn.setImageResource(R.drawable.ic_check2);
    }

    private void OffCheck1() {
        isCheck1On = false;
        check1Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void OnCheck2() {
        isCheck2On = true;
        check2Btn.setImageResource(R.drawable.ic_check2);
    }

    private void OffCheck2() {
        isCheck2On = false;
        check2Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void OnCheck3() {
        isCheck3On = true;
        check3Btn.setImageResource(R.drawable.ic_check2);
    }

    private void OffCheck3() {
        isCheck3On = false;
        check3Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void OnCheck4() {
        isCheck4On = true;
        check4Btn.setImageResource(R.drawable.ic_check2);
    }

    private void OffCheck4() {
        isCheck4On = false;
        check4Btn.setImageResource(R.drawable.ic_disabled_check2);
    }

    private void OnNextBtn() {
        checkAllBtn.setImageResource(R.drawable.ic_check);
        nextBtn.setBackgroundResource(R.drawable.ic_button);
        nextBtn.setTextColor(getResources().getColor(R.color.white));
        nextBtn.setClickable(true);
    }

    private void OffNextBtn() {
        checkAllBtn.setImageResource(R.drawable.ic_disabled_check);
        nextBtn.setBackgroundResource(R.drawable.ic_disabled_button);
        nextBtn.setTextColor(getResources().getColor(R.color.sub_gray));
        nextBtn.setClickable(false);
    }

}