package com.amtzhmt.launcher.util.utils.toolview;


import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amtzhmt.launcher.R;
import com.amtzhmt.launcher.util.utils.commonbean.CommonBean;
import com.amtzhmt.launcher.util.utils.Constant;


/**
 * Created by Administrator on 2019/4/3.
 * 一般的图片焦点控件
 * 布局中包含一个图片
 * 焦点在view布局上
 */
final public class TextViewTool {
    public void creatView(TextViewToolBean textViewToolBean,CommonBean commonBean ){
        RelativeLayout rootlayout = new RelativeLayout(commonBean.getContext());
        rootlayout.setOnFocusChangeListener(commonBean.getFocusChangeListener());
        rootlayout.setFocusable(textViewToolBean.focus);
        rootlayout.requestFocus();
        if (textViewToolBean.focus) {
            rootlayout.setBackgroundResource(R.drawable.bgseletor);
        }

        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if (textViewToolBean.focus) {
            //有背景框的话 就减去背景框的长度
            params.setMargins(textViewToolBean.getMarleft()- Constant.margin,textViewToolBean.getMartop()-Constant.margin,0,0);
        }else{
            params.setMargins(textViewToolBean.getMarleft() ,textViewToolBean.getMartop() ,0,0);
        }

        rootlayout.setLayoutParams(params);
        rootlayout.setTag(commonBean.getTag());
        rootlayout.setOnClickListener(commonBean.getOnClickListener());

        RelativeLayout.LayoutParams imgparams =  new RelativeLayout.LayoutParams(textViewToolBean.getWidth(),textViewToolBean.getHeigh());
        imgparams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);//居中显示
        TextView textView = new TextView(commonBean.getContext());
        textView.setWidth(textViewToolBean.getWidth());
        textView.setHeight(textViewToolBean.getHeigh());
        textView.setGravity(Gravity.CENTER);
        textView.setText(textViewToolBean.getText());
        textView.setTextSize(textViewToolBean.getTextsize());
        textView.setTextColor(commonBean.getContext().getResources().getColor(R.color.whithe));
        rootlayout.addView(textView);
        commonBean.getLayout().addView(rootlayout);

    }

}
