package com.ichsy.hrys.common.utils.download;

/**
 * 下载进程的观察者
 */
public interface DownLoadObserver {
    void onDownloadTaskStatusChanged(DownLoadTask task);
}
