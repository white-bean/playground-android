package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister4Binding;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity4 extends AppCompatActivity {
    private ActivityRegister4Binding binding;
    Sign_upDTO sign_upDTO;
    InputMethodManager inputMethodManager;
    private boolean isMan, isWoman, isregion;
    private List<String> list;
    private Search_school_Adapter adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister4Binding.inflate(getLayoutInflater());
        inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        setContentView(binding.getRoot());
        context=this;
        //getschooldata();
        sign_upDTO=(Sign_upDTO)getIntent().getSerializableExtra("sign_upDTO");
        initUI();
    }

    private void initUI() {
        isMan = false;
        isWoman = false;
        isregion=false;
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
            if (text1.length() > 0 && text2.length() ==4 && isregion) {
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
            if (text1.length() > 0 && text2.length() ==4 && isregion) {
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
                if (text1.length() > 0 && text2.length() ==4 && isregion && (isMan || isWoman)) {
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
                if (text1.length() ==4 && text2.length() > 0 && isregion && (isMan || isWoman)) {
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
                isregion=false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text2 = binding.nicknameEdit.getText().toString();
                String text3 = binding.birthYearEdit.getText().toString();
                if (isregion && text2.length() > 0 && text3.length() ==4 && (isMan || isWoman)) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });

        binding.nextBtn.setOnClickListener(v -> {
            if(isMan) sign_upDTO.setSex("M");
            else sign_upDTO.setSex("F");
            sign_upDTO.setName(binding.nicknameEdit.getText().toString());
            sign_upDTO.setAge(binding.birthYearEdit.getText().toString());
            sign_upDTO.setRegion(binding.searchEdit.getText().toString());
            Intent intent = new Intent(getApplicationContext(), RegisterActivity5.class);
            intent.putExtra("sign_upDTO",sign_upDTO);
            startActivity(intent);
            finish();
        });

        binding.schoollist.setOnItemClickListener(listener);
        binding.searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER){
                    getschooldata();
                    binding.schoollist.setVisibility(View.VISIBLE); //나오기
                    //inputMethodManager.hideSoftInputFromWindow(binding.searchEdit.getWindowToken(),0);
                    return true;
                }
                return false;
            }
        });
    }
    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println(list.get(position));
            binding.searchEdit.setText(list.get(position));
            isregion=true;
            list.clear();
            adapter.notifyDataSetChanged();
            binding.schoollist.setVisibility(View.INVISIBLE);
            String text2 = binding.nicknameEdit.getText().toString();
            String text1 = binding.birthYearEdit.getText().toString();
            if (text1.length() ==4 && text2.length() > 0 && isregion && (isMan || isWoman)) {
                onNextBtn();
            }
            else {
                offNextBtn();
            }
        }
    };
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


    private void getschooldata(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                list=getXmlData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (list!=null)
                            adapter=new Search_school_Adapter(list,context,binding.searchEdit.getText().toString());
                        binding.schoollist.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    ArrayList<String> getXmlData(){
        ArrayList<String> schoolList =new ArrayList();
        String str= binding.searchEdit.getText().toString();//EditText에 작성된 Text얻어오기
        String regionname = URLEncoder.encode(str);
        System.out.println("지낙마111111111111111111111111111");
        String queryUrl="http://api.vworld.kr/req/data?service=data&request=GetFeature&data=LT_C_ADSIGG_INFO&key=E0DD4DBC-0E1A-3136-BDF8-3D98B8E8A211&domain=http://api.vworld.kr/req/data&format=xml&[&attrFilter=sig_kor_nm:like:"+regionname+"&]";
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기
            System.out.println("지낙마22");
            String tag;
            System.out.println(xpp);
            xpp.next();
            System.out.println("지낙마33");
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                System.out.println(xpp.getName());
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        //buffer.append("파싱 시작...\n\n");
                        //break;
                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("result")) ;
                        else if(tag.equals("full_nm")) {
                            xpp.next();
                            schoolList.add(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            System.out.println(xpp.getText());
                            //list.append("\n");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기
                        if(tag.equals("result")) ;// 첫번째 검색결과종료..줄바꿈
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return schoolList;

    }
}