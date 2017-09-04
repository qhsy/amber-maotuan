package com.ichsy.hrys.common.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;

/**
 * 功能：dialog中简单的选择布局  如:性别选择
 * ＊创建者：赵然 on 16/5/15 21:09
 * ＊
 */
public class SimpleDialogView extends LinearLayout {
    /**
     *
     */
    private TextView topTV,centerTV,bottomTV;

    public SimpleDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleDialogView(Context context) {
        super(context);
        init(context);
    }

    private void  init(Context context){

        View.inflate(context, R.layout.view_simpledialogview,this);
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
