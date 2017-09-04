package com.ichsy.hrys.entity.share;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.ichsy.hrys.common.utils.imageloadutils.ImageDownloadCallback;
import com.ichsy.hrys.common.view.DownloadResouceDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import zz.mk.utilslibrary.FileUtil;

/**
 * author: ihesen on 2016/11/7 15:48
 * email: hesen@ichsy.com
 */

public abstract class BaseDownloadPlatForm<T> extends BaseSharePlatform {

    protected Activity mActivity;
    protected IDownloadCallback downloadCallback;
    protected DownloadResouceDialog dialog;
    protected Handler handler;
    /** 保存文件的文件夹目录*/
    private String saveFileDirector = "";

    /** 设置分享信息数据实体*/
    protected abstract void setContentData(T data);
    /** 资源下载完成回调（单张）*/
    protected abstract void onDownloadSuccess();

    private boolean downloading = true;

    public BaseDownloadPlatForm(Activity activity) {
        this.mActivity = activity;
        saveFileDirector = Environment.getExternalStorageDirectory().getPath() + File.separator + "DCIM" + File.separator + "Camera" + File.separator;
    }

    @Override
    public void setShareContent(Object o) {
        setContentData((T)o);
    }

    ImageDownloadCallback callback = new ImageDownloadCallback() {

        @Override
        public void onException(Exception e) {

        }

        @Override
        public void onSuccess(File file) {
            if(downloading){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
                /** 防止DICM/Camera 目录不存在*/
                File tempFile = new File(saveFileDirector);
                if(!tempFile.exists()){
                    tempFile.mkdir();
                }
                String filePath = saveFileDirector + sdf.format(new Date()) + ".jpg";
                FileUtil.copyFile(file.getPath(), filePath);
                notifGrallery(new File(filePath));
                onDownloadSuccess();
            }
        }
    };

    /** 用于处理 通知相册及时更新记录*/
    private void notifGrallery(File file){
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("_data", file.toString());
        localContentValues.put("description", "save image ---");
        localContentValues.put("mime_type", "image/jpeg");
        ContentResolver localContentResolver = mActivity.getContentResolver();
        Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        localContentResolver.insert(localUri, localContentValues);
    }

    @Override
    public void share() {
        dialog = new DownloadResouceDialog(mActivity);
        dialog.setOnClickCancelLister(new DownloadResouceDialog.OnClickCancelLister() {
            @Override
            public void onDialogCancel() {
                downloading = false;
                Glide.with(mActivity).onDestroy();
                dialog.dismiss();
            }
        });
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                DownloadProgress downloadProgress = (DownloadProgress) msg.obj;
                dialog.setProgress(downloadProgress.progress);
                if (!TextUtils.isEmpty(downloadProgress.text)) {
                    dialog.setResourceText(downloadProgress.text);
                }
                if (dialog.isShowing() && downloadProgress.progress == 100 && downloadCallback != null) {
                    dialog.dismiss();
                    downloadCallback.onSuccess();
                }
            }
        };
    }

    public void setIDownloadCallback(IDownloadCallback callback) {
        this.downloadCallback = callback;
    }

    public class DownloadProgress {
        public int progress;
        public String text;
    }
}
