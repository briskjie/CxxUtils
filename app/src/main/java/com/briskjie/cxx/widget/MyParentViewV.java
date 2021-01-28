package com.briskjie.cxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyParentViewV extends FrameLayout {
    private String TAG = "cxx-竖滑父布局";
    public MyParentViewV(@NonNull Context context) {
        super(context);
    }

    public MyParentViewV(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyParentViewV(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: action down:");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: action move:");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: action up:");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean intercept = super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: action down:"+intercept);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: action move:"+intercept);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: action up:"+intercept);
                break;
        }
        return intercept;
    }
}
