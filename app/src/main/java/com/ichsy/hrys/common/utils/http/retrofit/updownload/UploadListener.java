package com.ichsy.hrys.common.utils.http.retrofit.updownload;

/**
 * 功能：上传回调
 * ＊创建者：赵然 on 16/5/24 20:31
 * ＊
 */
public interface UploadListener {
    void onStart();
    void onProgress(long currentSize, long totalSize, String speed);
    void onSuccess(String imgUrl);
    void onFailure(Exception e);

}
