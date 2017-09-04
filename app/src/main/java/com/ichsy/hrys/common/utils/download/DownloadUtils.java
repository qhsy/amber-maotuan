package com.ichsy.hrys.common.utils.download;

import android.content.Context;

import com.ichsy.hrys.common.utils.http.retrofit.updownload.UploadListener;


/**
 * 功能：下载功能的工具类
 * ＊创建者：赵然 on 16/4/27 11:16
 * ＊
 */
public class DownloadUtils {
    /**
     * 开启下载
     * @param context
     * @param downloadUrl  下载链接
     * @param uploadListener 下载监听
     */
    public static void  startDownLoad(Context context, String downloadUrl, final UploadListener uploadListener){
        DownLoadManager manager = DownLoadManager.getInstance().init(context);
        DownLoadTask task = new DownLoadTask();
        task.setId(String.valueOf(String.valueOf(System.currentTimeMillis())));
        task.setUrl(downloadUrl);
        task.setDlSavePath(FileConfig.getRootDir(context));

        manager.addDLTask(task, new DownLoadListener() {
            @Override
            public void onDownLoadStart(DownLoadTask task) {
                if (uploadListener != null){
                    uploadListener.onStart();
                }

            }

            @Override
            public void onDownLoadUpdate(DownLoadTask task) {
                if (uploadListener != null){
                    uploadListener.onProgress(task.getDlFinishSize(),task.getDlTotalSize(),""+task.getDlSpeed());
                }
            }

            @Override
            public void onDownLoadPause(DownLoadTask task) {

            }

            @Override
            public void onDownLoadResume(DownLoadTask task) {

            }

            @Override
            public void onDownLoadSuccess(DownLoadTask task) {
                if (uploadListener != null){
                    uploadListener.onSuccess(task.getDlSavePath());
                }
            }

            @Override
            public void onDownLoadCancel(DownLoadTask task) {

            }

            @Override
            public void onDownLoadFaile(DownLoadTask task) {
                if (uploadListener != null){
                    uploadListener.onFailure(null);
                }
            }

            @Override
            public void onDownLoadRetry(DownLoadTask task) {

            }
        });
    }

}
