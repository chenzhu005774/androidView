package com.amtzhmt.launcher.main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import com.amtzhmt.launcher.R;
import com.amtzhmt.launcher.mvp.MVPBaseActivity;
import com.amtzhmt.launcher.util.utils.LogUtils;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainActivity extends MVPBaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    ImageView icon;
    SeekBar seek;
    RelativeLayout iconview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon = (ImageView)findViewById(R.id.icon);
        seek = (SeekBar)findViewById(R.id.seek);
        iconview= (RelativeLayout)findViewById(R.id.iconview);
        mPresenter.initScreen(icon,seek);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始逻辑判断检查网络登录获取本地存贮的账号信息等一切 逻辑判断
        mPresenter.gotoNext();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.leaveScreen(icon);
    }

    @Override
    public void loginSuccess(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void loginFail(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void getTokenSuccess(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void getTokenFail(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void getpageSuccess(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void getpageFail(String result) {
        LogUtils.toast(this,result);
    }

    @Override
    public void gotoNextActivity(String data, Class<? extends Activity> cls) {
        Intent intent = new Intent();
        if (null!=data) {
            intent.putExtra("data", data);
        }
        intent.setClass(this,cls);
        startActivity(intent);
    }
}
