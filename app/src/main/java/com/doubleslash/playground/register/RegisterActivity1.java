package com.doubleslash.playground.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.doubleslash.playground.R;
import com.doubleslash.playground.databinding.ActivityRegister1Binding;
import com.doubleslash.playground.retrofit.dto.Sign_upDTO;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity1 extends AppCompatActivity {
    ActivityRegister1Binding binding;
    InputMethodManager inputMethodManager;
    private List<String> list;
    private Search_school_Adapter adapter;
    private Context context;
    Sign_upDTO sign_upDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegister1Binding.inflate(getLayoutInflater());
        inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        context=this;
        setContentView(binding.getRoot());
        initUI();
    }

    private void initUI() {
        binding.yearEdit.setVisibility(View.INVISIBLE);
        //다음버튼
        binding.nextBtn.setOnClickListener(v -> {
            sign_upDTO=new Sign_upDTO();
            sign_upDTO.setSchoolname(binding.univEdit.getText().toString());
            sign_upDTO.setSchoolnum(binding.yearEdit.getText().toString());
            Intent intent = new Intent(getApplicationContext(), RegisterActivity2.class);
            intent.putExtra("sign_upDTO",sign_upDTO);
            startActivity(intent);
            finish();
        });
        binding.univEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                binding.yearEdit.setVisibility(View.INVISIBLE); //숨기기
            }
        });

        // 두 EditText에 모두 내용이 있을 때만 다음 버튼 활성화
        binding.univEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.yearEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });
        binding.yearEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text1 = s.toString();
                String text2 = binding.univEdit.getText().toString();
                if (text1.length() > 0 && text2.length() > 0) {
                    onNextBtn();
                }
                else {
                    offNextBtn();
                }
            }
        });
        binding.schoollist.setOnItemClickListener(listener);
        binding.univEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER){
                    getschooldata();
                    binding.schoollist.setVisibility(View.VISIBLE); //나오기
                    inputMethodManager.hideSoftInputFromWindow(binding.univEdit.getWindowToken(),0);
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
            binding.univEdit.setText(list.get(position));
            binding.schoollist.setVisibility(View.INVISIBLE);
            binding.linearLayout.bringChildToFront(binding.schoollist);
            binding.yearEdit.setVisibility(View.VISIBLE);
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
                            adapter=new Search_school_Adapter(list,context, binding.univEdit.getText().toString());
                            binding.schoollist.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    ArrayList<String> getXmlData(){
        ArrayList<String> schoolList =new ArrayList();
        String str= binding.univEdit.getText().toString();//EditText에 작성된 Text얻어오기
        String schoolname = URLEncoder.encode(str);
        String queryUrl="https://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=7538d4c9b57fcaf0b4e7389242606c73&svcType=api&svcCode=SCHOOL&contentType=xml&gubun=univ_list&searchSchulNm="+schoolname;
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        //buffer.append("파싱 시작...\n\n");
                        //break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("content")) ;
                        else if(tag.equals("schoolName")) {
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
                        if(tag.equals("content")) ;// 첫번째 검색결과종료..줄바꿈
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