package com.doubleslash.playground.customwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.doubleslash.playground.R;

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {

    private Drawable clearDrawable;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;

    public ClearEditText(final Context context) {
        super(context);
        init();
    }

    public ClearEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
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

    private void init() {
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_closed);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        setClearIconVisible(false);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
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
        if (clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {
            if (event.getAction() == event.ACTION_UP) {
                setError(null);
                setText(null);
            }
            return true;
        }

        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, event);
        } else {
            return false;
        }
    }

    private void setClearIconVisible(boolean visible) {
        clearDrawable.setVisible(visible, false);
        setCompoundDrawables(null, null, visible ? clearDrawable : null, null);
    }
}
