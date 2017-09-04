package com.ichsy.hrys.common.utils.download;

public interface DownLoadListener {

    void onDownLoadStart(DownLoadTask task);

    void onDownLoadUpdate(DownLoadTask task);

    void onDownLoadPause(DownLoadTask task);

    void onDownLoadResume(DownLoadTask task);

    void onDownLoadSuccess(DownLoadTask task);

    void onDownLoadCancel(DownLoadTask task);

    void onDownLoadFaile(DownLoadTask task);

    void onDownLoadRetry(DownLoadTask task);

}
