package com.cjkzy.cjkzy.supermine;

/**
 * Created by xuncl on 2015/12/20.
 */

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.WindowManager;

import com.umeng.onlineconfig.OnlineConfigAgent;

public class AppStart extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.appstart);

        // 获取在线参数方法，储存在本地mContext
        OnlineConfigAgent.getInstance().updateOnlineConfig(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                Intent intent;
                    intent = new Intent (AppStart.this,Welcome.class);

                startActivity(intent);
                AppStart.this.finish();
            }
        }, 1000);
    }
}