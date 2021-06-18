package com.briskjie.cxx;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.briskjie.cxx.adapter.HAdapter;
import com.briskjie.cxx.adapter.VAdapter;
import com.briskjie.cxx.widget.HorizontalRecyclerView;
import com.briskjie.cxx.widget.VerticalRecyclerView;

public class GestureTestActivity extends AppCompatActivity {
    private VerticalRecyclerView mVRecycler;
    private HorizontalRecyclerView mHRecycler;
    private LinearLayoutManager mVLinearLayoutManager;
    private LinearLayoutManager mHLinearLayoutManager;
    private static String TAG = "GestureTestActivity-cxx";

    Handler mHandler = new Handler();

    public static void start(Context context) {
        Intent intent = new Intent(context, GestureTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_test);
        mVRecycler = findViewById(R.id.v_recycler);
        mHRecycler = findViewById(R.id.h_recycler);
        mVLinearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mHLinearLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mVRecycler.setLayoutManager(mVLinearLayoutManager);
        mHRecycler.setLayoutManager(mHLinearLayoutManager);
        VAdapter vAdapter = new VAdapter(this);
        HAdapter hAdapter = new HAdapter(this);
        mVRecycler.setAdapter(vAdapter);
        mHRecycler.setAdapter(hAdapter);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void goToCActivity(View view) {
        CTestActivity.start(this);
    }
}
