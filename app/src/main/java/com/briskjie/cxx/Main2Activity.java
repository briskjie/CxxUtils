package com.briskjie.cxx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.briskjie.myaidldemo.IMyAidlInterface;
import com.briskjie.myaidldemo.MyService;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAndBindService();
    }

    /**
     * test aidl
     */
    private IMyAidlInterface myAidlInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "链接Service成功: ");
            try {
                String s = myAidlInterface.getInfor("我是传来的字符串");
                Log.d(TAG, "onServiceConnected: "+s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: 链接失败");
        }
    };

    private void startAndBindService() {
        Intent service = new Intent(Main2Activity.this, MyService.class);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}