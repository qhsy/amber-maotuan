package com.ichsy.hrys.model.details.controller;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.ichsy.hrys.common.utils.DialogUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.view.CommentView;
import com.ichsy.hrys.common.view.dialog.SimpleDialogViewThree;

/**
 * 评论弹窗
 */
public class CommentController {

    public CommentView cvCommentLayer;

    /**
     * 获取评论弹窗
     * 规则：自己不能回复自己，自己不能删除别人的
     * @param context
     * @return
     */
    public Dialog getCommentDialog(final Context context, String pUserCode, final CommentCallBack callBack) {
        SimpleDialogViewThree view = new SimpleDialogViewThree(context);
        view.getTopTV().setText("回复");
        view.getCenterTV().setText("复制");
        view.getLastTV().setText("删除");
        view.getBottomTV().setText("取消");

        if (isMyself(context, pUserCode)) {
            view.getLastTV().setVisibility(View.VISIBLE);
            view.getDevide2().setVisibility(View.VISIBLE);
        } else {
            view.getLastTV().setVisibility(View.GONE);
            view.getDevide2().setVisibility(View.GONE);
        }

        final Dialog headerViewDialog = DialogUtils.getBottomDialog(context, view, true);
        view.getTopTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                callBack.onReplyClick();
            }
        });
        view.getCenterTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
                callBack.onCopy();
            }
        });
        view.getLastTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callBack.onDelete();
            }
        });
        view.getBottomTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerViewDialog.dismiss();
            }
        });

        return headerViewDialog;
    }

    private boolean isMyself(Context context, String mUserCode) {
        return SharedPreferencesUtils.getUserInfo(context).getUserCode().equals(mUserCode);
    }

    public interface CommentCallBack {
        /**
         * 回复
         */
        void onReplyClick();

        /**
         * 复制
         */
        void onCopy();

        /**
         * 删除
         */
        void onDelete();
    }
}
