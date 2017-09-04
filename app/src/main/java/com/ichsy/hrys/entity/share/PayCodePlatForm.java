package com.ichsy.hrys.entity.share;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.AppApplication;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;

/**
 * 分享平台付款码
 * author: ihesen on 2016/11/7 14:26
 * email: hesen@ichsy.com
 */

public class PayCodePlatForm extends BaseDownloadPlatForm<ArtShareModel> {

    private ArtShareModel artShareModel;

    public PayCodePlatForm(Activity activity) {
        super(activity);
    }

    @Override
    String initShowName() {
        return "paycodeplatformshowname";
    }

    @Override
    String initPlateName() {
        return "paycodeplatname";
    }

    @Override
    String initIconOnName() {
        return "qr_code";
    }

    @Override
    String initIconOffName() {
        return "qr_code";
    }

    @Override
    protected void setContentData(ArtShareModel data) {
        this.artShareModel = data;
    }

    @Override
    public void share() {
        if(LoginUtils.isLogin(mActivity)){
            if (!TextUtils.isEmpty(artShareModel.payUrl)) {
                PayCodePlatForm.super.share();
                dialog.setResourceText("正在为您生成专属付款码");
                ImageLoaderUtils.downloadPic(mActivity, artShareModel.payUrl, callback);
            }
            ClipboardManager manager = (ClipboardManager) AppApplication.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(ClipData.newPlainText(null, artShareModel.materialContent));
        } else{
            CenterEventBus.getInstance().postTask(new LoginParams(mActivity, LoginEvent.LOGIN));
        }
    }

    @Override
    protected void onDownloadSuccess() {
        Message msg = Message.obtain();
        DownloadProgress downloadProgress = new DownloadProgress();
        downloadProgress.progress = 100;
        msg.obj = downloadProgress;
        handler.sendMessage(msg);
    }
}
