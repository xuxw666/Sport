package com.sport;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.sport.util.LogUtil;
import com.sport.util.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class BeginActivity extends Activity {
    private boolean isFirstRun;
    private Intent intent;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1 :
                    LogUtil.debug(this.getClass(), "first");
                    intent = new Intent();
                    intent.setClass(BeginActivity.this,MainActivity.class);
                    startActivity(intent);
                    BeginActivity.this.finish();
                    break;
                case 2:
                    LogUtil.debug(this.getClass(), "not first");
                    intent = new Intent();
                    intent.setClass(BeginActivity.this, MainActivity.class);
                    startActivity(intent);
                    BeginActivity.this.finish();
                    break;
            }
        }
    };

    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getSharedPreferences(this, "setting");

        //如果key对应的值不存在则赋值true，否则返回key对应的值
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isFirstRun)
                    handler.sendEmptyMessage(1);
                else
                    handler.sendEmptyMessage(2);
            }
        };
    }

    @Override
    protected void onStart(){
        super.onStart();

        timer.schedule(timerTask,1200);

    }
}
