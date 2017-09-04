package com.ichsy.hrys.entity.share;

import android.content.ClipboardManager;
import android.content.Context;

import com.ichsy.hrys.AppApplication;

import zz.mk.utilslibrary.ToastUtils;


/**
 * 功能：QQ平台对应的分享实体
 * ＊创建者：赵然 on 16/8/4 14:17
 * ＊
 */
public class CopyPlateForm extends BaseSharePlatform{

    private String data;
    @Override
    String initShowName() {
        return "copyplateshowname";
    }

    @Override
    String initPlateName() {
        return "copyplatename";
    }

    @Override
    String initIconOnName() {
        return "link";
    }

    @Override
    String initIconOffName() {
        return "link";
    }

    @Override
    public void setShareContent(Object o) {
        data = (String) o;
    }

    @Override
   public void share() {
        ClipboardManager manager = (ClipboardManager) AppApplication.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setText(data);
        ToastUtils.showShortToast("复制成功");
    }
}
