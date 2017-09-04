package com.ichsy.hrys.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ichsy.hrys.R;


/**
 * 功能：
 * ＊创建者：赵然 on 16/6/6 01:54
 * ＊
 */
public class UpdateProgressDialogView extends LinearLayout {


    private TextView progressTV;
    private ProgressBar pb;
    public UpdateProgressDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.item_updateprogressdialog,this);
        progressTV = (TextView) findViewById(R.id.tv_itemupdateprogress_tv);
        pb = (ProgressBar) findViewById(R.id.pb_itemupdateprogress_pb);

    }

    public void setProgress(int progress){

        progressTV.setText(progress+" %");
        pb.setProgress(progress);
    }
}
