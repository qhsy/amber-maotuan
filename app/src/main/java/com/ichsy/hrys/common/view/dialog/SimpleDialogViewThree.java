package com.ichsy.hrys.common.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;

/**
 * 评论回复
 * author: zhu on 2017/9/8 10:14
 * email: mackkilled@gmail.com
 */

public class SimpleDialogViewThree extends LinearLayout {
    /**
     *
     */
    private TextView topTV,centerTV,lastTV,bottomTV;
    private View devide1, devide2;

    public SimpleDialogViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleDialogViewThree(Context context) {
        super(context);
        init(context);
    }

    private void  init(Context context){
        View.inflate(context, R.layout.view_simpledialogview_three,this);

        topTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_topitem);
        centerTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_centreitem);
        lastTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_lastitem);
        devide1 = findViewById(R.id.divide_1);
        devide2 = findViewById(R.id.divide_2);
        bottomTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_bottomitem);
    }


    public TextView getTopTV() {
        return topTV;
    }

    public TextView getCenterTV() {
        return centerTV;
    }

    public TextView getLastTV() {
        return lastTV;
    }

    public TextView getBottomTV() {
        return bottomTV;
    }

    public View getDevide1() {
        return devide1;
    }

    public View getDevide2() {
        return devide2;
    }
}