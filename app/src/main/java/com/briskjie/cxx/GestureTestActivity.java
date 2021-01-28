package com.briskjie.cxx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.briskjie.cxx.adapter.HAdapter;
import com.briskjie.cxx.adapter.VAdapter;
import com.briskjie.cxx.widget.HRecyclerView;
import com.briskjie.cxx.widget.VRecyclerView;

public class GestureTestActivity extends AppCompatActivity {

    private VRecyclerView mVRecycler;
    private HRecyclerView mHRecycler;
    private LinearLayoutManager mVLinearLayoutManager;
    private LinearLayoutManager mHLinearLayoutManager;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
