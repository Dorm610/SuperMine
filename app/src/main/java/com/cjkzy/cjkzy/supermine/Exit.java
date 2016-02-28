package com.cjkzy.cjkzy.supermine;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by CLEVO on 2015/12/28.
 */
public class Exit extends Activity {
    //private MyDialog dialog;
    private RelativeLayout layout;
    private LinearLayout dialogLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.exit_dialog);
        //dialog=new MyDialog(this);
        layout = (RelativeLayout) findViewById(R.id.exit_layout);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialogLayout = (LinearLayout) findViewById(R.id.exit_dialog_layout);
        dialogLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void exitButton_1(View v) {
        this.finish();
    }

    public void exitButton_0(View v) {
        this.finish();
        MainSuperMine.instance.finish();//关闭Main 这个Activity
    }

}