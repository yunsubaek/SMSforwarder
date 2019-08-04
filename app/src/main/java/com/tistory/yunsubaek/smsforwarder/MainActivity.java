package com.tistory.yunsubaek.smsforwarder;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class MainActivity extends Activity {

    BroadcastReceiver myReceiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate()","main start");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        registerReceiver(myReceiver, intentFilter);
        Log.d("onCreate()","브로드캐스트리시버 등록됨");
        Log.d("onCreate()","main end");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        Log.d("onDestory()","브로드캐스트리시버 해제됨");
    }
}
