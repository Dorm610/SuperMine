package com.cjkzy.cjkzy.supermine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_VIEW;


/**
 * Created by xuncl on 2015/12/20.
 */
public class Login extends Activity {

    private EditText mUser;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUser = (EditText) findViewById(R.id.login_user_edit);
        mPassword = (EditText) findViewById(R.id.login_pw_edit);
    }

    public void login_main(View v) {
//        if ("123".equals(mUser.getText().toString()) && ("123".equals(mPassword.getText().toString()))) {
            if (("".equals(mUser.getText().toString().trim())) || ("".equals(mPassword.getText().toString().trim()))){
            Intent intent = new Intent();
            intent.setClass(Login.this, LoadingActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                this.finish();
        } else if (("".equals(mUser.getText().toString().trim())) || ("".equals(mPassword.getText().toString().trim()))) {

            showErrorDialog("登陆错误", "用户名或密码不能为空，\n请输入后再登录！");
        } else {
            showErrorDialog("登陆失败", "用户名或密码不正确，\n请检查后重新输入！");
        }
    }

    public void login_back(View v) {     //标题栏 返回按钮
        this.finish();
    }

    public void login_pw(View v) {     //忘记密码按钮
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(ACTION_VIEW, uri);
        startActivity(intent);

    }


    /**
     * 显示登录失败的对话框
     *
     * @param title
     * @param message
     */
    private void showErrorDialog(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            new AlertDialog.Builder(Login.this)
                    .setIcon(getResources().getDrawable(R.drawable.login_error_icon, getTheme()))
                    .setTitle(title)
                    .setMessage(message)
                    .create().show();
        } else {
            new AlertDialog.Builder(Login.this)
                    .setIcon(getResources().getDrawable(R.drawable.login_error_icon))
                    .setTitle(title)
                    .setMessage(message)
                    .create().show();
        }
    }
}
