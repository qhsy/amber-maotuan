package com.ichsy.hrys.common.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;

/**
 * 两个功能按键
 *
 */
public class SimpleDialogViewMessage extends LinearLayout {
    /**
     *
     */
    private TextView topTV,centerTV,bottomTV;

    public SimpleDialogViewMessage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleDialogViewMessage(Context context) {
        super(context);
        init(context);
    }

    private void  init(Context context){

        View.inflate(context, R.layout.view_simpledialogview_message,this);
        topTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_topitem);
        centerTV = (TextView) findViewById(R.id.tv_viewsimpledialogview_centreitem);
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

    public TextView getBottomTV() {
        return bottomTV;
    }

    public void setBottomTV(TextView bottomTV) {
        this.bottomTV = bottomTV;
    }
}
