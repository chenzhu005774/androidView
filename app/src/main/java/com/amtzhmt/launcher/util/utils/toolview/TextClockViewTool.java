package com.amtzhmt.launcher.util.utils.toolview;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextClock;

import com.amtzhmt.launcher.R;
import com.amtzhmt.launcher.util.utils.commonbean.CommonBean;
import com.amtzhmt.launcher.util.utils.Constant;

/**
 * Created by Administrator on 2019/5/17.
 */
public class TextClockViewTool {

    public void creatView(TextClockViewToolBean textClockViewToolBean,CommonBean commonBean ){
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());
        rootlayout.setFocusable(textClockViewToolBean.focus);
        rootlayout.requestFocus();
        if (textClockViewToolBean.focus) {
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (textClockViewToolBean.focus) {
            //有背景框的话 就减去背景框的长度
            params.setMargins(textClockViewToolBean.getMarleft()- Constant.margin,textClockViewToolBean.getMartop()-Constant.margin,0,0);
        }else{
            params.setMargins(textClockViewToolBean.getMarleft() ,textClockViewToolBean.getMartop() ,0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(textClockViewToolBean.getWidth(),textClockViewToolBean.getHeigh());
        imgparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);//居中显示
        TextClock textClock = new TextClock(commonBean.getContext());
        textClock.setGravity(Gravity.CENTER);
//        textClock.setFormat12Hour("yyyy.MM.dd\nEE a hh:mm");
        //a 表示上下午
//        if(textClockViewToolBean.getFormattype()!=null&&!textClockViewToolBean.getFormattype().equals("")) {
//            textClock.setFormat12Hour(textClockViewToolBean.getFormattype());
//        }else {
            textClock.setFormat12Hour("yyyy.MM.dd EE hh:mm");
//        }
        textClock.setGravity(Gravity.CENTER);
        textClock.setTextSize(textClockViewToolBean.getTextsize());
        textClock.setTextColor(commonBean.getContext().getResources().getColor(R.color.whithe));
        rootlayout.addView(textClock);
        commonBean.getLayout().addView(rootlayout);

    }
}
