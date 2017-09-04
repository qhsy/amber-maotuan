package com.ichsy.hrys.common.utils.download;

/**
 * 功能：下载功能 数据库操作完成的回调
 * ＊创建者：赵然 on 16/4/27 14:06
 * ＊
 */
public interface OnDBOperationFinishedCallback {
    void onDBOperationFinishedCallback(DownLoadTask task);
    void onDBOperationonFailure(String errorDetail);
}
