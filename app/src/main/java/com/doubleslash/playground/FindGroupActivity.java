package com.doubleslash.playground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleslash.playground.GroupList.Group;
import com.doubleslash.playground.GroupList.GroupAdapter;
import com.doubleslash.playground.databinding.ActivityFindGroupBinding;
import com.doubleslash.playground.infoGroup.InfoGroupActivity;
import com.doubleslash.playground.retrofit.RetrofitClient;
import com.doubleslash.playground.retrofit.dto.response.Find_group_responseDTO;
import com.doubleslash.playground.retrofit.dto.response.Total_group_responseDTO;

public class FindGroupActivity extends AppCompatActivity {
    ActivityFindGroupBinding binding;
    private RetrofitClient retrofitClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindGroupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        retrofitClient = RetrofitClient.getInstance();

        initUI();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {      // edittext 포커싱 문제 해결 : view 밖을 터치하면 포커싱 해제 및 키보드 hide
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    if(binding.searchEdit.getText().toString().length()>0){
                        binding.searchEdit.setBackgroundResource(R.drawable.r20_stroke_gray);
                    }
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    private void initUI(){
        binding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.searchEdit.setOnEditorActionListener((textView, actionId, event) -> {
            // 텍스트 내용을 가져온다.
            String searchData = textView.getText().toString();
            // 텍스트 내용이 비어있다면...
            if (searchData.isEmpty()) {
                textView.clearFocus();
                textView.setFocusable(false);
                textView.setFocusableInTouchMode(true);
                textView.setFocusable(true);
                return true;
            }
            // 텍스트 내용이 비어있지않다면
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    //키보드의 돋보기 버튼 눌렀을경우
                    //여기에다가 searchData(입력한 데이터)로 찾으면 됨
                    showitems(searchData);
                    Toast.makeText(this, searchData, Toast.LENGTH_LONG).show();
                    break;
                default:
                    return false;
            }
            return true;
        });

    }
    private void showitems(String searchData){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GroupAdapter adapter = new GroupAdapter(getApplicationContext());

        Find_group_responseDTO body = retrofitClient.get_findgrouplist(searchData);

        for (int i = 0; i < body.getData().size(); i++) {
            adapter.addItem(new Group(
                    body.getData().get(i).getLocation(),
                    body.getData().get(i).getCategory(),
                    body.getData().get(i).getCurrentMemberCount(),
                    body.getData().get(i).getMaxMemberCount(),
                    body.getData().get(i).getName(),
                    body.getData().get(i).getContent(),
                    body.getData().get(i).getImageUri()));
        }

        if (adapter.getItemCount() > 0) {
            binding.layoutCaution.setVisibility(View.GONE);
        } else {
            binding.layoutCaution.setVisibility(View.VISIBLE);
        }

        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((holder, view, position) -> {
            Intent intent = new Intent(this, InfoGroupActivity.class);
            intent.putExtra("teamId", body.getData().get(position).getTeamId());

            startActivity(intent);
        });
    }
}