package com.briskjie.cxx;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.briskjie.cxx.adapter.HAdapter;
import com.briskjie.cxx.adapter.VAdapter;
import com.briskjie.cxx.widget.HorizontalRecyclerView;
import com.briskjie.cxx.widget.VerticalRecyclerView;

public class CTestActivity extends AppCompatActivity {
    private VerticalRecyclerView mVRecycler;
    private HorizontalRecyclerView mHRecycler;
    private LinearLayoutManager mVLinearLayoutManager;
    private LinearLayoutManager mHLinearLayoutManager;
    private static String TAG = "GestureTestActivity-cxx";

    Handler mHandler = new Handler();

    public static void start(Context context) {
        Intent intent = new Intent(context, CTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_test);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void goToAActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void showText(View view) {
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show();
    }
}
