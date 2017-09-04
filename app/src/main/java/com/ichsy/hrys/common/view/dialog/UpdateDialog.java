package com.ichsy.hrys.common.view.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ichsy.hrys.R;


/**
 * 常用的dialog
 *
 * @author 赵然
 */
public class UpdateDialog extends AlertDialog {
    /**
     * 弹框的 标题  内容
     */
    private TextView content;
    private Button leftButton, rightButton;

    public UpdateDialog(Context context, CommonDialogViewType viewType) {
        super(context, R.style.loginDialog);
        init(viewType);
    }

    /**
     * 初始化
     */
    private void init(CommonDialogViewType viewTypeType) {
        show();
        setContentView(R.layout.view_updatedialogview);
        content = (TextView) findViewById(R.id.tv_updatedialogview_content);
        leftButton = (Button) findViewById(R.id.btn_updatedialogview_leftbtn);
        rightButton = (Button) findViewById(R.id.btn_updatedialogview_rightbtn);

        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setViewType(viewTypeType);
    }

    /**
     * 设置内容的文字
     *
     * @param contentText
     * @return
     */
    public UpdateDialog setContent(String contentText) {
        content.setText(contentText);
        return this;
    }

    /**
     * 设置类型
     *
     * @param viewType
     * @return
     */
    public UpdateDialog setViewType(CommonDialogViewType viewType) {
        if (viewType == CommonDialogViewType.ONE) {
            //单按钮
            leftButton.setVisibility(View.GONE);
            rightButton.setVisibility(View.VISIBLE);
        }
        return this;

    }

    /**
     * 获取双按钮模式下得 左边的按钮
     *
     * @return
     */
    public TextView getLeftButton() {
        return leftButton;
    }

    /**
     * 获取双按钮模式下得 右边的按钮
     *
     * @return
     */
    public TextView getRightButton() {
        return rightButton;
    }

    public enum CommonDialogViewType {
        //单按钮
        ONE
        //双按钮
        , TWO
    }
}
