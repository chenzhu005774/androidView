package com.amtzhmt.launcher.login;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amtzhmt.launcher.R;
import com.amtzhmt.launcher.home.HomeActivity;
import com.amtzhmt.launcher.mvp.MVPBaseActivity;
import com.amtzhmt.launcher.util.utils.LogUtils;

import static com.amtzhmt.launcher.util.utils.CheckNet.getMacDefault;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View , View.OnClickListener{
    EditText nameed, pwded;
    Button loginbt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //控制登录用户名图标大小
        nameed = (EditText) findViewById(R.id.name_et);
        pwded = (EditText) findViewById(R.id.pwd_et);
        Drawable drawablename = getResources().getDrawable(R.mipmap.nameicon);
        Drawable drawablepwd = getResources().getDrawable(R.mipmap.pwdicon);
        drawablename .setBounds(0, 0,  20, 20);//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        drawablepwd .setBounds(0, 0,  20, 20);//第一个 0 是距左边距离，第二个 0 是距上边距离，40 分别是长宽
        nameed.setCompoundDrawables(drawablename , null, null, null);//只放左边
        pwded.setCompoundDrawables(drawablepwd , null, null, null);
        loginbt =(Button)findViewById(R.id.loginbt);
        loginbt.setOnClickListener(this);
    }

    @Override
    public void loginsuccess(String result) {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("data",result);
        LogUtils.toast(this,"登录成功");
        startActivity(intent);
    }

    @Override
    public void loginfail() {
        LogUtils.toast(this,"登录失败了...");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbt:
                mPresenter.login(nameed.getText().toString(),pwded.getText().toString(),getMacDefault(LoginActivity.this));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
