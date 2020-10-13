package com.doubleslash.playground.customwidget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.doubleslash.playground.R;

public class SearchbarEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {

    private Drawable clearDrawable;
    private Drawable searchDrawable;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;
    private EditText search_edit;

    public SearchbarEditText(final Context context) {
        super(context);
        init();
    }

    public SearchbarEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchbarEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public void setOnEditorActionListener(OnEditorActionListener l) {
        super.setOnEditorActionListener(l);
    }

    private void init() {
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_closed);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_search);
        searchDrawable = DrawableCompat.wrap(tempDrawable);
        searchDrawable.setBounds(0, 0, searchDrawable.getIntrinsicWidth(), searchDrawable.getIntrinsicHeight());

        search_edit = findViewById(R.id.search_edit);
        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener(){    //키보드에서의 edittext 완료버튼 처리
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
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
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });

        setClearIconVisible(false);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);

        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
            search_edit.setBackgroundResource(R.drawable.r20);
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }

    @Override
    public boolean onTouch(final View view, final MotionEvent event) {
        final int x = (int) event.getX();
        Rect outRect = new Rect();
        view.getGlobalVisibleRect(outRect);

        if (clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {  //X버튼 눌렸을 때
            if (event.getAction() == event.ACTION_UP) {
                setError(null);
                setText(null);
            }
            return true;
        }
        else if (searchDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()){
            if (event.getAction() == event.ACTION_UP) {
                //돋보기버튼 눌렸을 때
                Log.d("OnTouch", "돋보기");
            }
            return true;
        }
        else if (outRect.contains((int)event.getRawX(), (int)event.getRawY())){ // 다시 edittext 눌렀을 때 background 레이아웃 변경
            search_edit.setBackgroundResource(R.drawable.r20);
        }

        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, event);
        } else {
            return false;
        }
    }

    private void setClearIconVisible(boolean visible) {
        clearDrawable.setVisible(visible, false);
        if (visible) {
            setCompoundDrawables(null, null, clearDrawable, null);
        } else {
            setCompoundDrawables(null, null, searchDrawable, null);
        }
    }
}
