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
        bottomTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_bottomitem);
    }


    public TextView getTopTV() {
        return topTV;
    }

    public void setTopTV(TextView topTV) {
        this.topTV = topTV;
    }

    public TextView getCenterTV() {
        return centerTV;
    }

    public void setCenterTV(TextView centerTV) {
        this.centerTV = centerTV;
    }

    public TextView getLastTV() {
        return lastTV;
    }

    public void setLastTV(TextView lastTV) {
        this.lastTV = lastTV;
    }

    public TextView getBottomTV() {
        return bottomTV;
    }

    public void setBottomTV(TextView bottomTV) {
        this.bottomTV = bottomTV;
    }
}