package com.cjkzy.cjkzy.supermine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by xuncl on 2015/12/20.
 */
public class Welcome extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void welcome_login(View v) {
        Intent intent = new Intent();
        intent.setClass(Welcome.this, Login.class);

        startActivity(intent);
        this.finish();

    }

    public void welcome_register(View v) {
        Intent intent = new Intent();
        if (MainApp.isFirst()){
            intent.setClass(Welcome.this, Whatsnew.class);
        }else{
            intent.setClass(Welcome.this, MainSuperMine.class);
        }

        startActivity(intent);
        this.finish();
    }
}
