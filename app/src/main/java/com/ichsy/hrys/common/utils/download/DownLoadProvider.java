package com.ichsy.hrys.common.utils.download;

import java.util.List;


public interface DownLoadProvider {

    void saveDownLoadTask(DownLoadTask task);

    void updateDownLoadTask(DownLoadTask task);

    void deleteDownLoadTask(DownLoadTask task);

    DownLoadTask findDownloadTaskById(String id);

    DownLoadTask findDownloadTask(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);

    List<DownLoadTask> getAllDownloadTask();

    void notifyDownloadStatusChanged(DownLoadTask task);
}
