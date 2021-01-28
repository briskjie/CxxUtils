package com.briskjie.cxx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyParentViewH extends RelativeLayout {
    public String TAG = "cxx-横滑父布局";
    public MyParentViewH(@NonNull Context context) {
        super(context);
    }

    public MyParentViewH(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyParentViewH(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float lastX;
    float lastY;

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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getRawX();
        float y = ev.getRawY();
        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                Log.d(TAG, "onInterceptTouchEvent: action down:" + intercept);
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - lastX;
                float dy = y - lastY;
                if (Math.abs(dy) > Math.abs(dx)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                Log.d(TAG, "onInterceptTouchEvent: action move:" + intercept);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: action up:" + intercept);
                break;
        }
        lastX = x;
        lastY = y;
        return intercept;
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
