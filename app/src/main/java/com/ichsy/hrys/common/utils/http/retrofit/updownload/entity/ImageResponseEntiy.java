package com.ichsy.hrys.common.utils.http.retrofit.updownload.entity;

/**
 * 功能：上传图片返回的实体
 * ＊创建者：赵然 on 16/6/3 16:54
 * ＊
 */
public class ImageResponseEntiy {
    /**
     * "status": 1,
     "error": null,
     "fileUrl": "http://ali-cfile.ichsy.com/cfiles/staticfiles/zoofile/2735b/s-540-540/18a6ecabfc064c9d9af4be97710e476e.jpg",
     "thumbnailUrl": null,
     "resultCode": 1,
     "resultMessage": null,
     "resultObject": "http://ali-cfile.ichsy.com/cfiles/staticfiles/zoofile/2735b/s-540-540/18a6ecabfc064c9d9af4be97710e476e.jpg",
     "resultList": null
     */
    private String fileUrl;
    private int status;
    private String error;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
//
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
