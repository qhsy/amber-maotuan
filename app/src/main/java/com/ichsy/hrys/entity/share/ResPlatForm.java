package com.ichsy.hrys.entity.share;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Message;

import com.ichsy.hrys.AppApplication;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.entity.ArtShareModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享平台素材下载
 * author: ihesen on 2016/11/3 15:09
 * email: hesen@ichsy.com
 */

public class ResPlatForm extends BaseDownloadPlatForm<ArtShareModel> {

    private List<String> sharePics = new ArrayList<>();
    private int picCount;
    private ArtShareModel artShareModel;

    public ResPlatForm(Activity activity) {
        super(activity);
    }

    @Override
    String initShowName() {
        return "resplateshowname";
    }

    @Override
    String initPlateName() {
        return "resplatename";
    }

    @Override
    String initIconOnName() {
        return "download";
    }

    @Override
    String initIconOffName() {
        return "download";
    }

    @Override
    protected void setContentData(ArtShareModel data) {
        this.artShareModel = data;
        sharePics.addAll(data.picMaterialList);
    }

    @Override
    public void share() {
        if(sharePics != null && sharePics.size() > 0){
            picCount = sharePics.size();
            super.share();
            dialog.setResourceText(String.format(mActivity.getResources().getString(R.string.resourcetext), "0/" + picCount));
            ImageLoaderUtils.downloadPic(mActivity, sharePics.get(0), callback);
        }
        ClipboardManager manager = (ClipboardManager) AppApplication.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText(null, artShareModel.materialContent));
    }

    @Override
    protected void onDownloadSuccess() {
        sharePics.remove(0);

        int completeCount = picCount - sharePics.size();
        Message msg = Message.obtain();
        DownloadProgress downloadProgress = new DownloadProgress();
        float i = (float) completeCount / picCount;
        downloadProgress.progress = (int) (i * 100);
        downloadProgress.text = String.format(mActivity.getResources().getString(R.string.resourcetext), completeCount + "/" + picCount);
        msg.obj = downloadProgress;
        handler.sendMessage(msg);

        if (sharePics.size() > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ImageLoaderUtils.downloadPic(mActivity, sharePics.get(0), callback);
        }
    }
}
